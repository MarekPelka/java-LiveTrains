package umik.persistance;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

public class StopDAO implements StopDAOInterface<Stop, Integer>{

	private Session currentSession;
	
	private Transaction currentTransaction;

	public StopDAO() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Stop.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(Stop entity) {
		getCurrentSession().save(entity);
	}

	public void update(Stop entity) {
		getCurrentSession().update(entity);
	}

	public Stop findById(Integer id) {
		Stop Stop = (Stop) getCurrentSession().get(Stop.class, id);
		return Stop; 
	}

	public void delete(Stop entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Stop> findAll() {
		List<Stop> Stops = (List<Stop>) getCurrentSession().createQuery("from Stop").list();
		return Stops;
	}

	public void deleteAll() {
		List<Stop> entityList = findAll();
		for (Stop entity : entityList) {
			delete(entity);
		}
	}
}
