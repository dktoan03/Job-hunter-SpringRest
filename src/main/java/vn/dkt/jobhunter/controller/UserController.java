package vn.dkt.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.dkt.jobhunter.domain.User;
import vn.dkt.jobhunter.service.UserService;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/create")
  public String createNewUser() {
    User newUser = new User();
    newUser.setName("dkt");
    newUser.setEmail("dkt");
    newUser.setPassword("123456");
    this.userService.saveUser(newUser);
    return this.userService.getAllUsers().toString();
  }
}
