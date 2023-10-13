package org.delivery.storeadmin.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class PageController {

  @RequestMapping(path = {"/main", ""})
  public ModelAndView main() {
    // view의 네임 main -> main.html을 나타낸다
    return new ModelAndView("main");
  }

  @RequestMapping("/order")
  public ModelAndView order() {
    return new ModelAndView("order");
  }
}
