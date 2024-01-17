package by.study.news.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Date date;
	private String title;
	private String brief;
	private String content;
	private ArticleStatus status;
	private int userId;

	public Article() {
	}

	public Article(String title, String brief, String content) {

		this.title = title;
		this.brief = brief;
		this.content = content;
	}

	public Article(int id, String title, String brief, String content) {

		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
	}
	
	public Article(String title, String brief, String content, ArticleStatus status) {

		this.title = title;
		this.brief = brief;
		this.content = content;
		this.status = status;
	}
	
	public Article(String title, String brief, String content, ArticleStatus status, int userId) {

		this.title = title;
		this.brief = brief;
		this.content = content;
		this.status = status;
		this.userId = userId;

	}

	public Article(int id, String title, String brief, String content, ArticleStatus status) {

		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.status = status;
	}

	public Article(int id, String title, String brief, String content, Date date, ArticleStatus status, int userId) {

		this.date = date;
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.status = status;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArticleStatus getStatus() {
		return status;
	}

	public void setStatus(ArticleStatus status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brief, content, date, id, status, title, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		return Objects.equals(brief, other.brief) && Objects.equals(content, other.content)
				&& Objects.equals(date, other.date) && id == other.id && status == other.status
				&& Objects.equals(title, other.title) && userId == other.userId;
	}

	@Override
	public String toString() {
		return " Article [id=" + id + ", date=" + date + ", title=" + title + ", brief=" + brief + ", content=" + content
				+ ", status=" + status + ", userId=" + userId + "]" ;
	}

}