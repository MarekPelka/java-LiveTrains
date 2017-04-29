package umik.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import umik.app.model.ApiResult;
import umik.app.model.Stop;
import umik.app.services.StopService;

@RestController
@ComponentScan("umik.config.root")
public class StopController {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/stop")
	public Stop stopInfo(@RequestParam(value = "id", defaultValue = "100") String id) {
		StopService stopService = new StopService();

		Stop s = null;
		try {
			s = stopService.findById(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}
	
	@RequestMapping("/test")
	public ApiResult test() {
		
		try {
			ApiResult quote = restTemplate.getForObject("https://api.um.warszawa.pl/api/action/wsstore_get/?id=c7238cfe-8b1f-4c38-bb4a-de386db7e776&apikey=382ec2fc-692c-4ef1-ad39-893065d6fad8", ApiResult.class);
	        System.out.println(quote.toString());
	        return quote;
			//return restTrainReader.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
