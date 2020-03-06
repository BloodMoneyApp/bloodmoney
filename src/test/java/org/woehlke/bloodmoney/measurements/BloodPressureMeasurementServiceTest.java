package org.woehlke.bloodmoney.measurements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@ActiveProfiles("dev")
@SpringBootTest
public class BloodPressureMeasurementServiceTest {

    private final BloodPressureMeasurementService bloodPressureMeasurementService;

    private final List<BloodPressureMeasurement> testData;

    private final List<BloodPressureMeasurement> testDataToAdd;

    private final int testDataHowManyTestData = 10;

    private final int testDataHowManyPlustestDataToAdd;

    private final boolean featureUuid_isSettedByOurselves = true;

    @Autowired
    public BloodPressureMeasurementServiceTest(
        BloodPressureMeasurementService bloodPressureMeasurementService
    ) throws UnknownHostException {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.testData = new ArrayList<>();
        this.testDataToAdd = new ArrayList<>();
        for(int i = 0; i < testDataHowManyTestData; i++){
            String situation = "LfdNr "+i+" New Measurement";
            BloodPressureMeasurement m = BloodPressureMeasurement.getInstance(situation);
            this.testData.add(m);
        }
        String situation = "added more Testdata";
        BloodPressureMeasurement m1 = BloodPressureMeasurement.getInstance();
        BloodPressureMeasurement m2 = BloodPressureMeasurement.getInstance(situation);
        testDataToAdd.add(m1);
        testDataToAdd.add(m2);
        testDataHowManyPlustestDataToAdd = testDataHowManyTestData + 2;
    }

    private void deletePersitentTestData(){
        bloodPressureMeasurementService.deleteAll();
    }

    private void persistTestData(){
        List<BloodPressureMeasurement> srcListe = this.getTestData();
        Assertions.assertNotNull(srcListe);
        for(BloodPressureMeasurement m:srcListe){
            bloodPressureMeasurementService.add(m);
        }
    }

    @Test
    public void deletePersitentTestDataTest(){
        log.info("TEST: deletePersitentTestDataTest");
        deletePersitentTestData();
        List<BloodPressureMeasurement> resultList = bloodPressureMeasurementService.getAll();
        int assertCountExpected = 0;
        int resultSize = resultList.size();
        Assertions.assertTrue(resultList.isEmpty(),"getAll: resultList is Empty");
        Assertions.assertEquals(assertCountExpected,resultSize,"getAll: resultList.size()");
    }

    @Test
    public void persistTestDataTest(){
        log.info("TEST: persistTestData");
        deletePersitentTestData();
        persistTestData();
        List<BloodPressureMeasurement> resultList = bloodPressureMeasurementService.getAll();
        int assertCountExpected = 0;
        int resultSize = resultList.size();
        Assertions.assertTrue(resultList.isEmpty(),"getAll: resultList is Empty");
        Assertions.assertEquals(assertCountExpected,resultSize,"getAll: resultList.size()");
    }

    @Test
    public void getAllListTest(){
        log.info("TEST: getAllPageTest");
        deletePersitentTestData();
        persistTestData();
        List<BloodPressureMeasurement> srcListe = this.getTestData();
        List<BloodPressureMeasurement> resultList = bloodPressureMeasurementService.getAll();
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(srcListe.size(),resultList.size(),"srcListe.size()==resultList.size()");
        Assertions.assertEquals(testDataHowManyTestData,srcListe.size(),"srcListe.size()=="+testDataHowManyTestData);
        Assertions.assertEquals(testDataHowManyTestData,resultList.size(),"resultList.size()==,"+testDataHowManyTestData);
        Assertions.assertTrue(true);
        for(int i = 0; i < testDataHowManyTestData; i++){
            BloodPressureMeasurement src = srcListe.get(i);
            BloodPressureMeasurement target = resultList.get(i);
            this.assertEquals(src,target);
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void getOneTest(){
        log.info("TEST: getOneTest");

        Assertions.assertTrue(true);
    }

    @Test
    public void addTest(){
        log.info("TEST: addTest");
        persistTestData();
        List<BloodPressureMeasurement> srcListe = this.getTestData();
        List<BloodPressureMeasurement> moreTestDataToAdd = this.getTestDataToAdd();
        for(BloodPressureMeasurement m : moreTestDataToAdd){
            bloodPressureMeasurementService.add(m);
        }
        moreTestDataToAdd = this.getTestDataToAdd();
        srcListe.addAll(moreTestDataToAdd);
        List<BloodPressureMeasurement> resultList = bloodPressureMeasurementService.getAll();
        Assertions.assertNotNull(resultList,"resultList != null");
        Assertions.assertEquals(srcListe.size(),resultList.size(),"srcListe.size()==resultList.size()");
        Assertions.assertEquals(testDataHowManyTestData,srcListe.size(),"srcListe.size()=="+testDataHowManyTestData);
        Assertions.assertEquals(testDataHowManyPlustestDataToAdd,resultList.size(),"resultList.size()==,"+testDataHowManyPlustestDataToAdd);
        for(int i = 0; i < testDataHowManyPlustestDataToAdd; i++){
            BloodPressureMeasurement src = srcListe.get(i);
            BloodPressureMeasurement target = resultList.get(i);
            this.assertEquals(src,target);
        }
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

    private void assertEquals(
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
        assertEqualsTrueActual = src.getDateTime().compareTo(target.getDateTime());
        Assertions.assertEquals(assertEqualsTrueExpected,assertEqualsTrueActual,"getDateTime");
        Assertions.assertNotNull(target.getId(),"target.getId()");
        Assertions.assertNotNull(target.getVersion(),"target.getVersion()");
    }
}
