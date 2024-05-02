
var stompClient = null;


//소켓 연결했을 때의 상태를 정의하는 함수.
function setConnected(connected) {

    var connectionStatusElement = document.getElementById("connection-status");

    if(connected)
        connectionStatusElement.textContent = "연결 상태: 연결됨";
    else
        connectionStatusElement.textContent = "연결 상태: 연결 안 됨";

}


// /topic/ 으로 정보를 받았을 때 처리하는 함수.
function refresh() {
    location.reload();
}

//socket 연결 함수.
function connect() {

    //webSocketConfig 에서 설정한 주소로 연결(소켓 end point)
    var socket = new SockJS('/web-socket-start');

    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {

        //연결 후의 상태로 전환
        setConnected(true);
        console.log('Connected: ' + frame);

        // Stomp 를 이용하여 /topic/ 주소 구독.
        // /topic/ 으로 메시지 브로커가 정보 전달 시 아래 함수 실행.
        stompClient.subscribe('/topic/', function (request) {

            // WebSocketBroadCast Controller의 broadCast의 반환값을 인자로 받음.
            // topic으로 메시지가 왔을때의 행동을 작성
            refresh();
        });

    });
}


//윈도우가 오픈되었을 때마다 webSocket connect 실행
window.onload = function() {
    connect();
};

