<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div>
		<div>
			<form action="/user/loginProc" method="post" id="frm">
				<div><input type="text" name="user_id" placeholder="아이디" value="${id}"></div>
				<div><input type="password" name="user_pw" placeholder="비밀번호"></div>
				<div><input type="submit" value="로그인"></div>
			</form>
			<div>${msg }</div>
			<a href="/user/join">회원가입</a>
		</div>
	</div>
