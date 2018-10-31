/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import es.uam.eps.tweetextractorfx.util.DateAdapter;
import es.uam.eps.tweetextractorfx.util.XMLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * @author Jose Antonio García del Saz
 *
 */
@Entity
@Table(name = "perm_user")
@XmlType(propOrder={"id","idDB","nickname", "password", "creationDate","lastConnectionDate","credentialList","extractionIDList"})
public class User {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "identifier")
	private int idDB;
	@Column(name = "nickname", length=50, unique=true, nullable=false)
	private String nickname;
	@Column(name = "password", length=256, nullable=false)
	private String password;
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@Column(name = "last_connection_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastConnectionDate=null;
	@Transient
    private List<String> extractionIDList;
	@OneToMany(cascade = CascadeType.PERSIST,orphanRemoval = true,mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Credentials> credentialList;
	@XmlTransient
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Extraction> extractionList;
	@Column(name = "id",length=36,unique=true, nullable=false)
	private String id;
	/**
	 * 
	 */
	public User(String nickname,String password) {
			this.setNickname(nickname);
			this.setPassword(password);
			creationDate=new Date();
			extractionList= FXCollections.observableArrayList(); 
			credentialList = new ArrayList<Credentials>();
			extractionIDList= new ArrayList<String>();
			id= UUID.randomUUID().toString();
	}
	public User() {
		extractionList= FXCollections.observableArrayList(); 
		credentialList = new ArrayList<Credentials>();
		extractionIDList= new ArrayList<String>();
	}

	/**
	 * @return the idDB
	 */
	public int getIdDB() {
		return idDB;
	}
	/**
	 * @param idDB the idDB to set
	 */
	public void setIdDB(int idDB) {
		this.idDB = idDB;
	}
	/**
	 * @return the extractionIDList
	 */
	@XmlElementWrapper(name = "extractionIDList")
    @XmlElement(name = "extractionID")
	public List<String> getExtractionIDList() {
		return extractionIDList;
	}
	/**
	 * @param extractionIDList the extractionIDList to set
	 */
	public void setExtractionIDList(List<String> extractionIDList) {
		this.extractionIDList = extractionIDList;
	}

	/**
	 * @param credentialList the credentialList to set
	 */
	public void setCredentialList(List<Credentials> credentialList) {
		this.credentialList = credentialList;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the creationDate
	 */
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the lastConnectionDate
	 */
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getLastConnectionDate() {
		return lastConnectionDate;
	}
	/**
	 * @param lastConnectionDate the lastConnectionDate to set
	 */
	public void setLastConnectionDate(Date lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the credentials
	 */
	@XmlElementWrapper(name = "credentialList")
	@XmlElement(name="credential")
	public List<Credentials> getCredentialList() {
		return credentialList;
	}
	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(List<Credentials> credentials) {
		this.credentialList = credentials;
	}
	/**
	 * @param credentials the credentials to add
	 */
	public void addCredentials(Credentials credentials) {
		if(credentials!=null) {
			credentials.setUser(this);
			this.credentialList.add(credentials);
		}
		
	}
	/**
	 * @return the extractionList
	 */
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy="user")
	public List<Extraction> getExtractionList() {
		return extractionList;
	}
	/**
	 * @param extractionList the extractionList to set
	 */
	public void setExtractionList(ObservableList<Extraction> extractionList) {
		this.extractionList = extractionList;
	}

	/**
	 * @param extraction the extraction to add to the list
	 */
	public void addExtractionToList(Extraction extraction) {
		if (extraction!=null) {
			extraction.setUser(this);
			this.extractionIDList.add(extraction.getId());
			this.getExtractionList().add(extraction);
		}
	}
	public void removeExtractionFromList(Extraction extraction) {
		if (extraction!=null) {
			this.extractionIDList.remove(extraction.getId());
			this.extractionList.remove(extraction);
		}
	}

	public boolean hasCredentials(Credentials credentials) {
		if(credentials==null)return false;
		for(Credentials own : credentialList) {
			if(credentials.equals(own))return true;
		}
		return false;
	}
	public boolean hasCredentials(String accountScreenName) {
		if(accountScreenName==null)return false;
		for(Credentials own : credentialList) {
			if(accountScreenName.equals(own.getAccountScreenName()))return true;
		}
		return false;
	}
	public boolean hasAnyCredentials() {
		return (credentialList!=null&&credentialList.size()>0);
	}
	public Credentials getCredentials(String accountscreenName) {
		Credentials ret=null;
		for(Credentials credentials: credentialList) {
			if(accountscreenName.equals(credentials.getAccountScreenName())) {
				ret=credentials;
			}
		}
		return ret;
	}
	public Extraction getExtraction(String id) {
		Extraction ret=null;
		for(Extraction extraction: extractionList) {
			if(id.equals(extraction.getId())) {
				ret=extraction;
			}
		}
		return ret;
	}
	public void loadXmlData() {
		for(String id: extractionIDList) {
			this.extractionList.add(XMLManager.loadExtraction(id));
		}
	}
	
}
