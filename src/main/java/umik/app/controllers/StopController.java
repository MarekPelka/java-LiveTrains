package umik.app.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import umik.app.model.Train;
import umik.app.services.ApiService;
import umik.app.services.StopService;

@RestController
@ComponentScan("umik")
@RequestMapping("/stop")
public class StopController {

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
		try {
			s = stopService.findStopLines(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	//TODO Consider - too many injections
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/lines/info")
	public List<Line> stopLinesInfo(@PathVariable String id) {

		List<Line> s = null;
		if (id == null)
			id = "100";
		try {
			s = stopService.findStopLines(Integer.parseInt(id));
			//TODO For s find schedule and one closest train;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	@RequestMapping("/runningTrains")
	public List<Train> runningTrains() {

		List<Train> out = null;
		List<Train> temp = null;
		if (lastcheck == null || lastcheck.getTime() + 10000 < Calendar.getInstance().getTimeInMillis()) {
			lastcheck = Calendar.getInstance().getTime();
			temp = apiService.pullTrainDataFromApi();
			if(temp.size() != 0)
				api = temp; 
			System.out.println("Request to API WARSZAWA");
		}

		if (out == null)
			out = api;
		return out;
	}
	
	@RequestMapping("/test")
	public ApiResultTimetableDTO test() {

		return apiService.pullTimetableDataFromApi(7009, 523);
	}
	
	@RequestMapping("/test1")
	public List<Train> test1() {

		return apiService.pullTrainDataFromApi();
	}
}
