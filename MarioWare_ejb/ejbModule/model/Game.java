package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the games database table.
 * 
 */
@Entity
@Table(name="games")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idGame;

	private String description;

	private String name;

	//bi-directional many-to-many association to Party
	@ManyToMany
	@JoinTable(
		name="tj_games_partie"
		, joinColumns={
			@JoinColumn(name="idGame")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idPartie")
			}
		)
	private List<Party> parties;

	public Game() {
	}

	public int getIdGame() {
		return this.idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Party> getParties() {
		return this.parties;
	}

	public void setParties(List<Party> parties) {
		this.parties = parties;
	}

}