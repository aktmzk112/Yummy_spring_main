/**
@author 유태희
@since 2020.05.23
 	이 JS는 관리자 회원관리 페이지에 사용할 JavaScript 입니다
*/

function search(){
		let sel =	$('#opt').val();
		let scname = $('#search-input').val();
		if(sel == 'nos'){
			alert('검색 조건을 먼저 선택하세요');
			return;
		}
		if(sel != 'nos' && !scname){
			alert('검색어를 입력하세요');
			return;
		}
		
		$('#searchs').val(scname);
		$('#opts').val(sel);
		$('#frm').attr('action','/YummyMap/admin/main.mmy');
		$('#frm').submit();
	}

$(function(){
	let mno;
	$('.obtn').click(function(){
		let no = $(this).parents().parents().attr('id');
		let sel =	$('#opt').val();
		let scname = $('#search-input').val();
		$('#searchs').val(scname);
		$('#opts').val(sel);
		$('#no').val(no);
		$('#frm').attr('action','/YummyMap/admin/remember.mmy');
		$('#frm').submit();
	});
	
	$('.pbtn').click(function(){
		let page = $(this).text();
		let sel =	$('#opt').val();
		let scname = $('#search-input').val();
		$('#searchs').val(scname);
		$('#opts').val(sel);
		$('#nowpage').val(page);
		$('#frm').attr('action','/YummyMap/admin/main.mmy');
		$('#frm').submit();
		
	});
	
	$('.next').click(function(){
		let page = $(this).attr('id');
		let sel =	$('#opt').val();
		let scname = $('#search-input').val();
		$('#searchs').val(scname);
		$('#opts').val(sel);
		$('#nowpage').val(page);
		$('#frm').attr('action','/YummyMap/admin/main.mmy');
		$('#frm').submit();
	});
	
	$('.pre').click(function(){
		let page = $(this).attr('id');
		let sel =	$('#opt').val();
		let scname = $('#search-input').val();
		$('#searchs').val(scname);
		$('#opts').val(sel);
		$('#nowpage').val(page);
		$('#frm').attr('action','/YummyMap/admin/main.mmy');
		$('#frm').submit();
	});
	
	
	$('#sbtn').click(search);
	$('#search-input').keydown(function(key){
		if(key.keyCode == 13){
			search();
			return;
		}
	});
	
	$('.rbtn').click(function(){
		mno = $(this).parents().parents().attr('id');
		$('#no').val(no);
		let id = $(this).parents().prevAll('.mid').text();
		$('.modal-body').html('<b>'+id+'</b> 해당 계정을 정말 삭제 하시겠습니까? ');
	});
	
	$('#rmbtn').click(function(){
		$('#no').val(mno);
		$('#frm').attr('action', '/YummyMap/admin/delmemberProc.mmy');
		$('#frm').submit();
	});
	$('#sid').hover(function(){
		$('#idinfo').attr('display','');
	},function(){
		$('#idinfo').attr('display','none');
	});
});