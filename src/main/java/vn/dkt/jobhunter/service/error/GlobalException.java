package vn.dkt.jobhunter.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.dkt.jobhunter.domain.RestResponse;

@RestControllerAdvice
public class GlobalException {
  @ExceptionHandler(value = IdInvalidException.class)
  public ResponseEntity<RestResponse<Object>> handleIdException(IdInvalidException idInvalidException) {
    RestResponse<Object> res = new RestResponse<Object>();
    res.setStatusCode(HttpStatus.BAD_REQUEST.value());
    res.setMessage("IdInvalidException");
    res.setError(idInvalidException.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    
  }
}
