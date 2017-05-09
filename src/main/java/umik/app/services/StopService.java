package umik.app.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umik.app.dao.LineDAO;
import umik.app.dao.StopDAO;
import umik.app.dao.TimetableDAO;
import umik.app.model.Line;
import umik.app.model.Stop;
import umik.app.model.Timetable;

@Service
public class StopService {

	@Autowired
	private StopDAO stopDAO;

	@Autowired
	private LineDAO lineDAO;

	@Autowired
	private TimetableDAO timetableDAO;

	@Transactional(readOnly = true)
	public Stop findStopById(int id) {
		return stopDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Line> findStopLines(int id) {
		return lineDAO.findStopLine(id);
	}

	@Transactional(readOnly = true)
	public List<Integer> getStopIds() {
		return stopDAO.getStopIds();
	}

	@Transactional()
	public void saveTimetable(List<Timetable> list) {
		timetableDAO.saveList(list);
	}

	@Transactional(readOnly = true)
	public List<Timetable> getClosestTimetable(List<Line> list) {
		List<Timetable> out = new ArrayList<Timetable>();
		for (Line l : list) {
			List<Timetable> t = timetableDAO.getTimetable(l);
			Calendar now = Calendar.getInstance();
			//now.set(2017, 5, 9, 14, 0);
			// Filter hours , minutes
			// SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Timetable outToList = t.stream().filter((pos) -> Integer.parseInt(pos.getTime().split(":")[0]) >= now.get(Calendar.HOUR_OF_DAY))
					.filter((pos) -> Integer.parseInt(pos.getTime().split(":")[0]) == now.get(Calendar.HOUR_OF_DAY)
							? Integer.parseInt(pos.getTime().split(":")[1]) >= now.get(Calendar.MINUTE) : true)
					.min((f1, f2) -> Integer.compare(Integer.parseInt(f1.getTime().split(":")[0]) * 100 + Integer.parseInt(f1.getTime().split(":")[1]),
							Integer.parseInt(f2.getTime().split(":")[0]) * 100 + Integer.parseInt(f2.getTime().split(":")[1]))).orElse(new Timetable());
					//.min((f1, f2) -> Integer.compare(Integer.parseInt(f1.getTime().split(":")[1]), Integer.parseInt(f2.getTime().split(":")[1])))
					;
			
					out.add(outToList);
		}
		if(out.stream().map(o -> o.getTime()).distinct().limit(2).count() <= 1) {
			for (Line l : list) {
				List<Timetable> t = timetableDAO.getTimetable(l);
				Calendar now = Calendar.getInstance();
				now.set(Calendar.HOUR_OF_DAY, 0);
				now.set(Calendar.MINUTE, 0);
				// Filter hours , minutes
				// SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Timetable outToList = t.stream().filter((pos) -> Integer.parseInt(pos.getTime().split(":")[0]) >= now.get(Calendar.HOUR_OF_DAY))
						.filter((pos) -> Integer.parseInt(pos.getTime().split(":")[0]) == now.get(Calendar.HOUR_OF_DAY)
								? Integer.parseInt(pos.getTime().split(":")[1]) >= now.get(Calendar.MINUTE) : true)
						.min((f1, f2) -> Integer.compare(Integer.parseInt(f1.getTime().split(":")[0]) * 100 + Integer.parseInt(f1.getTime().split(":")[1]),
								Integer.parseInt(f2.getTime().split(":")[0]) * 100 + Integer.parseInt(f2.getTime().split(":")[1]))).orElse(new Timetable());
						out.add(outToList);
			}
		}
		return out;
	}
}
