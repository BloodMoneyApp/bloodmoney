package org.woehlke.bloodmoney.measurements;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.session.UserSession;
import org.woehlke.bloodmoney.user.session.UserSessionService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

//TODO: #48 Add a REST Controller Resource for org.woehlke.bloodmoney.measurements.BloodPressureMeasurement
/**
 * http://localhost:5000/
 * http://localhost:5000/spring-jersey/resources/v1/bpm
 */
@Slf4j
@RestController
@RequestMapping("/rest/measurement")
@SessionAttributes("userSession")
public class BloodPressureMeasurementResource {

    @GetMapping("/all")
    @ResponseBody
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<BloodPressureMeasurement>  getAll(
        @SessionAttribute(name="userSession",required=false) UserSession userSession,
        Model model
    ) {
      model = userSessionService.handleUserSession(userSession, model);
      return bloodPressureMeasurementService.getAll();
    }

    @GetMapping("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public BloodPressureMeasurement getOne(
        @PathVariable("id") BloodPressureMeasurement one,
        @SessionAttribute(name="userSession",required=false) UserSession userSession,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSession, model);
        return one;
    }

    @PutMapping("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public BloodPressureMeasurement update(
        BloodPressureMeasurement one,
        @PathVariable("id") long id,
        @SessionAttribute(name="userSession",required=false) UserSession userSession,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSession, model);
        return bloodPressureMeasurementService.update(one, id);
    }

    @DeleteMapping("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response delete(
        @PathVariable("id") long id,
        @SessionAttribute(name="userSession",required=false) UserSession userSession,
        Model model
    ) {
        model = userSessionService.handleUserSession(userSession, model);
        BloodPressureMeasurement one = bloodPressureMeasurementService.getOne(id);
        bloodPressureMeasurementService.delete(one);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PostMapping("/add")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public BloodPressureMeasurement add(
       BloodPressureMeasurement one,
       @Context UriInfo uriInfo,
       @SessionAttribute(name="userSession",required=false) UserSession userSession,
       Model model
    ) {
        model = userSessionService.handleUserSession(userSession, model);
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
