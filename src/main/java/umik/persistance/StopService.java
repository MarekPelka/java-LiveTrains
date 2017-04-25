package umik.persistance;

import java.util.List;

public class StopService {
	private static StopDAO stopDAO;

	public StopService() {
		stopDAO = new StopDAO();
	}

	public void persist(Stop entity) {
		stopDAO.openCurrentSessionwithTransaction();
		stopDAO.persist(entity);
		stopDAO.closeCurrentSessionwithTransaction();
	}

	public void update(Stop entity) {
		stopDAO.openCurrentSessionwithTransaction();
		stopDAO.update(entity);
		stopDAO.closeCurrentSessionwithTransaction();
	}

	public Stop findById(int id) {
		stopDAO.openCurrentSession();
		Stop Stop = stopDAO.findById(id);
		stopDAO.closeCurrentSession();
		return Stop;
	}

	public void delete(int id) {
		stopDAO.openCurrentSessionwithTransaction();
		Stop Stop = stopDAO.findById(id);
		stopDAO.delete(Stop);
		stopDAO.closeCurrentSessionwithTransaction();
	}

	public List<Stop> findAll() {
		stopDAO.openCurrentSession();
		List<Stop> Stops = stopDAO.findAll();
		stopDAO.closeCurrentSession();
		return Stops;
	}

	public void deleteAll() {
		stopDAO.openCurrentSessionwithTransaction();
		stopDAO.deleteAll();
		stopDAO.closeCurrentSessionwithTransaction();
	}

	public StopDAO StopDAO() {
		return stopDAO;
	}
}
