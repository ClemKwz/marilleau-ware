package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the parties database table.
 * 
 */
@Entity
@Table(name="parties")
public class Party implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPartie;

	private String description;

	private String name;

	//bi-directional many-to-many association to Game
	@ManyToMany(mappedBy="parties")
	private List<Game> games;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUserCreate")
	private User user;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="party")
	private List<User> users;

	public Party() {
	}

	public int getIdPartie() {
		return this.idPartie;
	}

	public void setIdPartie(int idPartie) {
		this.idPartie = idPartie;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}