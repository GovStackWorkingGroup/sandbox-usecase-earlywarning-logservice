package com.global.govstack.logviewer.contoller;

import com.global.govstack.logviewer.repository.entity.Log;
import com.global.govstack.logviewer.service.LogViewerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/log/")
public class LogController {

    private final LogViewerService logViewerService;

    @GetMapping(path = {"{broadcastId}"}, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Log> readSystemLogs(@PathVariable String broadcastId) {
        return this.logViewerService.readLog(broadcastId);
    }
}
