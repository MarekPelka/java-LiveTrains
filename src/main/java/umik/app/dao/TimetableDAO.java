package umik.app.dao;

import java.util.List;

import umik.app.model.Line;
import umik.app.model.Timetable;

public interface TimetableDAO {

	public void saveList(List<Timetable> list);
	public List<Timetable> getTimetable(Line line);
}
