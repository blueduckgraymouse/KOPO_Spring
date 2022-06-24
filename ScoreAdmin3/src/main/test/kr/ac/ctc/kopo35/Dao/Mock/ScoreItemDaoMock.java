package kr.ac.ctc.kopo35.Dao.Mock;

import java.sql.Connection;
import java.util.List;

import kr.ac.ctc.kopo35.Dao.ScoreItemDao;
import kr.ac.ctc.kopo35.Domain.ScoreItem;

// mock 객체 : service에서 junit 테스트를 실행할 때  dao의 메서드로부터 return 받는 domain객체를 대신 한다.
public class ScoreItemDaoMock implements ScoreItemDao {

	@Override
	public List<ScoreItem> selectAll(Connection conn, int startRecordNo, int countPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreItem selectId(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScoreItem> selectName(Connection conn, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectTotalCount(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectNewId(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectFirstId(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOne(Connection conn, ScoreItem scoreItem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOne(Connection conn, ScoreItem scoreItem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(Connection conn, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

}
