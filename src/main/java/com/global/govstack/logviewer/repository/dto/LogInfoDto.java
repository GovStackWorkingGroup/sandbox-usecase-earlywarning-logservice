package com.global.govstack.logviewer.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record LogInfoDto (String from, String to, String content, LocalDateTime timeStamp, String broadcastId) {
}