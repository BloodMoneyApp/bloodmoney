package org.woehlke.bloodmoney.measurements;

import org.woehlke.bloodmoney.common.BloodMoneyEntityService;

public interface BloodPressureMeasurementService extends BloodMoneyEntityService<BloodPressureMeasurementEntity,Long> {

    @Deprecated
    BloodPressureMeasurementEntity update(BloodPressureMeasurementEntity one);

}
