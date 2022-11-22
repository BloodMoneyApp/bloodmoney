package org.woehlke.bloodmoney.domain.measurements;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodPressureMeasurementRepository extends JpaRepository<BloodPressureMeasurementEntity, Long> {
}
