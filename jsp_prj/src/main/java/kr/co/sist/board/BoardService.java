package kr.co.sist.board;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

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
	
	public List<BoardDTO> searchBoard(RangeDTO rDTO){
		List<BoardDTO> listBoard=null;
		
		BoardDAO bDAO=BoardDAO.getInstance();
		try {
			listBoard=bDAO.selectBoard(rDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		
		}//end catch
		return listBoard;
	}
	public BoardDTO searchBoardDetail(int num){
		BoardDTO bDTO=null;
		
		BoardDAO bDAO=BoardDAO.getInstance();
		try {
			//글 번호에 해당하는 글을 읽고 
			bDTO=bDAO.selectBoardDetail(num);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}//end catch
		return bDTO;
	}
	
	public void modifyCount(int num){
		
		BoardDAO bDAO=BoardDAO.getInstance();
		try {
			//글 번호에 해당하는 글을 읽고 
			bDAO.updateCnt(num);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}//end catch
	}
	
	public boolean addBoard(BoardDTO bDTO) {
		boolean flag=false;
		
		BoardDAO bDAO=BoardDAO.getInstance();
		try {
			bDAO.insertBoard(bDTO);
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}//end catch
		
		return flag;
	}
	public boolean modifyBoard(BoardDTO bDTO) {
		boolean flag=false;
		
		BoardDAO bDAO=BoardDAO.getInstance();
		try {
			flag=bDAO.updateBoard(bDTO)==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}
	public boolean removeBoard(BoardDTO bDTO) {
		boolean flag=false;
		
		BoardDAO bDAO=BoardDAO.getInstance();
		try {
			flag=bDAO.deleteBoard(bDTO)==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}
	
}//class
