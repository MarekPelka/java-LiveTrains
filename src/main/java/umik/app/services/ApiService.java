package umik.app.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import umik.app.controllers.StopController;
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
	
	static Logger log = Logger.getLogger(ApiService.class.getName());

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
	
	public List<Timetable> pullTimetableDataFromApi(int stopId, int line) {
		//TODO throw
		List<Timetable> out = new ArrayList<Timetable>();
		String stop, stopN;
		int stopNumber = stopId % 100;
		//stopId /= 100;
		if(stopId > 1000){
			stop = "" + stopId / 100;
		}
		else
			stop = "R-0" + stopId / 100;
		
		if(stopNumber < 10)
			stopN = "0" + stopNumber;
		else
			stopN = "" + stopNumber;
		String api = STOP_URL + STOP_RESOURCE_ID + STOP_CONNECTOR + stop + STOP_NUMBER_CONNECTOR + stopN + STOP_LINE_CONNECTOR + line + STOP_APIKEY_CONNECTOR + APIKEY;
		log.info(api);
		ApiResultTimetableDTO resultSet = restTemplate.getForObject(api, ApiResultTimetableDTO.class);
		
		for(TimetableDTO t : resultSet.getResult()) {
			Timetable toList = cast(t);
			toList.setStopId(stopId);
			toList.setLineId(line);
			out.add(toList);
		}
		return out;
	}
	
	public Train cast(TrainDTO train) {
		Train out = new Train(train.getStatus(), train.getFirstLine(), train.getLon(), train.getLines(),
				train.getTime(), train.getLat(), train.isLowFloor(), train.getBrigade());
		return out;
	}
	
	public Timetable cast(TimetableDTO t) {
		
		Timetable out = new Timetable();
		out.setBrigade(t.getValueDTOs().stream().filter((e) -> e.getKey().equals("brygada")).findFirst().get().getValue());
		String dir = t.getValueDTOs().stream().filter((e) -> e.getKey().equals("kierunek")).findFirst().get().getValue();
		out.setDirection(dir.replace("ś", "s").replace("ć", "c").replace("ł", "l").replace("ń", "n").replace("Ż", "Z").replace("ę", "e"));
		out.setRoute(t.getValueDTOs().stream().filter((e) -> e.getKey().equals("trasa")).findFirst().get().getValue());
		out.setTime(t.getValueDTOs().stream().filter((e) -> e.getKey().equals("czas")).findFirst().get().getValue());
		return out;
	}

}
