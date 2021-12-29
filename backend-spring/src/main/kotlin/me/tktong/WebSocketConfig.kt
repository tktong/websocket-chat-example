package me.tktong

import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(private val environment: Environment): WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic")
        registry.setApplicationDestinationPrefixes("/app")
    }

    /**
     * Defines a new STOMP endpoint with SockJS and allowed origins if the property allowed origins is set. The
     * default application.properties does not set an allowed origin to prevent CORS vulnerability. The local
     * development version (application-dev.properties) sets a wildcard allowed origin since the client app is
     * running on a different port (i.e. localhost:8080 for spring vs localhost:3000 for reactjs).
     */
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        val endpointRegistration = registry.addEndpoint("/chat")

        environment.getProperty(PropertyNames.ALLOWED_ORIGINS)?.let { allowedOrigins ->
            endpointRegistration.setAllowedOriginPatterns(allowedOrigins)
        }

        endpointRegistration.withSockJS()
    }
}