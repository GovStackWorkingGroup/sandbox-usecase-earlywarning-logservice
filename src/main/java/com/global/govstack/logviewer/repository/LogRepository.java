package com.global.govstack.logviewer.repository;

import com.global.govstack.logviewer.repository.entity.Log;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface LogRepository extends ReactiveCrudRepository<Log, Long> {
    @Query("SELECT l.* FROM LOG l WHERE l.processed is false and l.broadcast = :broadcastId order by l.timestamp asc limit 1")
    Mono<Log> findLogByProcessedFalseOrderByTimestampAsc(String broadcastId);
}
