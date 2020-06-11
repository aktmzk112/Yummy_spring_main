<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>YummyMap</title>
    <link rel="stylesheet" href="/YummyMap/css/main/mainList.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="/YummyMap/css/bootstrap.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="topNav border-bottom">
        <div class="topNavLogo">
            <a href="">YummyMap</a>
        </div>
        <ul class="topNavItem d-flex">
            <li><a class="topNavItem-icon" href=""><i class="far fa-heart "></i></a></li>
            <li><a class="topNavItem-icon" href=""><i class="fas fa-user"></i></a></li>
            <c:if test="${SID == null}">
            <li class="pt-1"><a class="topNavItem-font" href="/YummyMap/member/loginView.mmy">Login</a></li>
            </c:if>
            <c:if test="${SID != null}">
            <li class="pt-1"><a class="topNavItem-font" href="/YummyMap/member/logoutProcess.mmy">Logout</a></li>
            </c:if>
        </ul>
    </div>
    <div class="gameBanner border">
        <a class="d-flex gameBannerItem" href="">
            <i class="fas fa-gamepad pt-1 pr-1"></i>
            <p>픽픽메뉴</p>
        </a>
    </div>
    <div class="boardBanner border">
        <a class="d-flex gameBannerItem" href="">
            <i class="far fa-clipboard pt-1 pr-1"></i>
            <p>커뮤니티</p>
        </a>
    </div>
    <div class="leftNav border-right bg-light">
        <div class="leftNavSearch d-flex">
            <input type="text" id="search" onkeydown="submitKeyword()">
            <p class="bg-white m-0"><i class="fas fa-search"></i></p>
        </div>
        <p class="leftNavTitle border-bottom">List</p>
    </div>
    <div class="itemBody">
        <div class="itemBody-categoryBox border-top border-bottom bg-light">
            <div class="pt-2">
                <div class="d-flex category">
                    <div class="pr-4">통합검색</div>
                    <div class="pr-4 pl-2">식당</div>
                    <div class="pr-4 pl-2">메뉴</div>
                    <div class="pr-4 pl-2">
                        <select class="selBox bg-light" name="" id="">
                            <option value="">카테고리</option>
                            <option value="한식">한식</option>
                            <option value="중식">중식</option>
                            <option value="중식">일식</option>
                        </select>
                    </div>
                    <div class="pr-4 pl-2 d-flex chartBtn" onclick="showChartFrame()">
                        <div>통계</div>
                        <div class="arBox">
                            <i class="fas fa-angle-down ar"></i>
                        </div>
                    </div>
                </div>
                <div class="chartFrame border mb-0 dnone" id="chartFrame">
                    <div class="d-flex">
                        <div class="mt-4 pl-3 mb-4 pr-4 border-right">
                            <form action="" method="get" id="chartForm">
                            <div class="la">
                                <input type="radio" class="radioTag" name="chart" id="reviewTop" value="review" checked>
                                <label for="reviewTop">리뷰순</label>
                            </div>
                            <div class="mt-1 la">
                                <input type="radio" class="radioTag" name="chart" id="avgTop" value="avg">
                                <label for="avgTop">평점순</label>
                            </div>
                        </div>
                        <div class="mt-4 pl-2 ">
                            <select name="category" id="chartModalInput">
                                <option value="all">모두</option>
                                <option value="kor">한식</option>
                                <option value="chi">중식</option>
                            </select>
                            </form>
                            <div class="chartModalBtnBox">
                                <input id="chartModalBtn" type="button" onclick="submitChart()" value="검색">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:forEach var="upSoVoList"  items="${upSoVoList}">
        <div class="itemBody-item border shadow-lg mt-4 ">
            <div class="itemBody-img">
                <img src="/YummyMap/img/main/noimage.jpg" alt="">
            </div>
            <div class="itemBody-info">
                <p class="itemBody-info-name">${upSoVoList.place_name}</p>
                <div class="d-flex">
                    <p class="itemBody-info-star" id="star_grade">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                    </p>
                    <p class="itemBody-info-review pt-1">리뷰${upSoVoList.cont_sum}</p>
                </div>
            </div>
        </div>
        </c:forEach>
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

</script>
</html>