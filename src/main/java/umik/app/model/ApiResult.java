package umik.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import umik.app.dto.TrainDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResult {

	private List<TrainDTO> result;
	
	public ApiResult() {}

	public List<TrainDTO> getResult() {
		return result;
	}

	public void setResult(List<TrainDTO> result) {
		this.result = result;
	}
	
	@Override
    public String toString() {
        return "Result{" +
                "numberOfTrains='" + result.size() + '\'' +
                '}';
    }
}
