package prdInquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import inquiry.InquiryDTO;
import kr.co.sist.dao.GetConnection;

public class PrdInquiryDAO {

	private static PrdInquiryDAO piDAO;
	
	private PrdInquiryDAO() {
		
	}
	
	public static PrdInquiryDAO getInstance() {
		if(piDAO==null) {
			piDAO=new PrdInquiryDAO();
		}
		return piDAO;
	}
	
	//상품별 문의 목록 조회
	public List<InquiryDTO> selectInquiryList(String prdId) {
		
		List<InquiryDTO> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        GetConnection gc=GetConnection.getInstance();
		
		try {
			
			con=gc.getConn("dbcp");

            StringBuilder sql = new StringBuilder();

            sql.append(" SELECT * ");
            sql.append(" FROM INQUIRY ");
            sql.append(" WHERE PRD_ID = ? ");
            sql.append(" ORDER BY INQUIRY_DATE DESC ");

            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, prdId);

            rs = pstmt.executeQuery();

            while(rs.next()) {

                InquiryDTO iDTO = new InquiryDTO();

                iDTO.setInquiryId(rs.getString("INQUIRY_ID"));
                iDTO.setInquiryDate(rs.getDate("INQUIRY_DATE"));
                iDTO.setInquiryTitle(rs.getString("INQUIRY_TITLE"));
                iDTO.setInquirySecret(rs.getString("INQUIRY_SECRET"));
                iDTO.setInquiryContent(rs.getString("INQUIRY_CONTENT"));
                iDTO.setAnswerStatus(rs.getString("ANSWER_STATUS"));
                iDTO.setAnswer(rs.getString("ANSWER"));
                iDTO.setAnswerDate(rs.getDate("ANSWER_DATE"));
                iDTO.setPrdId(rs.getString("PRD_ID"));

                list.add(iDTO);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
	//문의 상세 내용 조
	public InquiryDTO selectInquiryDetail(int inquiryId) {

        InquiryDTO iDto = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        GetConnection gc=GetConnection.getInstance();
		
		try {
			
			con=gc.getConn("dbcp");

            StringBuilder sql = new StringBuilder();

            sql.append(" SELECT * ");
            sql.append(" FROM INQUIRY ");
            sql.append(" WHERE INQUIRY_ID = ? ");

            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, inquiryId);

            rs = pstmt.executeQuery();
            

            if(rs.next()) {

                InquiryDTO iDTO= new InquiryDTO();

                iDTO.setInquiryId(rs.getString("INQUIRY_ID"));
                iDTO.setInquiryDate(rs.getDate("INQUIRY_DATE"));
                iDTO.setInquiryTitle(rs.getString("INQUIRY_TITLE"));
                iDTO.setInquirySecret(rs.getString("INQUIRY_SECRET"));
                iDTO.setInquiryContent(rs.getString("INQUIRY_CONTENT"));
                iDTO.setAnswerStatus(rs.getString("ANSWER_STATUS"));
                iDTO.setAnswer(rs.getString("ANSWER"));
                iDTO.setAnswerDate(rs.getDate("ANSWER_DATE"));
                iDTO.setPrdId(rs.getString("PRD_ID"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return iDto;
    }
	
	//신규 상품 문의 등록
	public int insertInquiry(InquiryDTO dto) {

        int cnt = 0;

        Connection con = null;
        PreparedStatement pstmt = null;

        GetConnection gc=GetConnection.getInstance();
		
		try {
			
			con=gc.getConn("dbcp");

            StringBuilder sql = new StringBuilder();

            sql.append(" INSERT INTO INQUIRY ");
            sql.append(" (INQUIRY_ID, INQUIRY_DATE, INQUIRY_TITLE, ");
            sql.append(" INQUIRY_SECRET, INQUIRY_CONTENT, ");
            sql.append(" ANSWER_STATUS, INQUIRY_STATUS, ");
            sql.append(" PRD_ID) "); 
            sql.append(" VALUES ");
            sql.append(" (SEQ_INQUIRY.NEXTVAL, SYSDATE, ?, ?, ?, ");
            sql.append(" '답변대기', '정상', ?) ");
            
            pstmt = con.prepareStatement(sql.toString());
            

            pstmt.setString(1, dto.getInquiryTitle());
            pstmt.setString(2, dto.getInquirySecret());
            pstmt.setString(3, dto.getInquiryContent());
            pstmt.setString(4, dto.getPrdId());

            cnt = pstmt.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return cnt;
    }
            
            
	//상품 문의 내용 수정
	public int updateInquiry(InquiryDTO dto) {

        int cnt = 0;

        Connection con = null;
        PreparedStatement pstmt = null;

        GetConnection gc=GetConnection.getInstance();
		
		try {
			
			con=gc.getConn("dbcp");

            StringBuilder sql = new StringBuilder();

            sql.append(" UPDATE INQUIRY ");
            sql.append(" SET INQUIRY_TITLE = ?, ");
            sql.append(" INQUIRY_SECRET = ?, ");
            sql.append(" INQUIRY_CONTENT = ? ");
            sql.append(" WHERE INQUIRY_ID = ? ");

            pstmt = con.prepareStatement(sql.toString());

            pstmt.setString(1, dto.getInquiryTitle());
            pstmt.setString(2, dto.getInquirySecret());
            pstmt.setString(3, dto.getInquiryContent());
            pstmt.setString(4, dto.getInquiryId());

            cnt = pstmt.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return cnt;
    }
	//상품 문의 삭제
	public int deleteInquiry(String inquiryId) {

        int cnt = 0;

        Connection con = null;
        PreparedStatement pstmt = null;

        GetConnection gc=GetConnection.getInstance();
		
		try {
			
			con=gc.getConn("dbcp");

            StringBuilder sql = new StringBuilder();

            sql.append(" UPDATE INQUIRY ");
            sql.append(" SET INQUIRY_STATUS = '삭제' ");
            sql.append(" WHERE INQUIRY_ID = ? ");

            pstmt = con.prepareStatement(sql.toString());

            pstmt.setString(1, inquiryId);

            cnt = pstmt.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return cnt;
    }

	//상품 문의 답변(댓글) 등록 및 수정 

	public int updateReply(String inquiryId, String answer) {
    int cnt = 0;

    Connection con = null;
    PreparedStatement pstmt = null;

    GetConnection gc=GetConnection.getInstance();
	
	try {
		
		con=gc.getConn("dbcp");

        StringBuilder sql = new StringBuilder();

        sql.append(" UPDATE INQUIRY ");
        sql.append(" SET ANSWER = ?, ");
        sql.append(" ANSWER_STATUS = '답변완료', ");
        sql.append(" ANSWER_DATE = SYSDATE ");
        sql.append(" WHERE INQUIRY_ID = ? ");

        pstmt = con.prepareStatement(sql.toString());

        pstmt.setString(1, answer);
        pstmt.setString(2, inquiryId);

        cnt = pstmt.executeUpdate();

    } catch(Exception e) {
        e.printStackTrace();
    }

    return cnt;
	}
	
	//비밀글 여부 확인
	public boolean getPrivatePost(String inquiryId) {

        boolean flag = false;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        GetConnection gc=GetConnection.getInstance();
		
		try {
			
			con=gc.getConn("dbcp");

            String sql =
                    "SELECT INQUIRY_SECRET FROM INQUIRY WHERE INQUIRY_ID = ?";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, inquiryId);

            rs = pstmt.executeQuery();

            if(rs.next()) {

                flag = "Y".equals(rs.getString("INQUIRY_SECRET"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
   



	
}
