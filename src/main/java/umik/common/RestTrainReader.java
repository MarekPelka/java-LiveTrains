package umik.common;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTrainReader implements ItemReader<TrainDTO>{

	private final String apiUrl;
    private final RestTemplate restTemplate;
    
    private int nextTrainIndex;
    private List<TrainDTO> trainData;
    
    
	public RestTrainReader(String apiUrl, RestTemplate restTemplate) {
		super();
		this.apiUrl = apiUrl;
		this.restTemplate = restTemplate;
		this.nextTrainIndex = 0;
	}


	@Override
	public TrainDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(trainDataIsNotInitialized()) {
			trainData = fetchTrainDataFromApi();
		}
		
		TrainDTO nextTrain = null;
		
		if(nextTrainIndex < trainData.size()) {
			nextTrain = trainData.get(nextTrainIndex);
			nextTrainIndex++;
		}
		
		return nextTrain;
	}
	
	private boolean trainDataIsNotInitialized() {
		return this.trainData == null;
	}

	private List<TrainDTO> fetchTrainDataFromApi() {
		ResponseEntity<TrainDTO[]> response = restTemplate.getForEntity(apiUrl, TrainDTO[].class);
		TrainDTO[] trainData = response.getBody();
		return Arrays.asList(trainData);
	}
}
