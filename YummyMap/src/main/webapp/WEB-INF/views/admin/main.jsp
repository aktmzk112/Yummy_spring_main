<!-- 
@author 유태희
@since 2020.05.23
 	이 페이지는 관리자 게시글 관리 페이지 입니다
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/YummyMap/css/bootstrap.min.css">
<link rel="stylesheet" href="/YummyMap/css/admin/base.css">
<link rel="stylesheet" href="/YummyMap/css/admin/board.css">
<script type="text/javascript" src="/YummyMap/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript" src="/YummyMap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<title>메인 페이지</title>
</head>

<style>
	.box text{
		font-size: 12px !important;
	}
	 
</style>

<body>
	<div class="container mt-1 mb5">
		<div class="bg-white nav-body">
			<div class=" mt-2 nav-btn">
				<div class="member_nav text-right mt-3">
					<div class="ctQZg">
						<!-- <a href="#" class="badge badge-light nav-item" id="join">Join</a>-->
						<div class="badge badge-light nav-item">${ADMINSID}</div>
						<a href="./adminEdit.mmy" class="badge badge-light nav-item">정보수정</a>
						<a href="./logoutProc.mmy" class="badge badge-light nav-item" id="logout">LOGOUT</a>
					</div>
				</div>
			</div>
			<a class="navbar-brand tcolor logo ml-1" href="./main.mmy" id=""> YUMMY MAP </a>
		</div>
		<div class="bar"></div>
		<div class="row row-cols-2 sub ml-1">
			<div class="col-3 pt-5 pr-4 text-right bar-ri">
				<div class="text-left mt-3">
					<a href="./main.mmy">
						<h5>메인 화면</h5>
					</a>
				</div>
				<div class="text-left mt-4">
					<a href="./member.mmy">
						<h5>회원 관리</h5>
					</a>
				</div>
				<div class="mt-4 text-left">
					<a href="./boardList.mmy">
						<h5>게시글 관리</h5>
					</a>
				</div>
			</div>

			<div class="col-9 d-flex flex-column bd-highlight mt-2" style="height: 575px;">
				<div>
				<h3 class="mt-2 text-center ml-4">Yummy Map 현황표</h3>
				<div id="piechart_3d" class="col text-center " style="width: 900px; height: 300px;"></div>
				</div>
				<div id="top_x_div" class="mt-5 col box" style="width: 900px; height: 200px;"></div>
			</div>

		</div>
		<div class="bar"></div>
		</div>
	</div>

</body>
<script type="text/javascript">
	google.charts.load('current', {'packages':['bar']});
	google.charts.setOnLoadCallback(drawStuff);
	
	function drawStuff() {
	  let data = new google.visualization.arrayToDataTable([
	    ['', ''],
	    ["개시글 수", ${CNT.txtcnt}],
	    ["회원 수", ${CNT.memcnt}],
	    ["악성 회원", ${CNT.isuemem}],
	    ['Other', 0]
	  ]);
	
	  var options = {
	    title: 'Chess opening moves',
	    textStyle: {
	           color: '333333',
	           fontName: 'Arial',
	           fontSize: 24         },
	    width: 800,
	    legend: { position: 'none' },
	    bars: 'horizontal', // Required for Material Bar Charts.
	    bar: { groupWidth: "90%" }
	  };
	
	  let chart = new google.charts.Bar(document.getElementById('top_x_div'));
	  chart.draw(data, options);
	};
	
	
    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        <c:forEach items="${LIST}" var="cate">
        	['${cate.category}',  ${cate.cnt}],
        </c:forEach>
      ]);

      var options = {
        is3D: true,
        height : 400
      };

      var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
      chart.draw(data, options);
    };
	</script>
</html>