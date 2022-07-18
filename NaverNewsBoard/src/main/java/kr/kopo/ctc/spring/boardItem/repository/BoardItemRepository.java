package kr.kopo.ctc.spring.boardItem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.kopo.ctc.spring.boardItem.domain.NewsBoardItem;

@Repository
public interface BoardItemRepository extends JpaRepository<NewsBoardItem, Integer> {
}
