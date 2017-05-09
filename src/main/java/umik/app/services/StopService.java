package umik.app.services;

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
    
    @Transactional(readOnly = true)
    public void saveTimetable(List<Timetable> list) {
        timetableDAO.saveList(list);
    }
}
