package com.likelion.kafkapratice.repository;

import com.likelion.kafkapratice.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}