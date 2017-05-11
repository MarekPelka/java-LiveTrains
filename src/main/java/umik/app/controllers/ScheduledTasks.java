package umik.app.controllers;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

	//@Scheduled(cron = "${api.to.database.job.cron}")
	public void saveApiToDatabase() {

		log.info("Pull started");
		Date startTime = Calendar.getInstance().getTime();
		ApiSingleton.getInstance().runningTrains(apiService);
		long pullTime = Calendar.getInstance().getTimeInMillis();
		trainService.saveApiInTrainHistory(ApiSingleton.getInstance().getCurrentTrains());
		long pushTime = Calendar.getInstance().getTimeInMillis();
		log.info("Pulling running trains time: " + (pullTime - startTime.getTime()) + "ms; Push time: "
				+ (pushTime - pullTime) + "ms");
	}

	//@Scheduled(cron = "${stop.to.database.job.cron}")
	public void saveTimetableToDatabase() {
		stopService.updateTimetable();
	}
}
