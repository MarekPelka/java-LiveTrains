package umik.app.dao;

import java.util.List;

import umik.app.model.Line;

public interface LineDAO {

	public List<Line> findStopLine(int stopId);
}
