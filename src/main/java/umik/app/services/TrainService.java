package umik.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umik.app.dao.TrainHistoryDAO;
import umik.app.model.Timetable;
import umik.app.model.Train;

@Service
public class TrainService {

	static Logger log = Logger.getLogger(TrainService.class.getName());

	@Autowired
	private TrainHistoryDAO trainHistoryDAO;

	@Transactional
	public void saveApiInTrainHistory(List<Train> list) {

		// trainDAO.truncate();
		// trainDAO.saveList(list);
		try {
			trainHistoryDAO.saveListHistory(list);
		} catch (ConstraintViolationException e) {
			log.warn("Duplicate entry - nothing happens");
		} catch (DataIntegrityViolationException e) {
			log.warn("Duplicate entry - nothing happens");
		}
	}

	@Transactional
	public Map<String, Double> findDistance(umik.app.model.Stop s, List<Timetable> list) {

		Map<String, Double> out = new HashMap<String, Double>();
		for (Timetable t : list) {
			Train outTrain = ApiSingleton.getInstance().getCurrentTrains().stream().filter(
					(tr) -> tr.getBrigade().equals(t.getBrigade()) && Integer.parseInt(tr.getLines()) == t.getLineId())
					.findFirst().orElse(new Train());
			
			Double outDouble = Math.sqrt(Math.pow(69.1 * (s.getLat() - outTrain.getLat()), 2)
					+ Math.pow(69.1 * (outTrain.getLon() - s.getLon()) * Math.cos(s.getLat() / 57.3), 2));
			if(outTrain.getLines() != null)
				out.put(outTrain.getLines(), outDouble);
		}
		return out;
	}

	public List<Train> findLinePosition(String lineId) {
		return ApiSingleton.getInstance().getCurrentTrains().stream().filter((t) -> t.getLines().equals(lineId)).collect(Collectors.toList());
	}
}