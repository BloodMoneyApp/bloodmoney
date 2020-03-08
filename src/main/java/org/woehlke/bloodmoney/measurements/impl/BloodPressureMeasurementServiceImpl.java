package org.woehlke.bloodmoney.measurements.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurementEntity;
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
        this.zone = ZoneId.of(BloodPressureMeasurementEntity.ZONE_ID__ECT__EUROPE_PARIS);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Page<BloodPressureMeasurementEntity> getAll(Pageable pageable) {
        return this.bloodPressureMeasurementRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<BloodPressureMeasurementEntity> getAll() {
        return this.bloodPressureMeasurementRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public BloodPressureMeasurementEntity getOne(long id) {
        return this.bloodPressureMeasurementRepository.getOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public long count() {
        return this.bloodPressureMeasurementRepository.count();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BloodPressureMeasurementEntity add(BloodPressureMeasurementEntity o) {
        return this.bloodPressureMeasurementRepository.save(o);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BloodPressureMeasurementEntity update(BloodPressureMeasurementEntity one) {
        one.setUpdated(LocalDateTime.now(zone));
        return this.bloodPressureMeasurementRepository.save(one);
    }

    @Override
    public BloodPressureMeasurementEntity update(BloodPressureMeasurementEntity one, long id) {
        one.setUpdated(LocalDateTime.now(zone));
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAll() {
        this.bloodPressureMeasurementRepository.deleteAll();
    }

    @Override
    public Optional<BloodPressureMeasurementEntity> findById(Long id) {
        return this.bloodPressureMeasurementRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(BloodPressureMeasurementEntity one) {
        this.bloodPressureMeasurementRepository.delete(one);
    }

}
