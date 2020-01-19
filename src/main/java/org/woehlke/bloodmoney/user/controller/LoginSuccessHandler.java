package org.woehlke.bloodmoney.user.controller;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.woehlke.bloodmoney.user.model.UserAccount;
import org.woehlke.bloodmoney.user.services.UserAccountLoginSuccessService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Log
@Component
public class LoginSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserAccountLoginSuccessService userAccountLoginSuccessService;

    private final LocaleResolver localeResolver;

    @Autowired
    public LoginSuccessHandler(UserAccountLoginSuccessService userAccountLoginSuccessService, LocaleResolver localeResolver) {
        super();
        this.userAccountLoginSuccessService = userAccountLoginSuccessService;
        this.localeResolver = localeResolver;
    }

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);
        UserAccount user = userAccountLoginSuccessService.retrieveCurrentUser();
        userAccountLoginSuccessService.updateLastLoginTimestamp(user);
        Locale locale = user.getDefaultLanguage();
        localeResolver.setLocale(request,response,locale);
        log.info("successful logged in "+user.getUserEmail());
    }

}
