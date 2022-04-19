package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.ReplyVO;
import com.ict.service.ReplyService;

@RestController
@RequestMapping("/replies") // 접속시 기본 주소에 replies가 기본적으로 붙음
public class ReplyController {
	// 컨트롤러 -> 서비스 -> 매퍼 -> 쿼리문
	@Autowired
	private ReplyService service; // 컨트롤러 내부에 의존성 주입
	
	// consumes(소비)는 이 메서드의 파라미터를 넘겨줄때 어떤 형식으로 넘겨줄지를 설정하는데
	// 기본적으로 rest방식에서는 JSON을 사용합니다.
	// produces는 입력받은 데이터를 토대로 로직을 실행한 후에
	// 사용자에게 결과로 보여줄 데이터의 형식(즉, 리턴자료형)을 나타냅니다.
	// 아래 메서든느 json을 사용하므로 무조건 jackson-databind가 추가되어야 합니다.
	@PostMapping(value="", consumes="application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
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
		// 위의 try블럭이나 catch블럭에서 얻어온 entity변수 리턴하기
		return entity;
	}
	
	@GetMapping(value="/all/{bno}", // url만 보고도 어떤 화면인지 유추할 수 있게 만들어주는게 좋음
		// 단일 숫자데이터 bno만 넣어서 조회하기도 하고
		// PathVariable 어노테이션으로 이미 입력데이터가 명시 되었으므로
		// consumes는 따로 주지 않아도 됩니다.
		// produces는 댓글 목록이 XML로도, JSON으로도 표현될 수 있도록
		// 아래와 같이 2개를 모두 얹습니다.
		// jackson-dataformat-xml을 추가해야 xml도 작동합니다.
		produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Long bno) {
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			entity = new ResponseEntity<>(
					service.listReply(bno), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	

}
