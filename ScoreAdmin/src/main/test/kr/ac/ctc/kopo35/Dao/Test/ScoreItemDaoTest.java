package kr.ac.ctc.kopo35.Dao.Test;

import org.junit.jupiter.api.Test;

import kr.ac.ctc.kopo35.Dao.ScoreItemDao;
import kr.ac.ctc.kopo35.Dao.ScoreItemDaoImpl;
import kr.ac.ctc.kopo35.Domain.ScoreItem;

class ScoreItemDaoTest {
	private ScoreItemDao scoreItemDao = new ScoreItemDaoImpl();
	
	@Test
	void testInsert() {
		ScoreItem scoreItemInput = new ScoreItem();
		scoreItemInput.setName("홍길동");
		scoreItemInput.setId(209999);
		scoreItemInput.setKor(100);
		scoreItemInput.setEng(100);
		scoreItemInput.setMat(100);
		ScoreItem scoreItemOutput = scoreItemDao.insertOne(scoreItemInput);	// dao로 데이터 옮길 때 domain사용하기.
		assertEquals(scoreItemOutput.getId(), );	// id는 내부적 고유번호용도, auto increase설정, DB에서 확인 후 비교값으로 넣기. 
	}
}
