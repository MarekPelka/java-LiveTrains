package umik.app.dao;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import umik.app.model.Delay;

@Repository
public class DelayDAO {

	@Autowired
	private SessionFactory _sessionFactory;
	
	public Delay getDelay(int timetable) {
		StatelessSession session = _sessionFactory.openStatelessSession();
		Delay foo = (Delay) session.get(Delay.class, timetable);
        session.close();
        return foo;
	}
}
