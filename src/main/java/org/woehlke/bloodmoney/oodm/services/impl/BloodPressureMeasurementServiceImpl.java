package org.woehlke.bloodmoney.oodm.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.oodm.model.BloodPressureMeasurement;
import org.woehlke.bloodmoney.oodm.repositories.BloodPressureMeasurementRepository;
import org.woehlke.bloodmoney.oodm.services.BloodPressureMeasurementService;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BloodPressureMeasurementServiceImpl implements BloodPressureMeasurementService {

    private final BloodPressureMeasurementRepository bloodPressureMeasurementRepository;

    @Autowired
    public BloodPressureMeasurementServiceImpl(BloodPressureMeasurementRepository bloodPressureMeasurementRepository) {
        this.bloodPressureMeasurementRepository = bloodPressureMeasurementRepository;
    }

    @Override
    public Page<BloodPressureMeasurement> getAll(Pageable pageable) {
        return this.bloodPressureMeasurementRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public BloodPressureMeasurement add(BloodPressureMeasurement one) {
        return this.bloodPressureMeasurementRepository.save(one);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public BloodPressureMeasurement update(BloodPressureMeasurement one) {
        return this.bloodPressureMeasurementRepository.save(one);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void delete(BloodPressureMeasurement one) {
        this.bloodPressureMeasurementRepository.delete(one);
    }

    @Override
    public List<BloodPressureMeasurement> getAll() {
        return this.bloodPressureMeasurementRepository.findAll();
    }
}
