package umik.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umik.app.dao.StopDAO;
import umik.app.model.Stop;


@Service
public class StopService {

	@Autowired
	private StopDAO stopDAO;
	
    @Transactional(readOnly = true)
    public Stop findStopById(int id) {
        return stopDAO.findById(id);
    }
}
