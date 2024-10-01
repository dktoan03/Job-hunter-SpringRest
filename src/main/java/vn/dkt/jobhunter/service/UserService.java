package vn.dkt.jobhunter.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.dkt.jobhunter.domain.User;
import vn.dkt.jobhunter.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return this.userRepository.save(user);
  }

  public User getUserById(long id) {
    return this.userRepository.findById(id).isPresent() ? this.userRepository.findById(id).get() : null;
  }

  public User updateUser(User newUser) {
    User oldUser = this.getUserById(newUser.getId());
    if (oldUser == null)
      return null;

    oldUser.setEmail(newUser.getEmail());
    oldUser.setPassword(newUser.getPassword());
    oldUser.setName(newUser.getName());
    return this.saveUser(oldUser);

  }

  public List<User> getAllUsers() {
    return this.userRepository.findAll();
  }

  public void deleteUserById(long id) {
    this.userRepository.deleteById(id);
  }

  public void deleteAllUsers() {
    this.userRepository.deleteAll();
  }
}
