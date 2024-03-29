package com.bowwowcare.sm.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 이메일로 사용자 계정 단건 조회
     * @param email 조회할 대상 계정의 이메일
     * @return Optional 객체 (account 객체 있는 경우), 빈 Optional 객체 (account 가 없는 경우)
     */
    Optional<Member> findByEmail(String email);
}
