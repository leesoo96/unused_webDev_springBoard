'use strict'
// js 코드수정!!!!!!!!!!!!
//글 제목 클릭
function clkArticle(i_board) {		
	var url = `/board/detail?i_board=${i_board}`;
	location.href = url; //주소값 이동
}

//삭제 버튼 클릭 
function clkDel(i_board, typ) {
	if(confirm('삭제 하시겠습니까?')) {
		fetch(`/board/del/${i_board}`, {
			method: 'delete'
		})
		.then(function(res) {			
			return res.json(); // 꼭 리턴해야함
		}).then(function(myJson) {
		    console.log(myJson);

			if(myJson.result === 1) { 
				alert('삭제가 완료되었습니다.');
				location.href = `/board/list?typ=${typ}`;
			} 
			else { 
				alert("삭제 실패하였습니다.");
			}
		});
	}
}

//댓글에서 수정버튼 클릭 
function clkCmtMod(i_cmt) {
	var trForm = document.querySelector('#mod_' + i_cmt);
	trForm.classList.remove('cmd_mod_form');
	console.log(trForm);
}

function clkCmtClose(i_cmt) {
	var trForm = document.querySelector('#mod_' + i_cmt);
	trForm.classList.add('cmd_mod_form');
}

// Ajax통신 사용
// 좋아요 기능 처리(첫번째)
function toggleFavorite (i_board) {
	var div_favorite = document.querySelector('#favoriteContainer');
	var state = div_favorite.getAttribute('is_favorite');  // -> 문자열상태!
	console.log(state); // 좋아요 안누른 상태 -> 0이 나온다(기본값)
	
	var state = 1 - state;
	
	// get 방식으로 통신
	axios.get('/board/ajaxFavorite', {
		params: {
			// state 값이 1이면 좋아요안누름 0이면 좋아요누름 
			'state': state,
			// t_board의 i_board => 게시물 번호(PK)
			'i_board': i_board
		}	
	}).then(function (res) { //통신 성공
		console.log(res);
		
		/*	data:
	      result: 1 - 콘솔창에서 확인완료
		*/
		if(res.data.result == 1) {
			var iconClass = state == 1 ? 'fas' : 'far';
			div_favorite.innerHTML = `<i class="${iconClass} fa-heart"></i>`;
			div_favorite.setAttribute('is_favorite', state)
		} else {
			alert('에러가 발생하였습니다.')
		}
	}).catch(function(err) { //통신 실패
		console.err('err 발생 : ' + err)
	});
}

// 댓글삭제
function delCmt(i_board) {
	console.log(i_board)
	
	fetch(`/board/delCmt?i_cmt=${i_cmt}`, {
		method: 'delete'
	})
	.then(function (res) {
		return res.json()
	})
	.then(function (myJson) {
		switch(myJson.result) {
			case 1: 
				cmtObj.getCmtList()
				return
			case 0:
				alert('댓글 삭제 실패')
				return			
		}
	})
}

// 댓글쓰기 - ajax 이용
var cmtObj = {
	i_board = 0,
	createCmtTable: function() {
		var tableElem = document.createElement('table')
		tableElem.innerHTML = 
		`<tr>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>비고</th>
		</tr>`			
		return tableElem
	},
	
	getCmtList: function() {
		if(this.i_board === 0){
			return 
		}
		
		fetch(`/board/cmtList?i_board=${this.i_board}`)
			.then(function(res) {
				return res.json()
			})
			.then((list) => {
				cmtListElem.innerHTML = ''
				this.proc(list)
			})
	},
	
	createRecode: function(item) {
		var etc = ''
		if(item.is_mycmt === 1) {
			etc = `<button>수정</button> 
					   <button onclick=delCmt(${item.i_cmt})>삭제</button>`
		}
		var tr = document.createElement('tr')
		tr.innerHTML = `
			<td>${item.ctnt}</td>
			<td>${item.user_nm}</td>
			<td>${item.r_dt}</td>
			<td>etc</td>
		`
		return tr
	},
	proc: function(list) {
		if(list.length == 0){
			return 
		}
		
		var table = this.createCmtTable()
		for(var i = 0; i < list.length; i++) {
			var recode = this.createRecode(list[i])
			table.append(recode)
		}
		console.log(list)
		cmtListElem.append(table)
	}	
}

// 댓글 리스트 
var cmtListElem = document.querySelector('#cmtList')
if(cmtListElem) {
	var i_board = document.querySelector('#i_board').dataset.id
	cmtObj.i_board = i_board
	cmtObj.getCmtList(i_board)
}


//댓글 달기
var cmtFrmElem = document.querySelector('#cmtFrm')
if(cmtFrmElem) {	
	cmtFrmElem.onsubmit = function(e) {
		e.preventDefault()
	}

	var ctntElem = cmtFrmElem.ctnt
	var btnElem = cmtFrmElem.btn	
	var i_board = document.querySelector('#i_board').dataset.id

	ctntElem.onkeyup = function(e) {
		console.log(e.keyCode)
		if(e.keyCode === 13) {
			ajax()
		}
	}	
	btnElem.addEventListener('click', ajax)
		
	function ajax () {		
		if(ctntElem.value === '') {
			return
		}
				
		var param = {
			i_board: i_board,
			ctnt: ctnt,
		}
	
		console.log(param)
		fetch('/board/insCmt', {
			method: 'POST',
			headers: {
            	'Content-Type': 'application/json'
			},
			body: JSON.stringify(param)
		}).then(function(res) {
			return res.json()
		}).then(function(data) {
			proc(data)
		})
	}
	
	function proc (data) {
		switch(data.result){
			case 0:
				alert('댓글 작성 실패하였습니다')
			return
			case 1:
				ctntElem.value = ''
				cmgObj.getCmtList(i_board)
			return
		}
	}
}