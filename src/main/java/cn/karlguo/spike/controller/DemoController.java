package cn.karlguo.spike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @Classname DemoController
 * @Date 2021/5/11
 * @author karlguo
 * @Description
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    /**
        *
        [model]
        * @return java.lang.String
        * @Description 
        */
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "karlguo");
        return "hello";
    }
}
