package com.likelion.kafkapratice.service;

import com.likelion.kafkapratice.dto.LoginRequest;
import com.likelion.kafkapratice.dto.SignupRequest;
import com.likelion.kafkapratice.entity.User;
import com.likelion.kafkapratice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    // 회원가입
    @Transactional
    public String signup(SignupRequest request) {

        // 1. 이메일 중복 검사
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        // 2. 비밀번호 암호화
        String encodedPassword =
                passwordEncoder.encode(request.password());

        // 3. 회원 객체 생성
        User user = new User(
                request.email(),
                encodedPassword,
                request.nickname()
        );

        // 4. MySQL 저장
        userRepository.save(user);

        return "회원가입 성공";
    }

    // 로그인
    @Transactional(readOnly = true)
    public String login(LoginRequest request) {

        // 1. 이메일로 회원 조회
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new IllegalArgumentException("이메일 또는 비밀번호가 틀렸습니다.")
                );

        // 2. 비밀번호 비교
        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword()
        )) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 틀렸습니다.");
        }

        // 3. 로그인 성공
        return "로그인 성공";
    }
}