package umik.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainDAO {

	@Autowired
	private SessionFactory _sessionFactory;

}
