package org.woehlke.bloodmoney.measurements;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodPressureMeasurementRepository extends JpaRepository<BloodPressureMeasurement, Long> {
}
