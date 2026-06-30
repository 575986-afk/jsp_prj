package deliveryChg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.GetConnection;

public class DeliveryChgDAO {
	
	private static DeliveryChgDAO dDAO;
	
	private DeliveryChgDAO() {
		
	}
	
	public static DeliveryChgDAO getInstance() {
		if(dDAO==null) {
			dDAO=new DeliveryChgDAO();
		}
		return dDAO;
	}
	//회원별 배송지 목록 조회
	public List<DeliveryDTO> selectDeliveryList(String clientId){
		
		List<DeliveryDTO> list=new ArrayList<>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		try {
			con=gc.getConn("dbcp");
			
			String sql="SELECT DELIVERY_POSTCODE,DELIVERY_ADDR,FIRST_DESTINATION FROM DELIVERY_DESTINATION WHERE CLIENT_NO=?";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, clientId);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				DeliveryDTO dDTO=new DeliveryDTO();
				dDTO.setDeliveryPost(rs.getString("DELIVERY_POSTCODE"));
				dDTO.setDeliveryAddr(rs.getString("DELIVERY_ADDR"));
				dDTO.setFirstDestination(rs.getBoolean("FIRST_DESTINATION"));
				
				list.add(dDTO);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				gc.dbClose(rs, pstmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}
	//배송지 상세 정보 조회
	public DeliveryDTO selectDeliveryDetail(String deliveryId) {
		
		DeliveryDTO dDTO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		String sql="";
		
		try {
			con=gc.getConn("dbcp");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				gc.dbClose(rs, pstmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dDTO;
	}
	//신규 배송지 등록
	public int insertNewDelivery(DeliveryDTO dDTO) {
		
		int cnt=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		String sql="INSERT INTO DELIVERY_DESTINATION (DELIVERY_POSTCODE, DELIVERY_ADDR, FIRST_DESTINATION, DELIVERY_INPUT_DATE, CLIENT_NO) VALUES (?, ?, ?, SYSDATE, ?)";
		
		try {
			con=gc.getConn("dbcp");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				gc.dbClose(null, pstmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	//배송 상태 및 내용 업데이트
	public int updateDeliveryDetail(String deliveryId) {
		
		int cnt=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		try {
			con=gc.getConn("dbcp");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				gc.dbClose(null, pstmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	//배송지 정보 수정
	public int modifyDelivery(DeliveryDTO dDTO) {
		
		int cnt=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		try {
			con=gc.getConn("dbcp");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				gc.dbClose(null, pstmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
}
