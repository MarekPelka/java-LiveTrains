package umik.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import umik.app.model.Train;
import umik.app.services.ApiService;
import umik.app.services.TrainService;

@Component
public class ScheduledTasks {
	
	@Autowired
	private ApiService apiService;

	@Autowired
	private TrainService trainService;
	
	//@Scheduled(cron = "${api.to.database.job.cron}")
	public void saveApiToDatabase() {
		
		List<Train> listTrain = apiService.pullDataFromApi();
		trainService.saveApiInTwoTables(listTrain);
		System.out.println(listTrain.get(0));
	}
	
//	@Scheduled(cron = "${api.to.database.job.cron}")
//	public void saveApiToDatabase() {
//		
//		List<Train> listTrain = apiService.pullDataFromApi();
//		trainService.saveApiInTwoTables(listTrain);
//		System.out.println(listTrain.get(0));
//	}
}
