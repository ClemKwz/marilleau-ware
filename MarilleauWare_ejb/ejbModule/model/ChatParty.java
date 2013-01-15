package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the chat_party database table.
 * 
 */
@Entity
@Table(name="chat_party")
public class ChatParty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idMessage;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int idParty;

	private int idUser;

	private String message;

	public ChatParty() {
	}

	public int getIdMessage() {
		return this.idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdParty() {
		return this.idParty;
	}

	public void setIdParty(int idParty) {
		this.idParty = idParty;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}