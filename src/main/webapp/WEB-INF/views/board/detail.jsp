<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div>
	<a href="/board/list?typ=${requestScope.data.typ}">���ư���</a>
	
	<c:if test="${data.i_user == loginUser.i_user}">
		<button onclick="clkDel(${requestScope.data.i_board}, ${requestScope.data.typ});">����</button>
		<a href="/board/mod?typ=${requestScope.data.typ}&i_board=${requestScope.data.i_board}">
			<button>����</button>
		</a>
	</c:if>
	<div>
		<div>��ȣ : ${requestScope.data.seq}</div>
		<div>��ȸ�� : ${requestScope.data.hits}</div>
		<div>
			�̸� :
			<c:if test="${data.profile_img == null}">
				<div class="circular--landscape circular--size40">
					<img id="profileImg" src="/res/img/basic_profile.jpg">
				</div>				
			</c:if>
			<c:if test="${data.profile_img != null}">
				<div class="circular--landscape circular--size40">
					<img id="profileImg" src="/res/img/${data.i_user}/${data.profile_img}">
				</div>
			</c:if>
			<span class="profile-td-nm">${data.writer_nm}</span>
		</div>
		<div>���� : ${data.title}</div>
		<div>�ۼ��� : ${data.r_dt}</div>
		<div>
			${data.ctnt}
		</div>
	</div>
	<div style="margin-top: 20px;">
		<span id="i_board" data-id="${requestScope.data.i_board }"></span>
		
		<c:if test="${loginUser != null}">
		<div>
			<form id="cmtFrm">
				���: <input type="text" name="ctnt">
				<input type="button" name="btn" value="��۾���">
			</form>
		</div>	
		</c:if>
		
		<div id="cmtList"></div>
	</div>

	<c:if test="${loginUser != null}">
	<div id="favoriteContainer" is_favorite="${data.is_favorite}"
		 onclick="toggleFavorite(${data.i_board});">	
		<c:choose>
			<c:when test="${data.is_favorite == 1}">
				<i class="fas fa-heart"></i>
			</c:when>
			<c:otherwise>
				<i class="far fa-heart"></i>
			</c:otherwise>
		</c:choose>		
	</div>
	</c:if>
