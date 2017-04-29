package umik.config.root;

import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import umik.common.RestTrainReader;
import umik.common.TrainDTO;

@Configuration
public class RestTrainJobConfig {

	private String apiUrl = "https://api.um.warszawa.pl/api/action/wsstore_get/?id=c7238cfe-8b1f-4c38-bb4a-de386db7e776&apikey=";
	private String apiKey = "382ec2fc-692c-4ef1-ad39-893065d6fad8";
	
	@Bean
	ItemReader<TrainDTO> restTrainReader(Environment environment, RestTemplate restTemplate) {
		return new RestTrainReader(environment.getRequiredProperty(apiUrl + apiKey), restTemplate);
	}
}
