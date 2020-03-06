package org.woehlke.bloodmoney.measurements.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BloodPressureMeasurementServiceImpl implements BloodPressureMeasurementService {

    private final BloodPressureMeasurementRepository bloodPressureMeasurementRepository;

    @Autowired
    public BloodPressureMeasurementServiceImpl(BloodPressureMeasurementRepository bloodPressureMeasurementRepository) {
        this.bloodPressureMeasurementRepository = bloodPressureMeasurementRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Page<BloodPressureMeasurement> getAll(Pageable pageable) {
        return this.bloodPressureMeasurementRepository.findAll(pageable);
    }

    @Override
    public BloodPressureMeasurement add(BloodPressureMeasurement o) {
        ZoneId zone = ZoneId.of("Europe/Paris");
        LocalDate today = LocalDate.now(zone);
        LocalTime now = LocalTime.now(zone);
        LocalDateTime dateTimeNow = LocalDateTime.now(zone);
        o.setDateTime(dateTimeNow);
        o.setDate(today);
        o.setTime(now);
        return this.bloodPressureMeasurementRepository.save(o);
    }

    @Override
    public BloodPressureMeasurement update(BloodPressureMeasurement one) {
        ZoneId zone = ZoneId.of("Europe/Paris");
        LocalDateTime dateTimeNow = LocalDateTime.now(zone);
        one.setDateTimeUpdated(dateTimeNow);
        return this.bloodPressureMeasurementRepository.save(one);
    }

    @Override
    public void delete(BloodPressureMeasurement one) {
        this.bloodPressureMeasurementRepository.delete(one);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<BloodPressureMeasurement> getAll() {
        return this.bloodPressureMeasurementRepository.findAll();
    }

    @Override
    public BloodPressureMeasurement getOne(long id) {
        return this.bloodPressureMeasurementRepository.getOne(id);
    }
}
