package com.global.govstack.logviewer.repository.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Data
@Table("log")
public class Log {
    @Id
    private Long id;
    private String sender;
    private String receiver;
    private String content;
    private boolean processed;
    private String broadcast;
    private LocalDateTime timestamp;
}
