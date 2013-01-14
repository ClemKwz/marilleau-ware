package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the games_desc database table.
 * 
 */
@Entity
@Table(name="games_desc")
public class GamesDesc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idGame_desc;

	private String description;

	private String name;

	//bi-directional many-to-one association to Game
	@OneToMany(mappedBy="gamesDesc")
	private List<Game> games;

	public GamesDesc() {
	}

	public int getIdGame_desc() {
		return this.idGame_desc;
	}

	public void setIdGame_desc(int idGame_desc) {
		this.idGame_desc = idGame_desc;
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

	public List<Game> getGames() {
		return this.games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

}