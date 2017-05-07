package umik.app.impl;

import java.util.List;

import org.hibernate.Session;
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

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(Stop Stop) {
		getSession().save(Stop);
		return;
	}

	public void delete(Stop Stop) {
		getSession().delete(Stop);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Stop> getAll() {
		return getSession().createQuery("from Stop").list();
	}

	public Stop getById(long id) {
		return (Stop) getSession().load(Stop.class, id);
	}

	public void update(Stop Stop) {
		getSession().update(Stop);
		return;
	}

	@Override
	public Stop findById(int id) {
        StatelessSession session = _sessionFactory.openStatelessSession();
        Stop foo = (Stop) session.get(Stop.class, id);
        session.close();
        return foo;
	}
}
