package umik.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "value", "key" })
public class ValueDTO {

	@JsonProperty("value")
	private String value;
	@JsonProperty("key")
	private String key;
	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public ValueDTO() {
	}

	/**
	 * 
	 * @param value
	 * @param key
	 */
	public ValueDTO(String value, String key) {
		super();
		this.value = value;
		this.key = key;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}
}
