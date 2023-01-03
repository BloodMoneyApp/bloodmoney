package org.woehlke.bloodmoney.domain.measurements;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Slf4j
@ActiveProfiles("default")
public class MeasurementTest {

  private final static boolean featureUuid_isSettedByOurselves = true;

  public static void assertTransientEqualsPersistent(
    MeasurementEntity src,
    MeasurementEntity target
  ) {
    int assertEqualsTrueExpected = 0;
    int assertEqualsTrueActual;
    Assertions.assertNotNull(src, "src");
    Assertions.assertNotNull(target, "target");
    if (featureUuid_isSettedByOurselves) {
      Assertions.assertNotNull(src.getUuid(), "src.getUuid()");
    } else {
      Assertions.assertNull(src.getUuid(), "src.getUuid()");
    }
    Assertions.assertNotNull(target.getUuid(), "target.getUuid()");
    assertEqualsTrueActual = src.getUuid().toString().compareTo(target.getUuid().toString());
    if (featureUuid_isSettedByOurselves) {
      Assertions.assertEquals(assertEqualsTrueExpected, assertEqualsTrueActual, "getUuid");
    } else {
      Assertions.assertNotEquals(assertEqualsTrueExpected, assertEqualsTrueActual, "getUuid");
    }
    MeasurementTest.assertEquals(src, target);
  }

  public static void assertEqualsUuid(
    MeasurementEntity src,
    MeasurementEntity target
  ) {
    Assertions.assertNotNull(src, "src");
    Assertions.assertNotNull(target, "target");
    int assertEqualsTrueExpected = 0;
    int assertEqualsTrueActual;
    Assertions.assertNotNull(src.getUuid(), "src.getUuid()");
    Assertions.assertNotNull(target.getUuid(), "target.getUuid()");
    assertEqualsTrueActual = src.getUuid().toString().compareTo(target.getUuid().toString());
    Assertions.assertEquals(assertEqualsTrueExpected, assertEqualsTrueActual, "getUuid");
    MeasurementTest.assertEquals(src, target);
  }

  public static void assertEquals(
    MeasurementEntity src,
    MeasurementEntity target
  ) {
    Assertions.assertNotNull(src, "src");
    Assertions.assertNotNull(target, "target");
    int assertEqualsTrueExpected = 0;
    int assertEqualsTrueActual;
    assertEqualsTrueActual = src.getDiastolicBottomNumber().toString().compareTo(target.getDiastolicBottomNumber().toString());
    Assertions.assertEquals(assertEqualsTrueExpected, assertEqualsTrueActual, "getDiastolicBottomNumber");
    assertEqualsTrueActual = src.getSystolicTopNumber().toString().compareTo(target.getSystolicTopNumber().toString());
    Assertions.assertEquals(assertEqualsTrueExpected, assertEqualsTrueActual, "getSystolicTopNumber");
    assertEqualsTrueActual = src.getPulse().toString().compareTo(target.getPulse().toString());
    Assertions.assertEquals(assertEqualsTrueExpected, assertEqualsTrueActual, "getPulse");
    assertEqualsTrueActual = src.getSituation().compareTo(target.getSituation());
    Assertions.assertEquals(assertEqualsTrueExpected, assertEqualsTrueActual, "getSituation");
    assertEqualsTrueActual = src.getDate().compareTo(target.getDate());
    Assertions.assertEquals(assertEqualsTrueExpected, assertEqualsTrueActual, "getDate");
  }

  public static void assertEqualsUpdated(
    MeasurementEntity src,
    MeasurementEntity target
  ) {
    Assertions.assertNotNull(src, "src");
    Assertions.assertNotNull(target, "target");
    LocalDateTime updatedSrc = src.getUpdated();
    LocalDateTime updatedTarget = target.getUpdated();
    boolean bothNull = (updatedSrc == null) && (updatedTarget == null);
    String msg = "bothNull: LocalDateTime getUpdated()";
    if (bothNull) {
      log.info(msg);
    } else {
      msg = "Not bothNull: LocalDateTime getUpdated()";
      log.info(msg);
      boolean bothNotNull = (updatedSrc != null) && (updatedTarget != null);
      if (bothNotNull) {
        ZoneId zoneId = ZoneId.of(MeasurementEntity.ZONE_ID__ECT__EUROPE_PARIS);
        ZoneOffset offset = zoneId.getRules().getOffset(updatedSrc);
        msg = "src.getUpdated()toEpochSecond(offset) == target.getUpdated()toEpochSecond(offset) with offset=";
        msg += offset.toString();
        msg += " for timezone ";
        msg += MeasurementEntity.ZONE_ID__ECT__EUROPE_PARIS;
        log.info(msg);
        Assertions.assertEquals(
          updatedSrc.toEpochSecond(offset),
          updatedTarget.toEpochSecond(offset),
          msg
        );
      } else {
        Assertions.assertNotNull(updatedSrc, "LocalDateTime src.getUpdated()");
        Assertions.assertNotNull(updatedTarget, "LocalDateTime target.getUpdated()");
      }
    }
    //Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getDateTime");
    Assertions.assertNotNull(target.getId(), "target.getId()");
    Assertions.assertNotNull(target.getVersion(), "target.getVersion()");
  }

  public static void assertEqualsCreated(
    MeasurementEntity src,
    MeasurementEntity target
  ) {
    Assertions.assertNotNull(src, "src");
    Assertions.assertNotNull(target, "target");
    int srcTime = src.getTime().toSecondOfDay();
    int targetTime = target.getTime().toSecondOfDay();
    Assertions.assertEquals(srcTime, targetTime, "getTime");
  }

  public static void assertAllFieldNonNullForPersistent(MeasurementEntity o) {
    Assertions.assertNotNull(o, "o not null");
    Assertions.assertNotNull(o.getId(), "o.getId() not null");
    Assertions.assertNotNull(o.getUuid(), "o.getUuid() not null");
    Assertions.assertNotNull(o.getCreated(), "o.getCreated() not null");
    Assertions.assertNotNull(o.getUpdated(), "o.getUpdated() not null");
    Assertions.assertNotNull(o.getIp(), "o.getIp() not null");
    Assertions.assertNotNull(o.getVersion(), "o.getVersion() not null");
    Assertions.assertNotNull(o.getHostname(), "o.getHostname() not null");
    Assertions.assertNotNull(o.getHostnameCanonical(), "o.getHostnameCanonical() not null");
    Assertions.assertNotNull(o.getDiastolicBottomNumber(), "o.getDiastolicBottomNumber() not null");
    Assertions.assertNotNull(o.getSystolicTopNumber(), "o.getDiastolicBottomNumber() not null");
    Assertions.assertNotNull(o.getPulse(), "o.getPulse() not null");
    Assertions.assertNotNull(o.getSituation(), "o.getSituation() not null");
    Assertions.assertNotNull(o.getDate(), "o.getDate() not null");
    Assertions.assertNotNull(o.getTime(), "o.getTime() not null");
    Assertions.assertNotNull(o.toString(), "o.toString() not null");
  }
}
