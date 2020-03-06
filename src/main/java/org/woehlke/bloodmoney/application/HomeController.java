package org.woehlke.bloodmoney.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.woehlke.bloodmoney.user.session.UserSession;
import org.woehlke.bloodmoney.user.session.UserSessionService;


@Slf4j
@Controller
@SessionAttributes("userSession")
public class HomeController {

    @GetMapping("/")
    public String root(
        @SessionAttribute(name="userSession", required=false) UserSession userSession,
        Model model
    ){
        model = userSessionService.handleUserSession(userSession, model);
        return "redirect:/measurement/all";
    }

    @GetMapping("/home")
    public String home(
        @SessionAttribute(name="userSession", required=false) UserSession userSession,
        Model model
    ){
        model = userSessionService.handleUserSession(userSession, model);
        return "redirect:/measurement/all";
    }

    private final UserSessionService userSessionService;

    @Autowired
    public HomeController(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }
}
