package ml.wonwoo.zookeepermanager.web;

import ml.wonwoo.zookeepermanager.admin.Stats;
import ml.wonwoo.zookeepermanager.config.ZookeeperProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AdminController {

  private final RestTemplate restTemplate;

  public AdminController(RestTemplateBuilder builder, ZookeeperProperties properties) {
    this.restTemplate = builder
        .rootUri(properties.getAdmin().getUri())
        .build();
  }

  @GetMapping("/connections")
  public String connections(Model model) {
    model.addAttribute("stats", this.restTemplate
        .getForEntity("/stats", Stats.class)
        .getBody());
    return "connection";
  }
}
