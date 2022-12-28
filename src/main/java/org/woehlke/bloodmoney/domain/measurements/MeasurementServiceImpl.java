package org.woehlke.bloodmoney.domain.measurements;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final ZoneId zone;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
        this.zone = ZoneId.of(MeasurementEntity.ZONE_ID__ECT__EUROPE_PARIS);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Page<MeasurementEntity> getAll(Pageable pageable) {
        return this.measurementRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<MeasurementEntity> getAll() {
        return this.measurementRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public MeasurementEntity getOne(Long id) {
        return this.measurementRepository.getReferenceById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public long count() {
        return this.measurementRepository.count();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MeasurementEntity add(MeasurementEntity o) {
        o.prepareNew();
        return this.measurementRepository.save(o);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MeasurementEntity update(MeasurementEntity one, Long id) {
        Optional<MeasurementEntity> persistentEntity = this.measurementRepository.findById(id);
        if(persistentEntity.isPresent()) {
            MeasurementEntity p = persistentEntity.get();
            p.merge(one);
            p.prepareUpdated();
            one = this.measurementRepository.save(p);
        }
        return one;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAll() {
        this.measurementRepository.deleteAll();
    }

    @Override
    public Optional<MeasurementEntity> findById(Long id) {
        return this.measurementRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(MeasurementEntity one) {
        this.measurementRepository.delete(one);
    }

}
