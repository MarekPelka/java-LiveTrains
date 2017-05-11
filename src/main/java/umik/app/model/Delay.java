package umik.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "delay")
public class Delay implements Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "timetable"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "timetable_id", unique = true, nullable = false)
	private int id;
	@Column(name = "arrival_time", nullable = false, columnDefinition = "varchar(30)")
	private String arrivalTime;
	@Column(name = "delay", nullable = false, columnDefinition = "varchar(30)")
	private String delay;
	
	public Delay() {}
	
	public Delay(int id, String arrivalTime, String delay) {
		super();
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.delay = delay;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
}
