package edu.du.gatewayservice.repository;

import edu.du.gatewayservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);
    Optional<User> findByUserNo(Long userNo); // userNo로 조회
} 