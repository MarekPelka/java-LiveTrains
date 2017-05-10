package umik.app.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import umik.app.model.Line;
import umik.app.model.Stop;
import umik.app.model.Timetable;
import umik.app.model.Train;
import umik.app.services.ApiService;
import umik.app.services.ApiSingleton;
import umik.app.services.StopService;
import umik.app.services.TrainService;

@RestController
@ComponentScan("umik")
@RequestMapping("/stop")
public class RestfulController {

	static Logger log = Logger.getLogger(RestfulController.class.getName());
	
	@Autowired
	private ApiService apiService;

	@Autowired
	private StopService stopService;
	
	@Autowired
	private TrainService trainService;
	
	@Value("${api.to.database.job.time}")
	private int updateMillis;

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
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/line/{lineId}/timetable")
	public List<Timetable> lineTimetable(@PathVariable String id, @PathVariable String lineId) {

		List<Timetable> s = null;
		if (id == null)
			id = "100";
		if (id.contains("R"))
			id = id.replace("R-", "");
		if (lineId == null)
			id = "1";
		try {
			Line line = new Line(Integer.parseInt(id), lineId);
			s = stopService.getTimetable(line);//(Integer.parseInt(id));
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

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/lines/info/distance")
	public Map<String, Double> linesDistance(@PathVariable String id) {

		runningTrains();
		List<Line> s = null;
		List<Timetable> timetableList = new ArrayList<Timetable>();
		Map<String, Double> out = new HashMap<String, Double>();
		if (id == null)
			id = "100";
		if (id.contains("R"))
			id = id.replace("R-", "");
		try {
			s = stopService.findStopLines(Integer.parseInt(id));
			timetableList = stopService.getClosestTimetable(s);
		
			Stop stop = stopInfo(id);
			out = trainService.findDistance(stop, timetableList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return out;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/updateTimetable")
	public String updateTimetable() {

		if(stopService.updateTimetable())
			return "Done!";
		else
			return "Failed!";
	}

	@RequestMapping("/runningTrains")
	public List<Train> runningTrains() {

		List<Train> apiResponse = null;
		ApiSingleton api = ApiSingleton.getInstance();
		
		if (api.getLastUpdate() == null || api.getLastUpdate().getTime() + updateMillis < Calendar.getInstance().getTimeInMillis()) {
			api.setLastUpdate(Calendar.getInstance().getTime());
			apiResponse = apiService.pullTrainDataFromApi();
			if (apiResponse.size() != 0)
				api.setCurrentTrains(apiResponse);
			log.info("Request to API WARSZAWA");
		}

		return api.getCurrentTrains();
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
