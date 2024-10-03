package vn.dkt.jobhunter.util.error;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.micrometer.core.ipc.http.HttpSender.Response;
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<RestResponse<Object>> validateError(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    final List<FieldError> fieldErrors = result.getFieldErrors();

    RestResponse<Object> res = new RestResponse<Object>();
    res.setStatusCode(HttpStatus.BAD_REQUEST.value());
    res.setError(ex.getBody().getDetail());

    List<String> errors = fieldErrors.stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());
    res.setMessage(errors);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }
}
