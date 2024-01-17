package by.study.news.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String lastName;
	private String login;
	private String email;
	private String password;
	private UserStatus status;
	private Date date;
	private UserRole role;

	public User() {
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public User(String name, String lastName, String login, String email, String password, UserRole role) {
		this.name = name;
		this.lastName = lastName;
		this.login = login;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(int id, String name, String lastName, String login, String email, String password, UserRole role,
			Date date) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.login = login;
		this.email = email;
		this.password = password;
		this.role = role;
		this.date = date;
	}

	public User(String name, String lastName, String login, String email, String password) {
		this.name = name;
		this.lastName = lastName;
		this.login = login;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, email, id, lastName, login, name, password, role, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(date, other.date) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", login=" + login + ", email=" + email
				+ ", password=" + password + ", status=" + status + ", date=" + date + ", role=" + role + "]";
	}

}