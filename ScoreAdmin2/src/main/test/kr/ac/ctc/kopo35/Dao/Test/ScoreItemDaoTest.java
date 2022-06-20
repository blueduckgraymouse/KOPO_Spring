package kr.ac.ctc.kopo35.Dao.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import kr.ac.ctc.kopo35.Dao.ScoreItemDao;
import kr.ac.ctc.kopo35.Dao.ScoreItemDaoImpl;
import kr.ac.ctc.kopo35.Domain.ScoreItem;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScoreItemDaoTest {
	private ScoreItemDao scoreItemDao = new ScoreItemDaoImpl();
	
	
	/* 목록 조회 관련 메소드 unit test */
	@Test
	@Order(1)
	void testScoreItemSelectAll01() {		// 페이지 단위 다중 조회
		List<ScoreItem> scoreItems = scoreItemDao.selectAll(0, 9);
		
		assertEquals("나연", scoreItems.get(0).getName());
		assertEquals(209901, scoreItems.get(0).getStudentId());
		assertEquals(95, scoreItems.get(0).getKor());
		assertEquals(100, scoreItems.get(0).getEng());
		assertEquals(95, scoreItems.get(0).getMat());
		
		assertEquals("정연", scoreItems.get(1).getName());
		assertEquals(209902, scoreItems.get(1).getStudentId());
		assertEquals(95, scoreItems.get(1).getKor());
		assertEquals(95, scoreItems.get(1).getEng());
		assertEquals(95, scoreItems.get(1).getMat());
		
		assertEquals("모모", scoreItems.get(2).getName());
		assertEquals(209903, scoreItems.get(2).getStudentId());
		assertEquals(100, scoreItems.get(2).getKor());
		assertEquals(100, scoreItems.get(2).getEng());
		assertEquals(100, scoreItems.get(2).getMat());
		
		assertEquals("사나", scoreItems.get(3).getName());
		assertEquals(209904, scoreItems.get(3).getStudentId());
		assertEquals(100, scoreItems.get(3).getKor());
		assertEquals(95, scoreItems.get(3).getEng());
		assertEquals(90, scoreItems.get(3).getMat());
		
		assertEquals("지효", scoreItems.get(4).getName());
		assertEquals(209905, scoreItems.get(4).getStudentId());
		assertEquals(80, scoreItems.get(4).getKor());
		assertEquals(100, scoreItems.get(4).getEng());
		assertEquals(70, scoreItems.get(4).getMat());
		
		assertEquals("미나", scoreItems.get(5).getName());
		assertEquals(209906, scoreItems.get(5).getStudentId());
		assertEquals(100, scoreItems.get(5).getKor());
		assertEquals(100, scoreItems.get(5).getEng());
		assertEquals(70, scoreItems.get(5).getMat());
		
		assertEquals("다현", scoreItems.get(6).getName());
		assertEquals(209907, scoreItems.get(6).getStudentId());
		assertEquals(70, scoreItems.get(6).getKor());
		assertEquals(70, scoreItems.get(6).getEng());
		assertEquals(70, scoreItems.get(6).getMat());
		
		assertEquals("채영", scoreItems.get(7).getName());
		assertEquals(209908, scoreItems.get(7).getStudentId());
		assertEquals(80, scoreItems.get(7).getKor());
		assertEquals(75, scoreItems.get(7).getEng());
		assertEquals(72, scoreItems.get(7).getMat());
		
		assertEquals("쯔위", scoreItems.get(8).getName());
		assertEquals(209909, scoreItems.get(8).getStudentId());
		assertEquals(78, scoreItems.get(8).getKor());
		assertEquals(79, scoreItems.get(8).getEng());
		assertEquals(82, scoreItems.get(8).getMat());
	}
	@Test
	@Order(2)
	void testScoreItemSelectTotalCount01() {	// 총 레코드 수 조회
		int count = scoreItemDao.selectTotalCount();
		
		assertEquals(9, count);
	}
	
	
	/* 상세 조회 관련 메서드 unit test */
	@Test
	@Order(3)
	void testScoreItemSelectId01() {		// id로  단일 조회
		ScoreItem scoreItem = scoreItemDao.selectId(209901);

		assertEquals("나연", scoreItem.getName());
		assertEquals(209901, scoreItem.getStudentId());
		assertEquals(95, scoreItem.getKor());
		assertEquals(100, scoreItem.getEng());
		assertEquals(95, scoreItem.getMat());
	}
	@Test
	@Order(4)
	void testScoreItemSelectName01() {		// 이름으로 단일 조회
		List<ScoreItem> scoreItems = scoreItemDao.selectName("나연");

		assertEquals("나연", scoreItems.get(0).getName());
		assertEquals(209901, scoreItems.get(0).getStudentId());
		assertEquals(95, scoreItems.get(0).getKor());
		assertEquals(100, scoreItems.get(0).getEng());
		assertEquals(95, scoreItems.get(0).getMat());
	}
	
	
	/* 입력 관련 메서드 unit test */
	@Test
	@Order(5)
	void testScoreItemSelectNewId01() {	// 자동 부여 될(다음 레코드가 없는) id 조회
		int newId = scoreItemDao.selectNewId();
		
		assertEquals(209910, newId);
	}
	@Test
	@Order(6)
	void testScoreItemSelectFirstId01() {	// DB 상의 첫 id 조회
		int FirstId = scoreItemDao.selectNewId();

		assertEquals(209910, FirstId);
	}
	@Test
	@Order(7)
	void testScoreItemInsertOne01() {		// 새로운 레코드 입력
		ScoreItem scoreItemInput = new ScoreItem();
		
		scoreItemInput.setName("춘향");
		scoreItemInput.setStudentId(209910);
		scoreItemInput.setKor(99);
		scoreItemInput.setEng(99);
		scoreItemInput.setMat(99);
		
		int countEffected = scoreItemDao.insertOne(scoreItemInput);
		ScoreItem scoreItemOutput = scoreItemDao.selectId(209910);
		
		assertEquals(1, countEffected);
		assertEquals("춘향", scoreItemOutput.getName());
		assertEquals(209910, scoreItemOutput.getStudentId());
		assertEquals(99, scoreItemOutput.getKor());
		assertEquals(99, scoreItemOutput.getEng());
		assertEquals(99, scoreItemOutput.getMat());
	}
	
	
	/* 수정 관련 메서드 unit test */
	@Test
	@Order(8)
	void testScoreItemUpdateOne01() {		// 기존 레코드 수정
		ScoreItem scoreItemInput = new ScoreItem();
		
		scoreItemInput.setName("정연");
		scoreItemInput.setStudentId(209902);
		scoreItemInput.setKor(100);
		scoreItemInput.setEng(100);
		scoreItemInput.setMat(100);
		
		int countEffected = scoreItemDao.updateOne(scoreItemInput);
		ScoreItem scoreItemOutput = scoreItemDao.selectId(209902);
		
		assertEquals(1, countEffected);
		assertEquals("정연", scoreItemOutput.getName());
		assertEquals(209902, scoreItemOutput.getStudentId());
		assertEquals(100, scoreItemOutput.getKor());
		assertEquals(100, scoreItemOutput.getEng());
		assertEquals(100, scoreItemOutput.getMat());
	}
	

	/* 삭제 관련 메서드 unit test */
	@Test
	@Order(9)
	void testScoreItemDeleteOne01() {		// 기존 레코드 삭제
		int countEffected = scoreItemDao.deleteOne(209909);
		
		assertEquals(1, countEffected);
		assertEquals(9, scoreItemDao.selectTotalCount());
	}
}
