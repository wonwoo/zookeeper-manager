package ml.wonwoo.zookeepermanager.web;

import ml.wonwoo.zookeepermanager.zookeeper.ExistNodeException;
import ml.wonwoo.zookeepermanager.zookeeper.Zoo;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManagerController {

  private final Zoo zoo;

  public ManagerController(Zoo zoo) {
    this.zoo = zoo;
  }

  @PostMapping("/")
  public String createZoo(String[] path, CreateMode createMode) {
    String str = StringUtils.arrayToDelimitedString(path, "/");
    String node = zoo.createNode(str, createMode);
    return "redirect:/zoo?create&path=" + node;
  }

  @GetMapping("/zoo")
  public String getZoo(String path, Model model) {
    byte[] data = zoo.getData(path);
    model.addAttribute("nodes", zoo.zooChild(path));
    model.addAttribute("node", zoo.existNode(path));
    model.addAttribute("data", data == null ? "" : new String(data));
    model.addAttribute("p", path);
    return "detail.html";
  }

  @PostMapping("/zoo")
  public String createData(String path, String data) {
    zoo.setData(path, data.getBytes());
    return "redirect:/zoo?data&path=" + path;
  }

  @DeleteMapping("/zoo")
  public String deleteZoo(String path) {
    zoo.deleteNode(path);
    return "redirect:/";
  }

  @ExceptionHandler(ExistNodeException.class)
  public String existNodeException(ExistNodeException e) {
    return "redirect:/zoo?exist&path=" + e.getPath();
  }
}
