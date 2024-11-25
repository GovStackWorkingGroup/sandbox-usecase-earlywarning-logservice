package com.global.govstack.logviewer.pub_sub;

import com.global.govstack.logviewer.repository.entity.Log;
import com.global.govstack.logviewer.service.LogViewerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class IMListener {
    private static final String LOG_TOPIC = "log-topic";

    private final LogViewerService service;

    @KafkaListener(groupId = "log-listener", topics = LOG_TOPIC)
    public Mono<Void> handleIncomingLogsFromIM(String threatMessage){
       return this.service.handleIncomingLogsFromIM(threatMessage);
    }
}
