package kr.kopo.ctc.spring.board2.repo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.kopo.ctc.spring.board2.domain.BoardItem;

@Mapper
@Repository
public interface BoardItemMapper {
	List<BoardItem> findAll();
}
