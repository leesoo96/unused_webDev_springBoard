'use strict'

// 글 번호 클릭 시  해당 url로 이동
function clickArticle(i_board){
		var url = `/board/detail?i_board=${i_board}`;
		location.href = url; 
}

//  글 삭제 - fetch API 사용
function clickDel(i_board, typ){
	if(confirm('정말 삭제하시겠습니까?')){
		fetch(`/board/del/${i_board}`)
		.then(function(res){
			return res.json(); // 꼭 리턴해야함
		})
		.then(function(myJson) {
			console.log(myJson);
			
			if(myJson.result === 1){
				alert('삭제가 완료되었습니다.');
				location.href = `/board/list?typ=${typ}`;
			}else {
				alert('삭제 실패하였습니다.');
			}
		});
	}
}

// 댓글 수정버튼 클릭 
function cmtMod(i_cmt){
	var modFrm = document.querySelector('#mod_'+i_cmt);
	modFrm.classList.remove('cmt_mod_form');	
}
function cmtModClose(i_cmt){
	var modFrm = document.querySelector('#mod_'+i_cmt);
	modFrm.classList.add('cmt_mod_form');
}

// Ajax통신 사용
// 좋아요 기능 처리 1
function toggleFavorite (i_board) {
	var heart = document.querySelector('#favoriteFunc');
	var state = heart.getAttribute('is_favorite'); // 문자열상태임!
	console.log(state); // 좋아요 안누른 상태 -> 0이 나온다(기본값)
	state = 1 - state; 
	
	// get 방식으로 통신
	axios.get('/board/ajax_favorite.korea', {
		params: {
			// state 값이 1이면 좋아요안누름 0이면 좋아요 누름 
			state : state,
			// t_board의 i_board => 게시물 번호(PK)
			i_board : i_board
		}
	}).then(function (res) { 
		console.log(res); // 통신 성공
		
	/*	data:
	      result: 1 - 콘솔창에서 확인완료
	*/	if(res.data.result == 1){
			var iconClass = state == 1 ? 'fas' : 'far';
			heart.innerHTML = `<i class="${iconClass} fa-heart"></i>`;
			heart.setAttribute('is_favorite', state);
		}else {
			alert('에러가 발생하였습니다.');
		}
	}).catch(function(err){
		console.err('에러 발생!!' + err); // 통신 실패 
	});
}

// 댓글쓰기 - ajax 이용
var cmtFrmEle = document.querySelector('#cmtFrm'')
if(cmtFrmEle) { // -> cmtFrmEle != undefined
	var i_board = cmtFrmEle.dataset.id // data-id="${data.i_board }
	var ctntEle = cmtFrmEle.ctnt
	var btnEle = cmtFrmEle.btn
	
	btnEle.addEventListener('click', ajax)
	
	function ajax () {
		var param = {
			i_board : i_board,
			ctnt : ctntEle.value
		}
		
		console.log(`i_board = ${param.i_board}`)
		
		fetch('/board/insCmt', {
			method: 'POST',
			headers: {
				'Content-Type' : 'application/json'
			},
			body: JSON.stringify(param) // 객체를 문자열(JSON) 형태로 변환한다
		}).then(function (res) {
			return res.json() // promise 리턴
		}).then(function (myjson) {
			console.log(myjson)
		})
	}
}