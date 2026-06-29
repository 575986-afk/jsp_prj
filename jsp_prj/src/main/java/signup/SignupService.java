package signup;

import java.security.MessageDigest;

public class SignupService {

	private SignupDAO sDAO;
	
	public SignupService() {
		sDAO=SignupDAO.getInstance();
	}
	
	//신규 회원 정보 등록(회원가입 성공 여부 반환)
    public boolean addUser(ClientDTO cDTO) {
    	
    	if (cDTO == null) {
			return false;
		}

    	if(checkDupId(cDTO.getClientId())) {
            return false;
        }

        cDTO.setClientHash(
                hashingPassword(cDTO.getClientHash()));

        return sDAO.insertClient(cDTO) == 1;
    }
    //아이디 중복 체크
    public boolean checkDupId(String id) {
    	
    	if(id==null&&id.trim().isEmpty()) {
    		return true;
    	}
    	
    	ClientDTO cDTO=new ClientDTO();
    	
    	cDTO.setClientId(id);
    	
        return sDAO.selectID(cDTO)>0;
    }
    //비밀번호 암호화 해시 처리
    public String hashingPassword(String pw) {
    	
    	if(pw ==null) {
    		return null;
    	}
        
        String hash = "";

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] bytes = md.digest(pw.getBytes());

            StringBuilder sb = new StringBuilder();

            for(byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            hash = sb.toString();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return hash;
    }
}
