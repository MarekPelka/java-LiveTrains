package umik.app.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
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

	static Logger log = Logger.getLogger(StopService.class.getName());

	@Autowired
	private ApiService apiService;

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
	public List<Timetable> getTimetable(Line line) {
		return timetableDAO.getTimetable(line);
	}

	@Transactional(readOnly = true)
	public List<Timetable> getClosestTimetable(List<Line> list) {
		List<Timetable> out = new ArrayList<Timetable>();
		for (Line l : list) {
			List<Timetable> t = timetableDAO.getTimetable(l);
			Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Warsaw"));
			// now.set(2017, 5, 9, 14, 0);
			// Filter hours , minutes
			// SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Timetable outToList = t.stream()
					.filter((pos) -> Integer.parseInt(pos.getTime().split(":")[0]) >= now.get(Calendar.HOUR_OF_DAY))
					.filter((pos) -> Integer.parseInt(pos.getTime().split(":")[0]) == now.get(Calendar.HOUR_OF_DAY)
							? Integer.parseInt(pos.getTime().split(":")[1]) >= now.get(Calendar.MINUTE) : true)
					.min((f1, f2) -> Integer.compare(
							Integer.parseInt(f1.getTime().split(":")[0]) * 100
									+ Integer.parseInt(f1.getTime().split(":")[1]),
							Integer.parseInt(f2.getTime().split(":")[0]) * 100
									+ Integer.parseInt(f2.getTime().split(":")[1])))
					.orElse(new Timetable());
			// .min((f1, f2) ->
			// Integer.compare(Integer.parseInt(f1.getTime().split(":")[1]),
			// Integer.parseInt(f2.getTime().split(":")[1])))
			;

			out.add(outToList);
		}
		if (out.stream().map(o -> o.getTime()).distinct().limit(2).count() <= 1) {
			for (Line l : list) {
				List<Timetable> t = timetableDAO.getTimetable(l);
				Calendar now = Calendar.getInstance();
				now.set(Calendar.HOUR_OF_DAY, 0);
				now.set(Calendar.MINUTE, 0);
				// Filter hours , minutes
				// SimpleDateFormat dateFormat = new
				// SimpleDateFormat("HH:mm:ss");
				Timetable outToList = t.stream()
						.filter((pos) -> Integer.parseInt(pos.getTime().split(":")[0]) >= now.get(Calendar.HOUR_OF_DAY))
						.filter((pos) -> Integer.parseInt(pos.getTime().split(":")[0]) == now.get(Calendar.HOUR_OF_DAY)
								? Integer.parseInt(pos.getTime().split(":")[1]) >= now.get(Calendar.MINUTE) : true)
						.min((f1, f2) -> Integer.compare(
								Integer.parseInt(f1.getTime().split(":")[0]) * 100
										+ Integer.parseInt(f1.getTime().split(":")[1]),
								Integer.parseInt(f2.getTime().split(":")[0]) * 100
										+ Integer.parseInt(f2.getTime().split(":")[1])))
						.orElse(new Timetable());
				out.add(outToList);
			}
		}
		return out;
	}

	@Transactional
	public boolean updateTimetable() {
		List<Integer> stopIds;
		List<Line> linesForCurrentStop;
		List<Timetable> timetableForStopLine;
		int lastStop = 0;
		Line lastLine = null;
		try {
			stopIds = getStopIds();
			lastStop = stopIds.get(stopIds.size() - 1);
			for (int i : stopIds) {
				log.info(i + " ");
			}
			try {
				int maxLineInDatabase = timetableDAO.getMaxLine();	
				if(maxLineInDatabase != lastStop)
					stopIds = stopIds.subList(stopIds.indexOf(maxLineInDatabase), stopIds.size());
				else
					return true;
			} catch(NullPointerException e) {
				log.warn("Timetable is empty!");
			}
			
			
			
			long startTime = Calendar.getInstance().getTimeInMillis();
			float i = 0;
			for (int id : stopIds) {
				log.info("TOTAL %: " + (float) i / stopIds.size() * 100f);
				log.info("Time passed: " + (Calendar.getInstance().getTimeInMillis() - startTime));
				long cTime = Calendar.getInstance().getTimeInMillis();
				log.warn("Estimated time: " + ((cTime - startTime) / i * stopIds.size()));
				
				linesForCurrentStop = findStopLines(id);
				for (Line l : linesForCurrentStop) {
					log.info(l.getLines() + " ");
				}
				float j = 0;
				for (Line line : linesForCurrentStop) {
					log.info("FOR STOP: " + id + "; %: " + (float) j / linesForCurrentStop.size() * 100f);
					timetableForStopLine = apiService.pullTimetableDataFromApi(id,
							Integer.parseInt(line.getLines()));
					for (Timetable t : timetableForStopLine) {
						log.info(t.getTime() + " ");
					}
					saveTimetable(timetableForStopLine);
					++j;
				}
				++i;
			}
			long endTime = Calendar.getInstance().getTimeInMillis();
			log.info("TIME TOTAL: " + (endTime - startTime) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Line> test = findStopLines(lastStop);
		lastLine = test.get(test.size()-1);
		if(timetableDAO.getTimetable(lastLine).size() > 0)
			return true;
		else
			return false;
	}
	
	@Transactional
	public boolean updateLine(Line line) {
		List<Timetable> list = apiService.pullTimetableDataFromApi(line.getStopId(),
				Integer.parseInt(line.getLines()));
		timetableDAO.updateLine(line, list);
		return true;
	}
}
