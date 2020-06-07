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
<script type="text/javascript" src="/YummyMap/js/admin/board.js"></script>
<title>게시글 관리 페이지</title>
</head>

<body>
	<form method="post" action="" id="frm">
		<input type="hidden" name="mno" id="no" >
		<input type="hidden" name="nowpage" id="nowpage" value="${PAGE.nowPage}">
		<input type="hidden" name="searchs" id="searchs" >
		<input type="hidden" name="opts" id="opts">
		<input type="hidden" name="nos" id="txtno">
	</form>
	<div class="container mt-1 mb5">
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

			<div class="col-9 d-flex flex-column bd-highlight mt-2"
				style="height: 575px;">
				<div class="mb-auto p-2 ">
					<div class="d-flex justify-content-around text-center">
						<div class="col-2">번호</div>
						<div class="col-4">제목</div>
						<div class="col-2">작성자</div>
						<div class="col-3">작성일</div>
						<div class="col-2">비고</div>
					</div>
					<div class="row bar py-1"></div>
					<c:forEach var="data" items="${LIST}">
						<div class="d-flex mt-3 justify-content-around text-center">
							<div class="col-2 ckd" id="${data.txtno}">
								<input type="checkbox" class="mr-2" name="no" value="${data.txtno }">${data.txtno}
							</div>
							<div class="col-4 title-box"><a class="title">${data.title }</a></div>
							<div class="col-2">${data.mid }</div>
							<div class="col-3">${data.sdate }</div>
							<div class="col-2">
								<button class="rbtn  btn-danger" data-toggle="modal" data-target="#staticBackdrop">삭제</button>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class=" p-2 d-flex bd-highlight">
					<button class="bd-highlight" id="allck">전체 선택</button>
					<button class="bd-highlight ml-1" id="allremve">선택 삭제</button>
					<select style="display: inline-block;" class="ml-auto col-2 form-control" id="opt" name="opt" >
						<c:if test="${empty OPT}">
							<option value="nos" selected>검색조건</option>
							<option id="idsel" value="idch">작성자 검색</option>
							<option id="namesel" value="titlch">제목 검색</option>
						</c:if>
						<c:if test="${not empty OPT and OPT eq 'idch' }">
							<option value="nos">검색조건</option>
							<option id="idsel" value="idch" selected>작성자 검색</option>
							<option id="namesel" value="titlch">제목 검색</option>
						</c:if>
						<c:if test="${not empty OPT and OPT eq 'titlch' }">
							<option value="nos">검색조건</option>
							<option id="idsel" value="idch" >작성자 검색</option>
							<option id="namesel" value="titlch" selected>제목 검색</option>
						</c:if>
					</select>
					<div class="col-3 bd-highlight">
						<input type="search" class="form-control ds-input" id="search-input" placeholder="Search..."
							aria-label="Search for..." autocomplete="off" data-docs-version="4.5" spellcheck="false" role="combobox"
							aria-autocomplete="list" aria-expanded="false" aria-owns="algolia-autocomplete-listbox-0" dir="auto"
							style="position: relative; vertical-align: top;" name="search" value="${SCH}">
					</div>
						<button type="button" class="btn btn-primary" id="sbtn">검색</button>
				</div>
	<div class="pager d-flex justify-content-center paging">
         <div class="mb-5">
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
		<div class="bar"></div>
		</div>
	</div>
	
	<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-danger" id="rmbtn">확인</button>
	      </div>
	    </div>
	  </div>
	</div>

</body>

</html>