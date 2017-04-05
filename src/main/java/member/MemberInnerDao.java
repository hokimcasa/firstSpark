package member;

import java.util.List;

public class MemberInnerDao implements MemberDAO_interface {
	@Override
	public List<Member> getAll() {
		MemberData memberList = new MemberData();
		return 	 memberList.getMemberList();
	}

	@Override
	public Member getOne(String id) {
		MemberInnerDao Dao = new MemberInnerDao();
		for (Member tmp : Dao.getAll()) {
			if(tmp.getId().equals(id)){
				return tmp;
			}		
		}
		return null;
	}

}
