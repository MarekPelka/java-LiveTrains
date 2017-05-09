package umik.app.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import umik.app.model.ApiResultTimetableDTO;
import umik.app.model.ApiResultTrainDTO;
import umik.app.model.Line;
import umik.app.model.Stop;
import umik.app.model.Timetable;
import umik.app.model.Train;
import umik.app.services.ApiService;
import umik.app.services.StopService;

@RestController
@ComponentScan("umik")
@RequestMapping("/stop")
public class StopController {

	static Logger log = Logger.getLogger(StopController.class.getName());
	
	@Autowired
	private ApiService apiService;

	@Autowired
	private StopService stopService;

	private Date lastcheck = null;
	private List<Train> api = null;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Stop stopInfo(@PathVariable String id) {

		Stop s = null;
		if (id == null)
			id = "100";
		try {
			s = stopService.findStopById(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/lines")
	public List<Line> stopLines(@PathVariable String id) {

		List<Line> s = null;
		if (id == null)
			id = "100";
		if (id.contains("R"))
			id = id.replace("R-", "");
		try {
			s = stopService.findStopLines(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	// TODO Consider - too many injections
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/lines/info")
	public List<Timetable> stopLinesInfo(@PathVariable String id) {

		List<Line> s = null;
		List<Timetable> out = new ArrayList<Timetable>();
		if (id == null)
			id = "100";
		if (id.contains("R"))
			id = id.replace("R-", "");
		try {
			s = stopService.findStopLines(Integer.parseInt(id));
			out = stopService.getClosestTimetable(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/updateTimetable")
	public String updateTimetable() {

		List<Integer> stopIds;
		List<Line> linesForCurrentStop;
		List<Timetable> timetableForStopLine;
		try {
			stopIds = stopService.getStopIds();
			for (int i : stopIds) {
				log.info(i + " ");
			}
			long startTime = Calendar.getInstance().getTimeInMillis();
			float i = 0;
			for (int id : stopIds) {
				log.info("TOTAL %: " + (float) i / stopIds.size() * 100f);
				log.info("Time passed: " + (Calendar.getInstance().getTimeInMillis() - startTime));
				long cTime = Calendar.getInstance().getTimeInMillis();
				log.warn("Estimated time: " + ((cTime - startTime) / i * stopIds.size()));
				
				linesForCurrentStop = stopService.findStopLines(id);
				for (Line l : linesForCurrentStop) {
					log.info(l.getLines() + " ");
				}
				float j = 0;
				for (Line line : linesForCurrentStop) {
					log.info("FOR STOP: " + id + "; %: " + (float) j / linesForCurrentStop.size() * 100f);
					timetableForStopLine = apiService.pullTimetableDataFromApi(id,
							Integer.parseInt(line.getLines()));
					for (Timetable t : timetableForStopLine) {
						log.info(t.getTime() + " ");
					}
					stopService.saveTimetable(timetableForStopLine);
					++j;
				}
				++i;
			}
			long endTime = Calendar.getInstance().getTimeInMillis();
			log.info("TIME TOTAL: " + (endTime - startTime) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Done!";
	}

	@RequestMapping("/runningTrains")
	public List<Train> runningTrains() {

		List<Train> out = null;
		List<Train> temp = null;
		if (lastcheck == null || lastcheck.getTime() + 10000 < Calendar.getInstance().getTimeInMillis()) {
			lastcheck = Calendar.getInstance().getTime();
			temp = apiService.pullTrainDataFromApi();
			if (temp.size() != 0)
				api = temp;
			log.info("Request to API WARSZAWA");
		}

		if (out == null)
			out = api;
		return out;
	}

	@RequestMapping("/test")
	public List<Timetable> test() {

		return apiService.pullTimetableDataFromApi(100, 10);
	}

	@RequestMapping("/test1")
	public List<Train> test1() {

		return apiService.pullTrainDataFromApi();
	}
}
