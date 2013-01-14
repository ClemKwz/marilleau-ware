package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tj_games_users database table.
 * 
 */
@Entity
@Table(name="tj_games_users")
public class TjGamesUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TjGamesUserPK id;

	private int score;

	//bi-directional many-to-one association to Game
	@ManyToOne
	@JoinColumn(name="idGame", insertable = false, updatable = false)
	private Game game;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser", insertable = false, updatable = false)
	private User user;

	public TjGamesUser() {
	}

	public TjGamesUserPK getId() {
		return this.id;
	}

	public void setId(TjGamesUserPK id) {
		this.id = id;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}