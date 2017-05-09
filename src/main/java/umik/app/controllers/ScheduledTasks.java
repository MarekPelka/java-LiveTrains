package umik.app.controllers;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import umik.app.model.Train;
import umik.app.services.ApiService;
import umik.app.services.TrainService;

@Component
public class ScheduledTasks {
	
	static Logger log = Logger.getLogger(ScheduledTasks.class.getName());
	
	@Autowired
	private ApiService apiService;

	@Autowired
	private TrainService trainService;
	
	//@Scheduled(cron = "${api.to.database.job.cron}")
	public void saveApiToDatabase() {
		log.info("Pull started");
		long startTime = Calendar.getInstance().getTimeInMillis();
		List<Train> listTrain = apiService.pullTrainDataFromApi();
		long pullTime = Calendar.getInstance().getTimeInMillis();
		trainService.saveApiInTwoTables(listTrain);
		long pushTime = Calendar.getInstance().getTimeInMillis();
		log.info("Pulling running trains time: " + (pullTime - startTime) + "ms; Push time: " + (pushTime - pullTime) + "ms");
	}
	
//	@Scheduled(cron = "${api.to.database.job.cron}")
//	public void saveApiToDatabase() {
//		
//		List<Train> listTrain = apiService.pullDataFromApi();
//		trainService.saveApiInTwoTables(listTrain);
//		System.out.println(listTrain.get(0));
//	}
}
