package com.ict.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {

	// board_tbl 구조에 맞게 멤버 변수를 선언해주세요.
	private long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
}
