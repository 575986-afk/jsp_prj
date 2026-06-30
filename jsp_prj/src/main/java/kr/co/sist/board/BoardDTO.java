package kr.co.sist.board;

import java.util.Date;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	private int num;
	private String id,title,content,ip,cnt;	
	private Date input_date;	

}
