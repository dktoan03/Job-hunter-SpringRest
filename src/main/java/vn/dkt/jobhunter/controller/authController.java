package vn.dkt.jobhunter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.dkt.jobhunter.domain.dto.LoginDTO;

@RestController
public class authController {
  private final AuthenticationManagerBuilder authenticationManagerBuilder;

  public authController(AuthenticationManagerBuilder authenticationManagerBuilder) {
    this.authenticationManagerBuilder = authenticationManagerBuilder;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        loginDTO.getUsername(), loginDTO.getPassword());
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    return ResponseEntity.ok(loginDTO);
  }
}
