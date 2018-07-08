package ml.wonwoo.zookeepermanager.zookeeper;

public class ExistNodeException extends RuntimeException {

  private final String path;

  ExistNodeException(String path, String message) {
    super(message);
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
