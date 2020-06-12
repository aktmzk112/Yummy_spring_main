<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>야미맵</title>
    <link rel="stylesheet" href="/YummyMap/css/main/main.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="/YummyMap/css/bootstrap.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="topNavHidden border-bottom dnone pl-4 pt-1" id="topNavHidden">
        <div class="d-flex m-0">
            <div class="topNavLogoHD">
                <a href="">YUMMY MAP</a>
            </div>
            <ul class="topNavItemHD d-flex justify-content-end pr-4 pt-1">
                <li><a class="topNavItem-iconHD" href=""><i class="far fa-heart"></i></a></li>
                <li><a class="topNavItem-iconHD" href=""><i class="fas fa-user"></i></a></li>
                <c:if test="${SID == null}">
                <li><a class="topNavItem-iconHD" href="/YummyMap/member/loginView.mmy"><i class="fas fa-toggle-off"></i></a></li>
                </c:if>
                <c:if test="${SID != null}">
                <li><a class="topNavItem-iconHD" href="/YummyMap/member/logoutProcess.mmy"><i class="fas fa-toggle-on"></i></a></li>
                </c:if>
            </ul>
        </div>
        <div class="d-flex justify-content-center">
            <div class="searchBoxHD d-flex">
                <div class="searchBox-fontHD"><i class="fas fa-search"></i></div>
                <input type="text" id="search" onkeydown="submitKeyword()">
                <div class="searchBox-textHD">검색</div>
            </div>
        </div>
    </div>
    <div class="topNav border-bottom">
        <div class="d-flex">
            <div class="topNavLogo pl-4 pt-2">
                <a href="">YUMMY MAP</a>
            </div>
            <ul class="topNavItem d-flex justify-content-end pr-4 pt-2">
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
        <div class="d-flex justify-content-center pt-3">
            <div class="sub-title">
                맛집 검색을 손쉽게! 야미맵
            </div>
        </div>
        <div class="d-flex justify-content-center pt-3">
            <div class="searchBox d-flex">
                <div class="searchBox-font"><i class="fas fa-search"></i></div>
                <input type="text" id="search" onkeydown="submitKeyword()">
                <div class="searchBox-text ml-1">검색</div>
            </div>
        </div>
        <div class="d-flex justify-content-end pt-4 pr-5">
            <div class="gameBanner mr-3">
                <a class="d-flex justify-content-center  gameBannerItem" href="">
                    <div>GamePlay</div>
                </a>
            </div>
            <div class="boardBanner">
                <a class="d-flex justify-content-center gameBannerItem" href="">
                    <div>Community</div>
                </a>
            </div>
        </div>
    </div>
    <div class="itemBody container mb-5">
        <div class="itemBody-title">전국 인기 순위</div>
        <div class="d-flex mt-3">
            <div class="item-card border mr-3" style="background-image: url(/YummyMap/img/main/img111.jpg)">
                <div class="d-flex justify-content-center">
                    <div class="item-card-title">구로디지털단지역 맛집 베스트</div>
                </div>
            </div>
            <div class="item-card border mr-3" style="background-image: url(/YummyMap/img/main/noimage.jpg)">
                <div class="d-flex justify-content-center">
                    <div class="item-card-title"></div>
                </div>
            </div>
            <div class="item-card border mr-1" style="background-image: url(/YummyMap/img/main/noimage.jpg)">
                <div class="d-flex justify-content-center">
                    <div class="item-card-title"></div>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-end">
            <div class="mr-2 mt-4 border p-2 pager">이전</div>
            <div class="mr-2 mt-4 border p-2 pager">다음</div>
        </div>
        <div class="itemBody-title">요즘 뜨는 맛집</div>
        <div class="d-flex mt-3">
            <div class="item-card border mr-3">
    
            </div>
            <div class="item-card border mr-3">
    
            </div>
            <div class="item-card border mr-1">
    
            </div>
        </div>
        <div class="d-flex justify-content-end">
            <div class="mr-2 mt-4 border p-2 pager">이전</div>
            <div class="mr-2 mt-4 border p-2 pager">다음</div>
        </div>
    </div>
</body>
<script type="text/javascript">
function showChartFrame(){
    document.getElementById('chartFrame').classList.toggle('dnone');
}
function submitChart(){
    document.getElementById('chartForm').submit();
}

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


onmousewheel = function(e){
    let currentWheelDelta = e.wheelDelta;
    if(currentWheelDelta > 0) {
        if(scrollY < 200){
            document.getElementById('topNavHidden').classList.add('dnone');
        }
    } else {
        if(scrollY > 150){
            document.getElementById('topNavHidden').classList.remove('dnone');
        }
    }
}



</script>
</html>