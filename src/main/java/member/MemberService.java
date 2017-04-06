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

	public Member update(Member member){
		return DAO.update(member);
	}
	public Member insert(Member member){
		return DAO.insert(member);
	}
	
}
