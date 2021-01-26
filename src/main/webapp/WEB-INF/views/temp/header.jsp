<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="menuList" />
<header>
	<ul>
		<c:if test="${loginUser == null}">
			<li><a href="/user/login">로그인</a></li>
		</c:if>

		<c:if test="${loginUser != null}">
			<li>${loginUser.nm }님, 환영합니다!</li>
			<li><a href="/user/logout">로그아웃</a></li>
		</c:if>

		<li><a href="/board/home">Home</a></li>

		<!-- 게시판 목록 표출 -->
		<!--  pageScope -> page객체에 바인딩된 데이터에 접근할 수 있다  -->
		<c:forEach items="${pageScope.menuList}" var="menu">
			<li> <a href="/board/list?typ=${menu.typ }">${menu.nm }</a></li>
		</c:forEach>

		<c:if test="${loginUser != null }">
			<li><a href="/user/profile">프로필</a></li>
			<li><a href="/user/changePw">비밀번호 변경</a></li>
		</c:if>
	</ul>
</header>
