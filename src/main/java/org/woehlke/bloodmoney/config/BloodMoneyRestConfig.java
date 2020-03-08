package org.woehlke.bloodmoney.config;

import com.sun.research.ws.wadl.Application;
import org.woehlke.bloodmoney.measurements.exception.BloodPressureMeasurementAlreadyExistsExceptionHandler;
import org.woehlke.bloodmoney.measurements.exception.BloodPressureMeasurementNotFoundExceptionHandler;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurementResource;

import javax.ws.rs.ApplicationPath;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/resources")
public class BloodMoneyRestConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(
            Arrays.asList(
                BloodPressureMeasurementResource.class,
                BloodPressureMeasurementNotFoundExceptionHandler.class,
                BloodPressureMeasurementAlreadyExistsExceptionHandler.class));
    }
}
