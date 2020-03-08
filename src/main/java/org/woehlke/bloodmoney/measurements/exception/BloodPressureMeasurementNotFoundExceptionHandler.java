package org.woehlke.bloodmoney.measurements.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BloodPressureMeasurementNotFoundExceptionHandler implements ExceptionMapper<BloodPressureMeasurementNotFoundException>  {

    @Override
    public Response toResponse(BloodPressureMeasurementNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
