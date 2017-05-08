package umik.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import umik.app.dto.TimetableDTO;
import umik.app.dto.TrainDTO;
import umik.app.model.ApiResultTimetableDTO;
import umik.app.model.ApiResultTrainDTO;
import umik.app.model.Timetable;
import umik.app.model.Train;

@Service
public class ApiService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.to.database.job.url}")
	private String TRAIN_URL;

	@Value("${api.to.database.job.resource.id}")
	private String TRAIN_RESOURCE_ID;

	@Value("${api.to.database.job.url.connector}")
	private String TRAIN_CONNECTOR;

	@Value("${api.to.database.job.api.key}")
	private String APIKEY;
	
	@Value("${stop.to.database.job.url}")
	private String STOP_URL;

	@Value("${stop.to.database.job.resource.id}")
	private String STOP_RESOURCE_ID;

	@Value("${stop.to.database.job.url.stop.connector}")
	private String STOP_CONNECTOR;
	
	@Value("${stop.to.database.job.url.stop.number.connector}")
	private String STOP_NUMBER_CONNECTOR;

	@Value("${stop.to.database.job.url.stop.number}")
	private String STOP_NUMBER;

	@Value("${stop.to.database.job.url.line.connector}")
	private String STOP_LINE_CONNECTOR;
	
	@Value("${stop.to.database.job.url.api.key.connector}")
	private String STOP_APIKEY_CONNECTOR;
	

	public List<Train> pullTrainDataFromApi() {
		//TODO throw
		String api = TRAIN_URL + TRAIN_RESOURCE_ID + TRAIN_CONNECTOR + APIKEY;
		ApiResultTrainDTO resultSet = restTemplate.getForObject(api, ApiResultTrainDTO.class);
		List<Train> out = new ArrayList<Train>();
		for(TrainDTO t : resultSet.getResult()) {
			out.add(cast(t));
		}
		return out;
	}
	
	public ApiResultTimetableDTO pullTimetableDataFromApi(int stopId, int line) {
		//TODO throw
		List<Timetable> out = new ArrayList<Timetable>();
		String api = STOP_URL + STOP_RESOURCE_ID + STOP_CONNECTOR + stopId + STOP_NUMBER_CONNECTOR + STOP_NUMBER + STOP_LINE_CONNECTOR + line + STOP_APIKEY_CONNECTOR + APIKEY;
		ApiResultTimetableDTO resultSet = restTemplate.getForObject(api, ApiResultTimetableDTO.class);
		
		for(TimetableDTO t : resultSet.getResult()) {

		}
		return resultSet;
	}
	
	public Train cast(TrainDTO train) {
		Train out = new Train(train.getStatus(), train.getFirstLine(), train.getLon(), train.getLines(),
				train.getTime(), train.getLat(), train.isLowFloor(), train.getBrigade());
		return out;
	}
	
	public Timetable cast(TimetableDTO t) {

		return null;
	}

}
