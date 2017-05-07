package umik.app.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import umik.app.model.Stop;

@RestController
@ComponentScan("umik")
@RequestMapping("/line")
public class LineController {

	@RequestMapping(method = RequestMethod.GET, value = "/{lineId}/stop/{stopId}")
	public Stop stopInfo(@PathVariable String lineId, @PathVariable String stopId) {

		try {
			//TODO get schedule for lineId on stopId
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
