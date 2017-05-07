package umik.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import umik.app.dto.TrainDTO;
import umik.app.model.ApiResult;
import umik.app.model.Train;

@Service
public class ApiService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.to.database.job.url}")
	private String URL;

	@Value("${api.to.database.job.resource.id}")
	private String RESOURCE_ID;

	@Value("${api.to.database.job.url.connector}")
	private String CONNECTOR;

	@Value("${api.to.database.job.api.key}")
	private String APIKEY;

	public List<Train> pullDataFromApi() {
		//TODO throw
		String api = URL + RESOURCE_ID + CONNECTOR + APIKEY;
		ApiResult resultSet = restTemplate.getForObject(api, ApiResult.class);
		List<Train> out = new ArrayList<Train>();
		for(TrainDTO t : resultSet.getResult()) {
			out.add(cast(t));
		}
		return out;
	}
	
	public Train cast(TrainDTO train) {
		Train out = new Train(train.getStatus(), train.getFirstLine(), train.getLon(), train.getLines(),
				train.getTime(), train.getLat(), train.isLowFloor(), train.getBrigade());
		return out;
	}
	// @Autowired
	// private TrainDAO trainDAO;
	//
	// @Transactional
	// public void saveTwoTables(List<Train> list) {
	//
	// trainDAO.truncate();
	// trainDAO.saveList(list);
	// trainDAO.saveListHistory(list);
	// }
}
