package kr.kopo.ctc.spring.board2.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BoardItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	//@ManyToOne(optional=false)
	//@ManyToOne(optional=false, fetch=FetchType.EAGER)		// fetch=FetchType.EAGER를 설정함으로써 부모 테이블의 정보까지 조회
	@ManyToOne(optional=false, fetch=FetchType.LAZY)		
	@JoinColumn(name="boardGroupId")		// BoardItem 테이블에 추가되는 외래키 컬럼명 지정
	private BoardGroup boardGroup;			// 실제 DB에 생성되는 외래키 컬럼은 int타입이지만 domain클래스에서 필드타입은 관께를 맺는 domain 클래스인 BoardGroup로 선언
	
	
	
	public BoardItem() { }
	
	public BoardItem(Integer no, String title, String author) {
		this.no = no;
		this.title = title;
		this.author = author;
	}

	public BoardItem(Integer no, String title, String author, Date created, Integer view) {
		this.no = no;
		this.title = title;
		this.author = author;
		this.created = created;
		this.view = view;
	}

	public BoardItem(Integer no, String title, String author, Date created, Integer view, BoardGroup boardGroup) {
		this.no = no;
		this.title = title;
		this.author = author;
		this.created = created;
		this.view = view;
		this.boardGroup = boardGroup;
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

	public void setBoardGroup(BoardGroup boardGroup) {
		this.boardGroup = boardGroup;
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

	public BoardGroup getBoardGroup() {
		return boardGroup;
	}

	@Override
	public String toString() {
		return "BoardItem [id=" + id + ", no=" + no + ", title=" + title + ", author=" + author + ", created=" + created
				+ ", view=" + view + "]";
	}
}


