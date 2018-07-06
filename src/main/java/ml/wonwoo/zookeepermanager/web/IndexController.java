package ml.wonwoo.zookeepermanager.web;

import ml.wonwoo.zookeepermanager.zookeeper.Zoo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  private final Zoo zoo;

  public IndexController(Zoo zoo) {
    this.zoo = zoo;
  }

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("nodes", zoo.zooChild("/"));
    return "index";
  }
}
