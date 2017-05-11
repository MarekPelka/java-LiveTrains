package umik.app.dao;

import java.util.List;

import umik.app.model.Train;

public interface TrainHistoryDAO {
	
	public void saveListHistory(List<Train> list);
}
