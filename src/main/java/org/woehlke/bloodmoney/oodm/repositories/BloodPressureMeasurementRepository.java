package org.woehlke.bloodmoney.oodm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woehlke.bloodmoney.oodm.model.BloodPressureMeasurement;

public interface BloodPressureMeasurementRepository extends JpaRepository<BloodPressureMeasurement, Long> {
}
