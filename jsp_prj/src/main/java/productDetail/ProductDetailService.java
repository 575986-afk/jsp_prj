package productDetail;

public class ProductDetailService {
	
	private ProductDetailDAO pddDAO;
	
	public ProductDetailService() {
		pddDAO=ProductDetailDAO.getInstance();
	}
	
	//상품 ID로 상품 기본 정보 조회
	public ProductDTO getProductInfo(String prdID) {
		return pddDAO.selectProductInfo(prdID);
	}
	//상품 ID로 상품 상세 정보 조회
	public ProductDTO getProductDetail(String prdID) {
		return pddDAO.selectProductDetail(prdID);
		
	}
	
}
