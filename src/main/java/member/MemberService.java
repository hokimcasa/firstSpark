package member;

import java.util.List;

public class MemberService {
	private MemberDAO_interface DAO = new MemberInnerDao();
	
	public List<Member> getAll(){
		return DAO.getAll();
	}

	public Member getOne(String id){
		return DAO.getOne(id);
	}

}
