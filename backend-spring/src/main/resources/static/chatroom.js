var stompClient = null;

function connect() {
    var socket = new SockJS('/chat'); // STOMP endpoint defined in WebSocketConfig.java.

    stompClient = Stomp.over(socket)

    stompClient.connect({}, function (frame) {
        // Update the UI for on-connect.
        $("#connect").prop("disabled", true);
        $("#disconnect").prop("disabled", false);
        $("#conversation").show();
        $("#chatroom").html("");

        // Path is defined in ChatController.java.
        stompClient.subscribe('/topic/chatroom', onMessageReceived);
    });
}

function onMessageReceived(payload) {
    var parsedMessage = JSON.parse(payload.body);
    updateChatroom(parsedMessage.username, parsedMessage.text);
}

// Finds the table with ID chatroom and appends a row with the username and text.
function updateChatroom(username, message) {
   $("#chatroom").append("<tr><td>" + username + "</td><td>" + message + "</td></tr>");
}

function sendMessage(username, text) {
    var message = {
        'username': $("#name").val(),
        'text': $("#message").val()
    };

    // Endpoint prefix (/app) defined in WebSocketConfig whereas the remaining path ("/message") is defined by
    // ChatController.java.
    stompClient.send("/app/message", {}, JSON.stringify(message));
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }

    // Update UI for disconnect.
    $("#connect").prop("disabled", false);
    $("#disconnect").prop("disabled", true);
    $("#conversation").hide();
    $("#chatroom").html("");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});