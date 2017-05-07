package umik.app.dao;

import java.util.List;

import umik.app.model.TrainHistory;

public interface TrainHistoryDAO {
	
	public void saveListHistory(List<TrainHistory> list);
}
