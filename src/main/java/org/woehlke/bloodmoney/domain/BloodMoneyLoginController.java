package org.woehlke.bloodmoney.domain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.woehlke.bloodmoney.domain.security.authorization.BloodMoneyUserAccountAuthorizationService;
import org.woehlke.bloodmoney.domain.security.authorization.LoginFormBean;
import org.woehlke.bloodmoney.domain.security.login.LoginSuccessService;

import jakarta.validation.Valid;

@Slf4j
@Controller
public class BloodMoneyLoginController {

    private final LoginSuccessService loginSuccessService;

    private final BloodMoneyUserAccountAuthorizationService bloodMoneyUserAccountAuthorizationService;

    @Autowired
    public BloodMoneyLoginController(
        LoginSuccessService loginSuccessService,
        BloodMoneyUserAccountAuthorizationService bloodMoneyUserAccountAuthorizationService
    ) {
        this.loginSuccessService = loginSuccessService;
        this.bloodMoneyUserAccountAuthorizationService = bloodMoneyUserAccountAuthorizationService;
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
        LoginFormBean loginFormBean = new LoginFormBean();
        model.addAttribute("loginFormBean", loginFormBean);
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("show loginForm");
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
    public final String loginPerform(@Valid LoginFormBean loginFormBean,
                                     BindingResult result, Model model) {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info(" login Perform ");
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        boolean authorized = bloodMoneyUserAccountAuthorizationService.authorize(loginFormBean);
        if (!result.hasErrors() && authorized) {
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            log.info(" login OK ");
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //UserAccountBean user = userAccountLoginSuccessService.retrieveCurrentUser();
            //userAccountLoginSuccessService.updateLastLoginTimestamp(user);
            log.info(" login OK ");
            return "redirect:/";
          } else {
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            log.info(" login FAILED ");
            log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            String objectName = "loginForm";
            String field = "userEmail";
            String defaultMessage = "Email or Password wrong.";
            FieldError e = new FieldError(objectName, field, defaultMessage);
            result.addError(e);
            log.info("not logged in : "+e.toString());
            return "user/loginForm";
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (SessionStatus status, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        status.setComplete();
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info(" logged out ");
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return "redirect:/";
    }

}
