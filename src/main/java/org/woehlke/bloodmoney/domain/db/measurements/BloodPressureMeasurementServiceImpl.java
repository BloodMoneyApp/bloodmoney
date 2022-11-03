package org.woehlke.bloodmoney.domain.db.measurements;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.domain.db.BloodPressureMeasurementEntity;

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
    public BloodPressureMeasurementEntity getOne(Long id) {
        return this.bloodPressureMeasurementRepository.getReferenceById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public long count() {
        return this.bloodPressureMeasurementRepository.count();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BloodPressureMeasurementEntity add(BloodPressureMeasurementEntity o) {
        o.prepareNew();
        return this.bloodPressureMeasurementRepository.save(o);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BloodPressureMeasurementEntity update(BloodPressureMeasurementEntity one, Long id) {
        Optional<BloodPressureMeasurementEntity> persistentEntity = this.bloodPressureMeasurementRepository.findById(id);
        if(persistentEntity.isPresent()) {
            BloodPressureMeasurementEntity p = persistentEntity.get();
            p.merge(one);
            p.prepareUpdated();
            one = this.bloodPressureMeasurementRepository.save(p);
        }
        return one;
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
