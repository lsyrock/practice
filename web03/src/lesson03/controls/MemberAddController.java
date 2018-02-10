package lesson03.controls;

import java.util.Map;

import lesson03.annotation.Component;
import lesson03.bind.DataBinding;
import lesson03.dao.MemberDao;
import lesson03.vo.Member;

@Component("MemberAddController")
public class MemberAddController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberAddController setMemberDao(MemberDao memberDao){
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[]{
			"member", lesson03.vo.Member.class	
		};		
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		
		if ( member.getEmail() == null ){ //입력폼을 요청할 때
			return "/member/MemberForm.jsp";
		} else { // 회원등록을 요청할 떄
			memberDao.insert(member);			
			return "redirect:list.do";
		}
	}

}
