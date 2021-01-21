<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<ul>
		<c:if test="${loginUser == null}">
			<li><a href="/user/login">로그인</a></li>
		</c:if>

		<c:if test="${loginUser != null}">
			<li>${loginUser.nm }님, 환영합니다!</li>
			<li><a href="/user/logout">로그아웃</a></li>
		</c:if>

		<!-- 게시판 목록 표출 -->
		<c:forEach items="${menus}" var="item">
			<li class="${item.typ == param.typ ? 'selectedMenu' : ''}"><a
				href="/board/list?typ=${item.typ }"> ${item.nm } </a></li>
		</c:forEach>

		<c:if test="${loginUser != null }">
			<li><a href="/user/profile">프로필</a></li>
			<li><a href="/user/changePw">비밀번호 변경</a></li>
		</c:if>
	</ul>
</header>