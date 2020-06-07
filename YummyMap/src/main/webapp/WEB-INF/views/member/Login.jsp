<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
<link rel="stylesheet" href="/YummyMap/css/bootstrap.min.css">
<link rel="stylesheet" href="/YummyMap/css/nav.css">
<link rel="stylesheet" href="/YummyMap/css/member/login.css">
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script> -->
<script type="text/javascript" src="/YummyMap/js/jquery-3.5.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
</head>
<body>
<!-- nav 영역입니다-->
<div class="bg-white border-bottom fixed-top nav-body">
  <div class=" mt-2 nav-btn">
      <div class="member_nav text-right mt-3">
          <div class="ctQZg">
              <div class="_47KiJ">
                  <div class="XrOey"><a class="_0ZPOP kIKUG _4700r " href="#"><svg aria-label="활동 피드"
                              class="_8-yf5 " fill="#262626" height="24" viewBox="0 0 48 48" width="24">
                              <path
                                  d="M34.6 6.1c5.7 0 10.4 5.2 10.4 11.5 0 6.8-5.9 11-11.5 16S25 41.3 24 41.9c-1.1-.7-4.7-4-9.5-8.3-5.7-5-11.5-9.2-11.5-16C3 11.3 7.7 6.1 13.4 6.1c4.2 0 6.5 2 8.1 4.3 1.9 2.6 2.2 3.9 2.5 3.9.3 0 .6-1.3 2.5-3.9 1.6-2.3 3.9-4.3 8.1-4.3m0-3c-4.5 0-7.9 1.8-10.6 5.6-2.7-3.7-6.1-5.5-10.6-5.5C6 3.1 0 9.6 0 17.6c0 7.3 5.4 12 10.6 16.5.6.5 1.3 1.1 1.9 1.7l2.3 2c4.4 3.9 6.6 5.9 7.6 6.5.5.3 1.1.5 1.6.5.6 0 1.1-.2 1.6-.5 1-.6 2.8-2.2 7.8-6.8l2-1.8c.7-.6 1.3-1.2 2-1.7C42.7 29.6 48 25 48 17.6c0-8-6-14.5-13.4-14.5z">
                              </path>
                          </svg></a>
                      <div class="_0Sl_t">
                          <div class="poA5q" style="margin-left: -423px;"></div>
                      </div>
                  </div>
                  <div class="XrOey"><a href="/YummyMap/join/join.mmy" id="mypage"><svg aria-label="프로필" class="_8-yf5 " fill="#262626" height="24"
                              viewBox="0 0 48 48" width="24">
                              <path
                                  d="M24 26.7c7.4 0 13.4-6 13.4-13.4S31.4 0 24 0 10.6 6 10.6 13.4s6 13.3 13.4 13.3zM24 3c5.7 0 10.4 4.6 10.4 10.4S29.7 23.7 24 23.7s-10.4-4.6-10.4-10.4S18.3 3 24 3zm9.1 27.1H14.9c-7.4 0-13.4 6-13.4 13.4v3c0 .8.7 1.5 1.5 1.5s1.5-.7 1.5-1.5v-3c0-5.7 4.6-10.4 10.4-10.4h18.3c5.7 0 10.4 4.6 10.4 10.4v3c0 .8.7 1.5 1.5 1.5s1.5-.7 1.5-1.5v-3c-.1-7.4-6.1-13.4-13.5-13.4z">
                              </path>
                          </svg></a></div>
              </div>
          </div>
      </div>
  </div>
  <a class="navbar-brand tcolor logo" href="/YummyMap/main.mmy" >
      YUMMY MAP
  </a>
  <div class="b-subtitle text-left"><p class="pt-3">로그인</p></div>
  <div class=" nav-item-1 d-flex justify-content-end">
      <a class="navbar-brand nav-item-size" href="#" id="">
          <i class="fas fa-gamepad"></i>
      </a>
      <a class="navbar-brand nav-item-size" href="#">
          <i class="far fa-clipboard"></i>
      </a>
  </div>
</div>
<!-- nav 마지막 입니다-->

<!-- body 시작 입니다-->
  <div class="main-body">
    <div class="m-box mt-4">
      <form id="frm" action="/YummyMap/join/LoginProc.mmy" method="POST">
        <div class="form-group">
          <label for="id">I D</label>
          <input type="text" class="form-control" id="id" name="id">
        </div>
        <div class="form-group">
          <label for="pw">P W</label>
          <input type="password" class="form-control" id="pw" name="pw">
          <small id="msg1" class="text-danger dnone">입력하신 내용에 일치하는 계정이 없습니다.</small>
          <small id="msg2" class="text-danger dnone">입력이 완료되지 않았습니다.</small>
        </div>
        <button type="button" class="btn btn-primary loginbtn" id="loginbtn">로그인</button>
      </form>
      <div class="btn-box">
        <div class="optionbox d-flex mt-3">
          <a><p class="option-item-text" id="searchmem">아이디/비밀번호 찾기</p></a>
          <a href="/YummyMap/join/join.mmy" class="ml-5"><p class="option-item-text">회원가입</p></a>
          <a href="/YummyMap/main.mmy" class="ml-5"><p class="option-item-text">홈으로</p></a>
        </div>
      </div>
    </div>
  </div>
