<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>야미맵 리스트</title>
    <link rel="stylesheet" href="/YummyMap/css/main/nav.css" />
    <link rel="stylesheet" href="/YummyMap/css/main/mainSearchList.css">
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
    <div class="itemBody container mb-5">
   	    <c:if test="${searchInfoVo.query_keyword.length() == 0 || searchInfoVo.query_keyword == null}">
    	<div class="title mt-4">${searchInfoVo.query_location} 인기 검색 순위</div>
    	<div class="border-bottom border-top mt-2 mb-2 p-2 d-flex">
    		<div class="filter-item border p-2 mr-2">한식</div>
    		<div class="filter-item border p-2 mr-2">중식</div>
    		<div class="filter-item border p-2 mr-2">일식</div>
    		<div class="filter-item border p-2 mr-2">양식</div>
    	</div>
    	</c:if>
   	    <c:if test="${searchInfoVo.query_keyword.length() != 0 || searchInfoVo.query_keyword != null}">
    	<div class="title mt-4">${searchInfoVo.query_location} ${searchInfoVo.query_keyword} 인기 검색 순위</div>   	    
   	    </c:if>    	
    	<c:forEach var="upSoVoList" items="${upSoVoList}" varStatus="status">
    	<c:if test="${status.count % 2 != 0}">
 	    <div class="item border d-inline-block mr-4 ml-3 mt-4" onclick="getDetail('${upSoVoList.id}')">
 	    	<div class="p-2">
	        	<div class="info-name m-0">${upSoVoList.place_name}</div>
	        	<div class="info-addr m-0">${upSoVoList.road_address_name}</div> 	    	
 	    	</div>
            <div class="imgBox">
                <img src="/YummyMap/img/main/noimage.jpg" alt="">
            </div>
            <div class="info-sub pl-2 pt-1">
            	<div class="d-flex">
            		<div class="mr-2">평점</div>
            		<div class="info-avg">${upSoVoList.star_avg}</div>
            	</div>
            	<div class="d-flex">
    				<div class="mr-2">리뷰</div>        	
	                <div class="info-sum">${upSoVoList.cont_sum}</div>
            	</div>
            </div>
        </div>
    	</c:if>
    	<c:if test="${status.count % 2 == 0}">
        <div class="item border d-inline-block mt-4" onclick="getDetail('${upSoVoList.id}')">
 	    	<div class="p-2">
	        	<div class="info-name m-0">${upSoVoList.place_name}</div>
	        	<div class="info-addr m-0">${upSoVoList.road_address_name}</div> 	    	
 	    	</div>
            <div class="imgBox">
                <img src="/YummyMap/img/main/noimage.jpg" alt="">
            </div>
            <div class="info-sub pl-2 pt-1">
            	<div class="d-flex">
            		<div class="mr-2">평점</div>
            		<div class="info-avg">${upSoVoList.star_avg}</div>
            	</div>
            	<div class="d-flex">
    				<div class="mr-2">리뷰</div>        	
	                <div class="info-sum">${upSoVoList.cont_sum}</div>
            	</div>
            </div>
        </div>
    	</c:if>
    	</c:forEach>
    </div>
</body>
<script type="text/javascript">
function submitKeyword(){
	if(event.keyCode == 13) {
		let keyword = event.target.value;
		if(!keyword)
			return;
		location.href = "/YummyMap/main/getList.mmy?keyword="+keyword;
	}
}
function getDetail(data){
	location.href = "/YummyMap/main/getDetail.mmy?id="+data;
}
</script>
</html>