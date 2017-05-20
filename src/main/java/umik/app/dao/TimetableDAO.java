package umik.app.dao;

import java.util.List;

import umik.app.model.Line;
import umik.app.model.Timetable;

public interface TimetableDAO {

	public void saveList(List<Timetable> list);
	public List<Timetable> getTimetable(Line line);
	public int getMaxLine();
	public void truncate();
	public void updateLine(Line line, List<Timetable> list);
}
