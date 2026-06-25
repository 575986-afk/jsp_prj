package inquiry;

import java.util.ArrayList;
import java.util.List;

public class InquiryDAO {

	private static InquiryDAO iqDAO;
	
	private InquiryDAO() {
		
	}
	
	public InquiryDAO getInstance() {
		if(iqDAO==null) {
			iqDAO=new InquiryDAO();
		}
		return iqDAO;
	}
	//회원별 문의 목록 조회
	public List<InquiryDTO> selectList(){
		List<InquiryDTO> list=new ArrayList<InquiryDTO>();
		
		
		
		return list;
	}
}
