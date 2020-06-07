/**
@author 유태희
@since 2020.05.23
 	이 JS는 관리자 로그인 사용할 JavaScript 입니다
*/

 
function join(){

		let sid = $('#id').val();
		let spw = $('#pw').val();
		if(!sid){
			alert('아이디를 입력하세요');
			$('#id').focus();
			return;
		}else if(!spw){
			alert('비밀번호를 입력하세요');
			$('#pw').focus();
			return;
		}
		
		let idck = idchk(sid);
		let pwck = pwchk(spw);
		if(idck == false || pwck == false){
			alert('아이디 또는 비밀번호가 올바르지 않습니다');
			$('#id').focus();
			return;
		}
		
		
		$('#frm').submit();

		alert(cnt);
/*		$.ajax({
			url: '/YummyMap/admin/login.ck',
			type: 'post',
			dataType: 'json',
			data: {
				'id' : sid,
				'pw' : spw
			},
			success: function(data) {
				let result = data.cnt;
				if(result == 0){
					$('#id').val('');
					$('#pw').val('');
					$('#id').focus();
					$('#idck').removeClass('d-none');
				}else{
					$('#frm').submit();
				}
			},
			error: function(){
				alert('통신 실패');
			}
		});*/
}
function idchk(id) {
	let pett = /^([A-Za-z]){1}\w{4,12}$/;
	let ok = pett.test(id);
	return ok;
}

function pwchk(pw) {
	let patt = /^([a-z0-9!@#$%^&*-+_]){1}(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*-=])[a-zA-Z0-9!@#$%^&*-+_]{7,12}$/i;
	let pwok = patt.test(pw);
	return pwok;
}

$(function(){
	
	$('#join').click(join);
	
	
	$('#id').keydown(function(key) {
		if (key.keyCode == 13) {
			$('#pw').focus();
			return;
		}
	});
	
	$('#pw').keydown(function(key) {
		if (key.keyCode == 13) {
			join();
		}
	});


	$('#sbtn').click(function(){
		$('#staticBackdrop').modal();
	});
	
	$('#sendbtn').click(function(){
		$('#frm2').submit();
	});
		
	
});