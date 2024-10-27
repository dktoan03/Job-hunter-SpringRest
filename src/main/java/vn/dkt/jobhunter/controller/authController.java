package vn.dkt.jobhunter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.dkt.jobhunter.domain.dto.LoginDTO;
import vn.dkt.jobhunter.domain.dto.ResponseLoginDTO;
import vn.dkt.jobhunter.util.SecurityUtil;

@RestController
public class authController {
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final SecurityUtil securityUtil;

  public authController(AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil) {
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.securityUtil = securityUtil;
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        loginDTO.getUsername(), loginDTO.getPassword());
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

    String access_token = this.securityUtil.createToken(authentication);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO();
    responseLoginDTO.setAccessToken(access_token);
    return ResponseEntity.ok(responseLoginDTO);
  }
}
