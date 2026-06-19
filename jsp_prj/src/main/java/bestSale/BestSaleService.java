package bestSale;

import java.util.List;

import productDetail.ProductDTO;

public class BestSaleService {

	private BestSaleDAO bsDAO;
	
	public BestSaleService() {
		bsDAO=BestSaleDAO.getInstance();
	}
	
	//베스트 랭킹 목록 조회
	public List<ProductDTO> searchBest(String categoryName, int minPrice, int maxPrice){
		return bsDAO.bestProduct(categoryName,minPrice,maxPrice);
	}
	//인기급상승 목록 조회
	public List<ProductDTO> searchRising(String categoryName, int minPrice, int maxPrice){
		return bsDAO.risingProduct(categoryName, minPrice, maxPrice);
	}
	//알뜰쇼핑 목록조회
	public List<ProductDTO> searchEconomy(String categoryName, int minPrice, int maxPrice){
		return bsDAO.economyProduct(categoryName, minPrice, maxPrice);
	}
	//반값세일 목록조회
	public List<ProductDTO> searchHalfSale(String categoryName, int minPrice, int maxPrice){
		return bsDAO.halfSaleProduct(categoryName, minPrice, maxPrice);
	}
	//베스트 랭킹 상품 총 개수
	public int getTotalBestCount(String categoryName, int minPrice, int maxPrice) {
		return bsDAO.getTotalBestCount(categoryName, minPrice, maxPrice);
	}
	//인기 급상승 상품 총개수
	public int getTotalRisingCount(String categoryName, int minPrice, int maxPrice) {
		return bsDAO.getTotalRisingCount(categoryName, minPrice, maxPrice);
	}
	//알뜰쇼핑 상품 총개수
	public int getTotalEconomyCount(String categoryName, int minPrice, int maxPrice) {
		return bsDAO.getTotalEconomyCount(categoryName, minPrice, maxPrice);
	}
	//반값세일 상품 총개수
	public int getTotalHalfSaleCount(String categoryName, int minPrice, int maxPrice) {
		return bsDAO.getTotalHalfSaleCount(categoryName, minPrice, maxPrice);
	}
	
	
	
}
