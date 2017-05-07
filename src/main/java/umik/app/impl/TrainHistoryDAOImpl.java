package umik.app.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import umik.app.dao.TrainHistoryDAO;
import umik.app.model.TrainHistory;

@Repository
public class TrainHistoryDAOImpl implements TrainHistoryDAO{

	@Autowired
	private SessionFactory _sessionFactory;

	@Override
	public void saveListHistory(List<TrainHistory> list) {
		StatelessSession session = _sessionFactory.openStatelessSession();
		Transaction tx = session.beginTransaction();
		for(TrainHistory train : list) {
		    session.insert(train);
		}
		tx.commit();
		session.close();
	}
}
