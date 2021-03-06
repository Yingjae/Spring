package com.ict.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.controller.vo.UserVO;

// 어노테이션에 네 종류가 있었는데 (@Component, @Repository, @Controller, @Service)
// 컨트롤러를 만드는 경우이니 당연히 @Controller를 씁니다.
@Controller
public class BasicController {

	// RequestMapping의 value는 localhost:8181/ 어떤주소로 접속시 해당 로직이 실행될지 결정합니다.
	// 아무것도 안적으면 기본적으로 get방식을 허용합니다.
	@RequestMapping(value="/goA")
	// 아래에 해당 주소로 접속시 실행하고 싶은 메서드를 작성합니다.
	public String goA() {
		System.out.println("goA 접속이 감지되었습니다.");
		// return "goA"; 라고 적으면 views 폴더 내부의 goA.jsp 파일을 보여줍니다.
		return "goA";
	}
	
	// goB로 접속했을때 b.jsp 창이 열리도록 아래에 세팅해주세요.
	@RequestMapping(value="/goB")
	public String go() {
		return"b";
	}
	
	// 여러분들의 성함 성씨 기준으로 패턴을 잡고
	// 결과 페이지는 "XXX의 페이지 입니다." 라는 문장이 뜨도록 처리해서 메서드와 어노테이션을 저에게 보내주세요.
	@RequestMapping(value="/Yim")
	public String Yim() {
		return"Yim";
	}
	
