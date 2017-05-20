package umik.app.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import umik.app.model.Line;
import umik.app.model.Timetable;
import umik.app.model.Train;
import umik.app.services.ApiService;
import umik.app.services.ApiSingleton;
import umik.app.services.StopService;

@RestController
@ComponentScan("umik")
public class DataBaseController {

	static Logger log = Logger.getLogger(DataBaseController.class.getName());
	
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/updateTimetable/line/{lineId}/stop/{stopId}")
	public boolean updateLine(@PathVariable String lineId, @PathVariable String stopId) {

		try {
			return stopService.updateLine(new Line(Integer.parseInt(stopId), stopId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/truncateTimetable")
	public String truncateTimetable() {

		if(stopService.truncateTimetable())
			return "Done!";
		else
			return "Failed!";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/history")
	public String history() {

		if(ApiSingleton.getInstance().isSaveHistory())
			return "History is being saved to database!";
		else
			return "History is NOT being saved!";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/history/t")
	public String historyToggle() {

		ApiSingleton.getInstance().setSaveHistory(!ApiSingleton.getInstance().isSaveHistory());
		if(ApiSingleton.getInstance().isSaveHistory())
			return "NOW history is being saved to database!";
		else
			return "NOW history is NOT being saved!";
	}
}
