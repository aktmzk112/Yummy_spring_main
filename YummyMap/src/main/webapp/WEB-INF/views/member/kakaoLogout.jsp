<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="/YummyMap/js/jquery-3.5.0.min.js"></script>
<style>

</style>

</head>
<body onload="countdown();">
</body>
<script type="text/javascript">

	function countdown() { 
			  location.href='https://accounts.kakao.com/logout?continue=https://accounts.kakao.com/weblogin/account';
		      opener.document.location.href='http://localhost/YummyMap/member/logoutProcess.mmy';
		      self.close();
	} 
</script>
</html>