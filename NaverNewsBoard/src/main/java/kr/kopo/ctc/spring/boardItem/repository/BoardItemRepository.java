package kr.kopo.ctc.spring.boardItem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.kopo.ctc.spring.boardItem.domain.NewsBoardItem;

@Repository
public interface BoardItemRepository extends JpaRepository<NewsBoardItem, Integer> {
	
	/* 전체 뉴스 게시물 개수 조회 쿼리 */
	@Query(value="select count(*) from NewsBoardItem")
	public int getTotalCount();
	
	
	/* 조회수 증가 쿼리 */
	@Query(value="update NewsBoardItem set viewcnt = viewcnt + 1 where id = :id")
	@Modifying
	@Transactional
	public void increaseViewcnt(@Param(value = "id") int id);
}
