package org.woehlke.bloodmoney.domain.meso.session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.domain.meso.session.UserSessionService;
import org.woehlke.bloodmoney.domain.meso.session.UserSessionBean;


@Slf4j
@Service
public class UserSessionServiceImpl implements UserSessionService {

    private final BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    public UserSessionServiceImpl(BloodMoneyProperties bloodMoneyProperties) {
        this.bloodMoneyProperties = bloodMoneyProperties;
    }

    public Model handleUserSession(UserSessionBean userSessionBean, Model model){
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("++++ handleUserSession: "+userSessionBean.toString());
        if(userSessionBean ==null){
            userSessionBean = new UserSessionBean();
            userSessionBean.setDevTesting(bloodMoneyProperties.getDevTesting());
            model.addAttribute("userSessionBean", userSessionBean);
        }
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return model;
    }
}
