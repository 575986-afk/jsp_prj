package deliveryChg;

import java.util.List;

public class DeliveryChgService {

	private DeliveryChgDAO dDAO;
	
	public DeliveryChgService() {
		dDAO=DeliveryChgDAO.getInstance();
	}
	 // 회원별 배송지 목록 조회
    public List<DeliveryDTO> getDeliveryList(String clientNo) {
        return dDAO.selectDeliveryList(clientNo);
    }

    // 신규 배송지 등록
    public boolean addDelivery(DeliveryDTO dDTO) {
        return dDAO.insertNewDelivery(dDTO) == 1;
    }

    // 배송지 삭제
    public boolean deleteDelivery(String clientNo, String deliveryId) {

        DeliveryDTO dDTO = new DeliveryDTO();
        dDTO.setClientNo(clientNo);
        dDTO.setDeliveryID(deliveryId);

        return dDAO.removeDelivery(dDTO) == 1;
    }
	
}
