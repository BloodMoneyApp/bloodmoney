package org.woehlke.bloodmoney.domain.security.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;
import org.woehlke.bloodmoney.domain.security.vo.UserAccountVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final LoginSuccessService loginSuccessService;

    private final LocaleResolver localeResolver;

    @Autowired
    public LoginSuccessHandler(
        LoginSuccessService loginSuccessService,
        LocaleResolver localeResolver
    ) {
        super();
        this.loginSuccessService = loginSuccessService;
        this.localeResolver = localeResolver;
    }

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        UserAccountVO user = loginSuccessService.retrieveCurrentUser();
        loginSuccessService.updateLastLoginTimestamp(user);
        Locale locale = user.getDefaultLanguage();
        localeResolver.setLocale(request,response,locale);
        log.info(" Locale:              " + locale.toString());
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info(" LoginSuccessHandler: successful logged in "+user.getUserEmail());
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}
