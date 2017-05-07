package umik.app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import umik.app.model.Stop;

@Repository
@Transactional
public class StopDAO {

	@Autowired
	private SessionFactory _sessionFactory;

	public Stop findById(int fooId) {
        StatelessSession session = _sessionFactory.openStatelessSession();
        Stop foo = (Stop) session.get(Stop.class, fooId);
        session.close();
        return foo;
    }
	
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

}
