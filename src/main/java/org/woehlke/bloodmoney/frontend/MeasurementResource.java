package org.woehlke.bloodmoney.frontend;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.domain.measurements.BloodPressureMeasurementEntity;
import org.woehlke.bloodmoney.domain.measurements.BloodPressureMeasurementService;
import org.woehlke.bloodmoney.domain.session.UserSessionBean;
import org.woehlke.bloodmoney.domain.session.UserSessionService;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * http://localhost:5000/
 * http://localhost:5000/rest/measurement/all
 */
@Slf4j
@RestController
@RequestMapping("/rest/measurement")
@SessionAttributes("userSession")
@PreAuthorize("isAuthenticated()")
public class MeasurementResource {

  private final BloodPressureMeasurementService bloodPressureMeasurementService;
  private final UserSessionService userSessionService;

  @Autowired
  public MeasurementResource(
    BloodPressureMeasurementService bloodPressureMeasurementService,
    UserSessionService userSessionService
  ) {
    this.bloodPressureMeasurementService = bloodPressureMeasurementService;
    this.userSessionService = userSessionService;
  }

    //TODO: #151 HTTP 406 - XML not accepted
    @GetMapping("all")
   // @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML/*, MediaType.TEXT_XML */})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML /* MediaType.APPLICATION_XML, */ })
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public Page<BloodPressureMeasurementEntity> getAll(
        @Nullable
        @PageableDefault(sort={"created"}, direction= Sort.Direction.DESC) Pageable pageable,
        @SessionAttribute(name="userSession", required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        log.info("getAll");
        if(null == pageable){
            int page=0; int size=10;
            Sort sort = Sort.by(Sort.Direction.DESC, "created");
            pageable = PageRequest.of(page, size, sort);
        }
        log.info("getAll - pageable:"+ pageable.toString());
      model = userSessionService.handleUserSession(userSessionBean, model);
        UserSessionBean u = (UserSessionBean) model.getAttribute("userSessionBean");
        log.info("getAll - userSessionBean: "+ u);
      return bloodPressureMeasurementService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public BloodPressureMeasurementEntity getOne(
        @PathVariable("id") BloodPressureMeasurementEntity one,
        @SessionAttribute(name="userSession", required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        return one;
    }

    @PutMapping("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public BloodPressureMeasurementEntity update(
        @Valid BloodPressureMeasurementEntity one,
        @PathVariable("id") long id,
        @SessionAttribute(name="userSession", required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        return bloodPressureMeasurementService.update(one, id);
    }

    @DeleteMapping(path = "/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @PreAuthorize("isAuthenticated()")
    public Response delete(
        @PathVariable("id") long id,
        @SessionAttribute(name="userSession", required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        BloodPressureMeasurementEntity one = bloodPressureMeasurementService.getOne(id);
        bloodPressureMeasurementService.delete(one);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PostMapping("/add")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public BloodPressureMeasurementEntity add(
       BloodPressureMeasurementEntity one,
       @Context UriInfo uriInfo,
       @SessionAttribute(name="userSession", required=false) UserSessionBean userSessionBean,
       Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        one = bloodPressureMeasurementService.add(one);
        return one;
    }
}
