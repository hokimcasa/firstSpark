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

	@Override
	public Member update(Member member) {
		MemberInnerDao Dao = new MemberInnerDao();
		Member updateMember = Dao.getOne(member.getId());
		int index = Dao.getAll().indexOf(updateMember);
		updateMember.setName(member.getName());
		updateMember.setFee(member.getFee());
		updateMember.setMobileNO(member.getMobileNO());
		updateMember.setAccountNo(member.getAccountNo());
		updateMember.setEmail(member.getEmail());
		updateMember.setWebSite(member.getWebSite());
		updateMember.setAddress(member.getAddress());
		updateMember.setUpdateUser(member.getUpdateUser());
		updateMember.setLastUpdate(member.getLastUpdate());
		Dao.getAll().set(index, updateMember);
		return updateMember;
	}

	@Override
	public Member insert(Member member) {
		new MemberInnerDao().getAll().add(member);
		return member;
	}

}
