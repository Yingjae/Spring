package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.domain.ReplyVO;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	// 서비스가 매퍼를 호출하므로 매퍼를 위에 선언해야 합니다.
	@Autowired
	private ReplyMapper mapper;
	
	// 0502댓글쓰기시 Board_tbl쪽에도 관여해야 하므로 board테이블을 수정하는 Mapper를 추가선언합니다.
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<ReplyVO> listReply(long bno) {
		return mapper.getList(bno);
	}

	@Override
	public void addReply(ReplyVO vo) {
		mapper.create(vo);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);	
	}

	@Transactional
	@Override
	public void removeReply(Long rno) {
		Long bno = mapper.getBno(rno);
		mapper.delete(rno);
			
		//댓글 갱신로직 수정필요
		//mapper.updateReplyCount(bno);
		
	}

	
}
