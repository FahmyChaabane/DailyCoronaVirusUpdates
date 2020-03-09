package com.chaabane.virus.corona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class CoronaScheduler implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private CoronaService coronaService;

    @Autowired
    private CoronaResponse coronaResponse;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(cron = "0 05 21 * * ?")
    public void greeting() throws Exception {
        //Thread.sleep(1000); // simulated delay
            coronaResponse.setList(coronaService.fetchCoronaVirusData());
            coronaResponse.setTotateff(coronaResponse.getList().stream().mapToInt(e -> e.getLastTotalCases()).sum());
            simpMessagingTemplate.convertAndSend("/topic/corona", coronaResponse);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
    //  config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*");
    }

}
