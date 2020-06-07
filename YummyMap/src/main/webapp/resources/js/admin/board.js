/**
@author 유태희
@since 2020.05.24
 	이 JS는 관리자 게시글관리 페이지에 사용할 JavaScript 입니다
*/
function search(){
		let inid = $('#search-input').val();
		let opt = $('#opt').val();
		if(opt == 'nos'){
			alert('검색 조건을 먼저 선택하세요');
			return;
		}
		if(opt != 'nos' && !inid){
			alert('검색어를 입력하세요');
			return;
		}
		$('#searchs').val(inid);
		$('#opts').val(opt);
		$('#frm').attr('action','/YummyMap/admin/boardList.mmy');
		$('#frm').submit();
		
}
$(function(){
	let txtnos = [];
	let tno;
	
	
	$('.pbtn').click(function(){
		let page = $(this).text();
		$('#nowpage').val(page);
		let inid = $('#search-input').val();
		let opt = $('#opt').val();
		$('#searchs').val(inid);
		$('#opts').val(opt);
		$('#frm').attr('action','/YummyMap/admin/boardList.mmy');
		$('#frm').submit();
	});
	
	$('.next').click(function(){
		let aa= $(this).attr('id');
		let inid = $('#search-input').val();
		let opt = $('#opt').val();
		$('#searchs').val(inid);
		$('#opts').val(opt);
		$('#nowpage').val(aa);
		$('#frm').attr('action','/YummyMap/admin/boardList.mmy');
		$('#frm').submit();
	});
	
	$('.pre').click(function(){
		let inid = $('#search-input').val();
		let opt = $('#opt').val();
		let aa = $(this).attr('id');
		$('#nowpage').val(aa);
		$('#searchs').val(inid);
		$('#opts').val(opt);
		$('#frm').attr('action','/YummyMap/admin/boardList.mmy');
		$('#frm').submit();
	});
	
	$('#sbtn').click(search);
	$('#search-input').keydown(function(key){
		if(key.keyCode == 13){
			search();
			return;
		}
	});
	$('#allck').click(function(){
		$("input:checkbox").attr('checked','checked');
		
	});
	$('.rbtn').click(function(){
		tno = $(this).parent().prevAll('.ckd').attr('id');
		$('.modal-body').text('해당 게시글을 정말 삭제 하시겠습니까?');
	});
	

	$('#allremve').click(function(){
		$("input:checkbox[name='no']:checked").each(function(){
			txtnos.push($(this).val());	
		});
		
		if(txtnos.length == 0){
			alert('선택을 먼저 부탁드립니다');
			return;
		}
		
		let inid = $('#search-input').val();
		let opt = $('#opt').val();
		$('#searchs').val(inid);
		$('#opts').val(opt);
		$('#txtno').val(txtnos); 
		$('#frm').attr('action','/YummyMap/admin/checkDel.mmy');
		$('#frm').submit();
		
		
/*		$.ajax({
			url: '/YummyMap/admin/checkDel.mmy',
			type: 'post',
			datatype: 'text',
			data:{
				'txtno' : txtnos
			},
			error : function(){
				alert('통신에러 ㅜ ㅜ');
			}
			
		});*/
		
	});
	
	$('#rmbtn').click(function(){
		let inid = $('#search-input').val();
		let opt = $('#opt').val();
		$('#searchs').val(inid);
		$('#opts').val(opt);
		$('#txtno').val(tno); 
		$('#frm').attr('action','/YummyMap/admin/checkDel.mmy');
		$('#frm').submit();
	});
	
	$('.title').click(function(){
		
		tno = $(this).parent().prevAll('.ckd').attr('id');
		let inid = $('#search-input').val();
		let opt = $('#opt').val();
		$('#searchs').val(inid);
		$('#opts').val(opt);
		$('#txtno').val(tno); 
		$('#frm').attr('action','/YummyMap/admin/boardDetail.mmy');
		$('#frm').submit();
		
	});
});