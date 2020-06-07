<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="/YummyMap/css/board/boardMain.css">
<link rel="stylesheet" href="/YummyMap/css/nav.css">
<script type="text/javascript" src="/YummyMap/js/jquery-3.5.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	let userid = '${sid}';
	if (!userid) {
		$('#login').show();
		$('#logout').hide();
		$('#mypage').attr('href', '/YummyMap/join/join.mmy');
	} else {
		$('#login').hide();
		$('#logout').show();
		$('#mypage').attr('href', '/YummyMap/member/mypage.mmy');
	}
	
	$(function(){
		$('#frm').submit();
	});

	});
</script> 
</head>
<body>
	<form method="post" action="/YummyMap/board/boardDetail.mmy" id="frm">
		<input type="hidden" name="txtno" value="${DATA.txtno}">
		<input type="hidden" name="title" value="${DATA.title}">
		<input type="hidden" name="mtxt" value="${DATA.mtxt}">
		<input type="hidden" name="rnum"  value="${DATA.rnum}">
		<input type="hidden" name="nowPage" id="nowPage" value=" ${nowPage}">
	</form>
</body>
</html>