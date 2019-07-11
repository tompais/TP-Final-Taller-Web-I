package ar.edu.unlam.tallerweb1.Servers;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //Representa el link del message mapping que debe tener en cuenta.
        stompEndpointRegistry.addEndpoint("/onAsientoSeleccionado");
        //Este segundo es lo mismo que el primero, pero con ayudas para utilizar la librería SockJS de Javascript
        stompEndpointRegistry.addEndpoint("/onAsientoSeleccionado").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        super.configureMessageBroker(registry);
        //Retornamos los datos a los clientes en destinos con una url cuyo prefijo sea el siguiente
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
