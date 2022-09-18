package org.woehlke.bloodmoney.domain.db.measurements;

import org.springframework.data.jpa.repository.JpaRepository;
import org.woehlke.bloodmoney.domain.db.BloodPressureMeasurementEntity;

public interface BloodPressureMeasurementRepository extends JpaRepository<BloodPressureMeasurementEntity, Long> {
}
