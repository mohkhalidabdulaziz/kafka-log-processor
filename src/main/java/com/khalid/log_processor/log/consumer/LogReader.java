package com.khalid.log_processor.log.consumer;

import com.khalid.log_processor.log.producer.LogProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class LogReader {

    private final LogProducer logProducer;
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Value("${log.file.path}")
    private String logFilePath;

    public LogReader(LogProducer logProducer, KafkaTemplate<String, String> kafkaTemplate) {
        this.logProducer = logProducer;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    public void readLogs() throws IOException {
        Path path = Paths.get(logFilePath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(logProducer::sendLog);
        }
    }
}
