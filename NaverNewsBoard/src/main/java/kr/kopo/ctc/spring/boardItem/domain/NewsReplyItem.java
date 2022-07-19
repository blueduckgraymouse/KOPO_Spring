package kr.kopo.ctc.spring.boardItem.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class NewsReplyItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private Date date;
	
	@Column
	private String writer;
	
	@Column
	private String content;

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="boardItemId")
	private NewsBoardItem newsBoardItem;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NewsBoardItem getNewsBoardItem() {
		return newsBoardItem;
	}

	public void setNewsBoardItem(NewsBoardItem newsBoardItem) {
		this.newsBoardItem = newsBoardItem;
	}

	@Override
	public String toString() {
		return "NewsReplyItem [id=" + id + ", date=" + date + ", writer=" + writer + ", content=" + content
				+ ", newsBoardItem=" + newsBoardItem + "]";
	}


}
