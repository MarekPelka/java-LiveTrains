package umik.app.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umik.app.controllers.Application;
import umik.app.dao.TrainDAO;
import umik.app.dao.TrainHistoryDAO;
import umik.app.model.Train;
import umik.app.model.TrainHistory;

@Service
public class TrainService {

	static Logger log = Logger.getLogger(TrainService.class.getName());
	
	@Autowired
	private TrainDAO trainDAO;

	@Autowired
	private TrainHistoryDAO trainHistoryDAO;

	@Transactional
	public void saveApiInTwoTables(List<Train> list) {

		//trainDAO.truncate();
		//trainDAO.saveList(list);
		try {
			trainHistoryDAO.saveListHistory(cast(list));
		} catch(ConstraintViolationException e){
			log.warn("Duplicate entry - nothing happens");
		} catch(DataIntegrityViolationException e){
			log.warn("Duplicate entry - nothing happens");
		}
	}

	public List<TrainHistory> cast(List<Train> list) {

		List<TrainHistory> out = new ArrayList<TrainHistory>();
		for (Train train : list) {
			TrainHistory o = new TrainHistory(train.getStatus(), train.getFirstLine(), train.getLon(), train.getLines(),
					train.getTime(), train.getLat(), train.isLowFloor(), train.getBrigade());
			out.add(o);
		}
		return out;
	}
}