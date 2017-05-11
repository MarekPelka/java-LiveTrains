package umik.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="train_history")
public class Train implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "status", nullable = false, columnDefinition = "varchar(30)")
	private String status;
	@Id
	@Column(name = "first_line", nullable = false, columnDefinition = "varchar(30)")
	private String firstLine;
	@Id
	@Column(name = "lon", nullable = false, columnDefinition = "double")
	public double lon;
	@Id
	@Column(name = "line", nullable = false, columnDefinition = "varchar(30)")
	private String lines;
	@Id
	@Column(name = "time_stamp", nullable = false, columnDefinition = "varchar(30)")
	private String time;
	@Id
	@Column(name = "lat", nullable = false, columnDefinition = "double")
	public double lat;
	@Id
	@Column(name = "low_floor", nullable = false, columnDefinition = "BIT(1)")
	private boolean lowFloor;
	@Id
	@Column(name = "brigade", nullable = false, columnDefinition = "varchar(30)")
	private String brigade;
	
	public Train() {}
	
	public Train(String status, String firstLine, double lon, String lines, String time, double lat, boolean lowFloor,
			String brigade) {
		this.status = status;
		this.firstLine = firstLine;
		this.lon = lon;
		this.lines = lines;
		this.time = time;
		this.lat = lat;
		this.lowFloor = lowFloor;
		this.brigade = brigade;
	}

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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "[" + status + " " + firstLine + " " + lon + " " + lines + " " + time + " " + lat + " " + lowFloor + " " + brigade + "]";
	}
	
}
