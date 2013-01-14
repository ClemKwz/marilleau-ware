package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tj_games_users database table.
 * 
 */
@Embeddable
public class TjGamesUserPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idGame;

	private int idUser;

	public TjGamesUserPK() {
	}
	public int getIdGame() {
		return this.idGame;
	}
	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}
	public int getIdUser() {
		return this.idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TjGamesUserPK)) {
			return false;
		}
		TjGamesUserPK castOther = (TjGamesUserPK)other;
		return 
			(this.idGame == castOther.idGame)
			&& (this.idUser == castOther.idUser);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGame;
		hash = hash * prime + this.idUser;
		
		return hash;
	}
}