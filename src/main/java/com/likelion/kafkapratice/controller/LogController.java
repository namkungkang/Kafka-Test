package com.likelion.kafkapratice.controller;

import com.likelion.kafkapratice.dto.LogRequest;
import com.likelion.kafkapratice.kafka.LogProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs")
public class LogController {

    private final LogProducer logProducer;

    @PostMapping
    public ResponseEntity<String> sendLog(@RequestBody LogRequest request) {

        logProducer.sendLog(request);

        return ResponseEntity.ok("로그 전송 요청 완료");
    }
}