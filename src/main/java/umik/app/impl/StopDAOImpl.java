package umik.app.impl;

import java.util.List;

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

	@Override
	public List<Integer> getStopIds() {
		StatelessSession session = _sessionFactory.openStatelessSession();
		@SuppressWarnings("unchecked")
		List<Integer> out = session.createQuery("SELECT DISTINCT(S.stopId) FROM Stop S").list();
		session.close();
		return out;
	}
}
