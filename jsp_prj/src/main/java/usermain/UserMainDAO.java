package usermain;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import productDetail.ProductDTO;

public class UserMainDAO {

	private static UserMainDAO umDAO;
	
	private UserMainDAO() {
		
		
	}
	
	public static UserMainDAO getInstance() {
		if(umDAO==null) {
			umDAO=new UserMainDAO();
		}
		return umDAO;
	}
	
	//베스트 상품
	public List<ProductDTO> selectBest(){
		
		
		
		List<ProductDTO> list=new ArrayList<>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			
			con=DBConnection.getInstance().getConn();
			
			String sql="SELECT p.PRODUCT_ID,p.PRODUCT_NAME,SUM(od.QUANTITY) sales_count"
					+ " FROM PRODUCT p"
					+ " INNER JOIN ORDER_DETAILS od"
					+ " ON p.PRODUCT_ID = od.PRODUCT_ID"
					+ " GROUP BY p.PRODUCT_ID, p.PRODUCT_NAME"
					+ " ORDER BY sales_count DESC";
			
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDTO pDTO=new ProductDTO();
				
				pDTO.setPrdID(rs.getString("PRODUCT_ID"));
				pDTO.setPrdName(rs.getString("PRODUCT_NAME"));
				
				list.add(pDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.getInstance().dbClose(rs, pstmt, con);
		}
		
		
		return list;
	}
	
	//세일 상품
	public List<ProductDTO> selectSale(){
		
		List<ProductDTO> list=new ArrayList<>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBConnection.getInstance().getConn();
			
			String sql="SELECT PRODUCT_ID, PRODUCT_NAME FROM PRODUCT WHERE DISCOUNT > 0";
			
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				ProductDTO pDTO=new ProductDTO();
				
				pDTO.setPrdID(rs.getString("PRODUCT_ID"));
				pDTO.setPrdName(rs.getString("PRODUCT_NAME"));
				
				list.add(pDTO);
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			DBConnection.getInstance().dbClose(rs, pstmt, con);
		}
		
		return list;
	}
	
	
	public List<ProductDTO> selectCategory(RangeDTO range){

	    List<ProductDTO> list=new ArrayList<>();

	    Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;

	    try {
	        con=DBConnection.getInstance().getConn();

	        String sql =
	        "SELECT * FROM ( " +
	        "    SELECT A.*, ROWNUM RN FROM ( " +
	        "        SELECT PRODUCT_ID, PRODUCT_NAME, PRICE " +
	        "        FROM PRODUCT " +
	        "        WHERE " + range.getSearchType() + " LIKE ? " +
	        "        ORDER BY PRODUCT_ID DESC " +
	        "    ) A " +
	        ") WHERE RN BETWEEN ? AND ?";

	        pstmt = con.prepareStatement(sql);

	        pstmt.setString(1, "%" + range.getKeyword() + "%");
	        pstmt.setInt(2, range.getStartNum());
	        pstmt.setInt(3, range.getEndNum());

	        rs = pstmt.executeQuery();

	        while(rs.next()){
	            ProductDTO pDTO=new ProductDTO();

	            pDTO.setPrdID(rs.getString("PRODUCT_ID"));
	            pDTO.setPrdName(rs.getString("PRODUCT_NAME"));
	            pDTO.setPrice(rs.getInt("PRICE"));

	            list.add(pDTO);
	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }finally {
	        DBConnection.getInstance().dbClose(rs, pstmt, con);
	    }

	    return list;
	}
	
	//카테고리에 맞는 상품 전체수
	public int selectTotalCount(RangeDTO range) {
		
		int count=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBConnection.getInstance().getConn();
		
			String sql =
					"SELECT COUNT(*) FROM PRODUCT WHERE " + range.getSearchType() + " LIKE ?";
		
		pstmt=con.prepareStatement(sql);
		
		pstmt.setString(1, "%" + range.getKeyword() + "%");

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.getInstance().dbClose(rs, pstmt, con);
	    }

	    return count;
	}

	
}
