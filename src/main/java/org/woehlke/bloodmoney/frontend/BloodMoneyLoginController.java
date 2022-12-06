package org.woehlke.bloodmoney.frontend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
import org.woehlke.bloodmoney.domain.security.login.UserAccountBean;
import org.woehlke.bloodmoney.domain.security.authorization.BloodMoneyUserAccountAuthorizationService;
import org.woehlke.bloodmoney.domain.security.vo.LoginFormBean;
import org.woehlke.bloodmoney.domain.security.login.UserAccountLoginSuccessService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@Controller
public class BloodMoneyLoginController {

    private final UserAccountLoginSuccessService userAccountLoginSuccessService;
    private final BloodMoneyUserAccountAuthorizationService bloodMoneyUserAccountAuthorizationService;
    private final UserDetailsService bloodMoneyUserDetailsService;

    @Autowired
    public BloodMoneyLoginController(
        UserAccountLoginSuccessService userAccountLoginSuccessService,
        BloodMoneyUserAccountAuthorizationService bloodMoneyUserAccountAuthorizationService,
        UserDetailsService bloodMoneyUserDetailsService
    ) {
        this.userAccountLoginSuccessService = userAccountLoginSuccessService;
        this.bloodMoneyUserAccountAuthorizationService = bloodMoneyUserAccountAuthorizationService;
        this.bloodMoneyUserDetailsService = bloodMoneyUserDetailsService;
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
        boolean authorized = bloodMoneyUserAccountAuthorizationService.authorize(loginFormBean);
        if (!result.hasErrors() && authorized) {
            UserDetails ub = this.bloodMoneyUserDetailsService.loadUserByUsername(loginFormBean.getUserEmail());
            Object principal = ub.getUsername();
            Object credentials = ub.getPassword();
            Collection<? extends GrantedAuthority> authorities = ub.getAuthorities();
            UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.authenticated(
              principal,credentials, authorities
            );
            SecurityContextHolder.getContext().setAuthentication(token);
            UserAccountBean user = userAccountLoginSuccessService.retrieveCurrentUser();
            log.info("OK logged in : "+user.getUserEmail());
            log.info( "redirect:/home");
            return "redirect:/home";
        } else {
            String objectName = "loginForm";
            String field = "userEmail";
            String defaultMessage = "Email or Password wrong.";
            FieldError e = new FieldError(objectName, field, defaultMessage);
            result.addError(e);
            log.info("NOT logged in : "+e.toString());
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
