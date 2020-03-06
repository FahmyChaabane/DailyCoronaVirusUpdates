package com.chaabane.virus.corona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class CoronaScheduler implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private CoronaService coronaService;

    @Autowired
    private CoronaResponse coronaResponse;

    @Scheduled(cron = "0 25 00 * * ?")
    @SendTo("/topic/corona")
    public CoronaResponse greeting() throws Exception {
        Thread.sleep(1000); // simulated delay
            coronaResponse.setList(coronaService.fetchCoronaVirusData());
            coronaResponse.setTotateff(coronaResponse.getList().stream().mapToInt(e -> e.getLastTotalCases()).sum());
        return coronaResponse;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
    //  config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*").withSockJS();
    }

}
