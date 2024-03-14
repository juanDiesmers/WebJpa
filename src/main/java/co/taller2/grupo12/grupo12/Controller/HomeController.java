package co.taller2.grupo12.grupo12.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupo12")
public class HomeController {

    @GetMapping("/Home")
    public ModelAndView losPipolSoloTexto() {
        return new ModelAndView("Home");
    }

    @GetMapping("/Login")
    public ModelAndView losPipolTemplateModelAndView() {
        return new ModelAndView("Login");
    }

}
