package org.woehlke.bloodmoney.domain.meso.session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;


@Slf4j
@Service
public class UserSessionServiceImpl implements UserSessionService {

    private final BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    public UserSessionServiceImpl(BloodMoneyProperties bloodMoneyProperties) {
        this.bloodMoneyProperties = bloodMoneyProperties;
    }

    public Model handleUserSession(UserSessionVO userSessionVO, Model model){
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("++++ handleUserSession: ");
        if(userSessionVO ==null){
            userSessionVO = new UserSessionVO();
            userSessionVO.setDevTesting(bloodMoneyProperties.getDevTesting());
            model.addAttribute("userSessionBean", userSessionVO);
        }
        log.info("++++ handleUserSession: "+ userSessionVO.toString());
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return model;
    }
}
