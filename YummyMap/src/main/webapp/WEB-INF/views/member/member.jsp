<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My page</title>
<link rel="stylesheet" href="/YummyMap/css/member/user.css">
<link rel="stylesheet" href="/YummyMap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="/YummyMap/css/nav.css" />
<link rel="stylesheet" href="/YummyMap/css/member/member.css">
<script type="text/javascript" src="/YummyMap/js/jquery-3.5.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script type="text/javascript"src="/YummyMap/js/bootstrap.bundle.min.js"></script>
<style>
</style>
<script type="text/javascript">
    function pwchk(pw) {
        let patt = /(?=.*\d{1})(?=.*[~`!@#$%\^&*()-+=]{1})(?=.*[a-zA-Z]{2}).{8,15}$/;
        let pwok = patt.test(pw);
    		
        return pwok;
       }
	$(document).ready(function() {
	       
	    $('#sbtn').click(function() {
	      //패스워드 정규식
          let pw = $('#changePW_V').val();
          let pwck = pwchk(pw);
          
          if(!pwck){
            alert('숫자/특수문자/영문 대/소문자 1자리이상 포함한 8자리이상 15자리 이하입니다');
            return;
          }
          
          if ($('#changePW_V').val() != $('#change_PW_check_V').val()) {
				alert('비밀번호 확인이 일치하지 않습니다.');
				return;
			}
          
			$('#ff').submit();
			//$('#ff').submit();
		 //로그인 여부를 확인합니다.
		  
	    });
		  let userid = '${sid}';
		  if(!userid) {
			  $('#login').show();
			  $('#logout').hide();
			  $('#mypage').attr('href', '/YummyMap/join/join.mmy');
		  } else {
			  $('#login').hide();
			  $('#logout').show();
			  $('#mypage').attr('href', '/YummyMap/member/member.mmy');
		  }
		
		$('#ebtn').click(function() {
			$('#ee').css('display', 'none');
		});
		$('#ebtn').click(function() {
			$('#ff').css('display', '');
		});
		
		$('#mlink').click(function(){
			$("#staticBackdrop").modal();
		});
		
		$('#dbtn').click(function(){
				$(location).attr('href' , '/YummyMap/Drop/dropProc.mmy');
		});

		{
			let aa = $('#pwsck').val();
			if (aa == 'x') {
				alert('현재 비밀번호가 맞지 안습니다~~');
			}

		}
	});
</script>
</head>
<body>
	
	<!-- nav 영역입니다-->
    <div class="bg-white border-bottom fixed-top nav-body">
        <div class=" mt-2 nav-btn">
            <div class="member_nav text-right mt-3">
                <div class="ctQZg">
                    <div class="_47KiJ">
                        <div class="XrOey"><a href="/YummyMap/main/myList.mmy" class="_0ZPOP kIKUG _4700r " id="myListIcon"><svg aria-label="활동 피드"
                                    class="_8-yf5 " fill="#262626" height="24" viewBox="0 0 48 48" width="24">
                                    <path
                                        d="M34.6 6.1c5.7 0 10.4 5.2 10.4 11.5 0 6.8-5.9 11-11.5 16S25 41.3 24 41.9c-1.1-.7-4.7-4-9.5-8.3-5.7-5-11.5-9.2-11.5-16C3 11.3 7.7 6.1 13.4 6.1c4.2 0 6.5 2 8.1 4.3 1.9 2.6 2.2 3.9 2.5 3.9.3 0 .6-1.3 2.5-3.9 1.6-2.3 3.9-4.3 8.1-4.3m0-3c-4.5 0-7.9 1.8-10.6 5.6-2.7-3.7-6.1-5.5-10.6-5.5C6 3.1 0 9.6 0 17.6c0 7.3 5.4 12 10.6 16.5.6.5 1.3 1.1 1.9 1.7l2.3 2c4.4 3.9 6.6 5.9 7.6 6.5.5.3 1.1.5 1.6.5.6 0 1.1-.2 1.6-.5 1-.6 2.8-2.2 7.8-6.8l2-1.8c.7-.6 1.3-1.2 2-1.7C42.7 29.6 48 25 48 17.6c0-8-6-14.5-13.4-14.5z">
                                    </path>
                                </svg></a>
                            <div class="_0Sl_t">
                                <div class="poA5q" style="margin-left: -423px;"></div>
                            </div>
                        </div>
                        <div class="XrOey" id=""><a href="/YummyMap/member/member.mmy"" id="mypage"><svg aria-label="프로필" class="_8-yf5 " fill="#262626" height="24"
                                    viewBox="0 0 48 48" width="24">
                                    <path
                                        d="M24 26.7c7.4 0 13.4-6 13.4-13.4S31.4 0 24 0 10.6 6 10.6 13.4s6 13.3 13.4 13.3zM24 3c5.7 0 10.4 4.6 10.4 10.4S29.7 23.7 24 23.7s-10.4-4.6-10.4-10.4S18.3 3 24 3zm9.1 27.1H14.9c-7.4 0-13.4 6-13.4 13.4v3c0 .8.7 1.5 1.5 1.5s1.5-.7 1.5-1.5v-3c0-5.7 4.6-10.4 10.4-10.4h18.3c5.7 0 10.4 4.6 10.4 10.4v3c0 .8.7 1.5 1.5 1.5s1.5-.7 1.5-1.5v-3c-.1-7.4-6.1-13.4-13.5-13.4z">
                                    </path>
                                </svg></a></div>
                    </div>
                    <a href="/YummyMap/join/login.mmy" class="badge badge-light nav-item" id="login">LOGIN</a>
                    <a href="/YummyMap/join/logoutProc.mmy" class="badge badge-light nav-item" id="logout">LOGOUT</a>
                </div>
            </div>
        </div>
 <a class="navbar-brand tcolor logo" href="/YummyMap/main.mmy" id=""> YUMMY MAP </a>
		<div class="b-subtitle text-right">
			<p class="pt-3 text-left">마이페이지</p>
		</div>
  		<div class=" nav-item-1 d-flex justify-content-end">
            <a class="navbar-brand nav-item-size" href="/YummyMap/game/game.mmy" >
                <i class="fas fa-gamepad" id="game"></i>
            </a>
            <a class="navbar-brand nav-item-size" href="/YummyMap/board/boardMain.mmy">
                <i class="far fa-clipboard"></i>
            </a>
        </div>
    </div>
    </div>
    <!-- nav 마지막 입니다-->

	<!-- body 시작 입니다-->
	<div class="main-body por text-right">
		<div class="row">
			<div class="col-2 bor-bottom-top bor-right padd-right">
				<div class="padd-text mt-4">
					<a href="/YummyMap/member/member.mmy"
						style="color: black" class="font-weight-bold">회원정보
							변경</a>
				</div>
				<div class="padd-text">
					<a href="/YummyMap/member/txtList.mmy"
						style="color: black" class="font-weight-bold">내가 쓴글
							보기</a>
				</div>
				<div class="mar-top bor-bottom-or padd-text">
				<h6 type="button" style="color: black" class="font-weight-bold text-danger" data-toggle="modal" id="mlink">회원탈퇴</h6>
					<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog" >
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">회원탈퇴 </h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<p><h4 class="text-center">정말로 탈퇴 하시겠습니까?</h4></h4></p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">취소</button>
									<button type="button" class="btn btn-primary" id="dbtn">예</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-9 text-center bor-bottom-top bor-bottom-or">
				<div class="mt-3">
					<h3>회원정보 변경</h3>
				</div>
				<div class="container">
					<div class="row mt-5 ">
						<div class="col border d-flex align-items-center">
							<div class="col ">
								<div class="d-flex text-left padd-left ">
									<h6>사용자 이름 :</h6>
									<p class="pl-2">${memberInfo.mname}</p>
								</div>
								<div class="d-flex text-left padd-left ">
									<h6>아이디 :</h6>
									<p class="pl-5">${memberInfo.mid}</p>
								</div>
							</div>
						</div>
						<div class="col h90 border" id="ctag">
							<div id="ee">
								<span>비밀번호 변경시 <br>수정버튼을 눌러주세요
								</span>
								<div class="por-button">
									<button type="button" class="btn btn-outline-dark" id="ebtn">수정</button>
								</div>
							</div>
							<form action="/YummyMap/member/passProc.mmy" id="ff" method="post" style="display: none;" class="mt-2">
									<input type="hidden" id="pwsck" value="${param.ck }">
								<div>
									<div>
										<input type="password" style="width: 270px;"
											placeholder="현재비밀번호" name="nowPW" id="nowPW">
									</div>
									<div>
										<input type="password" style="width: 270px;"
											placeholder="변경비밀번호" name="changePW_V" id="changePW_V">
									</div>
									<div>
										<input type="password" style="width: 270px;"
											placeholder="변경비밀번호확인" name="change_PW_check_V"
											id="change_PW_check_V">
									</div>
									<div class="por-button2">
										<button type="button" class="btn btn-outline-dark" id="sbtn">확인</button>
									</div>

								</div>
							</form>

						</div>
					</div>
					<div class="row">
						<div class="col h90 border text-left ">
							<h6 class="mt-2">
								<h6>휴대전화 :</h6>
								<p>${memberInfo.mtel}</p>
						</div>
						<div class="col h90 border text-left">
							<h6 class="mt-2">
								<h6>이메일 :</h6>
								<p>${memberInfo.memail}</p>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- body 마지막 입니다-->
</body>
</html>