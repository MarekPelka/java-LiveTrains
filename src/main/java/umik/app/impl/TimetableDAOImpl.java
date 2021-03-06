package umik.app.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import umik.app.dao.TimetableDAO;
import umik.app.model.Line;
import umik.app.model.Timetable;

@Repository
public class TimetableDAOImpl implements TimetableDAO{

	@Autowired
	private SessionFactory _sessionFactory;
	
	@Override
	public void saveList(List<Timetable> list) {
		
		StatelessSession session = _sessionFactory.openStatelessSession();
		Transaction tx = session.beginTransaction();
		for(Timetable timetable : list) {
		    session.insert(timetable);
		}
		tx.commit();
		session.close();	
	}

	@Override
	public List<Timetable> getTimetable(Line line) {
		StatelessSession session = _sessionFactory.openStatelessSession();
		@SuppressWarnings("unchecked")
		List<Timetable> out = session.createQuery("FROM Timetable T WHERE T.stopId =:stopId AND T.lineId =:lineId")
				.setParameter("stopId", line.getStopId())
				.setParameter("lineId", Integer.parseInt(line.getLines()))
				.list();
		session.close();
		return out;
	}

	@Override
	public int getMaxLine() {
		StatelessSession session = _sessionFactory.openStatelessSession();
		
		@SuppressWarnings("rawtypes")
		List out = session.createQuery("SELECT MAX(TT.stopId) from Timetable TT")
				.list();
		return (int) out.get(0);
	}
	
	@Override
 	public void truncate() {
 		
 		StatelessSession session = _sessionFactory.openStatelessSession();
 		Transaction tx = session.beginTransaction();
 		session.createNativeQuery("truncate table timetable").executeUpdate();
 		tx.commit();
 		session.close();
 	}

	@Override
	public void updateLine(Line line, List<Timetable> list) {
		
		StatelessSession session = _sessionFactory.openStatelessSession();
		Transaction tx = session.beginTransaction();
		session.createNativeQuery("DELETE FROM Timetable T WHERE T.stopId =:stopId AND T.lineId =:lineId")
				.setParameter("stopId", line.getStopId())
				.setParameter("lineId", Integer.parseInt(line.getLines()));
 		tx.commit();
 		session.close();
		saveList(list);
	}

}
