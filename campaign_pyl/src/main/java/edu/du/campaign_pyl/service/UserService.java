package edu.du.campaign_pyl.service;

import edu.du.campaign_pyl.entity.Users;
import edu.du.campaign_pyl.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getUserByUserNo(Long userNo) {
        return userRepository.findByUserNo(userNo);
    }
}
