'use strict'

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
	var fc = document.querySelector('#favoriteContainer');
	var state = fc.getAttribute('is_favorite');  // -> 문자열상태!
	console.log(state); // 좋아요 안누른 상태 -> 0이 나온다(기본값)
	
	var state = 1 - state;
	
	// get 방식으로 통신
	axios.get('/board/ajaxFavorite.korea', {
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
			fc.innerHTML = `<i class="${iconClass} fa-heart"></i>`;
			fc.setAttribute('is_favorite', state)
		} else {
			alert('에러가 발생하였습니다.')
		}
	}).catch(function(err) { //통신 실패
		console.err('err 발생 : ' + err)
	});
}

// 댓글쓰기 - ajax 이용
var cmtFrmElem = document.querySelector('#cmtFrm')
if(cmtFrmElem) { // -> cmtFrmEle != undefined
	
	cmtFrmElem.onsubmit = function(e) {
		e.preventDefault()
	}

	var ctntElem = cmtFrmElem.ctnt
	var i_board = ctntElem.dataset.id
	var btnElem = cmtFrmElem.btn
	
	btnElem.addEventListener('click', ajax)
		
	function ajax () {
		var param = {
			i_board,
			ctnt: ctntElem.value
		}
		console.log(param)
		
		fetch('/board/insCmt', {
			method: 'POST',
			headers: {
            	'Content-Type': 'application/json'
			},
			body: JSON.stringify(param) // 객체를 문자열(JSON) 형태로 변환한다
		}).then(function(res) {
			return res.json()  // promise 객체 리턴
		}).then(function(data) {
			proc(data)
		})
	}
	
	getCmtList(i_board) // 댓글목록읽어오기
	
	ctntElem.onkeyup = function(e) {
		if(e.keybcode == 13) {
			ajax()
		}
	}
}

function proc (data) {
	switch(data.result) {
		case 0 :
			alert('댓글작성에 실패하였습니다.')
		return
		case 1 : 
			ctntEle.value = ''
		return 
	}
}
//--------------------------------------

// 댓글읽기
var cmtList = document.querySelector('#cmtList')
var cmtObj = null;
if(cmtList) {
	cmtObj = {
		createCmtTable : function() {
			var table = document.createElement('table')
			table.innerHTML = 
			`<tr>					
			<th>내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>비고</th>					
			</tr>`
		
			return table
		},
		getCmtList : function (i_board) {
			fetch(`/board/cmtList?i_board=${i_board}`)
			.then(function (res) {
				return res.json()
			})
			.then(function (list) {
				console.log(list)
			})
		}
	}
}
//------------------------------------------------------------