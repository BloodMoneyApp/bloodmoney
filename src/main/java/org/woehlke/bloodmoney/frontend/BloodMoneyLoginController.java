package org.woehlke.bloodmoney.frontend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.woehlke.bloodmoney.domain.security.BloodMoneyAuthorizationService;
import org.woehlke.bloodmoney.frontend.vo.LoginFormBean;
import org.woehlke.bloodmoney.domain.security.LoginSuccessService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping(path = "/user")
public class BloodMoneyLoginController {

    private final LoginSuccessService loginSuccessService;
    private final BloodMoneyAuthorizationService bloodMoneyAuthorizationService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public BloodMoneyLoginController(
        LoginSuccessService loginSuccessService,
        BloodMoneyAuthorizationService bloodMoneyAuthorizationService,
        UserDetailsService userDetailsService
    ) {
        this.loginSuccessService = loginSuccessService;
        this.bloodMoneyAuthorizationService = bloodMoneyAuthorizationService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Login Formular. If User is not logged in, this page will be displayed for
     * all page-URLs which need login.
     *
     * @param model Model
     * @return Login Screen.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public final String loginForm(Model model) {
        log.info("-------------------------------------------------------------------------------------");
        log.info("show loginForm");
        log.info("-------------------------------------------------------------------------------------");
        LoginFormBean loginFormBean = new LoginFormBean();
        model.addAttribute("loginFormBean", loginFormBean);
        log.info("-------------------------------------------------------------------------------------");
        return "user/loginForm";
    }

    /**
     * Perform login.
     *
     * @param loginFormBean LoginForm
     * @param result BindingResult
     * @param model Model
     * @return Shows Root Project after successful login or login form with error messages.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public final String loginPerform(
      @Valid LoginFormBean loginFormBean,
       BindingResult result, Model model
    ) {
        log.info("loginPerform");
        boolean authorized = bloodMoneyAuthorizationService.authorize(loginFormBean);
        if (!result.hasErrors() && authorized) {
          UserDetails user = loginSuccessService.retrieveCurrentUser();
          log.info("logged in");
          log.info("OK logged in : "+user.getUsername());
          log.info( "redirect:/home");
          return "redirect:/home";
        } else {
          String objectName = "loginForm";
          String field = "userEmail";
          String defaultMessage = "Email or Password wrong.";
          FieldError fieldError = new FieldError(objectName, field, defaultMessage);
          result.addError(fieldError);
          field = "userPassword";
          fieldError = new FieldError(objectName, field, defaultMessage);
          result.addError(fieldError);
          log.info("not logged in");
          return "user/loginForm";
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage(SessionStatus status, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        status.setComplete();
        log.info("logged out");
        return "redirect:/";
    }
}
