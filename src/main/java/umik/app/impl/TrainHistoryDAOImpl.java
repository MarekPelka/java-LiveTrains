package umik.app.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import umik.app.dao.TrainHistoryDAO;
import umik.app.model.TrainHistory;

@Repository
public class TrainHistoryDAOImpl implements TrainHistoryDAO{

	static Logger log = Logger.getLogger(TrainHistoryDAOImpl.class.getName());
	
	@Autowired
	private SessionFactory _sessionFactory;

	@Override
	public void saveListHistory(List<TrainHistory> list) {
		StatelessSession session = _sessionFactory.openStatelessSession();
		Transaction tx = session.beginTransaction();
		for(TrainHistory train : list) {
		    System.out.println("Timestamp: " + train.getTime() +
		    		"; Status: " + train.getStatus() +
		    		"; LowFlor: : " + train.isLowFloor() +
		    		"; Lat: " + train.getLat() +
		    		"; Lon: " + train.getLon() +
		    		"; Line: " + train.getLines() +
		    		"; FirstLine: " + train.getFirstLine() +
		    		"; Brigade: " + train.getBrigade()
		    );
			session.insert(train);
		}
		tx.commit();
		session.close();
	}
}
