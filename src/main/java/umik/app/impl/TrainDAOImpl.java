package umik.app.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import umik.app.dao.TrainDAO;
import umik.app.model.Train;

@Repository
public class TrainDAOImpl implements TrainDAO{

	@Autowired
	private SessionFactory _sessionFactory;
	
	@Override
	public void saveList(List<Train> list) {
		StatelessSession session = _sessionFactory.openStatelessSession();
		Transaction tx = session.beginTransaction();
		for(Train train : list) {
		    session.insert(train);
		}
		tx.commit();
		session.close();	
	}

	@Override
	public void truncate() {
		
		StatelessSession session = _sessionFactory.openStatelessSession();
		Transaction tx = session.beginTransaction();
		session.createNativeQuery("truncate table train").executeUpdate();
		tx.commit();
		session.close();
	}

	@Override
	public Train findByLineNoBrigade(String lineNo, String brigade) {
		// TODO Auto-generated method stub
		return null;
	}

}
