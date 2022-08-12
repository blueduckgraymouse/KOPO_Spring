package kr.ac.ctc.kopo35.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import kr.ac.ctc.kopo35.Dao.ScoreItemDao;
import kr.ac.ctc.kopo35.Dao.ScoreItemDaoImpl;
import kr.ac.ctc.kopo35.Domain.ScoreItem;
import kr.ac.ctc.kopo35.Dto.DefultRecords;
import kr.ac.ctc.kopo35.Dto.Pagination;

public class ScoreItemServiceImpl implements ScoreItemService {
	final int COUNTPERPAGE = 10;
	final int PAGESIZE = 15;
	
	private Connection conn = null;
	private ScoreItemDao ScoreItemDao = new ScoreItemDaoImpl();
	
	
	// JDBC로드
	public ScoreItemServiceImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
		} catch (Exception e) {
			throw new IllegalStateException("jdbc 드라이버 로드 실패 : " + e.getMessage());
		}
	}
	
	
	// selectOne.jsp
	@Override
	public List<ScoreItem> selectAll(String strCPage) {
		List<ScoreItem> scoreItems = null;
		
		int cPage = checkCPage(strCPage);
		
		// DAO
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			int startRecordNo = (cPage - 1) * COUNTPERPAGE;
			scoreItems = ScoreItemDao.selectAll(conn, startRecordNo, COUNTPERPAGE); 
		} catch (Exception e) {
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			close(conn);
		}
		
		return scoreItems;
	}
	
	
	// selectOne.jsp
	@Override
	public List<ScoreItem> selectOne(String name) {
		List<ScoreItem> scoreItems = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			scoreItems = ScoreItemDao.selectOne(conn, name); 
		} catch (Exception e) {
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			close(conn);
		}
		
		return scoreItems;
	}
	
	
	// updateAndDeleteForm.jsp
	@Override
	public ScoreItem selectOne(int id) {
		ScoreItem scoreItem = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			scoreItem = ScoreItemDao.selectOne(conn, id);
		} catch (Exception e) {
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			close(conn);
		}
		
		return scoreItem;
	}
	
	
	// insertOne.jsp
	@Override
	public boolean insertOne(String name, String kor, String eng, String mat) {
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
			rollback(conn);
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			close(conn);
		}
		
		return result;
	}

	
	// updateOne.jsp
	@Override
	public boolean updateOne(String name, String id, String kor, String eng, String mat)  {
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			conn.setAutoCommit(false);
			
			ScoreItem scoreItem = new ScoreItem(name, Integer.parseInt(id), Integer.parseInt(kor), Integer.parseInt(eng), Integer.parseInt(mat));
			result = ScoreItemDao.updateOne(conn, scoreItem) == 1 ? true : false;
			
			conn.commit();
		} catch (Exception e) {
			rollback(conn);
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			close(conn);
		}
		
		return result;
	}

	
	// deleteOne.jsp
	@Override
	public boolean deleteOne(int id) {
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			conn.setAutoCommit(false);
			
			result = ScoreItemDao.deleteOne(conn, id) == 1 ? true : false;
			
			conn.commit();
		} catch (Exception e) {
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		}  finally {
			close(conn);
		}
		
		return result;
	}
	
	
	//resetTable.jsp
	@Override
	public boolean resetTable() {
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
			rollback(conn);
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		}  finally {
			close(conn);
		}
		
		return result;
	}

	
	/* ------------------------------------------------------------------------ */
	
	
	/* 전체 레코드 수 조회하는 메서드 */
	@Override
	public int getTotalCount() {
		int  totalCount = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kopoctc", "root", "abcd1234");
			totalCount = ScoreItemDao.selectTotalCount(conn);
		} catch (Exception e) {
			throw new IllegalStateException("dao메서드 호출 실패" + e.getMessage());
		} finally {
			close(conn);
		}
		
		return totalCount;
	}
	
	
	/* 페이지 정보를 리턴하는 메서드 */
	@Override
	public Pagination getPagination(String  strCPage) {	
		int cPage = checkCPage(strCPage);
		int totalRecordCount = getTotalCount();
		
		Pagination pagination = calPagination(cPage, COUNTPERPAGE, PAGESIZE, totalRecordCount);
		
		return pagination;
	}
	
	/* 현재페에지 번호, 한페이지 당 레코드 수, 한 그룹당 페이지 개수, 총 레코드 수를 파라미터로 받아 페이지 정보를 계산하여 pagination객체로 반환하는 메서드  */
	public Pagination calPagination(int cPage, int countPerPage, int pageSize, int totalRecordCount) {

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
		//if (!(startPage <= totalPage && totalPage <= startPage + pageSize) && (totalPage != 0)) {					
		if (!(startPage <= totalPage && totalPage <= startPage + pageSize - 1) && (totalPage != 0)) {					
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
	
	
	/* 파라미터로 들어온 현재 페이지 번호 null check & 형변환하는 메서드 */
	@Override
	public int checkCPage(String strCPage) {
		// 현재 페이지 번호 null 체크
		int cPage = 0;
		if (strCPage == null) {
			cPage = 1;
		} else {
			cPage = Integer.parseInt(strCPage);
		}
		return cPage;
	}
	
	/* 파라미터로 들어온 connection의 트렌젝션을 rollback하는 메서드 */
	@Override
	public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (Exception e) {
			throw new IllegalStateException("rollback 실패" + e.getMessage());
		}
	}
	
	
	/* 파라미터로 들어온 connection 자원을 반납하는 메서드 */
	@Override
	public void close(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			throw new IllegalStateException("connection 자원 반납 실패" + e.getMessage());
		}
	}
}
