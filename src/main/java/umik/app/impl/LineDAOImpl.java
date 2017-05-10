package umik.app.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import umik.app.dao.LineDAO;
import umik.app.model.Line;

@Repository
public class LineDAOImpl implements LineDAO{

	@Autowired
	private SessionFactory _sessionFactory;

	@Override
	public List<Line> findStopLine(int stopId) {
        StatelessSession session = _sessionFactory.openStatelessSession();
        @SuppressWarnings("unchecked")
		List<Line> foo = session.createQuery(
                "from Line where stopId = :stopId")
                .setParameter("stopId", stopId).list();
        session.close();
        return foo;
	}

}
