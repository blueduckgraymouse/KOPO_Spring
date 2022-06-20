package kr.ac.ctc.kopo35.Dao.Mock;

import java.util.List;

import kr.ac.ctc.kopo35.Dao.ScoreItemDao;
import kr.ac.ctc.kopo35.Domain.ScoreItem;

// mock 객체 : service에서 junit 테스트를 실행할 때  dao의 메서드로부터 return 받는 domain객체를 대신 한다.
public class ScoreItemDaoMock implements ScoreItemDao {

	@Override
	public List<ScoreItem> selectAll(int startRecordNo, int countPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreItem selectId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScoreItem> selectName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectNewId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectFirstId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOne(ScoreItem scoreItem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOne(ScoreItem scoreItem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
