package vn.dkt.jobhunter.service.error;

public class IdInvalidException extends Exception {
  public IdInvalidException() {
  }

  public IdInvalidException(String message) {
    super(message);
  }
}
