package kr.ac.ctc.kopo35.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import kr.ac.ctc.kopo35.Dao.ScoreItemDao;
import kr.ac.ctc.kopo35.Dao.ScoreItemDaoImpl;
import kr.ac.ctc.kopo35.Domain.ScoreItem;
import kr.ac.ctc.kopo35.Dto.DefultRecords;
import kr.ac.ctc.kopo35.Dto.Pagination;
import kr.ac.ctc.kopo35.Dto.ScoreItemsDto;

public class ScoreItemServiceImpl implements ScoreItemService {
	private Connection conn = null;
	private ScoreItemDao ScoreItemDao = new ScoreItemDaoImpl();
	private final int countPerPage = 10;
	private final int pageSize = 15;
	
	public ScoreItemServiceImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
		} catch (Exception e) {
			throw new IllegalStateException("jdbc 드라이버 로드 실패 : " + e.getMessage());
		}
	}
	
	@Override
	public ScoreItemsDto scoreItemSelectAll(String strCPage) throws SQLException {
		ScoreItemsDto scoreItemsDto = null;
		
		// 현재 페이지 번호 null 체크
		int cPage = 0;
		if (strCPage == null) {
			cPage = 1;
		} else {
			cPage = Integer.parseInt(strCPage);
		}
		
		// DAO
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			// 레코드 목록 조회
			int startRecordNo = (cPage - 1) * countPerPage;
			List<ScoreItem> scoreItems = ScoreItemDao.selectAll(conn, startRecordNo, countPerPage); 
			
			// 페이징 처리
			int totalRecordCount = ScoreItemDao.selectTotalCount(conn);	// 전체 레코드 수 조회
			Pagination pagination = getPagination(cPage, countPerPage, pageSize, totalRecordCount);
			
			// DTO에 저장
			scoreItemsDto = new ScoreItemsDto(scoreItems, pagination);
		} catch (Exception e) {
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			conn.close();
		}
		
		return scoreItemsDto;
	}

	
	@Override
	public List<ScoreItem> scoreItemSelectName(String name) throws SQLException {
		List<ScoreItem> scoreItems = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			scoreItems = ScoreItemDao.selectName(conn, name);
		} catch (Exception e) {
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			conn.close();
		}
		
		return scoreItems;
	}
	
	
	@Override
	public ScoreItem scoreItemSelectId(int id) throws SQLException {
		ScoreItem scoreItem = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			scoreItem = ScoreItemDao.selectId(conn, id);
		} catch (Exception e) {
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			conn.close();
		}
		
		return scoreItem;
	}
	

	@Override
	public boolean scoreItemInsertOne(String name, String kor, String eng, String mat) throws SQLException {
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			conn.setAutoCommit(false);
			
			// 209901을 첫 ID로 기준 자동 부여될 새로운 id값 계산
			int newId = ScoreItemDao.selectNewId(conn);
			int firstId = ScoreItemDao.selectFirstId(conn);	
			if ( firstId != 209901) {
				newId = 209901;
			}
			
			ScoreItem scoreItem = new ScoreItem(name, newId, Integer.parseInt(kor), Integer.parseInt(eng), Integer.parseInt(mat));
			result = ScoreItemDao.insertOne(conn, scoreItem) == 1 ? true : false;
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			conn.close();
		}
		
		return result;
	}

	
	@Override
	public boolean scoreItemUpdateOne(String name, String id, String kor, String eng, String mat) throws SQLException {
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			conn.setAutoCommit(false);
			
			ScoreItem scoreItem = new ScoreItem(name, Integer.parseInt(id), Integer.parseInt(kor), Integer.parseInt(eng), Integer.parseInt(mat));
			result = ScoreItemDao.updateOne(conn, scoreItem) == 1 ? true : false;
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			conn.close();
		}
		
		return result;
	}

	
	@Override
	public boolean scoreItemDeleteOne(int id) throws SQLException {
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			conn.setAutoCommit(false);
			
			result = ScoreItemDao.deleteOne(conn, id) == 1 ? true : false;
			
			conn.commit();
		} catch (Exception e) {
			conn.close();
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		}  finally {
			conn.close();
		}
		
		return result;
	}
	
	
	@Override
	public boolean scoreItemsReset() throws SQLException {
		int countInserted = 0;
		boolean result = false;
		DefultRecords defultRecords = new DefultRecords();
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			conn.setAutoCommit(false);
			
			// 기존 레코드 삭제
			ScoreItemDao.deleteAll(conn);
			int countTotal = ScoreItemDao.selectTotalCount(conn);
			
			// 기본 레코드 입력
			if (countTotal == 0) {
				for (int i = 0; i < defultRecords.getName().length; i++) {
					ScoreItem scoreItem = new ScoreItem(defultRecords.getName()[i],
							defultRecords.getStudentId()[i],
							defultRecords.getKor()[i],
							defultRecords.getEng()[i],
							defultRecords.getMat()[i]
							);
					countInserted += ScoreItemDao.insertOne(conn, scoreItem);
				}
				
				if (countInserted == defultRecords.getName().length) {
					conn.commit();
					result = true;
				} else {
					conn.rollback();
					result = false;
				}
			} else {
				conn.rollback();
				result = false;
			}
		} catch (Exception e) {
			conn.rollback();
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		}  finally {
			conn.close();
		}
		
		return result;
	}

	
	/* 페이지 정보를 계산 하는 메서드 */
	public Pagination getPagination(int cPage, int countPerPage, int pageSize, int totalRecordCount) {
		Pagination pagination = new Pagination();
		
		// 총 페이지 수
		int totalPage = 0;						
		if ((totalRecordCount % countPerPage) == 0) {		
			totalPage = totalRecordCount / countPerPage; 				
		} else {								
			totalPage = totalRecordCount / countPerPage + 1; 				
		}
		pagination.setTotalPage(totalPage);

		// 현재 페이지 번호
		if (cPage >= totalPage) {
			cPage = totalPage;
		} else if (cPage < 1) {
			cPage = 1;
		}
		pagination.setcPage(cPage);
		
		// 첫 페이지 번호
		int startPage = (cPage / pageSize) * pageSize + 1;	
		if ((cPage % pageSize) == 0) {		
			startPage -= pageSize;
		}
		pagination.setStartPage(startPage);
		
		// 마지막 페이지 번호
		int lastPage = (startPage + pageSize - 1) >= totalPage ? totalPage : (startPage + pageSize - 1);
		pagination.setLastPage(lastPage);
		
		// 첫 페이지 번호 & 이전 그룹 마지막 페이지 번호
		int ppPage = 0;
		int pPage = 0;
		if ( startPage != 1) {					
			ppPage = 1;
			pPage = startPage - 1;
		}
		pagination.setPpPage(ppPage);
		pagination.setpPage(pPage);
		
		// 다음 그룹 첫 페이지 번호 & 마지막 페이지 번호
		int nnPage = 0;
		int nPage = 0;
		if (!(startPage <= totalPage && totalPage <= startPage + pageSize) && (totalPage != 0)) {					
			nnPage = totalPage;
			nPage = startPage + pageSize;
		}
		pagination.setNnPage(nnPage);
		pagination.setnPage(nPage);
		
		// 한 페이지 당 레코드 수, 한 그룹 당 페이지 수, 총 게시물 수
		pagination.setCountPerPage(countPerPage);
		pagination.setPageSize(pageSize);
		pagination.setTotalRecordCount(totalRecordCount);
		
		// 레코드가 0개 일 경우 예외 처리
		if (totalRecordCount == 0) {
			pagination.setStartPage(0);
			pagination.setLastPage(0);
			pagination.setPpPage(0);
			pagination.setpPage(0);
			pagination.setNnPage(0);
			pagination.setnPage(0);
			pagination.setTotalPage(0);
		}
		
		return pagination;
	}
}
