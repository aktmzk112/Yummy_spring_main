<!-- 
@author 유태희
@since 2020.05.23
 	이 페이지는 회원정보 수정 페이지 입니다
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/YummyMap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/YummyMap/css/admin/base.css">
	<link rel="stylesheet" href="/YummyMap/css/admin/remember.css">
	<script type="text/javascript" src="../../js/jquery-3.5.0.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/YummyMap/js/admin/rememb.js"></script>
	<title>회원정보 수정 페이지</title>
</head>

<body>
	<div class="container mt-1 mb-5">
	<div class="bg-white nav-body">
			<div class=" mt-2 nav-btn">
				<div class="member_nav text-right mt-3">
					<div class="ctQZg">
						<!-- <a href="#" class="badge badge-light nav-item" id="join">Join</a>-->
						<div class="badge badge-light nav-item">${SID}</div>
						<a href="./adminedit.mmy" class="badge badge-light nav-item">정보수정</a>
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
						<h5>회원 관리</h5>
					</a>
				</div>
				<div class="mt-4 text-left">
					<a href="./boardList.mmy">
						<h5>게시글 관리</h5>
					</a>
				</div>
			</div>

			<div class="col-8  ml-5 text-center mb-5 ">
				<h3 class="mt-4">회원 정보 변경</h3>
				<div class="container" style="max-width: 550px;">
					<div class="mt-5 row ">
						<h5 class="col-3 ml-1 mr-5 ">아이디 </h5>
						<span class="border-b col-6 ml-1 pb-2 id-span">${MVO.mid }</span>
					</div>

					<form action="" method="POST" id="frm">
						<input type="hidden" name="mno" value="${MNO}">
						<input type="hidden" name="nowPage" id="nowPage" value="${param.nowpage}">
						<div class="mt-4 row ml-1">
							<h5 class="in-bl text-left ml-2 col-3 mt-1">이름</h5>
							<input class="col-6 form-control border-b margin-name text-center"  type="text" id="name" name="name" value="${MVO.mname }">
						</div>
						<div class="mt-3 text-left ml-4" style="font-size: 14px;" id="namehint"></div>
						<div class="mt-4 row ml-1">
							<h5 class="in-bl col-3  mt-1">비밀번호 </h5>
							<input type="password" class="col-6 form-control border-b margin-pw text-center" id="pw" name="pw">
						</div>
						<div class="mt-3" style="font-size: 14px;" id="pwhint"></div>
						<div class="mt-4  row ml-1">
							<h5 class="in-bl col-4 mt-1">비밀번호 확인 </h5>
							<input type="password" class="col-6 form-control border-b text-center" id="pwck">
						</div>
						<div class="mt-3  text-left ml-4" style="font-size: 14px;" id="pwok"></div>
						<div class="mt-4 row ml-1">
							<h5 class="in-bl col-3 mt-1  text-left ml-2">연락처 </h5>
							<input type="text" class="col-6 form-control border-b margin-tel text-center" id="tel" name="tel" value="${MVO.mtel }">
						</div>
						<div class="mt-4 row ml-1">
							<h5 class="in-bl col-3 mt-1  text-left ml-2">이메일 </h5>
							<input type="text" class="col-3 form-control border-b margin-mail text-center" id="mail" name="mail" value="${MVO.memail }">
							<span class="mt-2">@</span><input type="text" class="col-3 form-control border-b text-center" id="domain"
								name="domain" value="${MVO.mdomain}">

							<select class="col-3 form-control border-b  mt-1 margin-domain" id="mailSelect">
								<option value="">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="daum.net">daum.net</option>
								<option value="nate.com">nate.com</option>
								<option value="google.com">google.com</option>
								<option value="yahoo.com">yahoo.com</option>
							</select>
						</div>
							<div class="row  mt-3 d-flex justify-content-center">
								<div>
									<c:if test="${MVO.issue ne 'O' }">
									<input class="mr-2" type="checkbox"  name="issue" value="ok">악성회원
									</c:if>
									<c:if test="${MVO.issue eq 'O' }">
									<input class="mr-2" type="checkbox"  name="issue" value="ok" checked>악성회원
									</c:if>
								</div>
							</div>
					</form>
				</div>

				<div class="mt-5">
					<button type="button" class="btn btn-outline-primary px-5" id="okbtn">수정</button>
					<button type="button" class="btn btn-outline-danger px-5 ml-2" id="cbtn">취소</button>
				</div>
			</div>
		</div>
		<div class="bar"></div>
	</div>

<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">알림창</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>

</body>

</html>