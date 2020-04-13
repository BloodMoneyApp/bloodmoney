package org.woehlke.bloodmoney.measurements.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurementEntity;

public interface BloodPressureMeasurementRepository extends JpaRepository<BloodPressureMeasurementEntity, Long> {
}
