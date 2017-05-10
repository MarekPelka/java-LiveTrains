package umik.app.services;

import java.util.Date;
import java.util.List;

import umik.app.model.Train;

public class ApiSingleton {
	private static final ApiSingleton INSTANCE = new ApiSingleton();

	private Date lastUpdate;
	private List<Train> currentTrains;
	
    private ApiSingleton() {}

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
