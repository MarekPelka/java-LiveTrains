package umik.app.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import umik.app.model.Train;

public class ApiSingleton {

	static Logger log = Logger.getLogger(ApiSingleton.class.getName());
	
	private static final ApiSingleton INSTANCE = new ApiSingleton();

	private Date lastUpdate;
	private List<Train> currentTrains;
	
	@Value("${api.to.database.job.time}")
	private int updateMillis;
	
    private ApiSingleton() {}

    public void runningTrains(ApiService apiService) {
    	if (getLastUpdate() == null || getLastUpdate().getTime() + updateMillis < Calendar.getInstance().getTimeInMillis()) {
			setLastUpdate(Calendar.getInstance().getTime());
			List<Train> apiResponse = apiService.pullTrainDataFromApi();
			if (apiResponse.size() != 0)
				setCurrentTrains(apiResponse);
			log.info("Request to API WARSZAWA");
		}
    }
    
    public static ApiSingleton getInstance() {
        return INSTANCE;
    }
    
    public Date getLastUpdate() {
		return lastUpdate;
	}

    public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

    public List<Train> getCurrentTrains() {
		return currentTrains;
	}

    public void setCurrentTrains(List<Train> currentTrains) {
		this.currentTrains = currentTrains;
	}
}
