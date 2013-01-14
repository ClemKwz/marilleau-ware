package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idUser;

	private int isAdmin;

	private String password;

	private String pseudo;

	private int score;

	//bi-directional many-to-one association to TjGamesUser
	@OneToMany(mappedBy="user")
	private List<TjGamesUser> tjGamesUsers;

	//bi-directional many-to-one association to Party
	@ManyToOne
	@JoinColumn(name="idParty")
	private Party party;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<TjGamesUser> getTjGamesUsers() {
		return this.tjGamesUsers;
	}

	public void setTjGamesUsers(List<TjGamesUser> tjGamesUsers) {
		this.tjGamesUsers = tjGamesUsers;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

}