<!-- body 마지막 입니다 -->
<!-- 모달창 입니다 -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">회원 정보 찾기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body text-center">
       <input type="radio" name="memSrc" id="idSrc" value="id" checked="checked" > 아이디 찾기
       <input class="ml-2" type="radio" name="memSrc" id="passSrc" value="pass" > 비밀번호 찾기
      <form class="mt-3" action="/YummyMap/admin/mailProc.mmy" method="post" id="frm2" >
        <div> 사용자 이름 : <input type="txet" id="name" name="name"></div>
        <div class="mt-3"> 사용자 메일 : <input type="txet"  id="mail" name="mail"></div>
       </form>
        </div>
        <small class="d-none text-danger text-center" id="srchint">*입력하신 계정 정보가 없습니다</small>
       <div class="d-none text-center" id="okbox">
       	
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="mdbtn">취소</button>
        <button type="button" class="btn btn-danger" id="sendbtn">찾기</button>
        <button type="button" class="btn btn-danger d-none" data-dismiss="modal" id="cbtn">닫기</button>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
$(document).ready(function () {
	//로그인 이벤트처리 입니다.
  $('#loginbtn').click(function(){
    let sid = $('#id').val();
    let spw = $('#pw').val();
    if(!sid || !spw) {
      $('#msg2').show();
      return;
    }
    $('#msg2').hide();
    $('#frm').submit();
  });
	// 로그인시 실패시 msg를 출력해줍니다.
	let result = '${param.r}';
	if(result == 'x'){
		$('#msg1').show();
	}
	
	$('#searchmem').click(function(){
		$('#name').val('');	
		$('#mail').val('');
		$('#ids').val('');
		$('#mdbtn').removeClass('d-none');
		$('#sendbtn').removeClass('d-none');
		$('#cbtn').addClass('d-none');
		$('#srchint').addClass('d-none');
		$('#okbox').addClass('d-none');
		$('.modal-body').removeClass('d-none');
		$('#staticBackdrop').modal();
	});
	
	//라디오 버튼 클릭식 폼태그 변경
	$('input[name=memSrc]').click(function(){
		let sd = $(this).val();
		$('#srchint').addClass('d-none');
		if(sd == 'pass'){
			$('#frm2').html('<div> 사용자 아이디 : <input type="txet" id="ids" name="ids"></div>'+
			        		'<div class="mt-3"> 사용자 이메일 : <input type="txet"  id="mail" name="mail"></div>');		
			$('#sendbtn').text('발송');
		}else if(sd == 'id'){
			$('#frm2').html('<div> 사용자 이름 : <input type="txet" id="name" name="name"></div>'+
    						'<div class="mt-3"> 사용자 메일 : <input type="txet"  id="mail" name="mail"></div>');	
			$('#sendbtn').text('찾기');
		
		}
	})
	
	$('#sendbtn').click(function(){
		 let src = $('input[name=memSrc]:checked').val();
		 
		if(src == 'id'){
			let name = $('#name').val();	
			let mail = $('#mail').val();
			if(!name || !mail){
				alert('정보를 입력해 주세요!!');
				return;
			}
			
			$.ajax({
				url : '/YummyMap/join/idSearch.mmy',
				type: 'post',
				dataType:'json',
				data: {
					'name' : name,
					'mail' : mail
				},
				success: function(data){
					if(data.result == 'ok'){
						$('#mdbtn').addClass('d-none');
						$('#sendbtn').addClass('d-none');
						$('#cbtn').removeClass('d-none');
						$('#srchint').addClass('d-none');
						$('.modal-body').addClass('d-none');
						$('#okbox').html('<h3>고객님의 계정은 </h3> <h2>' + data.id + '</h2> <h3>입니다</h3>');
						$('#okbox').removeClass('d-none');
					}else if(data.result == 'no'){
						$('#srchint').removeClass('d-none');
					}
				},error : function(){
					alert('서버와의 통신에 오류가 생겼습니다. ');
				}
				
			});
		}else if(src == 'pass'){
			let id = $('#ids').val();	
			let mail = $('#mail').val();
			if(!id || !mail){
				alert('정보를 입력해 주세요!!');
				return;
			}
			
			$.ajax({
				url : '/YummyMap/join/passSearch.mmy',
				type: 'post',
				dataType:'json',
				data: {
					'id' : id,
					'mail' : mail
				},
				success: function(data){
					if(data.result == 'ok'){
						$('#mdbtn').addClass('d-none');
						$('#sendbtn').addClass('d-none');
						$('#srchint').addClass('d-none');
						$('#cbtn').removeClass('d-none');
						$('.modal-body').addClass('d-none');
						$('#okbox').html('<h4>고객님의 메일로 임시 패스워드가 발급 되었습니다. <h4>');
						$('#okbox').removeClass('d-none');
					}else if(data.result == 'no'){
						$('#srchint').removeClass('d-none');
					}
				},error : function(){
					alert('서버와의 통신에 오류가 생겼습니다. ');
				}
				
			});
		}
	});
	
	
});
</script>
</html>
</body>
</html>