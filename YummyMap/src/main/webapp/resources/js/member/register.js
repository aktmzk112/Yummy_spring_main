function $ComTimer(){
    //prototype extend
}

$ComTimer.prototype = {
	  comSecond : ""
    , fnCallback : function(){}
    , timer : ""
    , domId : ""
    , fnTimer : function(){
        var m = Math.floor(this.comSecond / 60) + "분 " + (this.comSecond % 60) + "초";	// 남은 시간 계산
        this.comSecond--;					// 1초씩 감소
        console.log(m);
        this.domId.innerText = m;
        if (this.comSecond < 0) {			// 시간이 종료 되었으면..
            clearInterval(this.timer);		// 타이머 해제
            this.domId.innerText = '인증시간 만료 입니다. 다시 인증해 주세요';
            $('#mailckBox').addClass('d-none');
            
            let mail1 = $('#email1').val();
            let mail2 = $('#email2').val();
            
            let totalmail = mail1 + mail2;
            $.ajax({
            	url: '/YummyMap/member/rmMail.mmy',
            	type: 'post',
            	dataType : 'text',
            	data:{
            		'mail' : totalmail
            	},
            	success: function(data){},
            	errer: function(){
            		alert('통신오류');
            	}
            	
            		
            });
        }
    }
    ,fnStop : function(){
        clearInterval(this.timer);
    }
}


