/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Jose Antonio García del Saz
 *
 */
@Entity
@Table(name="perm_geolocation")
public class GeoLocation {
	@Id@GeneratedValue
	@Column(name = "identifier")
	private int idDB;
private double latitude;
private double longitude;

public GeoLocation(double latitude, double longitude) {
	super();
	this.latitude = latitude;
	this.longitude = longitude;
}
public GeoLocation() {
	super();
}
/**
 * @return the latitude
 */
public double getLatitude() {
	return latitude;
}
/**
 * @param latitude the latitude to set
 */
public void setLatitude(double latitude) {
	this.latitude = latitude;
}
/**
 * @return the longitude
 */
public double getLongitude() {
	return longitude;
}
/**
 * @param longitude the longitude to set
 */
public void setLongitude(double longitude) {
	this.longitude = longitude;
}

}
