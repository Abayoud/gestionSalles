package beans;

import java.util.Date;

public class Occupation {
	private Date date;
	private Salle salle;
	private Crenom crenom;
	private Client client;
	
	public Occupation(Date date, Salle salle, Crenom crenom,Client client) {
		super();
		this.date = date;
		this.salle = salle;
		this.crenom = crenom;
		this.client=client;
	}
	
	
	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	public Crenom getCrenom() {
		return crenom;
	}
	public void setCrenom(Crenom crenom) {
		this.crenom = crenom;
	}
	
	

}
