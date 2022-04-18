package com.ict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.ReplyVO;
import com.ict.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	// consumes(소비)는 이 메서드의 파라미터를 넘겨줄때 어떤 형식으로 넘겨줄지를 설정하는데
	// 기본적으로 rest방식에서는 JSON을 사용합니다.
	// produces는 입력받은 데이터를 토대로 로직을 실행한 후에
	// 사용자에게 결과로 보여줄 데이터의 형식(즉, 리턴자료형)을 나타냅니다.
	// 아래 메서든느 json을 사용하므로 무조건 jackson-databind가 추가되어야 합니다.
	@PostMapping(value="", consumes="apllication/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	// PRODUCES에 TEXT_PLAIN_VALUE를 줬으므로 String 리턴
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
				// RestController에서는 받는 파라미터 앞에 RequestBody 어노테이션이 붙어야
				// 형식에 맞는 json 데이터를 vo에 매칭시켜 줍니다.
		// 에러나는 경우랑 안나는 경우를 대비해서 빈 ResponseEntity를 생성함
		ResponseEntity<String> entity = null;
		try {
			// 입력로직 실행 후
			service.addReply(vo);
			// 문제 없이 다음줄로 넘어왔다면 성공했을대 화면에 띄울 ResponseEntity 생성
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			// 에러나면 에러메세지와 함께 ResponseEntity 생성
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// 위의 try블럭이나 catch블럭에서 얻어온 entity변수 리턴
		return entity;
	}

}
