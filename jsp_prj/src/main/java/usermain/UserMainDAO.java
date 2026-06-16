package usermain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			
			//con=DBConnection.getInstance();
			
			String sql="SELECT p.product_ID, p.product_name, SUM(od"
					+ "FROM product p "
					+ "INNER JOIN order_details on p.product_ID= "
					+ "ORDER BY sales_count DESC";
			
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
			//DBConnection.getInstance().dbClose(rs, pstmt, con);
		}
		
		return list;
	}
	
	//세일 상품
	public List<ProductDTO> selectSale(){
		
		List<ProductDTO> list=new ArrayList<>();
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
	//배너 광고 상품 목록
	public List<ProductDTO> selectBannerProducts() {
		List<ProductDTO> list=new ArrayList<>();
		return list;
	}
	
	//카테고리별 상품 조회
	public List<ProductDTO> selectCategory(RangeDTO range){
		List<ProductDTO> list=new ArrayList<>();
		return list;
	}
	
	//카테고리에 맞는 상품 전체수
	public int selectTotalCount(RangeDTO range) {
		
		int count=0;
		
		String sql="SELECT COUNT(*) FROM product WHERE category=?";
		
		return count;
	}

	
}
