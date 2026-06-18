package usermain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import productDetail.ProductDTO;

class UsermainDAOTest {
	
	UserMainService service=new UserMainService();
	
	@Test
	@DisplayName("베스트 상품 조회 테스트")
	void testBest() {

	    List<ProductDTO> list = service.searchBest();

	    assertNotNull(list); // null이면 실패

	    System.out.println("개수: " + list.size());

	    for(ProductDTO p : list) {
	        System.out.println(p.getPrdID() + " / " + p.getPrdName());
	    }
	}
	
	@Test
	@DisplayName("세일 상품 조회 테스트")
	void testSale() {

	    List<ProductDTO> list = service.searchSale();

	    assertNotNull(list);

	    System.out.println("개수: " + list.size());

	    for(ProductDTO p : list) {
	        System.out.println(p.getPrdID() + " / " + p.getPrdName());
	    }
	}
	
	@Test
	@DisplayName("카테고리 + 페이징 테스트")
	void testCategory() {

	    RangeDTO range = new RangeDTO();
	    range.setSearchType("PRODUCT_NAME");
	    range.setKeyword("토마토");

	    int page = 1;

	    List<ProductDTO> list = service.getCategory(range, page);

	    assertNotNull(list);

	    System.out.println("결과 개수: " + list.size());

	    for(ProductDTO p : list) {
	        System.out.println(p.getPrdID() + " / " + p.getPrdName());
	    }
	}
}
