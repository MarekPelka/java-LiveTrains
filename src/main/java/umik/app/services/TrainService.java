package umik.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umik.app.dao.TrainDAO;
import umik.app.dao.TrainHistoryDAO;
import umik.app.model.Train;
import umik.app.model.TrainHistory;

@Service
public class TrainService {

	@Autowired
	private TrainDAO trainDAO;

	@Autowired
	private TrainHistoryDAO trainHistoryDAO;

	@Transactional
	public void saveApiInTwoTables(List<Train> list) {

		//trainDAO.truncate();
		//trainDAO.saveList(list);
		trainHistoryDAO.saveListHistory(cast(list));
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