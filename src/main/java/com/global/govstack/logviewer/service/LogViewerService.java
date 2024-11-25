package com.global.govstack.logviewer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.govstack.logviewer.repository.LogRepository;
import com.global.govstack.logviewer.repository.dto.LogInfoDto;
import com.global.govstack.logviewer.repository.entity.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LogViewerService {

    private final LogRepository logRepository;
    private final TransactionalOperator transactionalOperator;
    private final ObjectMapper mapper;

    public Mono<Void> handleIncomingLogsFromIM(String logMessage) {
        final LogInfoDto dto = this.mapIncomingMessage(logMessage);
        final Log saveLog = new Log();
        saveLog.setText(dto.toString());
        saveLog.setBroadcast(dto.broadcastId());
        saveLog.setTimestamp(LocalDateTime.now());
        return this.logRepository.save(saveLog)
                .doOnSuccess(savedLog -> log.info("Log saved: " + savedLog))
                .then();
    }

    private LogInfoDto mapIncomingMessage(String broadcastMessage) {
        try {
            return this.mapper.readValue(broadcastMessage, LogInfoDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Mapping log message failed" + e);
        }

    }

    public Flux<Log> readLog(String broadcastId) {
        log.info("Starting to read logs");
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick ->
                        logRepository.findLogByProcessedFalseOrderByTimestampAsc(broadcastId)
                                .flatMap(l -> {
                                    log.info("Found unprocessed log: {}", l);
                                    l.setProcessed(Boolean.TRUE);
                                    return logRepository.save(l)
                                            .doOnNext(savedLog -> log.info("Log saved as processed: {}", savedLog))
                                            .doOnError(error -> log.error("Error saving log: {}", error));
                                })
                                .switchIfEmpty(Mono.defer(Mono::empty))
                                .doOnError(error -> log.error("Error while processing logs: ", error))
                )
                .doOnSubscribe(subscription -> log.info("Subscribed to readLog"))
                .doOnNext(l -> log.info("Successfully processed log: {}", l))
                .doOnError(error -> log.error("Stream error occurred: ", error))
                .onErrorContinue((throwable, obj) ->
                        log.error("Continuing after error: {} with object: {}", throwable.getMessage(), obj)
                ).as(transactionalOperator::transactional);
    }
}
