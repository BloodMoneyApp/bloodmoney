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
      log.info(sc.getServletContextName());
      log.info(sc.getServerInfo());
      log.info(sc.getVirtualServerName());
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
  }
}