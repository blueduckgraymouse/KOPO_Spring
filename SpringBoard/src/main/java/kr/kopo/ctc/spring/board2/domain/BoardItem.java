package kr.kopo.ctc.spring.board2.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BoardItem {
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	@Column
	private Integer no;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private Date created;
	
	@Column
	private Integer view;

	public BoardItem() {
		super();
	}

	public BoardItem(Integer no, String title, String author, Date created, Integer view) {
		super();
		this.no = no;
		this.title = title;
		this.author = author;
		this.created = created;
		this.view = view;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setView(Integer view) {
		this.view = view;
	}

	public Integer getId() {
		return id;
	}

	public Integer getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Date getCreated() {
		return created;
	}

	public Integer getView() {
		return view;
	}

	@Override
	public String toString() {
		return "BoardItem [id=" + id + ", no=" + no + ", title=" + title + ", author=" + author + ", created=" + created
				+ ", view=" + view + "]";
	}
}


