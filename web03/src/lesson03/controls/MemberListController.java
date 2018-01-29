package lesson03.controls;

import java.util.Map;

import lesson03.dao.MemberDao;

public class MemberListController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		MemberDao memberDao = (MemberDao)model.get("memberDao");
		model.put("members", memberDao.selectList() );
		
		return "/member/MemberList.jsp";
	}
}
