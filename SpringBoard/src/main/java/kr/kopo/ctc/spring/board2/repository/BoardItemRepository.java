package kr.kopo.ctc.spring.board2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.kopo.ctc.spring.board2.domain.BoardItem;

@Repository
public interface BoardItemRepository extends JpaRepository<BoardItem, Integer> {
	List<BoardItem> findAllByTitleLike(String title);
	Page<BoardItem> findAllByTitleLike(String title, Pageable pageable);
	
	List<BoardItem> findAllByNo(Integer no);
	
	@Transactional
	void deleteByNo(Integer no);
	
	List<BoardItem> findAllByBoardGroupId(Integer BoardGroupId);
}
