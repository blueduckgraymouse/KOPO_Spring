package kr.kopo.ctc.spring.board2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kopo.ctc.spring.board2.domain.BoardGroup;

public interface BoardGroupRepository extends JpaRepository<BoardGroup, Integer> {
	List<BoardGroup> findAllByName(String name);
}
