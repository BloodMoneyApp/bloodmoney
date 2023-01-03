package org.woehlke.bloodmoney.domain.measurements;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MeasurementService {

  List<MeasurementEntity> getAll();

  Page<MeasurementEntity> getAll(Pageable pageable);

  long count();

  MeasurementEntity getOne(Long id);

  Optional<MeasurementEntity> findById(Long id);

  MeasurementEntity add(MeasurementEntity one);

  MeasurementEntity update(MeasurementEntity one, Long id);

  void delete(MeasurementEntity one);

  void deleteAll();
}
