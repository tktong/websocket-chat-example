# WebSocket Backend (Spring Boot)

Runs a simple Spring Boot application that receives messages on `/app/message` and broadcasts messages to all STOMP 
clients subscribed to `/topic/chatroom`.

## How to Run Locally
1. Run `./gradlew bootRun`. This will launch the Spring application and run on `localhost:8080`.
2. Open a browser and enter URL - `http://localhost:8080`. This will use the default HTML and JS files included in 
   the `/src/static/resources` directory of this project.

## References
- [jQuery](https://jquery.com/)
- [Simple Text Oriented Messaging Protocol (STOMP)](https://en.wikipedia.org/wiki/Streaming_Text_Oriented_Messaging_Protocol)
- [SockJS](https://github.com/sockjs/sockjs-client)
- [Spring Boot](https://spring.io/projects/spring-boot)