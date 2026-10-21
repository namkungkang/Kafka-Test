package com.likelion.kafkapratice.kafka;

import com.likelion.kafkapratice.entity.UserLog;
import com.likelion.kafkapratice.repository.UserLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogConsumer {

    private final UserLogRepository userLogRepository;

    @KafkaListener(topics = "user-logs", groupId = "shopping-log-group")
    public void consume(String message) {

        System.out.println("Kafka 로그 수신: " + message);

        String[] parts = message.split(" ", 3);

        if (parts.length < 3) {
            System.out.println("잘못된 로그 형식: " + message);
            return;
        }

        Long userId = Long.parseLong(parts[0]);
        String action = parts[1];
        String keyword = parts[2];

        UserLog userLog = new UserLog(userId, action, keyword);

        userLogRepository.save(userLog);

        System.out.println("MySQL 로그 저장 완료: " + message);
    }
}