package vn.dkt.jobhunter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.dkt.jobhunter.domain.User;
import vn.dkt.jobhunter.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User saveUser(User user) {
    return this.userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return this.userRepository.findAll();
  }
}
