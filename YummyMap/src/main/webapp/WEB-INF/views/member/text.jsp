<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/YummyMap/css/w3.css">
<link rel="stylesheet" href="/YummyMap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="/YummyMap/css/member/text.css">
<link rel="stylesheet" href="/YummyMap/css/nav.css">
<script type="text/javascript" src="/YummyMap/js/jquery-3.5.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script type="text/javascript"src="/YummyMap/js/bootstrap.bundle.min.js"></script>
<style>
</style>
<script type="text/javascript">
	$(document).ready(function() {
		
		 //로그인 여부를 확인합니다.
		  let userid = '${sid}';
		  if(!userid) {
			  $('#login').show();
			  $('#logout').hide();
			  $('#mypage').attr('href', '/YummyMap/join/join.mmy');
		  } else {
			  $('#login').hide();
			  $('#logout').show();
			  $('#mypage').attr('href', '/YummyMap/member/member.mmy');
		  }
		  
		$('.pbtn').click(function() {
			var str = $(this).text();
			$('#nowPage').val(str);
			$('#frm').attr('action', '/YummyMap/member/txtList.mmy');
			$('#frm').submit();
		});
		$('.btn').click(function() {
			var str = $(this).html();
			if (str == 'RPE') {
				$('#nowPage').val('${PAGE.nowPage -1}');
			} else if (str == 'NEXT') {
				$('#nowPage').val('${PAGE.nowPage +1}');
			} else {
				return;
			}
			$('#frm').attr('action', '/YummyMap/member/member.mmy');
			$('#frm').submit();
		});
		$('#mlink').click(function() {
			$("#staticBackdrop").modal();
		});
		$('#dbtn').click(function() {
			alert('asdsad');
			$(location).attr('href', '/YummyMap/Drop/dropProc.mmy');
		});
		
	      $('.bolist').click(function(){
	          //글번호 알아낸다
	          var txtno = $(this).attr('id');
	          $('#txtno').val(txtno);
	          $('#nowPage').val('${PAGE.nowPage}');
	          $('#frm').attr('action','/YummyMap/board/boardDetail.mmy?txtno='+txtno);
	          $('#frm').submit();
	       });
	
	});
