package org.woehlke.bloodmoney.domain.measurements;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodPressureMeasurementRepository extends JpaRepository<BloodPressureMeasurementEntity, Long> {
}
