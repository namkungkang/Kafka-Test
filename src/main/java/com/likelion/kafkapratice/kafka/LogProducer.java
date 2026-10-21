package com.likelion.kafkapratice.kafka;

import com.likelion.kafkapratice.dto.LogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendLog(LogRequest request) {

        String message = request.userId() + " "
                + request.action() + " "
                + request.keyword();

        kafkaTemplate.send("user-logs", message);

        System.out.println("Kafka 로그 전송: " + message);
    }
}