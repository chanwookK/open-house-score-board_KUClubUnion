//윈도우가 오픈되었을 때마다 webSocket connect 실행
window.onload = function() {
    connect();
};


var stompClient = null;

//소켓 연결했을 때의 상태를 정의하는 함수.
function setConnected(connected) {

    var connectionStatusElement = document.getElementById("connection-status");

    if(connected)
        connectionStatusElement.textContent = "연결 상태: 연결됨";
    else
        connectionStatusElement.textContent = "연결 상태: 연결 안 됨";

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

    });

}

// /app/broad-cast로 정보 전달하는 함수. -> WebSocketBroadCastController
function sendBroadCast() {
    stompClient.send("/app/broad-cast", {});
}
