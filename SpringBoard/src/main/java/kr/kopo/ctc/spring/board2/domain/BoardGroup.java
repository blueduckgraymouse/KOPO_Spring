package kr.kopo.ctc.spring.board2.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BoardGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	//@OneToMany(cascade=CascadeType.ALL, mappedBy="BoardGroup")		// mappedBy 대상은 BoardItem클래스의 BoardGroup 필드명과 일치.
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="boardGroup")	// 패치를 미룸. 가져왔다 치고 패스. default값.
	//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="BoardGroup")	// 패치 즉시 로딩, 자식 테이블에 해당하는 필드까지 조회해서 가져온다.
	private List<BoardItem> boardItems;

	
	public BoardGroup() {
	}
	
	public BoardGroup(String name) {
		this.name = name;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BoardItem> getBoardItems() {
		if (boardItems == null) {
			boardItems = new ArrayList<BoardItem>();
		}
		return boardItems;
	}
	public void setBoardItems(List<BoardItem> boardItems) {
		this.boardItems = boardItems;
	}
	public void addBoardItems(BoardItem boardItem) {
		List<BoardItem> boardItems = getBoardItems();
		boardItems.add(boardItem);
	}
	
	
	@Override
	public String toString() {
		String result = "BoardGroup [id=" + id + ", name=" + name + "] \n";
//		for (BoardItem boardItem : boardItems) {			-> fetch type이 lazy면 에러 발생, EAGER이면 출력 가능.
//			result += "  - " + boardItem + "\n";			
//		}
		return result;
	}
}
