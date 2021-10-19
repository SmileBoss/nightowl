<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/chat.css">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/js/chat.js"></script>
    <title>Chat</title>
</head>
<body onload="connect()">
<div class="wrapper">
    <section class="msger">
        <header class="msger-header">
            <div class="msger-header-title">
                <i class="fas fa-comment-alt"></i><a class="btn-back" href="/profile/${recipient.id}">❮ Back </a>
            </div>
            <div class="msger-header-options">
                <span><i class="fas fa-cog"></i></span>
            </div>
        </header>

        <main class="msger-chat" id="messages">


        </main>

        <div class="msger-inputarea">
            <input class="msger-input" id="message" placeholder="Enter your message...">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button id="sendBtn" type="submit" class="msger-send-btn" onclick="send()">Send</button>
        </div>
    </section>
</div>
<script>

    var token = $('#_csrf').attr('content');
    var header = $('#_csrf_header').attr('content');

    let stompClient = null;
    let currentUser = null;
    const recipientId = "${recipient.id}";
    const CHAT_SERVICE = "http://localhost:8080";

    let msgerChat = document.getElementById("messages");

    function send() {
        let msg = document.getElementById("message").value;
        sendMessage(msg);
        document.getElementById("message").value = "";
    }


    function getUser() {
        $.ajax({
            cache: false,
            type: 'POST',
            url: "http://localhost:8080/users/me",
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                currentUser = JSON.parse(JSON.stringify(data));
                console.log(currentUser.firstname);
                const div = document.getElementsByClassName("user");
                div.innerHTML = currentUser.firstname;
                loadMessage()
            }
        });
    }

    function scrollMessages() {
        $('#messages').stop().animate({
            scrollTop: $('#messages')[0].scrollHeight
        }, 800);
    }

    function connect() {
        SockJS = new SockJS("http://localhost:8080/ws");
        stompClient = Stomp.over(SockJS);

        stompClient.connect({}, onConnected, onError);
    }

    function onConnected() {
        console.log("connect succesfull");
        getUser();
        stompClient.subscribe(
            "/user/" + ${sender.id} + "/queue/messages",
            onMessageReceived
        );
    }

    const sendMessage = (msg) => {
        if (msg.trim() !== "") {
            const message = {
                senderId: currentUser.id,
                recipientId: recipientId,
                message: msg,
                date: new Date(),
            };
            stompClient.send("/webSocketApp/chat", {}, JSON.stringify(message));
            let sendMess = '<div class="msg right-msg"><div class="msg-img" style="background-image: url(${sender.icon})"></div><div class="msg-bubble"><div class="msg-info"> <div class="msg-info-name">${sender.firstname} ${sender.lastname}</div><div class="msg-info-time"></div></div><div class="msg-text">' + msg + '</div></div></div>';
            $(sendMess).appendTo("#messages");
            scrollMessages();
        }
    };

    function onMessageReceived(msg) {
        let mess = '<div class="msg left-msg"><div class="msg-img" style="background-image: url(${recipient.icon})"></div><div class="msg-bubble"><div class="msg-info"> <div class="msg-info-name">${recipient.firstname} ${recipient.lastname}</div><div class="msg-info-time"></div></div><div class="msg-text">' + JSON.parse(msg.body).message + '</div></div></div>';
        $(mess).appendTo("#messages");
        scrollMessages();
    }

    function onError() {
        console.log("error during connection!!!");
    }


    function loadMessage() {

        $("#messages").empty();

        $.ajax({
            cache: false,
            type: "POST",
            url: "http://localhost:8080/users/messages/" + recipientId,
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                $(function () {
                    for (let i = 0; i < data.length; i++) {
                        let test;
                        if (data[i].senderId === currentUser.id.toString()) {
                            test = '<div class="msg right-msg"><div class="msg-img" style="background-image: url(${sender.icon})"></div><div class="msg-bubble"><div class="msg-info"> <div class="msg-info-name">${sender.firstname} ${sender.lastname}</div><div class="msg-info-time"></div></div><div class="msg-text">' + data[i].message + '</div></div></div>';
                        } else {
                            test = '<div class="msg left-msg"><div class="msg-img" style="background-image: url(${recipient.icon})"></div><div class="msg-bubble"><div class="msg-info"> <div class="msg-info-name">${recipient.firstname} ${recipient.lastname}</div><div class="msg-info-time"></div></div><div class="msg-text">' + data[i].message + '</div></div></div>';
                        }
                        $(test).appendTo("#messages");

                        scrollMessages();
                    }
                })
            }
        });
    }

</script>
</body>
</html>
