package org.woehlke.bloodmoney.oodm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woehlke.bloodmoney.oodm.model.BloodPressureMeasurement;

public interface BloodPressureMeasurementRepositories  extends JpaRepository<BloodPressureMeasurement, Long> {
}
