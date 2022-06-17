package kr.ac.ctc.kopo35.Dao.Mock;

import java.util.List;

import kr.ac.ctc.kopo35.Domain.ScoreItem;
import kr.ac.ctc.kopo35.Dto.Pagination;
import kr.ac.ctc.kopo35.Dto.ScoreItemsDto;
import kr.ac.ctc.kopo35.Service.ScoreItemService;

// mock 객체 : service에서 junit 테스트를 실행할 때  dao의 메서드로부터 return 받는 domain객체를 대신 한다.
public class ScoreItemDaoMock implements ScoreItemService {

	@Override
	public ScoreItemsDto scoreItemSelectAll(String cPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScoreItem> scoreItemSelectOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination getPagination(int cPage, int countPerPage, int pageSize, int total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean scoreItemInsertOne(String name, String id, String kor, String eng, String mat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scoreItemUpdateOne(String name, String id, String kor, String eng, String mat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scoreItemDeleteOne(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
