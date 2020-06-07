<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"/>
    <link  rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	<link rel="stylesheet" href="/YummyMap/css/admin/base.css">
	<link rel="stylesheet" href="/YummyMap/css/nav.css">
	<link rel="stylesheet" href="/YummyMap/css/game/game.css">
	<script type="text/javascript" src="/YummyMap/js/jquery-3.5.0.min.js"></script>
	<script type="text/javascript" src="/YummyMap/js/game/Winwheel.js"></script>
	<script type="text/javascript" src="/YummyMap/js/bootstrap.bundle.min.js"></script>
	<script defer type="text/javascript" src="/YummyMap/js/game/game.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/gsap/1.18.0/TweenMax.min.js"></script>
	
	<title>게임 화면</title>
	<style>
        
	</style>
</head>

<body>
<!-- nav 영역입니다-->
<div class="bg-white border-bottom fixed-top nav-body">
    <div class=" mt-2 nav-btn">
        <div class="member_nav text-right mt-3">
            <div class="ctQZg">
                <div class="_47KiJ">
                    <div class="XrOey"><a class="_0ZPOP kIKUG _4700r " id="myListIcon"><svg aria-label="활동 피드"
                                class="_8-yf5 " fill="#262626" height="24" viewBox="0 0 48 48" width="24">
                                <path
                                    d="M34.6 6.1c5.7 0 10.4 5.2 10.4 11.5 0 6.8-5.9 11-11.5 16S25 41.3 24 41.9c-1.1-.7-4.7-4-9.5-8.3-5.7-5-11.5-9.2-11.5-16C3 11.3 7.7 6.1 13.4 6.1c4.2 0 6.5 2 8.1 4.3 1.9 2.6 2.2 3.9 2.5 3.9.3 0 .6-1.3 2.5-3.9 1.6-2.3 3.9-4.3 8.1-4.3m0-3c-4.5 0-7.9 1.8-10.6 5.6-2.7-3.7-6.1-5.5-10.6-5.5C6 3.1 0 9.6 0 17.6c0 7.3 5.4 12 10.6 16.5.6.5 1.3 1.1 1.9 1.7l2.3 2c4.4 3.9 6.6 5.9 7.6 6.5.5.3 1.1.5 1.6.5.6 0 1.1-.2 1.6-.5 1-.6 2.8-2.2 7.8-6.8l2-1.8c.7-.6 1.3-1.2 2-1.7C42.7 29.6 48 25 48 17.6c0-8-6-14.5-13.4-14.5z">
                                </path>
                            </svg></a>
                        <div class="_0Sl_t">
                            <div class="poA5q" style="margin-left: -423px;"></div>
                        </div>
                    </div>
                    <div class="XrOey"><a href="#" id="mypage"><svg aria-label="프로필" class="_8-yf5 " fill="#262626" height="24"
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
    <a class="navbar-brand tcolor logo" href="/YummyMap/main.mmy">
        YUMMY MAP
    </a>
    <div class="b-subtitle text-left"><p class="pt-3">골라줘게임</p></div>
    <div class=" nav-item-1 d-flex justify-content-end">
        <a class="navbar-brand nav-item-size" href="/YummyMap/game/game.mmy" id="">
            <i class="fas fa-gamepad"></i>
        </a>
        <a class="navbar-brand nav-item-size" href="/YummyMap/board/boardMain.mmy">
            <i class="far fa-clipboard"></i>
        </a>
    </div>
  </div>
  <!-- nav 마지막 입니다-->
  
<!-- body 시작 입니다-->
<div class="main-body mt-4" style="width: 840px;">
    <div>
        <p>카테고리를 선택해주세요</p>
    </div>
    <ul class="cate alert alert-light d-flex" role="alert">
    	<c:forEach var="data" items="${list}">
        <li class="pr-4 cate-item item-h" >#${data}</li>
        </c:forEach>
    </ul> 
    <div class="dnone" id="selbox">
        <div class="d-flex">
            <p>메뉴를 선택해주세요</p>
            <p class="pl-2 text-primary">5개 선택</p>
        </div>
        <div class="cate alert alert-light d-flex flex-wrap justify-content-center" id="itemBox">
            
        </div>
    </div>
    <div class="dnone" id="selbox2">
        <div class="d-flex">
            <p>선택된 메뉴</p>
            <p class="pl-2 text-primary" id="selectedStr"></p>
        </div>
        <div class="cate alert alert-light d-flex flex-wrap justify-content-center" id="itemBox2">
            
        </div>
    </div>
    <div class="mt-5">
        <p>게임시작</p>
    </div>
    <div class="d-flex">
        <div class="panbox border">
           <div id="canvasContainer" class="">
	            <img id="prizePointer" src="/YummyMap/img/game/basic_pointer.png" alt="V" />
	            <canvas id='canvas' width='500' height='340'>
	                
	            </canvas>
           </div>
        </div>
    </div>
    <div class="mt-2 mb-5 btnbox text-center">
        <button type="button" class="btn btn-primary btnw" id="start">시작</button>
        <button type="button" class="btn btn-secondary btnw" id="refresh">초기화</button>
    </div>

</div>
<!-- body 마지막 입니다-->
<!-- modal page 입니다-->
<div class="modal" tabindex="-1" role="dialog" id="result">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">오늘의 메뉴 딱 골라줘</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="mbody">
        
      </div>
      <div class="modal-footer">
      <div class="d-flex row">
      	<p class="pr-3 text-muted h5">담아둔 메뉴로 다시 하려면</p>
        <button type="button" class="btn btn-secondary" id="m-replay">클릭</button>
      </div>
      <div class="d-flex">
      	<p class="pr-3 text-muted h5">처음부터 다시하려면</p>
        <button type="button" class="btn btn-secondary" id="m-refresh">클릭</button>
      </div>
      </div>
    </div>
  </div>
</div>
<!-- modal page 마지막 입니다-->
</body>
<script type="text/javascript">
$(document).ready(function () {
	//로그인 여부를 확인합니다.
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
	//마이리스트 이벤트 처리 입니다.
	$('#myListIcon').click(function(){
		if(!userid) {
			alert('로그인을 진행해주세요');
			return;
		}
		$(location).attr('href', '/YummyMap/main/myList.mmy');
	});
});
</script>
</html>