</script>
</head>
<body>
	<form method="post" id="frm" action="/YummyMap/member/member.mmy">
		<input type="hidden" name="nowPage" id="nowPage">
	</form>
	<!-- nav 영역입니다-->
	<div class="bg-white border-bottom fixed-top nav-body">
		<div class=" mt-2 nav-btn">
			<div class="member_nav text-right mt-3">
				<div class="ctQZg por-nav">
					<div class="_47KiJ">
						<div class="XrOey">
							<a class="_0ZPOP kIKUG _4700r " href="/YummyMap/main/myList.mmy"><svg
									aria-label="활동 피드" class="_8-yf5 " fill="#262626" height="24"
									viewBox="0 0 48 48" width="24">
                        <path
										d="M34.6 6.1c5.7 0 10.4 5.2 10.4 11.5 0 6.8-5.9 11-11.5 16S25 41.3 24 41.9c-1.1-.7-4.7-4-9.5-8.3-5.7-5-11.5-9.2-11.5-16C3 11.3 7.7 6.1 13.4 6.1c4.2 0 6.5 2 8.1 4.3 1.9 2.6 2.2 3.9 2.5 3.9.3 0 .6-1.3 2.5-3.9 1.6-2.3 3.9-4.3 8.1-4.3m0-3c-4.5 0-7.9 1.8-10.6 5.6-2.7-3.7-6.1-5.5-10.6-5.5C6 3.1 0 9.6 0 17.6c0 7.3 5.4 12 10.6 16.5.6.5 1.3 1.1 1.9 1.7l2.3 2c4.4 3.9 6.6 5.9 7.6 6.5.5.3 1.1.5 1.6.5.6 0 1.1-.2 1.6-.5 1-.6 2.8-2.2 7.8-6.8l2-1.8c.7-.6 1.3-1.2 2-1.7C42.7 29.6 48 25 48 17.6c0-8-6-14.5-13.4-14.5z">
                        </path>
                     </svg></a>
							<div class="_0Sl_t">
								<div class="poA5q" style="margin-left: -423px;"></div>
							</div>
						</div>
						<div class="XrOey">
							<a href="/YummyMap/member/member.mmy""><svg aria-label="프로필" class="_8-yf5 "
									fill="#262626" height="24" viewBox="0 0 48 48" width="24">
                        <path
										d="M24 26.7c7.4 0 13.4-6 13.4-13.4S31.4 0 24 0 10.6 6 10.6 13.4s6 13.3 13.4 13.3zM24 3c5.7 0 10.4 4.6 10.4 10.4S29.7 23.7 24 23.7s-10.4-4.6-10.4-10.4S18.3 3 24 3zm9.1 27.1H14.9c-7.4 0-13.4 6-13.4 13.4v3c0 .8.7 1.5 1.5 1.5s1.5-.7 1.5-1.5v-3c0-5.7 4.6-10.4 10.4-10.4h18.3c5.7 0 10.4 4.6 10.4 10.4v3c0 .8.7 1.5 1.5 1.5s1.5-.7 1.5-1.5v-3c-.1-7.4-6.1-13.4-13.5-13.4z">
                        </path>
                     </svg></a>
						</div>
					</div>
					<!-- <a href="#" class="badge badge-light nav-item" id="join">Join</a>-->
					<a href="#" class="badge badge-light nav-item" id="login">LOGIN</a>
					<a href="#" class="badge badge-light nav-item" id="logout">LOGOUT</a>
				</div>
			</div>
		</div>
		<a class="navbar-brand tcolor logo" href="/YummyMap/main.mmy" id=""> YUMMY MAP </a>
		<div class="b-subtitle text-right">
			<p class="pt-3 text-left">마이페이지</p>
		</div>
  <div class=" nav-item-1 d-flex justify-content-end">
            <a class="navbar-brand nav-item-size" href="/YummyMap/game/game.mmy" >
                <i class="fas fa-gamepad" id="game"></i>
            </a>
            <a class="navbar-brand nav-item-size" href="/YummyMap/board/boardMain.mmy">
                <i class="far fa-clipboard"></i>
            </a>
        </div>
	</div>
	<!-- nav 마지막 입니다-->

	<!-- body 시작 입니다-->
	<div class="main-body por">
		<div class="text-right">
			<div class="row">
				<div class="col-2 bor-bottom-top bor-right padd-right">
					<div class="padd-text mt-4">
						<a href="/YummyMap/member/member.mmy">
							<h6 style="color: black" class="font-weight-bold">회원정보 변경</h6>
						</a>
					</div>
					<div class="padd-text">
						<a href="/YummyMap/member/txtList.mmy">
							<h6 style="color: black" class="font-weight-bold">내가 쓴글 보기</h6>
						</a>
					</div>
					<div class="mar-top bor-bottom-or padd-text">
					<h6 type="button" style="color: black" class="font-weight-bold text-danger" data-toggle="modal" id="mlink">회원탈퇴</h6>
					<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog" >
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">회원탈퇴 </h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<p><h4 class="text-center">정말로 탈퇴 하시겠습니까?</h4></h4></p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">취소</button>
									<button type="button" class="btn btn-primary" id="dbtn">예</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
										
				<div class="col-9 text-center bor-bottom-top bor-bottom-or">
					<div class="mt-3">
						<h3 class=" ">내가 쓴글 보기</h3>
					</div>
					<div>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">글번호</th>
									<th scope="col">제목</th>
									<th scope="col">아이디</th>
									<th scope="col">작성일</th>
									<th scope="col">조회수</th>
									<th scope="col">추천수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="data" items="${LIST}">
		                           <tr class="bolist" id="${data.txtno}">                           
			                           <td>${data.txtno}</td>
			                           <td>${data.title}</td>
			                           <td>${data.mid}</td>
			                           <td>${data.cdate}</td>
			                           <td>${data.count}</td>
			                           <td>${data.rnum}</td>
		                           </tr>
								</c:forEach>
							</tbody>
						</table>
	<div class="pager d-flex justify-content-center paging">
         <div class="mb-3">
            <nav aria-label="Page navigation example">
               <ul class="pagination">
                  <c:if test="${PAGE.endPage > 5}">
                     <li class="page-item pre" id="${PAGE.prePage }"><a class="page-link"   aria-label="Previous"><span aria-hidden="true" >&laquo;</span>
                     </a></li>
                  </c:if>
                  <c:forEach var="pageNo" begin="${PAGE.startPage}" end="${PAGE.endPage}">
                  	<c:if test="${PAGE.nowPage eq pageNo }">
                     <li class="page-item  page-link pbtn pbtnck">${pageNo}</li>
                    </c:if>
                  	<c:if test="${PAGE.nowPage ne pageNo }" >
                     <li class="page-item  page-link pbtn">${pageNo}</li>
                    </c:if>
                  </c:forEach>
                  <c:if test="${PAGE.endPage ne PAGE.totalPage}">
                     <li class="page-item next" id="${PAGE.nextPage }" ><a class="page-link" 
                        aria-label="Next"> <span aria-hidden="true" >&raquo;</span>
                     </a></li>
                  </c:if>

               </ul>
            </nav>
         </div>
      </div>
				</div>
				</div>
					</div>
			</div>
				</div>
	<!-- body 마지막 입니다-->
</body>
</html>