package umik.app.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import umik.app.dao.DelayDAO;
import umik.app.model.Delay;

@Service
public class DelayService {

	static Logger log = Logger.getLogger(DelayService.class.getName());

	@Autowired
	private DelayDAO delayDAO;

	@Transactional
	public Delay getDelay(int timetablePosition) {

		return delayDAO.getDelay(timetablePosition);
	}
}