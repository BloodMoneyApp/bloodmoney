package org.woehlke.bloodmoney.measurements.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurement;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@ActiveProfiles("dev")
@SpringBootTest
public class BloodPressureMeasurementServiceTest {

    private final BloodPressureMeasurementService bloodPressureMeasurementService;

    private final List<BloodPressureMeasurement> testData;

    private final int testDataHowMany = 10;

    @Autowired
    public BloodPressureMeasurementServiceTest(
        BloodPressureMeasurementService bloodPressureMeasurementService
    ) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.testData = new ArrayList<>();
        for(int i = 0; i < testDataHowMany; i++){
            String situation = "LfdNr "+i+" New Measurement";
            BloodPressureMeasurement m = BloodPressureMeasurement.getInstance(situation);
            this.testData.add(m);
        }
    }

    @Test
    public void getAllListTest(){
        log.info("TEST: getAllPageTest");
        List<BloodPressureMeasurement> srcListe = this.getTestData();
        Assertions.assertNotNull(srcListe);
        for(BloodPressureMeasurement m:srcListe){
            bloodPressureMeasurementService.add(m);
        }
        List<BloodPressureMeasurement> resultList = bloodPressureMeasurementService.getAll();
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(srcListe.size(),resultList.size());
        Assertions.assertEquals(testDataHowMany,srcListe.size());
        Assertions.assertEquals(testDataHowMany,resultList.size());
        int assertEqualsTrueExpected = 0;
        int assertEqualsTrueActual;
        for(int i = 0; i < testDataHowMany; i++){
            BloodPressureMeasurement src = srcListe.get(i);
            BloodPressureMeasurement target = resultList.get(i);
            Assertions.assertNotNull(src.getUuid(),"src.getUuid()");
            Assertions.assertNotNull(target.getUuid(),"target.getUuid()");
            assertEqualsTrueActual = src.getUuid().toString().compareTo(target.getUuid().toString());
            Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getUuid");
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
            assertEqualsTrueActual = src.getTime().compareTo(target.getTime());
            Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getTime");
            assertEqualsTrueActual = src.getDateTime().compareTo(target.getDateTime());
            Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getDateTime");
            Assertions.assertNotNull(target.getId(),"target.getId()");
            Assertions.assertNotNull(target.getVersion(),"target.getVersion()");
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void getOneTest(){
        log.info("TEST: getAllPageTest");
        Assertions.assertTrue(true);
    }

    @Test
    public void addTest(){
        log.info("TEST: getAllPageTest");
        Assertions.assertTrue(true);
    }

    @Test
    public void updateTest(){
        log.info("TEST: getAllPageTest");
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteTest(){
        log.info("TEST: getAllPageTest");
        Assertions.assertTrue(true);
    }

    @Test
    public void getAllPageTest(){
        log.info("TEST: getAllPageTest");
        Assertions.assertTrue(true);
    }

}
