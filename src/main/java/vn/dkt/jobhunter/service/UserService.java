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
}
