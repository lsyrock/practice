<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>값 설정 방식</h3>
<c:set var="username1" value="홍길동"/>
<c:set var="username2" value="이수용"/>
1. ${username1}<br>
2. ${username2}<br><br>

<h3>기본 보관소 - page</h3>
3. ${pageScope.username1}<br>
4. ${requestScope.username1}<br><br>

<h3>보관소 지정 - scope</h3>
<c:set var="username3" scope="request">이수용</c:set>
5. ${pageScope.username3}<br>
6. ${requestScope.username3}<br><br>

<h3>객체의 프로퍼티 값 변경</h3>
<%!
public static class MyMember{
	int no;
	String name;
	
	public int getNo( ){
		return no;
	}
	public void setNo(int no){
		this.no = no;
	}
	public String getName( ){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
}
%>
<%
MyMember member = new MyMember();
member.setNo(100);
member.setName("홍길동");
pageContext.setAttribute("member", member);
%>
9. ${member.name}<br>
<c:set target="${member}" property="name" value="이수용"/>
10. ${member.name}<br><br>

<h3>보관소에 저장된 값 제거</h3>
<% pageContext.setAttribute("username1", "홍길동"); %>
11. ${username1}<br>
<c:remove var="username1"/>
12. ${username1 }<br><br>

<h3>c:if 태그 예제</h3>
<c:if test="${10 > 20}" var="result1">13. 10은 20보다 크다.</c:if><br>
14. ${result1}<br>

<c:if test="${10 < 20}" var="result2">14. 10은 20보다 크다.</c:if><br>
15. ${result2}<br>

<h3>c:choose 태그 예제</h3>
<c:set var="userid" value="이수용"/>
<c:choose>
	<c:when test="${userid == 'hong' }">홍길동님 감사합니다</c:when>
	<c:when test="${userid == '이수용' }">이수용님 감사합니다</c:when>
	<c:otherwise>등록되지 않은 사용자이니다.</c:otherwise>
</c:choose>
<br><br>


<h3>c:forEach 태그 예제</h3>
<% pageContext.setAttribute("nameList", new String[]{"홍길동", "이수용", "임꺽정"}); %>
<ul>
<c:forEach var="name" items="${nameList }"> 
	<li>${name}</li>
</c:forEach>
</ul>


<h3>c:forTokens 태그 예제</h3>
<% pageContext.setAttribute("tokens","v1=20&v2=30&op=+"); %>
<ul>
<c:forTokens var="item" items="${tokens}" delims="&">
	<li>${item}</li>
</c:forTokens>
</ul>


<h3>c:url 태그 예제</h3>
<c:url var="calcUrl" value="http://localhost:8080/web03/calc/Calculator.jsp">
	<c:param name="v1" value="20" />
	<c:param name="v2" value="30" />
	<c:param name="op" value="+" />
</c:url>
<a href="${calcUrl}">계산하기</a>
 
 
<h3>c:import 태그 예제</h3>
<textarea rows="10" cols="80">
	<c:import url="http://www.zdnet.co.kr/Include2/ZDNetKorea_News.xml"/>
</textarea>
 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<h3>fmt:parseDate 태그 예제</h3>
<fmt:parseDate var="date1" value="2018-01-27" pattern="yyyy-MM-dd"/>
Date : ${date1 }
 
<h3>fmt:formatDate 태그 예제</h3>
<fmt:formatDate value="${date1}" pattern="MM/dd/yy"/>
 
 
 
</body>
</html>