package umik.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import umik.persistance.Stop;
import umik.persistance.StopService;

@RestController
public class StopController {

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
	public String test() {
		return "Testing!";
	}
}
