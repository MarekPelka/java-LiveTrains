package umik.persistance;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stop")
public class Stop implements Serializable{

	public static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stop_id", nullable = false)
	public int stopId;
	@Column(name = "name", nullable = false, columnDefinition = "varchar(30)")
	public String name;
	@Column(name = "street_id", nullable = false, columnDefinition = "int(11)")
	public int streetId;
	@Column(name = "lat", nullable = false, columnDefinition = "double")
	public double lat;
	@Column(name = "lon", nullable = false, columnDefinition = "double")
	public double lon;
	@Column(name = "direction", nullable = false, columnDefinition = "varchar(30)")
	public String direction;
	@Column(name = "validity", nullable = false, columnDefinition = "date")
	public Date validity;
	
	public Stop() {}
	public Stop(int stopId, String name, int streetId, double lat, double lon, String direction, Date validity) {
		this.stopId = stopId;
		this.name = name;
		this.streetId = streetId;
		this.lat = lat;
		this.lon = lon;
		this.direction = direction;
		this.validity = validity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getStopId() {
		return stopId;
	}

	public String getName() {
		return name;
	}

	public int getStreetId() {
		return streetId;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public String getDirection() {
		return direction;
	}

	public Date getValidity() {
		return validity;
	}

	public void setStopId(int stopId) {
		this.stopId = stopId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStreetId(int streetId) {
		this.streetId = streetId;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}
}
