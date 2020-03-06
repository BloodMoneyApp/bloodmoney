package org.woehlke.bloodmoney.measurements;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Slf4j
@ActiveProfiles("dev")
public class BloodPressureMeasurementTest {

    private final static boolean featureUuid_isSettedByOurselves = true;

    public static void assertTransientEqualsPersistent(
        BloodPressureMeasurement src ,
        BloodPressureMeasurement target
    ){
        int assertEqualsTrueExpected = 0;
        int assertEqualsTrueActual;
        Assertions.assertNotNull(src,"src");
        Assertions.assertNotNull(target,"target");
        if(featureUuid_isSettedByOurselves){
            Assertions.assertNotNull(src.getUuid(),"src.getUuid()");
        } else {
            Assertions.assertNull(src.getUuid(),"src.getUuid()");
        }
        Assertions.assertNotNull(target.getUuid(),"target.getUuid()");
        assertEqualsTrueActual = src.getUuid().toString().compareTo(target.getUuid().toString());
        if(featureUuid_isSettedByOurselves){
            Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getUuid");
        } else {
            Assertions.assertNotEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getUuid");
        }
        BloodPressureMeasurementTest.assertEquals(src, target);
    }

    public static void assertEqualsBothPersistent(
        BloodPressureMeasurement src ,
        BloodPressureMeasurement target
    ){
        int assertEqualsTrueExpected = 0;
        int assertEqualsTrueActual;
        Assertions.assertNotNull(src,"src");
        Assertions.assertNotNull(target,"target");
        Assertions.assertNotNull(src.getUuid(),"src.getUuid()");
        Assertions.assertNotNull(target.getUuid(),"target.getUuid()");
        assertEqualsTrueActual = src.getUuid().toString().compareTo(target.getUuid().toString());
        Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getUuid");
        BloodPressureMeasurementTest.assertEquals(src,target);
    }

    public static void assertEquals(
        BloodPressureMeasurement src ,
        BloodPressureMeasurement target
    ){
        int assertEqualsTrueExpected = 0;
        int assertEqualsTrueActual;
        Assertions.assertNotNull(src,"src");
        Assertions.assertNotNull(target,"target");
        assertEqualsTrueActual = src.getDiastolicBottomNumber().toString().compareTo(target.getDiastolicBottomNumber().toString());
        Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getDiastolicBottomNumber");
        assertEqualsTrueActual = src.getSystolicTopNumber().toString().compareTo(target.getSystolicTopNumber().toString());
        Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getSystolicTopNumber");
        assertEqualsTrueActual = src.getPulse().toString().compareTo(target.getPulse().toString());
        Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getPulse");
        assertEqualsTrueActual = src.getSituation().compareTo(target.getSituation());
        Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getSituation");
        assertEqualsTrueActual = src.getDate().compareTo(target.getDate());
        Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getDate");
        int srcTime = src.getTime().toSecondOfDay();
        int targetTime = target.getTime().toSecondOfDay();
        Assertions.assertEquals(srcTime,targetTime,"getTime");
        assertEqualsTrueActual = src.getCreated().compareTo(target.getCreated());
        LocalDateTime updatedSrc = src.getUpdated();
        LocalDateTime updatedTarget = target.getUpdated();
        boolean bothNull = (updatedSrc==null) && (updatedTarget==null);
        String msg = "bothNull: LocalDateTime getUpdated()";
        if(bothNull) {
            log.info(msg);
        } else {
            msg = "Not bothNull: LocalDateTime getUpdated()";
            log.info(msg);
            boolean bothNotNull = (updatedSrc != null) && (updatedTarget != null);
            if (bothNotNull) {
                ZoneId zoneId = ZoneId.of(BloodPressureMeasurement.ZONE_ID__ECT__EUROPE_PARIS);
                ZoneOffset offset = zoneId.getRules().getOffset(updatedSrc);
                msg = "src.getUpdated()toEpochSecond(offset) == target.getUpdated()toEpochSecond(offset) with offset=";
                msg += offset.toString();
                msg += " for timezone ";
                msg += BloodPressureMeasurement.ZONE_ID__ECT__EUROPE_PARIS;
                log.info(msg);
                Assertions.assertEquals(
                    updatedSrc.toEpochSecond(offset),
                    updatedTarget.toEpochSecond(offset),
                    msg
                );
            } else {
                Assertions.assertNotNull(updatedSrc,"LocalDateTime src.getUpdated()");
                Assertions.assertNotNull(updatedTarget,"LocalDateTime target.getUpdated()");
            }
        }
        Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getDateTime");
        Assertions.assertNotNull(target.getId(),"target.getId()");
        Assertions.assertNotNull(target.getVersion(),"target.getVersion()");
    }
}
