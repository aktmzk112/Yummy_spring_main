<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>야미맵 정보</title>
    <link rel="stylesheet" href="/YummyMap/css/main/nav.css" />
    <link rel="stylesheet" href="/YummyMap/css/main/mainDetail.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="/YummyMap/css/bootstrap.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="topNav border-bottom pl-4 pt-1 shadow-sm" id="topNav">
        <div class="d-flex m-0">
            <div class="topNavLogo">
                <a href="">YUMMY MAP</a>
            </div>
            <ul class="topNavItem d-flex justify-content-end pr-4 pt-1">
                <li><a class="topNavItem-icon" href=""><i class="far fa-heart"></i></a></li>
                <li><a class="topNavItem-icon" href=""><i class="fas fa-user"></i></a></li>
                <c:if test="${SID == null}">
                <li><a class="topNavItem-icon" href="/YummyMap/member/loginView.mmy"><i class="fas fa-toggle-off"></i></a></li>
                </c:if>
                <c:if test="${SID != null}">
                <li><a class="topNavItem-icon" href="/YummyMap/member/logoutProcess.mmy"><i class="fas fa-toggle-on"></i></a></li>
                </c:if>
            </ul>
        </div>
        <div class="d-flex justify-content-center">
            <div class="searchBox d-flex">
                <div class="searchBox-font"><i class="fas fa-search"></i></div>
                <input type="text" id="search" onkeydown="submitKeyword()">
                <div class="searchBox-text">검색</div>
            </div>
        </div>
    </div>
    <div class="itemBody container">
        <div class="Box">
            <div class="d-flex">
                <div class="imgBox">
                    <img src="/YummyMap/img/main/noimage.jpg" >
                </div>
                <div class="infoBox">
                    <p class="resName">천미미</p>
                    <div class="sub d-flex border-bottom">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star-half"></i>
                        <p class="resSub">n개 리뷰</p>
                    </div>
                    <div class="pickHeart">                        
                        <i class="far fa-heart unPick"></i>
                        <!-- <i class="fas fa-heart pick"></i> -->
                    </div>
                    <div class="resLoc">
                        <i class="fas fa-map-marked-alt"></i>
                        <p>서울 구로구 ...</p>
                    </div>
                    <div class="resMenu">
                        <i class="fas fa-utensils"></i>
                        <div class="d-flex">
                            <p>메뉴1</p>
                            <p>메뉴2</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="reviewBox">
                <p class="reviewBox-title border-bottom m-0">리뷰</p>
                <div class="reviewBtn" onclick="showInputBox()">
                    <i class="fas fa-pen"></i>
                </div>
                <div class="writeBox dnone">
                    <div class="d-flex starBox" id="star_grade">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                    </div>
                    <div class="input-group mt-1 mb-0">
                        <input type="text" class="form-control" >
                        <div class="input-group-append">
                          <button class="btn border writeBtn" type="button" id="button-addon2">작성</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mb-5">
                <div class="border-bottom pt-4">
                    <div class="reviewId">아이디</div>
                    <div class="d-flex reviewStarBox">
                        <i class="fas fa-star"></i>
                        <div class="pl-1">2020/05/01</div>
                    </div>
                    <div class="pt-4 pb-4">내용...</div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
function submitKeyword(){
	if(event.keyCode == 13) {
		let keyword = document.getElementById("search").value;
		if(!keyword)
			return;
	 	navigator.geolocation.getCurrentPosition(function(pos) {
			let longitude= pos.coords.longitude;
			let latitude= pos.coords.latitude;
			console.log(latitude);
			console.log(longitude);
			location.href = "/YummyMap/main/getList.mmy?keyword="+keyword+"&x="+longitude+"&y="+latitude;
		});
	}
}
$('#star_grade i').click(function(){
            $(this).parent().children("i").removeClass("on");  /* 별점의 on 클래스 전부 제거 */ 
            $(this).addClass("on").prevAll("i").addClass("on"); /* 클릭한 별과, 그 앞 까지 별점에 on 클래스 추가 */
            return false;
  });

function showInputBox() {
    $('.writeBox').show();
}
</script>
</html>