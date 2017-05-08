package umik.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import umik.app.dto.TimetableDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResultTimetableDTO {

	private List<TimetableDTO> result;
	
	public ApiResultTimetableDTO() {}

	public List<TimetableDTO> getResult() {
		return result;
	}

	public void setResult(List<TimetableDTO> result) {
		this.result = result;
	}
	
	@Override
    public String toString() {
        return "Result{" +
                "numberOfTrains='" + result.size() + '\'' +
                '}';
    }
}