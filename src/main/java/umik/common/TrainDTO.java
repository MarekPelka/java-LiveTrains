package umik.common;

import java.sql.Date;

public class TrainDTO {
	private String status;
	private String firstLine;
	private double lon;
	private String lines;
	private Date time;
	private double lat;
	private boolean lowFloor;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
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
}
