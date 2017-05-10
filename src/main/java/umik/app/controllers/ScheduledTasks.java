package umik.app.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import umik.app.model.Train;
import umik.app.services.ApiService;
import umik.app.services.ApiSingleton;
import umik.app.services.StopService;
import umik.app.services.TrainService;

@Component
public class ScheduledTasks {

	static Logger log = Logger.getLogger(ScheduledTasks.class.getName());

	@Autowired
	private ApiService apiService;

	@Autowired
	private TrainService trainService;

	@Autowired
	private StopService stopService;

	@Value("${api.to.database.job.time}")
	private int updateMillis;

	@Scheduled(cron = "${api.to.database.job.cron}")
	public void saveApiToDatabase() {

		List<Train> apiResponse = null;
		ApiSingleton api = ApiSingleton.getInstance();
		log.info("Pull started");
		Date startTime = Calendar.getInstance().getTime();
		long pullTime = Calendar.getInstance().getTimeInMillis();
		if (api.getLastUpdate() == null
				|| api.getLastUpdate().getTime() + updateMillis < Calendar.getInstance().getTimeInMillis()) {
			api.setLastUpdate(startTime);
			apiResponse = apiService.pullTrainDataFromApi();

			pullTime = Calendar.getInstance().getTimeInMillis();
			if (apiResponse.size() != 0) {

				api.setCurrentTrains(apiResponse);
				trainService.saveApiInTrainHistory(ApiSingleton.getInstance().getCurrentTrains());
			}

			log.info("Request to API WARSZAWA");
		}
		long pushTime = Calendar.getInstance().getTimeInMillis();
		log.info("Pulling running trains time: " + (pullTime - startTime.getTime()) + "ms; Push time: "
				+ (pushTime - pullTime) + "ms");
	}

	@Scheduled(cron = "${stop.to.database.job.cron}")
	public void saveTimetableToDatabase() {
		stopService.updateTimetable();
	}
}
