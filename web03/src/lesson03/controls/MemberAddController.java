package lesson03.controls;

import java.util.Map;

import lesson03.dao.MemberDao;
import lesson03.vo.Member;

public class MemberAddController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		if ( model.get("member") == null ){ //입력폼을 요청할 때
			return "/member/MemberForm.jsp";
		} else { // 회원등록을 요청할 떄
			MemberDao memberDao = (MemberDao)model.get("memberDao");
			
			Member member = (Member)model.get("member");
			memberDao.insert(member);
			
			return "redirect:list.do";
		}
	}
}
