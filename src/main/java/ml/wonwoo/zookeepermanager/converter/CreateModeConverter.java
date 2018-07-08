package ml.wonwoo.zookeepermanager.converter;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateModeConverter implements Converter<String, CreateMode> {

  @Override
  public CreateMode convert(String source) {
    try {
      return CreateMode.fromFlag(Integer.valueOf(source));
    } catch (KeeperException e) {
      throw new RuntimeException(e);
    }
  }
}
