package org.woehlke.bloodmoney.measurements;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.UserSessionBean;
import org.woehlke.bloodmoney.user.UserSessionService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

//TODO: #48 Add a REST Controller Resource for org.woehlke.bloodmoney.measurements.BloodPressureMeasurement
/**
 * http://localhost:5000/
 * http://localhost:5000/rest/measurement/all
 */
@Slf4j
@RestController
@RequestMapping("/rest/measurement")
@SessionAttributes("userSession")
public class BloodPressureMeasurementResource {

    @GetMapping("/all")
    @ResponseBody
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<BloodPressureMeasurementEntity> getAll(
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ) {
      model = userSessionService.handleUserSession(userSessionBean, model);
      return bloodPressureMeasurementService.getAll();
    }

    @GetMapping("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
    public BloodPressureMeasurementEntity update(
        BloodPressureMeasurementEntity one,
        @PathVariable("id") long id,
        @SessionAttribute(name="userSession",required=false) UserSessionBean userSessionBean,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSessionBean, model);
        return bloodPressureMeasurementService.update(one, id);
    }

    @DeleteMapping("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
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
