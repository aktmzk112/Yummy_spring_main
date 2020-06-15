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
		<div class="border-bottom mt-5">
			<div class="d-flex">
		        <div class="upso-name mr-2">${upsoVo.place_name}</div>
	            <div class="upso-count pt-3">${upsoVo.cont_sum}개 리뷰</div>
			</div>
	        <div class="d-flex">
	        	<div class="d-flex upso-avgBox pt-2">
		            <i class="fas fa-star upso-star"></i>
		            <i class="fas fa-star-half upso-star"></i>
					<div class="upso-avg">${upsoVo.star_avg}</div>		
	        	</div>
	        	<div>
			        <div class="pickHeart mb-1">                        
			            <i class="far fa-heart unPick"></i>
			            <!-- <i class="fas fa-heart pick"></i> -->
			        </div>
	        	</div>
	        </div>
		</div>
		<div class="d-flex">
			<div class="upso-subinfo-box pt-4">
		        <div>
		            <i class="fas fa-map-marked-alt rgba4"></i>
		            <div class="upso-subinfo-font">${upsoVo.road_address_name}</div>
		        </div>
		        <div class="mt-2">
		        	<i class="fas fa-phone rgba4"></i>
		        	<div class="upso-subinfo-font">${upsoVo.phone}</div>
		        </div>
		        <div class="mt-2">
		            <i class="fas fa-utensils rgba4"></i>
		            <div class="upso-subinfo-font">${upsoVo.category_name}</div>
		        </div>
			</div>
			<div class="upso-mapBox"></div>
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
	        <div class="review-item border-bottom pt-4">
	            <div class="reviewId">아이디</div>
	            <div class="d-flex reviewStarBox">
	                <i class="fas fa-star"></i>
	                <div class="pl-1">2020/05/01</div>
	            </div>
	            <div class="review-txt pt-4 pb-4">내용...</div>
	        </div>
	    </div>
    </div>
</body>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d8654c7466588faa58bc40d0b9bef6ce&libraries=services"></script>
<script type="text/javascript">
function submitKeyword(){
	if(event.keyCode == 13) {
		let keyword = document.getElementById("search").value;
		if(!keyword)
			return;
		location.href = "/YummyMap/main/getList.mmy?keyword="+keyword;
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

// 카카오map
var mapContainer = document.getElementById('upso-mapBox'), // 지도를 표시할 div 
mapOption = { 
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
s
//마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

//마커를 생성합니다
var marker = new kakao.maps.Marker({
position: markerPosition
});

//마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);
</script>
</html>