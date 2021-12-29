# WebSocket Frontend (Javascript)

Simple frontend using HTML and Javascript to connect to a WebSocket backend. The chat client will attempt to connect 
to STOMP endpoint `/chat`. Upon successful connection, it will listen in on inbound messages on `/topic/chatroom` 
and send messages out on `/app/message`. 

## References
- [jQuery](https://jquery.com/)
- [Simple Text Oriented Messaging Protocol (STOMP)](https://en.wikipedia.org/wiki/Streaming_Text_Oriented_Messaging_Protocol)
- [SockJS](https://github.com/sockjs/sockjs-client)