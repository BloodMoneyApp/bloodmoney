package org.woehlke.bloodmoney.measurements;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BloodPressureMeasurementService {

    Page<BloodPressureMeasurement> getAll(Pageable pageable);

    BloodPressureMeasurement add(BloodPressureMeasurement one);

    BloodPressureMeasurement update(BloodPressureMeasurement one);

    void delete(BloodPressureMeasurement one);

    List<BloodPressureMeasurement> getAll();

    BloodPressureMeasurement getOne(long id);

    long count();

    void deleteAll();

    Optional<BloodPressureMeasurement> findById(Long id);

}
