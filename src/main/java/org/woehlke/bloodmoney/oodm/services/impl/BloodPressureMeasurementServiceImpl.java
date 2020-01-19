package org.woehlke.bloodmoney.oodm.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.oodm.model.BloodPressureMeasurement;
import org.woehlke.bloodmoney.oodm.repositories.BloodPressureMeasurementRepositories;
import org.woehlke.bloodmoney.oodm.services.BloodPressureMeasurementService;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BloodPressureMeasurementServiceImpl implements BloodPressureMeasurementService {

    private final BloodPressureMeasurementRepositories bloodPressureMeasurementRepositories;

    @Autowired
    public BloodPressureMeasurementServiceImpl(BloodPressureMeasurementRepositories bloodPressureMeasurementRepositories) {
        this.bloodPressureMeasurementRepositories = bloodPressureMeasurementRepositories;
    }

    @Override
    public Page<BloodPressureMeasurement> getAll(Pageable pageable) {
        return this.bloodPressureMeasurementRepositories.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public BloodPressureMeasurement add(BloodPressureMeasurement one) {
        return this.bloodPressureMeasurementRepositories.save(one);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public BloodPressureMeasurement update(BloodPressureMeasurement one) {
        return this.bloodPressureMeasurementRepositories.save(one);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void delete(BloodPressureMeasurement one) {
        this.bloodPressureMeasurementRepositories.delete(one);
    }

    @Override
    public List<BloodPressureMeasurement> getAll() {
        return this.bloodPressureMeasurementRepositories.findAll();
    }
}
