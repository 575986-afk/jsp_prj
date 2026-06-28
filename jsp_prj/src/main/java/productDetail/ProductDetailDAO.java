package productDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;
import kr.co.sist.dao.GetConnection;

public class ProductDetailDAO {

private static ProductDetailDAO pdDAO;
	
	private ProductDetailDAO() {
		
	}
	
	public static ProductDetailDAO getInstance() {
		if(pdDAO==null) {
			pdDAO=new ProductDetailDAO();
		}
		return pdDAO;
	}
	//상품 기본 정보 조회
	public ProductDTO selectProductInfo(String prdID) {
		
		ProductDTO pDTO = null;

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    GetConnection gc=GetConnection.getInstance();
	    
	    try {
	    	
	    	con=gc.getConn("dbcp");

	        StringBuilder sql = new StringBuilder();

	        sql.append(" SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_TYPE, ");
	        sql.append(" PRICE, DISCOUNT, MANUFACTURER, ORIGIN ");
	        sql.append(" FROM PRODUCT ");
	        sql.append(" WHERE PRODUCT_ID = ? ");

	        pstmt = con.prepareStatement(sql.toString());
	        pstmt.setString(1, prdID);

	        rs = pstmt.executeQuery();

	        if(rs.next()) {

	        	pDTO = new ProductDTO();

	        	pDTO.setPrdID(rs.getString("PRODUCT_ID"));
	        	pDTO.setPrdName(rs.getString("PRODUCT_NAME"));
	        	pDTO.setPrdType(rs.getString("PRODUCT_TYPE"));
	        	pDTO.setPrice(rs.getInt("PRICE"));
	        	pDTO.setDiscount(rs.getInt("DISCOUNT"));
	        	pDTO.setManufacturer(rs.getString("MANUFACTURER"));
	        	pDTO.setOrigin(rs.getString("ORIGIN"));

	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.getInstance().dbClose(rs, pstmt, con);
	    }

	    return pDTO;
		
	}
	//상품 상세 설명 및 내용 조회
	public ProductDTO selectProductDetail(String prdID) {
		
		ProductDTO pDTO = null;

	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    GetConnection gc=GetConnection.getInstance();
	    
	    try {
	    	
	    	con=gc.getConn("dbcp");

	        StringBuilder sql = new StringBuilder();

	        sql.append(" SELECT DESCRIPTION, MANUFACTURER, ORIGIN,UNDERAGE_PURCHASE,WEIGHT, EXPIRATION_DATE,STORAGE_TYPE,MIN_PURCHASE, MAX_PURCHASE,PRODUCT_INPUT_DATE,CATEGORY_ID ");
	        sql.append(" FROM PRODUCT ");
	        sql.append(" WHERE PRODUCT_ID = ? ");

	        pstmt = con.prepareStatement(sql.toString());
	        pstmt.setString(1, prdID);

	        rs = pstmt.executeQuery();

	        if(rs.next()) {

	        	pDTO = new ProductDTO();

	        	pDTO.setDescription(rs.getString("DESCRIPTION"));
	        	pDTO.setManufacturer(rs.getString("MANUFACTURER"));
	        	pDTO.setOrigin(rs.getString("ORIGIN"));
	        	pDTO.setUnderagePurchase(rs.getInt("UNDERAGE_PURCHASE"));
	        	pDTO.setWeight(rs.getInt("WEIGHT"));
	        	pDTO.setExpirationDate(rs.getDate("EXPIRATION_DATE"));
	        	pDTO.setStorageType(rs.getString("storageType"));
	        	pDTO.setMinPurchase(rs.getInt("MIN_PURCHASE"));
	        	pDTO.setMaxPurchase(rs.getInt("MAX_PURCHASE"));
	        	pDTO.setProductInputDate(rs.getDate("PRODUCT_INPUT_DATE"));

	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBConnection.getInstance().dbClose(rs, pstmt, con);
	    }

	    return pDTO;
		
	}
}
