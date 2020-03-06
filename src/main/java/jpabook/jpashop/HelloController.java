package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model){
       model.addAttribute("dat1a","hello!!!");

       return "hello";
    }

    @GetMapping("hi")
    public String Hi(Model model){
        model.addAttribute("data","hello!!!");

        return "hi";
    }

}
