package ml.wonwoo.zookeepermanager.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.DeleteBuilder;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Zoo {

  private final CuratorFramework curator;

  public Zoo(CuratorFramework curator) {
    this.curator = curator;
  }

  public List<String> zooChild(String path) {

    try {
      return curator.getChildren().forPath(rootPath(path));
    } catch (Exception e) {
      throw new ZookeeperException("get child error", e);
    }
  }

  public String createNode(String path, CreateMode createModes, byte[] payload) {
    try {
      return curator.create().withMode(createModes).forPath(rootPath(path), payload);
    } catch (Exception e) {
      throw new ZookeeperException("create node error", e);
    }
  }

  public Stat setData(String path, byte[] payload) {
    try {
      return curator.setData().forPath(rootPath(path), payload);
    } catch (Exception e) {
      throw new ZookeeperException("create data error", e);
    }
  }

  public byte[] getData(String path) {
    try {
      return curator.getData().forPath(rootPath(path));
    } catch (Exception e) {
      throw new NotFoundException("get data error", e);
    }
  }

  public String createNode(String path, CreateMode createModes) {
    if (this.existNode(rootPath(path)) != null) {
      throw new ExistNodeException(path, path + " is exist");
    }
    return this.createNode(rootPath(path), createModes, new byte[0]);
  }

  public Stat existNode(String path) {
    try {
      return curator.checkExists().forPath(rootPath(path));
    } catch (Exception e) {
      throw new ZookeeperException("delete node error", e);
    }
  }

  public void deleteNode(String path, boolean isChild) {
    try {
      DeleteBuilder delete = curator.delete();
      if (isChild) {
        delete.deletingChildrenIfNeeded();
      }
      delete.forPath(rootPath(path));
    } catch (Exception e) {
      throw new ZookeeperException("delete node error", e);
    }
  }

  private String rootPath(String path) {
    return path.startsWith("/") ? path : "/" + path;
  }
}
