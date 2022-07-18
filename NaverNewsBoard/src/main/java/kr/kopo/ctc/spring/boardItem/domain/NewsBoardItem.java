package kr.kopo.ctc.spring.boardItem.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class NewsBoardItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String title;
	
	@Column
	private String writer;
	
	@Column
	private Date date;
	
	@Column
	private String content;

	@Column
	private Integer viewcnt;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="newsBoardItem")
	@JsonBackReference	
	private List<NewsReplyItem> newsReplyItems;
	
	public NewsBoardItem() {
		
	}
	
	public NewsBoardItem(String title, String writer, Date date, String content, Integer viewcnt) {
		this.title = title;
		this.writer = writer;
		this.date = date;
		this.content = content;
		this.viewcnt = viewcnt;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(Integer viewcnt) {
		this.viewcnt = viewcnt;
	}

	public List<NewsReplyItem> getNewsReplyItems() {
		return newsReplyItems;
	}

	public void setReplyItems(List<NewsReplyItem> newsReplyItems) {
		this.newsReplyItems = newsReplyItems;
	}
}


