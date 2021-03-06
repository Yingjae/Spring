package com.ict.controller.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 빈 컨테이너에 등록해 주세요.
@Component
public class Library {
	
	// Library가 Book에 의존하는 상태로 만들어 주세요.
	// Book의 기능을 Library가 호출 하려면, 내부 멤버 변수로 Book이 필요합니다.
	private Book book;
	
	// 단, 생성자는 void생성자(아무것도 압력받지 않고 아무것도 안하는)로 만들어 주시고
	public Library() {
		// 아무것도 안하는 생성자
	}
	
	// 멤버 변수 Book에 대한 setter만 만들어 주세요.(public void setBook(Book book))
	// @Autowired는 1. 멤버 변수 위, 2. 생성자 위, 3. setter위에 붙일 수 있습니다
	@Autowired
	public void setBook(Book book) {
		this.book = book;
	}
	
	// 멤버 변수 Book을 이용해 "도서관에서 책을 읽습니다" 라는 문장을 실행하는
	//browse 메서드를 생성해 주세요.
	public void browse() {
		System.out.print("도서관에서 ");
		book.read();
	}
}
