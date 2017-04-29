package umik.common;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import umik.persistance.Stop;
import umik.persistance.StopService;

@RestController
public class StopController {
	@Autowired
	ItemReader<TrainDTO> restTrainReader;

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
	public TrainDTO test() {
		
		try {
			return restTrainReader.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
