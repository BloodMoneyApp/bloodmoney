package org.woehlke.bloodmoney.frontend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

  @RequestMapping("/fehler")
  public String handleError(HttpServletRequest request, Model model) {
    Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
    log.info("exceptionMessage: " + exception);
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if (status != null) {
      log.info("statusCode: " + status.toString());
      Integer statusCode = Integer.valueOf(status.toString());
      if (statusCode == HttpStatus.FORBIDDEN.value()) {
        return "error/error-403";
      }
      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        return "error/error-404";
      } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        return "error/error-500";
      }
    }
    model.addAttribute("exceptionMessage", exception);
    return "error/error";
  }

  public String getErrorPath() {
    return "/fehler";
  }
}
