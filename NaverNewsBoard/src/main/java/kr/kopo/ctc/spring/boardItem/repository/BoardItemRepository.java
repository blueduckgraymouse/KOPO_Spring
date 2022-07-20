package kr.kopo.ctc.spring.boardItem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.kopo.ctc.spring.boardItem.domain.NewsBoardItem;

@Repository
public interface BoardItemRepository extends JpaRepository<NewsBoardItem, Integer> {

	/* 게시물 개수 조회 */
	public long count();
	public long countByTitleContaining(String keword);

	/* 록록 조회 (id기준 내림차순) */
	public Page<NewsBoardItem> findAllByOrderByIdDesc(Pageable pageagle);
	
	/* 제목 키워드 검색 (id기준 내림차순) */
	public Page<NewsBoardItem> findAllByTitleContainingOrderByIdDesc(String keword, Pageable pageagle);
	
	/* 조회수 증가 */
	@Query(value = "update NewsBoardItem set viewcnt = viewcnt + 1 where id = :id")
	@Modifying
	@Transactional
	public void increaseViewcnt(@Param(value = "id") int id);
}
