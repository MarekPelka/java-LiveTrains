package umik.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="timetable")
public class Timetable implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timetable_id", nullable = false)
	private int id;
	@Column(name = "stop_id", nullable = false, columnDefinition = "int(11)")
	private int stopId;
	@Column(name = "line_no", nullable = false, columnDefinition = "int(11)")
	private int lineId;
	@Column(name = "brigade", nullable = false, columnDefinition = "varchar(30)")
	private String brigade;
	@Column(name = "direction", nullable = false, columnDefinition = "varchar(30)")
	private String direction;
	@Column(name = "route", nullable = false, columnDefinition = "varchar(30)")
	private String route;
	@Column(name = "time", nullable = false, columnDefinition = "varchar(30)")
	private String time;

	public Timetable() {
	}

	public Timetable(int id, int stopId, int lineId, String brigade, String direction, String route, String time) {
		this.id = id;
		this.stopId = stopId;
		this.lineId = lineId;
		this.brigade = brigade;
		this.direction = direction;
		this.route = route;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStopId() {
		return stopId;
	}

	public void setStopId(int stopId) {
		this.stopId = stopId;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getBrigade() {
		return brigade;
	}

	public void setBrigade(String brigade) {
		this.brigade = brigade;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
