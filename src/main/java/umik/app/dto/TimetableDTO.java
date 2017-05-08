package umik.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "values" })
public class TimetableDTO {

	@JsonProperty("values")
	private List<ValueDTO> values = null;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public TimetableDTO() {
	}

	/**
	 * 
	 * @param ValueDTOs
	 */
	public TimetableDTO(List<ValueDTO> ValueDTOs) {
		super();
		this.values = ValueDTOs;
	}

	@JsonProperty("values")
	public List<ValueDTO> getValueDTOs() {
		return values;
	}

	@JsonProperty("values")
	public void setValueDTOs(List<ValueDTO> ValueDTOs) {
		this.values = ValueDTOs;
	}
}
