package vn.dkt.jobhunter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dkt.jobhunter.domain.User;
import vn.dkt.jobhunter.service.UserService;
import vn.dkt.jobhunter.service.error.IdInvalidException;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(this.userService.getAllUsers());
  }

  @PostMapping("/users")
  public ResponseEntity<User> createNewUser(@RequestBody User user) {
    User newUser = this.userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  @ExceptionHandler(value = IdInvalidException.class)
  public ResponseEntity<String> handleIdException(IdInvalidException idInvalidException) {
    return ResponseEntity.badRequest().body(idInvalidException.getMessage());
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) throws IdInvalidException {
    if (id > 100)
      throw new IdInvalidException("sai id");
    return ResponseEntity.ok(this.userService.getUserById(id));
  }

  @PutMapping("/users")
  public ResponseEntity<User> changeUserById(@RequestBody User newUser) {

    return ResponseEntity.ok(this.userService.updateUser(newUser));
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> deleteUserById(@PathVariable("id") long id) {
    this.userService.deleteUserById(id);
    return ResponseEntity.ok("ok");
  }
}
