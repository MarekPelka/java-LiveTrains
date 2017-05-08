package umik.app.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import umik.app.dao.TimetableDAO;
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

}
