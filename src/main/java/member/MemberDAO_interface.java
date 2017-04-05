package member;

import java.util.List;

public interface MemberDAO_interface {
	public List<Member> getAll();
	public Member getOne(String id);
}
