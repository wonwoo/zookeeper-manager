package ml.wonwoo.zookeepermanager.zookeeper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

  NotFoundException(String message, Throwable e) {
    super(message, e);
  }
}