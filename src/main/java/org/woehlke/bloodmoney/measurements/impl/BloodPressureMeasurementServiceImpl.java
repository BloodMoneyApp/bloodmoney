package org.woehlke.bloodmoney.measurements.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurement;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurementService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BloodPressureMeasurementServiceImpl implements BloodPressureMeasurementService {

    private final BloodPressureMeasurementRepository bloodPressureMeasurementRepository;
    private final ZoneId zone;

    @Autowired
    public BloodPressureMeasurementServiceImpl(BloodPressureMeasurementRepository bloodPressureMeasurementRepository) {
        this.bloodPressureMeasurementRepository = bloodPressureMeasurementRepository;
        this.zone = ZoneId.of(BloodPressureMeasurement.ZONE_ID__ECT__EUROPE_PARIS);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Page<BloodPressureMeasurement> getAll(Pageable pageable) {
        return this.bloodPressureMeasurementRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<BloodPressureMeasurement> getAll() {
        return this.bloodPressureMeasurementRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public BloodPressureMeasurement getOne(long id) {
        return this.bloodPressureMeasurementRepository.getOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public long count() {
        return this.bloodPressureMeasurementRepository.count();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BloodPressureMeasurement add(BloodPressureMeasurement o) {
        return this.bloodPressureMeasurementRepository.save(o);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BloodPressureMeasurement update(BloodPressureMeasurement one) {
        one.setUpdated(LocalDateTime.now(zone));
        return this.bloodPressureMeasurementRepository.save(one);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAll() {
        this.bloodPressureMeasurementRepository.deleteAll();
    }

    @Override
    public Optional<BloodPressureMeasurement> findById(Long id) {
        return this.bloodPressureMeasurementRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(BloodPressureMeasurement one) {
        this.bloodPressureMeasurementRepository.delete(one);
    }

}
