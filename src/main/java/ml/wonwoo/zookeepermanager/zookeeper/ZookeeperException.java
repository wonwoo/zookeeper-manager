package ml.wonwoo.zookeepermanager.zookeeper;

class ZookeeperException extends RuntimeException {

  ZookeeperException(String message, Throwable e) {
    super(message, e);
  }

}
