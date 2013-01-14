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
	@GeneratedValue
	private int idGame;

	private int endGame;

	private int sequence;

	private int startGame;

	//bi-directional many-to-one association to GamesDesc
	@ManyToOne
	@JoinColumn(name="idGame_desc")
	private GamesDesc gamesDesc;

	//bi-directional many-to-one association to Party
	@ManyToOne
	@JoinColumn(name="idParty")
	private Party party;

	//bi-directional many-to-one association to TjGamesUser
	@OneToMany(mappedBy="game")
	private List<TjGamesUser> tjGamesUsers;

	public Game() {
	}

	public int getIdGame() {
		return this.idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	public int getEndGame() {
		return this.endGame;
	}

	public void setEndGame(int endGame) {
		this.endGame = endGame;
	}

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getStartGame() {
		return this.startGame;
	}

	public void setStartGame(int startGame) {
		this.startGame = startGame;
	}

	public GamesDesc getGamesDesc() {
		return this.gamesDesc;
	}

	public void setGamesDesc(GamesDesc gamesDesc) {
		this.gamesDesc = gamesDesc;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public List<TjGamesUser> getTjGamesUsers() {
		return this.tjGamesUsers;
	}

	public void setTjGamesUsers(List<TjGamesUser> tjGamesUsers) {
		this.tjGamesUsers = tjGamesUsers;
	}

}