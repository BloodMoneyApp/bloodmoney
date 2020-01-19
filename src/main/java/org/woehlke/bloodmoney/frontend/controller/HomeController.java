package org.woehlke.bloodmoney.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.woehlke.bloodmoney.frontend.model.UserSession;


@Controller
@SessionAttributes("userSession")
public class HomeController {

    @Autowired
    public HomeController(UserSessionControllerPart userSessionControllerPart) {
        this.userSessionControllerPart = userSessionControllerPart;
    }

    @GetMapping("/")
    public String root(@SessionAttribute(name="userSession", required=false) UserSession userSession, Model model){
        model = userSessionControllerPart.handleUserSession(userSession, model);
        return "redirect:/measurement/all";
    }

    @GetMapping("/home")
    public String home(@SessionAttribute(name="userSession", required=false) UserSession userSession, Model model){
        model = userSessionControllerPart.handleUserSession(userSession, model);
        return "redirect:/measurement/all";
    }

    private final UserSessionControllerPart userSessionControllerPart;
}
