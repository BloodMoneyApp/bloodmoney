package org.woehlke.bloodmoney.test.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurement;
import org.woehlke.bloodmoney.measurements.impl.BloodPressureMeasurementRepository;
import org.woehlke.bloodmoney.test.TestService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TestServiceImpl implements TestService {

    private final BloodPressureMeasurementRepository bloodPressureMeasurementRepository;

    @Autowired
    public TestServiceImpl(BloodPressureMeasurementRepository bloodPressureMeasurementRepository) {
        this.bloodPressureMeasurementRepository = bloodPressureMeasurementRepository;
    }

    @Override
    public void createTestData() {
        ZoneId zone = ZoneId.of("Europe/Paris");
        LocalDate today =  LocalDate.now(zone);
        LocalTime now = LocalTime.now(zone);
        for(long i=0;i<1000;i++){
            LocalDate day = today.minusDays(i);
            BloodPressureMeasurement o = new BloodPressureMeasurement();
            o.setSystolicTopNumber(120);
            o.setDiastolicBottomNumber(80);
            o.setPulse(88);
            o.setSituation("situation "+i);
            o.setDate(day);
            o.setTime(now);
            this.bloodPressureMeasurementRepository.save(o);
        }
    }
}
