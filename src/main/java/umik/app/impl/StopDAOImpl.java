package umik.app.impl;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import umik.app.dao.StopDAO;
import umik.app.model.Stop;

@Repository
public class StopDAOImpl implements StopDAO {

	@Autowired
	private SessionFactory _sessionFactory;

	@Override
	public Stop findById(int id) {
        StatelessSession session = _sessionFactory.openStatelessSession();
        Stop foo = (Stop) session.get(Stop.class, id);
        session.close();
        return foo;
	}
}
