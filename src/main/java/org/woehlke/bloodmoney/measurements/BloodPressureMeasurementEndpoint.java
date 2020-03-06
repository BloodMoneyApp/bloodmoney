package org.woehlke.bloodmoney.measurements;

import lombok.extern.slf4j.Slf4j;


/**
 * TODO ADD URL http://localhost:5000/
 * TODO ADD URL http://localhost:5000/api/measurement/all
 * https://spring.io/projects/spring-ws
 * https://docs.spring.io/spring-ws/docs/3.0.8.RELEASE/reference/
 * https://spring.io/guides/gs/producing-web-service/
 */
@Slf4j
//@Endpoint
public class BloodPressureMeasurementEndpoint {

    /*
    private static final String NAMESPACE_URI = "https://woehlke.org/bloodmoney/soap/ns";

    private final BloodPressureMeasurementService bloodPressureMeasurementService;
    private final BloodMoneyProperties bloodMoneyProperties;
    private final UserSessionService userSessionService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBloodPressureMeasurement")
    @ResponsePayload
    public BloodPressureMeasurementResponse getMeasurement(@RequestPayload BloodPressureMeasurementRequest request) {
        BloodPressureMeasurementResponse response = new BloodPressureMeasurementResponse();
        String uuid = request.getUuid();
        BloodPressureMeasurement o = bloodPressureMeasurementService.findBydUuid(uuid);
        response.setBloodPressureMeasurement(o);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBloodPressureMeasurements")
    @ResponsePayload
    public BloodPressureMeasurementResponse getAll(@RequestPayload BloodPressureMeasurementRequest request) {
        BloodPressureMeasurementResponse response = new BloodPressureMeasurementResponse();
        List<BloodPressureMeasurement> all = bloodPressureMeasurementService.getAll();
        response.setAllBloodPressureMeasurements(all);
        return response;
    }

    @Autowired
    public BloodPressureMeasurementEndpoint(BloodPressureMeasurementService bloodPressureMeasurementService, BloodMoneyProperties bloodMoneyProperties, UserSessionService userSessionService) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.bloodMoneyProperties = bloodMoneyProperties;
        this.userSessionService = userSessionService;
    }
    */
}
