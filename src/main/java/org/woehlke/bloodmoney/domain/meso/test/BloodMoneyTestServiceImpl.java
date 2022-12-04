package org.woehlke.bloodmoney.domain.meso.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.domain.db.BloodPressureMeasurementEntity;
import org.woehlke.bloodmoney.domain.db.measurements.BloodPressureMeasurementRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.UUID;

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
        LocalDateTime nowDateTime = LocalDateTime.now(zone);
        for(long i=0;i<1000;i++){
            LocalDate day = today.minusDays(i);
            BloodPressureMeasurementEntity o = new BloodPressureMeasurementEntity();
            o.setSystolicTopNumber(120);
            o.setDiastolicBottomNumber(80);
            o.setPulse(88);
            o.setSituation("situation "+i);
            o.setDate(day);
            o.setTime(now);
            o.setUuid(UUID.randomUUID());
            o.setCreated(nowDateTime);
            o.setUpdated(nowDateTime);
            o.setHostnameCanonical("localhost");
            o.setHostname("localhost");
            o.setIp("127.0.0.1");
            this.bloodPressureMeasurementRepository.save(o);
        }
    }
}
