package org.woehlke.bloodmoney.measurements;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
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
@Path("/bpm")
public class BloodPressureMeasurementResource {

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<BloodPressureMeasurement>  getAll(
        @Context UriInfo uriInfo,
        Model model
    ) {
      return bloodPressureMeasurementService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public BloodPressureMeasurement getOne(
        @PathParam("id") long id
    ) {
        return bloodPressureMeasurementService.getOne(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(
        BloodPressureMeasurement one,
        @PathParam("id") long id
    ) {
        bloodPressureMeasurementService.update(one, id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteEmployee(@PathParam("id") int id) {
        BloodPressureMeasurement one = bloodPressureMeasurementService.getOne(id);
        bloodPressureMeasurementService.delete(one);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public final Response add(
       BloodPressureMeasurement one,
       @Context UriInfo uriInfo
    ) {
        one = bloodPressureMeasurementService.add(one);
        return Response.status(Response.Status.CREATED.getStatusCode()).header("Location", String.format("%s/%s", uriInfo.getAbsolutePath().toString(), one.getId())).build();
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
