package org.woehlke.bloodmoney.measurements.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurement;

public interface BloodPressureMeasurementRepository extends JpaRepository<BloodPressureMeasurement, Long> {
}
