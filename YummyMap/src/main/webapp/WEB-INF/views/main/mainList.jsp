<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>야미맵 리스트</title>
    <link rel="stylesheet" href="/YummyMap/css/main/nav.css" />
    <link rel="stylesheet" href="/YummyMap/css/main/mainList.css">
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
    <div class="border-bottom titleBody bg-light">
        <div class="d-flex justify-content-center">
            <div class="title">강남역 맛집 베스트 10곳</div>
        </div>
        <div class="d-flex justify-content-center">
            <div class="sub-title">"강남역 약속 모임 여기서 딱정하자!"</div>
        </div>
    </div>
    <div class="itemBody container">
        <div class="item border-bottom d-flex">
            <div class="imgBox">
                <img src="../img/img111.jpg" alt="">
            </div>
            <div class="pl-4">
                <div class="d-flex">
                    <div class="d-flex item-info">
                        <div>1.</div>
                        <div>천미미</div>
                        <div>&nbsp4.6</div>
                    </div>
                    <div class="heartBox">
                        <div class="pickHeart">                        
                            <i class="far fa-heart unPick"></i>
                            <!-- <i class="fas fa-heart pick"></i> -->
                        </div>
                    </div>
                </div>
                <div class="info-addr">주소</div>
                <div class="info-userID">유저ID</div>
                <div class="info-txt">너무맛있어너무맛있어요너무맛있어요너무맛있어요너무맛있어요너무맛있어요너무맛있어요너무맛있어요너무맛있어요너무맛있어요요</div>
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
</script>
</html>