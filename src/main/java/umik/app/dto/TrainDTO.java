package umik.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainDTO {
	
	@JsonProperty("Status")
	private String status;
	@JsonProperty("FirstLine")
	private String firstLine;
	@JsonProperty("Lon")
	private double lon;
	@JsonProperty("Lines")
	private String lines;
	@JsonProperty("Time")
	private String time;
	@JsonProperty("Lat")
	private double lat;
	@JsonProperty("LowFloor")
	private boolean lowFloor;
	@JsonProperty("Brigade")
	private String brigade;
	
	public TrainDTO() {}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public boolean isLowFloor() {
		return lowFloor;
	}

	public void setLowFloor(boolean lowFloor) {
		this.lowFloor = lowFloor;
	}

	public String getBrigade() {
		return brigade;
	}

	public void setBrigade(String brigade) {
		this.brigade = brigade;
	}

	@Override
    public String toString() {
		return "Train{" + 
    "line=" + lines +
    "time=" + time + '\'' +
    '}';    
	}
}
