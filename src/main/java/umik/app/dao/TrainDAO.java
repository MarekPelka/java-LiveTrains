package umik.app.dao;

import java.util.List;

import umik.app.model.Train;

public interface TrainDAO {
	
	public void saveList(List<Train> list);
	public void truncate();
	public Train findByLineNoBrigade(String lineNo, String brigade);
}
