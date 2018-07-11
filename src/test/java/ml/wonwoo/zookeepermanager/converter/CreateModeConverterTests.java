package ml.wonwoo.zookeepermanager.converter;

import org.apache.zookeeper.CreateMode;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;


public class CreateModeConverterTests {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void convertTest() {
    CreateModeConverter createModeConverter = new CreateModeConverter();
    CreateMode createMode = createModeConverter.convert("0");
    assertThat(createMode).isEqualTo(CreateMode.PERSISTENT);
  }

  @Test
  public void converterIllegalArgumentException() {
    exception.expect(IllegalArgumentException.class);
    CreateModeConverter createModeConverter = new CreateModeConverter();
    createModeConverter.convert("999");
  }
}