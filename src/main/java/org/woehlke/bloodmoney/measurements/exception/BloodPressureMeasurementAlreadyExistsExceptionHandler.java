package org.woehlke.bloodmoney.measurements.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BloodPressureMeasurementAlreadyExistsExceptionHandler implements ExceptionMapper<BloodPressureMeasurementAlreadyExistsException> {

    @Override
    public Response toResponse(BloodPressureMeasurementAlreadyExistsException exception) {
        return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
    }
}
