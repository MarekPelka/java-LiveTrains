package umik.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umik.app.dao.StopDAO;
import umik.app.model.Stop;

@Service
@Configurable
public class StopService {

	@Autowired
	private StopDAO stopDAO;
	
    @Transactional(readOnly = true)
    public Stop findStopById(long id) {
        return stopDAO.getById(id);
    }

//    @Transactional(readOnly = true)
//    public Stop findTodaysCaloriesForUser(String username) {
//        return userRepository.findTodaysCaloriesForUser(username);
//}
}
