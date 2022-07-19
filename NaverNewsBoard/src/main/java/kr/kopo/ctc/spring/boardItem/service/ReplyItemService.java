package kr.kopo.ctc.spring.boardItem.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kopo.ctc.spring.boardItem.domain.NewsBoardItem;
import kr.kopo.ctc.spring.boardItem.domain.NewsReplyItem;
import kr.kopo.ctc.spring.boardItem.repository.BoardItemRepository;
import kr.kopo.ctc.spring.boardItem.repository.ReplyItemRepository;

@Service
public class ReplyItemService {
	
	@Autowired
	ReplyItemRepository replyItemRepository;
	@Autowired
	BoardItemRepository boardItemRepository;
	
	
	/* 입력 처리 */
	public void insertItem(NewsReplyItem newsReplyItem, int boardId) {
		NewsBoardItem newsBoardItem = boardItemRepository.findById(boardId).get();
		newsReplyItem.setDate(new Date());
		newsReplyItem.setNewsBoardItem(newsBoardItem);
		replyItemRepository.save(newsReplyItem);
	}
	
	
	/* 단일 조회 */
	public NewsReplyItem getReply(int replyId) {
		NewsReplyItem replyItem = replyItemRepository.findById(replyId).get();
		return replyItem;
	}
	
	
	/* 수정 처리 */
	public int updateItem(NewsReplyItem newsReplyItem) {
		NewsReplyItem newsReplyItemmbefore = replyItemRepository.findById(newsReplyItem.getId()).get();
		newsReplyItemmbefore.setContent(newsReplyItem.getContent());
		NewsReplyItem replyItemInserted = replyItemRepository.save(newsReplyItemmbefore);
		int boardId = replyItemInserted.getNewsBoardItem().getId();
		return boardId;
	}
	
	
	/* 삭제 처리 */
	public int delete(int replyId) {
		int boardId = replyItemRepository.findById(replyId).get().getNewsBoardItem().getId();
		replyItemRepository.deleteById(replyId);
		return boardId;
	}
}
