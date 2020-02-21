package org.woehlke.bloodmoney.oodm.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.woehlke.bloodmoney.oodm.model.BloodPressureMeasurement;

import java.util.List;

public interface BloodPressureMeasurementService {

    Page<BloodPressureMeasurement> getAll(Pageable pageable);

    BloodPressureMeasurement add(BloodPressureMeasurement one);

    BloodPressureMeasurement update(BloodPressureMeasurement one);

    void delete(BloodPressureMeasurement one);

    List<BloodPressureMeasurement> getAll();

    BloodPressureMeasurement getOne(long id);
}
