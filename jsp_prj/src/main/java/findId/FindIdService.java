package findId;

import signup.ClientDTO;

public class FindIdService {
	
	private FindIdDAO fiDAO;
	
	public FindIdService() {
		fiDAO=FindIdDAO.getInstance();
	}
	
	public ClientDTO findId(String clientName, String clientEmail) {
		return fiDAO.selectClientId(clientName, clientEmail);
	}
	
}
