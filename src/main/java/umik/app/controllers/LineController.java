package umik.app.controllers;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import umik.app.model.Stop;
import umik.app.model.Timetable;
import umik.app.model.Train;
import umik.app.services.ApiService;
import umik.app.services.ApiSingleton;
import umik.app.services.StopService;

@RestController
@ComponentScan("umik")
public class LineController {

	static Logger log = Logger.getLogger(LineController.class.getName());
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private StopService stopService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/updateTimetable")
	public String updateTimetable() {

		if(stopService.updateTimetable())
			return "Done!";
		else
			return "Failed!";
	}

	@RequestMapping("/runningTrains")
	public List<Train> runningTrains() {

		ApiSingleton api = ApiSingleton.getInstance();
		api.runningTrains(apiService);
		return api.getCurrentTrains();
	}

	@RequestMapping("/test")
	public List<Timetable> test() {

		return apiService.pullTimetableDataFromApi(100604, 18);
	}

	@RequestMapping("/test1")
	public List<Train> test1() {

		return apiService.pullTrainDataFromApi();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/line/{lineId}/stop/{stopId}")
	public Stop stopInfo(@PathVariable String lineId, @PathVariable String stopId) {

		try {
			//TODO get schedule for lineId on stopId
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
