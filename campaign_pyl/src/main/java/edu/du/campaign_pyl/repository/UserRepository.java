package edu.du.campaign_pyl.repository;

import edu.du.campaign_pyl.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserNo(Long userNo);
}
