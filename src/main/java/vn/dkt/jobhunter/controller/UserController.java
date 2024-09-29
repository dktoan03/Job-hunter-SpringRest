package vn.dkt.jobhunter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dkt.jobhunter.domain.User;
import vn.dkt.jobhunter.service.UserService;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user")
  public List<User> getAllUsers() {
    return this.userService.getAllUsers();
  }

  @PostMapping("/user")
  public User createNewUser(@RequestBody User user) {
    return this.userService.saveUser(user);
  }

  @GetMapping("/user/{id}")
  public User getUserById(@PathVariable("id") long id) {
    return this.userService.getUserById(id);
  }

  @PutMapping("/user")
  public User changeUserById(@RequestBody User newUser) {

    return this.userService.updateUser(newUser);
  }

  @DeleteMapping("/user/{id}")
  public String deleteUserById(@PathVariable("id") long id) {
    this.userService.deleteUserById(id);
    return "ok";
  }
}
