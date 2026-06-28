package inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import kr.co.sist.dao.GetConnection;

public class InquiryDAO {

	private static InquiryDAO iqDAO;
	
	private InquiryDAO() {
		
	}
	
	public static InquiryDAO getInstance() {
		if(iqDAO==null) {
			iqDAO=new InquiryDAO();
		}
		return iqDAO;
	}
	
	//회원별 문의 목록 조회
	public List<InquiryDTO> selectList(){
		
		List<InquiryDTO> list=new ArrayList<InquiryDTO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		
		
		
		return list;
	}
	//회원별 문의 상세 조회
//	public List<>
}
