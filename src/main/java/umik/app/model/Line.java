package umik.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="line")
public class Line implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "stop_id", nullable = false)
	public int stopId;
	@Id
	@Column(name = "line", nullable = false, columnDefinition = "varchar(30)")
	private String lines;

	public Line() {
	}

	public Line(int stopId, String lines) {

		this.stopId = stopId;
		this.lines = lines;
	}

	public int getStopId() {
		return stopId;
	}

	public void setStopId(int stopId) {
		this.stopId = stopId;
	}

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

}
