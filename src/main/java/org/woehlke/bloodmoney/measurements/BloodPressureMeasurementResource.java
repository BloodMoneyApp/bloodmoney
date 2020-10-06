package org.woehlke.bloodmoney.measurements;


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
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.UserSessionBean;
import org.woehlke.bloodmoney.user.UserSessionService;

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
public class BloodPressureMeasurementResource {

    @GetMapping("all")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public Page<BloodPressureMeasurementEntity> getAll(
        @Nullable
        @PageableDefault(sort={"created"}, direction= Sort.Direction.DESC) Pageable pageable,
        @SessionAttribute(name="userSession", required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        if(null == pageable){
            int page=0; int size=10;
            Sort sort = Sort.by(Sort.Direction.DESC, "created");
            pageable = PageRequest.of(page, size, sort);
        }
      model = userSessionService.handleUserSession(userSessionBean, model);
      return bloodPressureMeasurementService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public BloodPressureMeasurementEntity getOne(
        @PathVariable("id") BloodPressureMeasurementEntity one,
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
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
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
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
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
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
       @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
       Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        one = bloodPressureMeasurementService.add(one);
        return one;
    }

    private final BloodPressureMeasurementService bloodPressureMeasurementService;
    private final BloodMoneyProperties bloodMoneyProperties;
    private final UserSessionService userSessionService;

    @Autowired
    public BloodPressureMeasurementResource(BloodPressureMeasurementService bloodPressureMeasurementService, BloodMoneyProperties bloodMoneyProperties, UserSessionService userSessionService) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.bloodMoneyProperties = bloodMoneyProperties;
        this.userSessionService = userSessionService;
    }
}
