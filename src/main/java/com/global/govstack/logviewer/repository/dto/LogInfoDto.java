package com.global.govstack.logviewer.repository.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LogInfoDto (String from, String to, String content, LocalDateTime timeStamp, String broadcastId) {
}