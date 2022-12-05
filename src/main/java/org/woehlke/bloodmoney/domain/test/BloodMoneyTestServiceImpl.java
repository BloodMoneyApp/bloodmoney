package org.woehlke.bloodmoney.domain.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.domain.measurements.BloodPressureMeasurementEntity;
import org.woehlke.bloodmoney.domain.measurements.BloodPressureMeasurementRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BloodMoneyTestServiceImpl implements BloodMoneyTestService {

    private final BloodPressureMeasurementRepository bloodPressureMeasurementRepository;

    @Autowired
    public BloodMoneyTestServiceImpl(BloodPressureMeasurementRepository bloodPressureMeasurementRepository) {
        this.bloodPressureMeasurementRepository = bloodPressureMeasurementRepository;
    }

    @Override
    public void createTestData() {
        ZoneId zone = ZoneId.of("Europe/Paris");
        LocalDate today =  LocalDate.now(zone);
        LocalTime now = LocalTime.now(zone);
        for(long i=0;i<1000;i++){
            LocalDate day = today.minusDays(i);
            Integer systolicTopNumber = 120;
            Integer diastolicBottomNumber = 80;
            Integer pulse = 88;
            Integer weight = 66;
            String situation = "situation "+i;
            BloodPressureMeasurementEntity o = BloodPressureMeasurementEntity.getInstance(
              systolicTopNumber,
              diastolicBottomNumber,
              pulse,
              weight,
              situation,
              day,
              now
            );
            this.bloodPressureMeasurementRepository.save(o);
        }
    }
}
