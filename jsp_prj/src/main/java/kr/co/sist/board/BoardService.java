package kr.co.sist.board;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;

import kr.co.sist.chipher.DataDecryption;
import kr.co.sist.user.member.MemberDTO;

public class BoardService {
	public int totalCount() {
		
		int cnt=0;
		BoardDAO bDAO=BoardDAO.getInstance();
		try {
			cnt=bDAO.selectTotalCount();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return cnt;			
	}//searchLogin
}//class
