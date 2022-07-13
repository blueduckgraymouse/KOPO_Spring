package kr.kopo.ctc.spring.board2.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import kr.kopo.ctc.spring.board2.domain.BoardItem;

@Mapper
@Repository
public interface BoardItemMapper {
	void insertOne(BoardItem boardItem);
	
	List<BoardItem> selectAll();
	List<BoardItem> selectAll(int no);
	List<BoardItem> selectAll(RowBounds rowBound);
	List<BoardItem> selectAllByTitle(String title);
	List<BoardItem> selectAllByTitle(String title, RowBounds rowBound);

	BoardItem selectOne(int id);

	void updateOne(BoardItem boardItem);
	
	void deleteAll(int no);
	//void delete(BoardItem boardItem); // id 혹은 no
	
}
