package umik.app.dao;

import java.util.List;

import umik.app.model.Stop;

public interface StopDAO {
	
	public Stop findById(int id);
	public List<Integer> getStopIds();
}
