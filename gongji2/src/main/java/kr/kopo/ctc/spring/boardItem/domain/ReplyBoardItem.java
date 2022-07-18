package kr.kopo.ctc.spring.boardItem.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ReplyBoardItem {
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	@Column
	private String title;
	
	@Column
	private Date date;
	
	@Column
	private String content;
	
	@Column
	private Integer rootid;
	
	@Column
	private Integer relevel;
	
	@Column
	private Integer recnt;
	
	@Column
	private Integer viewcnt;
	
	public ReplyBoardItem() {
		
	}
	
	public ReplyBoardItem(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public ReplyBoardItem(String title, Date date, String content, Integer relevel,
			Integer recnt, Integer viewcnt) {
		this.title = title;
		this.date = date;
		this.content = content;
		this.relevel = relevel;
		this.recnt = recnt;
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

	public Integer getRootid() {
		return rootid;
	}

	public void setRootid(Integer rootid) {
		this.rootid = rootid;
	}

	public Integer getRelevel() {
		return relevel;
	}

	public void setRelevel(Integer relevel) {
		this.relevel = relevel;
	}

	public Integer getRecnt() {
		return recnt;
	}

	public void setRecnt(Integer recnt) {
		this.recnt = recnt;
	}

	public Integer getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(Integer viewcnt) {
		this.viewcnt = viewcnt;
	}

	@Override
	public String toString() {
		return "BoardItem [id=" + id + ", title=" + title + ", date=" + date + ", content=" + content + ", rootid="
				+ rootid + ", relevel=" + relevel + ", recnt=" + recnt + ", viewcnt=" + viewcnt + "]";
	}
}