$(document).ready(function () {
	
let mailOk;
let mailcode=false;

// 완료버튼 클릭시 이벤트를 진행합니다.
  $('#joinbtn').click(function(){
    let result = checkfrm();
    if(result == -1){
      alert('모든 입력을 완료해주세요');
      return;
    }
    if(!(idchk($('#id').val()))) {
    	alert('아이디 입력이 잘못되었습니다.');
    	return;
    }
    if(!ckIdResult) {
    	alert('아이디 체크를 진행해주세요.');
    	return;
    }
    if(!(pwchk($('#pw').val()))) {
    	alert('비밀번호 입력이 잘못되었습니다.');
    	return;
    }
    if(!(namechk($('#name').val()))){
    	alert('이름 입력이 잘못되었습니다.')
    	return;
    }
    let tel = $('#telmid').val() + $('#telend').val();
    if(!(telchk(tel))) {
    	alert('휴대전화 입력이 잘못되었습니다.');
    	return;
    }
    if($('#repw').val() != $('#pw').val()) {
    	alert('비밀번호가 일치하지 않습니다.');
    	return;
    }
    if(mailcode == false){
    	alert('이메일 인증을 진행 하세요');
    	return;
    }
    
    let teltop = $('#teltop').val();
    let telmid = $('#telmid').val();
    let telend = $('#telend').val();
    
    let totaltel = teltop + "-" + telmid + "-" + telend;
    $('#mtel').val(totaltel);
    
    let mail1 = $('#email1').val();
    let mail2 = $('#email2').val();
    
    let totalmail = mail1 + mail2;
    $('#meMail').val(totalmail);
    
    $('#frm').submit();
  });
// input태그의 모든 값이 들어있는지 체크해주는 함수입니다.
  function checkfrm(){
    let formtag = $('.ckinput');
    for(let i=0; i<formtag.length; i++) {
      let tagval = $(formtag[i]).val();
      if(!tagval)
        return -1;
    }
  }
  //정규식 검사를 진행합니다.
	function idchk(id) {
		var pett = /^([A-Za-z]){1}\w{4,12}$/;
		var ok = pett.test(id);
		return ok;
	}
   function pwchk(pw) {
      	var patt = /^([a-z0-9!@#$%^&*-+_]){1}(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*-=])[a-zA-Z0-9!@#$%^&*-+_]{7,14}$/i;
      	var ok = patt.test(pw);
      	return ok;
   }
   function telchk(num) {
      	var reg = /^[0-9]{7,8}$/;
      	var ok = reg.test(num);
      	return ok;
   }
   function namechk(num) {
     	var reg = /^[가-힣]{2,6}$/;
     	var ok = reg.test(num);
     	return ok;
  }
   
   $('#id').keyup(function(){
	   $('#idmsg1').show();
	   if(idchk($('#id').val())) {
		   $('#idmsg1').text('아이디 입력양식이 일치합니다');
		   $('#idmsg1').attr('class', 'text-primary' );
	   } else {
		   $('#idmsg1').text('아이디 입력양식을 확인해주세요');
		   $('#idmsg1').attr('class', 'text-danger' );
	   }
   });
   $('#pw').keyup(function(){
	   $('#pwmsg1').show();
	   if(pwchk($('#pw').val())) {
		   $('#pwmsg1').text('비밀번호 입력양식이 일치합니다');
		   $('#pwmsg1').attr('class', 'text-primary' );
	   } else {
		   $('#pwmsg1').text('비밀번호 입력양식을 확인해주세요');
		   $('#pwmsg1').attr('class', 'text-danger' );
	   }
   });
   $('#repw').keyup(function(){
	   $('#repwmsg').show(); 	 
	   if($('#repw').val() == $('#pw').val()) {
		   $('#repwmsg').text('비밀번호가 일치합니다');
		   $('#repwmsg').attr('class', 'text-primary' );
	   } else {
		   $('#repwmsg').text('비밀번가 일치하지 않습니다');
		   $('#repwmsg').attr('class', 'text-danger');
	   }
   });


  let ckIdResult = false;
  //아이디체크 이벤트를 비동기로 진행합니다.
  $('#idcheck').click(function() {
	  let bid = $('#id').val();
	  if(!bid) return;
	  
		$.ajax({
			url: '/YummyMap/member/idCheck.mmy',
			type: 'post',
			dataType: 'json',
			data: {
				'mid': bid
			},
			success: function(data){
				let str = data.result;
				if(str == 'ok') {
					$('#idmsg2').text('사용가능한 아이디입니다.');
					$('#idmsg2').attr('class', 'text-primary d-block' );
					ckIdResult = true;
				} else {
					$('#idmsg2').text('이미 사용된 아이디입니다.');					
					$('#idmsg2').attr('class', 'text-danger d-block' );
					ckIdResult = false;
				}
					$('#idmsg2').show();
			},error : function(){
				alert('통신오류!!!');
			} 
		});
  });
  //이메일 인증 처리 
  $('#sendmail').click(function(){
	  let mailId = $('#email1').val();
	  let domain = $('#email2').val();
	  if(!mailId || !domain){
		  alert('메일을 입력해 주세요');
		  return;
	  }
	  let mail = mailId + domain;
	  $.ajax({
		 url : '/YummyMap/member/mailCk.mmy',
		 type: 'post',
		 dataType: 'json',
		 data: {
			 'email' : mail
		 },
		 success : function(data){
			 
	        if(data.emailCk == 'ok'){
	        	$('#mailckBox').removeClass('d-none');
	        	$('#mailmsg').addClass('d-none');
	        	var AuthTimer = new $ComTimer()
	        	AuthTimer.comSecond = 10;
	        	AuthTimer.timer =  setInterval(function(){AuthTimer.fnTimer()},1000);
	        	AuthTimer.domId = document.getElementById("mailmsg2");
	        	$('#mailmsg2').removeClass('d-none');
	        }

		 },error : function(){
			alert("통신 오류") 
		 }
	  });
  });
  //이메일 인증 코드값 확인 처리
  $('#eokbtn').click(function(){
	  
	  let mailId = $('#email1').val();
	  let domain = $('#email2').val();
	  
	 let email = mailId + domain;
	 let mailo = $('#malick').val();
	 
	 $.ajax({
		url: '/YummyMap/member/mailOk.mmy',
		type: 'post',
		dataType: 'json',
		data: {
			'mail' :email,
			'cftnum' : mailo
		},
		success : function(data){
			if(data.status == 'ok'){
				 $('#mailmsg').removeClass('text-danger');
				 $('#mailmsg').css('color','blue');
				 $('#mailmsg').html('메일 인증이 완료 되었습니다');
				 $('#mailckBox').addClass('d-none');
				 mailcode = true;
			}else{
				 $('#mailmsg').html('인증 번호가 틀립니다 다시 인증해주세요');
			}
			
		},error : function(){
			alert('통신 에러');
		}
	 });
  });
});