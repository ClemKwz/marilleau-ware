package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parties database table.
 * 
 */
@Entity
@Table(name="parties")
public class Party implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idParty;

	private String description;

	private int endParty;

	private int idCurrentGame;

	private int idUserCreate;

	private String name;

	private int startParty;

	public Party() {
	}

	public int getIdParty() {
		return this.idParty;
	}

	public void setIdParty(int idParty) {
		this.idParty = idParty;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEndParty() {
		return this.endParty;
	}

	public void setEndParty(int endParty) {
		this.endParty = endParty;
	}

	public int getIdCurrentGame() {
		return this.idCurrentGame;
	}

	public void setIdCurrentGame(int idCurrentGame) {
		this.idCurrentGame = idCurrentGame;
	}

	public int getIdUserCreate() {
		return this.idUserCreate;
	}

	public void setIdUserCreate(int idUserCreate) {
		this.idUserCreate = idUserCreate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartParty() {
		return this.startParty;
	}

	public void setStartParty(int startParty) {
		this.startParty = startParty;
	}

}