package com.khalid.log_processor.log.consumer;


import com.khalid.log_processor.entity.LogEntity;
import com.khalid.log_processor.repo.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogConsumer {

    private final LogRepository logRepository;

    public LogConsumer(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @KafkaListener(topics = "app-logs", groupId = "log-processing-group")
    public void consume(String message) {
        if (message.contains("ERROR")) {
         //   log.info("Processing error log: {}", message);
            LogEntity logEntity = new LogEntity(message, "ERROR");
            logRepository.save(logEntity);
        }
    }
}