	// 외부에서 전송하는 데이터는 메서드 선언부에 선언된 변수로 받습니다.
	// 이름만 일치하면 알아서 받아옵니다.
	// 자료형을 신경쓸 필요가 없습니다.
	@RequestMapping(value="/getData", method=RequestMethod.POST) // localhost:8181/getData
					// getData?data1=데이터1&data2=데이터2에 해당하는 요소를 받아옵니다.
	public String getData(String data1, int data2, 
						Model model, HttpServletRequest request) 
							throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		// String data1 = request.getParameter("data1"); // jsp를 사용할 떄 데이터를 받아오는 방법
		// int data2 = Integer.parseInt(strData2); // jsp를 사용할 때 받아온 데이터를 다른 데이터로 변환하는 방법
		System.out.println("data1에 든 값 : " + data1);
		System.out.println("data2에 든 값 : " + data2);
		System.out.println("data2가 정수임을 증명 : " + (data2+100));
		model.addAttribute("data1", data1);
		model.addAttribute("data2", data2);
		return "getResult";
	}
	
	// 외부에서 전송하는 데이터를 /getMoney 주소로 받아오겠습니다.
	// 이 주소는 int won 이라는 형식으로 금액을 받아서
	// 환율에 따른 환전금액을 콘솔에 찍어줍니다. 환전화폐는 임의로 정해주세요.
	// 결과 페이지는 exchange.jsp로 하겠습니다.
	// 메서드명은 임의로 만들어주세요.
	@RequestMapping(value="/getMoney", method=RequestMethod.POST) // post방식으로만 받도록 처리
					// 포워딩시 바인딩을 하고 싶다면 Model을 선언합니다.
	public String eMxchange(int won, Model model) {
		System.out.println("입력한 금액은 " + won + "원 입니다.");
	    System.out.println("현재 엔화 환율은 987원당 100엔 입니다.");
	    System.out.println("입력한 금액에 따른 환전 금액은 " + (won / 987 * 100) + "엔 입니다.");
	    double result = (won / 987 * 100);
	    // model.addAttribute("보낼이름", 보낼 자료);
	    // 넘어간 데이터는 .jsp 파일에서 el을 이용해 출력합니다.
	    // ex -> model.addAttribute("test", 자료); 로 바인딩 한 경우
	    // ${test}로 .jsp에서 출력 가능
	    model.addAttribute("result", result);
	    // won 변수에 해당하는 변수도 추가로 보내보세요.
	    model.addAttribute("won", won);
		return "exchange";
	}
	
	// form 페이지와 결과 페이지를 분리해야 합니다.
	// 다만 목적지 주소가 .jsp 기준이 아닌, @RequestMapping상의 주소 기준으로 갑니다.
	// 주소 moneyForm으로 연결되도록 아래에 어노테이션 + 메서드를 구성해 주세요.
	// moneyForm.jsp로 연결 됩니다.
	// moneyForm.jsp에는 목적지를 #으로 하고
	// name=won 인 폼을 추가로 만들어 주세요.
	
	// 1. @RequestMapping(어노테이션)에 어떤 주소로 접속할 지 입력
	@RequestMapping(value="/moneyForm")
	// 2. public String 메서드() 생성
	public String moneyForm() {
		// 3. return 구문 뒤에 연결할 .jsp파일의 이름을 작성(확장자는 작성하지 않는다)
		return "moneyForm";
	}
	
	// 상단 /getData 주소를 타겟으로 하는 /dataForm을 만들어 주세요.
	// data1, data2를 자료형에 맞게 폼으로 입력받아 전송 버튼을 누르면
	// 해당 데이터가 결과 페이지에 나올 수 있도록 .jsp 파일부터 시작해서
	// form태그나 세부 로직까지 완성시켜 주세요.
	// 1. 주소 및 연결 메서드
	// 2. 상단 /getData 주소를 타겟으로 하는 form태그 완성 후 보내주세요.
	@GetMapping(value="/dataForm")
	public String dataForm() {
		return "dataForm";
	}

	
	// 스프링 5버전 부터 허용
	// @요청메서드Mapping은 해당 메서드만 허용하는 어노테이션 입니다.
	@GetMapping(value="/onlyGet")
	public String onlyGet() {
		return "onlyGet";
	}
	
	// 성적 입력 폼 접근 로직
	@GetMapping(value="/score")
	public String scoreForm() {
		return "scoreForm";
	}
	
	// 성적 결과 페이지 접근 로직
	@PostMapping(value="/score")
	public String scoreResult(
			int math, 
			int eng, 
			int lang, 
			int social, 
			// computer -> com으로 입력하는 로직 (변수명이 일치하지 않아 발생하는 문제 해결)
			@RequestParam("computer") int com, 
			Model model) {
		
		int total = math + eng + lang + social + com;
		double avg = total / 5.0;
		
		// 바인딩
		model.addAttribute("math", math);
		model.addAttribute("eng", eng);
		model.addAttribute("lang", lang);
		model.addAttribute("social", social);
		model.addAttribute("com", com);
		model.addAttribute("total", total);
		model.addAttribute("avg", avg);
		
		return "scoreResult";
	}
	
	// 주소는 /page로 하겠습니다.
	// get방식 접속만 허용합니다.
	// 메서드명은 임의로 만들어주세요.
	// page.jsp로 연결됩니다.
	@GetMapping(value="/page/{pageNum}")
	public String getPage(@PathVariable int pageNum, 
						@PathVariable int bookNum,
						Model model) {
		
		model.addAttribute("page", pageNum);
		model.addAttribute("book", bookNum);
		return "page";
	}
	
	// 환율 계산기를 만들어 보겠습니다.
	// 단, 원화 금액은 @PathVariable을 이용해 입력 받습니다.
	// 주소는 /rate 입니다.
	// get방식으로 처리해주세요.
	// 원화를 입력 받으면 rate.jsp에서 결과로 환전한 금액을 보여줍니다.
	@GetMapping(value="/rate/{won}")
	public String getRate(@PathVariable int won, Model model) {
		final double USD_RATE = 1209.0;
		double result = won / USD_RATE;
		model.addAttribute("won", won);
		model.addAttribute("result", result);
		return "rate";
	}
	
	// 리스트를 받아서 처리하기
	@GetMapping("/getList")
	public String getList(
			// 배열 자료를 받을시 @RequestParam 사용이 강제됩니다.
			@RequestParam ArrayList<String> array, Model model) {
		// 리스트 자료형의 경우 같은 이름으로 여러 데이터를 연달아 보내면 처리가능합니다.
		model.addAttribute("array", array);
		return "getList";
	}

	// 만약 주소와 매칭된 메서드의 리턴자료형을 String이 아닌 void로 처리하는 경우
	// 지정주소 .jsp로 바로 연결 됩니다.(view파일(.jsp파일)이름 지정 불가
	// 주소와 파일명이 일치하다면 써주셔도 되지만
	// 기본적으로는 String을 쓰는걸 추천드립니다.
	@GetMapping("/test")// test.jsp로 바로 연결됨
	public void goTest() {
		// 내부 실행문 없음
	}
	
	// VO를 활용해 회원 데이터를 받는 컨트롤러를 만들어 보겠습니다.
	@PostMapping(value="/userInfo")
	public String getUserInfo(UserVO userVO, Model model) {
		// 변수명은 userVO로 지정했으나, 실제로는 내부 변수의 이름으로 데이터를 받습니다.
		
		// 바인딩 문법을 작성해주세요.
		model.addAttribute("userVO", userVO);
		
		return "user";
	}
	
	// userInfo 페이지를 만들어서 폼을 만들어
	// 상단의 userInfo로 보내게 해주세요.
	// userInfo로직은 post방식만 허용하게 해주시고
	// 폼 페이지는 get방식만 허용하도록 수정합니다.
	@GetMapping(value="/userInfo")
	public String getUserForm() {
		return "userForm";
	}
}

