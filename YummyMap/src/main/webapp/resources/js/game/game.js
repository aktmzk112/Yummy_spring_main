let cateList = new Array();	//카테고리 담아줄 배열입니다.
let menuList = new Array();	//메뉴 담아줄 배열입니다.
let cateItem; //카테고리 변수입니다.
let menuItem //메뉴 변수입니다.


// 카테고리 선택시 이벤트 처리입니다.
$('.cate-item').click(function(){
    cateItem = $(this).text().substring(1);
    let check = checkIsSelected(cateItem);
    if(check == 1) return;
    cateList.push(cateItem);
    getItems();
});

// 카테고리 선택여부를 체크합니다.
function checkIsSelected(cate) {
	for(let i=0; i<cateList.length; i++) {
		if(cateList[i] == cate)
			return 1;
	}
}

//아이템 선택시 이벤트 처리 입니다.
function setItem(e) {
	menuItem = e;
	setMenuList(menuItem);
	setSelectBox();
	setStrInSelectedBox();
}
// 선택, 취소된 아이템을 배열에 추가or삭제해주는 함수입니다.
function setMenuList(item) {
	if(menuList.length == 0){
		menuList.push(item);
	} else {
		for(let i=0; i<menuList.length; i++) {
			if(menuList[i] === item) {
				menuList.splice(i, 1);
				return;
			}
		}
		menuList.push(item);
	}
}
// 선택메뉴란에 출력을 설정해주는 함수입니다.
function setSelectBox() {
	$('#itemBox2').empty();
	for(let i=0; i<menuList.length; i++) {
		$('#itemBox2').append('<p class="pr-4 item3-h" onclick="setItem(\''+ menuList[i]+'\')">#'+menuList[i]+'</p>');
 	}
}
//선택된 메뉴 수에 따라 선택된 메뉴란에 메시지을 출력합니다.
function setStrInSelectedBox() {
	if(menuList.length == 5){	
		$('#selectedStr').attr('class','text-primary');
		$('#selectedStr').text('메뉴 선택이 완료되었습니다');
	} else {
		$('#selectedStr').attr('class','text-danger');
		$('#selectedStr').text('5개의 메뉴를 선택해주세요');
	}
}



//menuList배열 출력테스트용 함수입니다.
function getPrint() {
	console.log('#############');
	for(let i=0; i<menuList.length; i++) {
	console.log(menuList[i]);			
	}
	console.log('#############');
}

// 카테고리 선택 이벤트 비동기 처리 함수입니다.
let getItems = function() {
	$.ajax({
        url:'/YummyMap/game/gameItem.mmy',
        type:'post',
        dataType:'json',
        data:{
            'cate':cateItem
        },
        success:function(data){
            let obj = data.items;
            for(let i=0; i<obj.length; i++) {
                let getItem = obj[i];
                $('#itemBox').append('<p class="pr-4 item2-h" onclick="setItem(\''+ getItem+'\')">#'+getItem+'</p>');
            }
            $('#selbox').slideDown(100);
            $('#selbox2').slideDown(100);
        }
    });
}

//시작 버튼 클릭시 이벤트 입니다.
$('#start').click(function() {
	if(menuList.length != 5) {
		alert('5개의 메뉴를 선택해주세요.');
		return;
	}
	 theWheel = new Winwheel({
		'canvasId': 'canvas',
		'fillStyle': '#e7706f',
		'lineWidth': 3,
		'numSegments': 5,
		'segments': [{
				'fillStyle': '#eae56f',
				'text': menuList[0]
			},
			{
				'fillStyle': '#89f26e',
				'text': menuList[1]
			},
			{
				'fillStyle': '#7de6ef',
				'text': menuList[2]
			},
			{
				'fillStyle': '#e7706f',
				'text': menuList[3]
			},
			{	
				'fillStyle': '#e41311',
				'text': menuList[4]
			}
		],
		'animation':
		{
			'type': 'spinToStop',
			'duration': 5,
			'spins': 8,
			'callbackFinished': 'showSelectedMenu()'

		}
	});	
	theWheel.startAnimation();
});
// 휠 게임 종료시 이벤트 처리 함수 입니다.
let showSelectedMenu = function alertPrize() {
let winningSegment = theWheel.getIndicatedSegment();
$('#mbody').append('<p class="h4 text-muted">오늘의 메뉴는</p>');
$('#mbody').append('<p class="h2 text-primary"><b>'+winningSegment.text+'</b></p>');
$('#mbody').append('<p class="h4 text-muted">어떠세요?</p>');
$('#result').modal('show')

}

// 게임 초기화 이벤트입니다.
$('#refresh').click(function() {
	location.reload();
});
//모달페이지 다시하기버튼 이벤트입니다.
$('#m-refresh').click(function() {
	location.reload();		
});
//모달페이지 다시하기버튼 이벤트입니다.
$('#m-replay').click(function() {
	$('#result').modal('hide');		
});

// 휠 기본 셋팅입니다.
 theWheel = new Winwheel({
		'canvasId': 'canvas',
		'fillStyle': '#e7706f',
		'lineWidth': 3,
		'numSegments': 5,
		'segments': [{
				'fillStyle': '#eae56f',
				'text': ''
			},
			{
				'fillStyle': '#89f26e',
				'text': ''
			},
			{
				'fillStyle': '#7de6ef',
				'text': ''
			},
			{
				'fillStyle': '#e7706f',
				'text': ''
			},
			{
				'fillStyle': '#e41311',
				'text': ''
			}
		]
	});