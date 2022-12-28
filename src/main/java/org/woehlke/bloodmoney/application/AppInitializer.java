package org.woehlke.bloodmoney.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;

@Slf4j
public class AppInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext sc) {
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      log.info(" Application on Startup: ");
      log.info(" getServletContextName: ");
      log.info(sc.getServletContextName());
      log.info(" getServerInfo: ");
      log.info(sc.getServerInfo());
      log.info(" getVirtualServerName: ");
      log.info(sc.getVirtualServerName());

      /*
    log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    log.info(" securityFilterChain ");
    log.info("-------------------------------------------------------------------------------------");
    log.info(" getAntMatchersPermitAll:  ");
    for (String urlPath : this.bloodMoneyProperties.getWebSecurity().getAntMatchersPermitAll()) {
      log.info(urlPath);
    }
    log.info("-------------------------------------------------------------------------------------");
    log.info(" getAntPatternsPublic:  ");
    for (String urlPath : this.bloodMoneyProperties.getWebSecurity().getAntPatternsPublic()) {
      log.info(urlPath);
    }
    log.info("-------------------------------------------------------------------------------------");
    log.info(" getAntMatchersFullyAuthenticated:  ");
    log.info(this.bloodMoneyProperties.getWebSecurity().getAntMatchersFullyAuthenticated());
    log.info("-------------------------------------------------------------------------------------");
    log.info(" application Properties:  " + this.bloodMoneyProperties.toString());
    log.info("-------------------------------------------------------------------------------------");
    log.info(" configure SecurityFilterChain from HttpSecurity http ");
    */

      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
  }
}
