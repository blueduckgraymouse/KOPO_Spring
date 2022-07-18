package kr.kopo.ctc.spring.boardItem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.kopo.ctc.spring.boardItem.domain.ReplyBoardItem;

@Repository
public interface BoardItemRepository extends JpaRepository<ReplyBoardItem, Integer> {
	Page<ReplyBoardItem> findAllByOrderByRootidDescRecntAsc(Pageable pageable);
}
