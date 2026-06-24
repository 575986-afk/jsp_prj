package findPw;

import signup.ClientDTO;

public class FindPwService {

	private FindPwDAO fPWDAO;
	
	public FindPwService() {
		fPWDAO=FindPwDAO.getInstance();
	}
	//회원 아이디와 이메일 정보로 비밀번호 존재여부 확인
	public boolean findPassword(String clientId, String clientEmail) {
		return fPWDAO.selectClientPW(clientId, clientEmail)!=null;
	}
	//비밀번호 변경
	public boolean resetPassword(String clientId, String newPw) {
		return fPWDAO.updateClientPw(clientId, newPw)>0;
	}
}
