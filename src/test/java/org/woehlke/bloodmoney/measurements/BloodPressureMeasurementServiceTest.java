package org.woehlke.bloodmoney.measurements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;

import java.time.ZoneId;
import java.util.*;

@Slf4j
@Getter
@ActiveProfiles("dev")
@SpringBootTest
public class BloodPressureMeasurementServiceTest {

    private final BloodPressureMeasurementService bloodPressureMeasurementService;
    private final BloodMoneyProperties bloodMoneyProperties;

    private final List<BloodPressureMeasurement> testData;
    private final List<BloodPressureMeasurement> testDataToAdd;
    private final int testDataHowManyPlustestDataToAdd;
    private final int testDataHowManyTestData;

    @Autowired
    public BloodPressureMeasurementServiceTest(
        BloodPressureMeasurementService bloodPressureMeasurementService,
        BloodMoneyProperties bloodMoneyProperties) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.bloodMoneyProperties = bloodMoneyProperties;
        this.testDataHowManyTestData = this.bloodMoneyProperties.getTestDataHowManyTestData();
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
        testDataHowManyPlustestDataToAdd = testDataHowManyTestData + testDataToAdd.size();
        ZoneId zoneId = ZoneId.of(BloodPressureMeasurement.ZONE_ID__ECT__EUROPE_PARIS);
        TimeZone.setDefault(TimeZone.getTimeZone(zoneId.getId()));
    }

    private void deletePersistentTestData(){
        bloodPressureMeasurementService.deleteAll();
    }

    private void persistTestData(){
        List<BloodPressureMeasurement> srcListe = this.getTestData();
        Assertions.assertNotNull(srcListe);
        for(BloodPressureMeasurement m:srcListe){
            bloodPressureMeasurementService.add(m);
        }
    }

    private void persistMuchTestData(){
        List<BloodPressureMeasurement> moreTestData = this.getTestData();
        String situation;
        int i;
        for(i = 0; i < testDataHowManyTestData*20; i++){
            situation = "LfdNr "+i+" New Measurement";
            BloodPressureMeasurement m = BloodPressureMeasurement.getInstance(situation);
            moreTestData.add(m);
        }
        i++;
        situation = "LfdNr "+i+" New Measurement - added more Testdata";
        BloodPressureMeasurement m1 = BloodPressureMeasurement.getInstance();
        BloodPressureMeasurement m2 = BloodPressureMeasurement.getInstance(situation);
        moreTestData.add(m1);
        moreTestData.add(m2);
        for(BloodPressureMeasurement m : moreTestData){
            bloodPressureMeasurementService.add(m);
        }
    }

    private void resetTestData(){
        deletePersistentTestData();
        persistTestData();
    }

    @Test
    public void deletePersitentTestDataTest(){
        log.info("TEST: deletePersitentTestDataTest");
        deletePersistentTestData();
        List<BloodPressureMeasurement> resultList = bloodPressureMeasurementService.getAll();
        int assertCountExpected = 0;
        int resultSize = resultList.size();
        Assertions.assertTrue(resultList.isEmpty(),"getAll: resultList is Empty");
        Assertions.assertEquals(assertCountExpected,resultSize,"getAll: resultList.size()");
        Assertions.assertTrue(true);
    }

    @Test
    public void persistTestDataTest(){
        log.info("TEST: persistTestDataTest");
        deletePersistentTestData();
        persistTestData();
        List<BloodPressureMeasurement> resultList = bloodPressureMeasurementService.getAll();
        int assertCountExpected = testDataHowManyTestData;
        int resultSize = resultList.size();
        Assertions.assertFalse(resultList.isEmpty(),"getAll: resultList is Empty");
        Assertions.assertEquals(assertCountExpected,resultSize,"getAll: resultList.size()");
        Assertions.assertTrue(true);
    }

    @Test
    public void getAllListTest(){
        log.info("TEST: getAllPageTest");
        resetTestData();
        List<BloodPressureMeasurement> srcListe = this.getTestData();
        List<BloodPressureMeasurement> resultList = bloodPressureMeasurementService.getAll();
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(srcListe.size(),resultList.size(),"srcListe.size()==resultList.size()");
        Assertions.assertEquals(testDataHowManyTestData,srcListe.size(),"srcListe.size()=="+testDataHowManyTestData);
        Assertions.assertEquals(testDataHowManyTestData,resultList.size(),"resultList.size()==,"+testDataHowManyTestData);
        for(int i = 0; i < testDataHowManyTestData; i++){
            BloodPressureMeasurement src = srcListe.get(i);
            BloodPressureMeasurement target = resultList.get(i);
            BloodPressureMeasurementTest.assertTransientEqualsPersistent(src,target);
        }
        Assertions.assertTrue(true);
    }

    private BloodPressureMeasurement getRandomElement(){
        long size = bloodPressureMeasurementService.count();
        int mySize = Long.valueOf(size % Long.valueOf(Integer.MAX_VALUE)).intValue();
        Random random = new Random();
        int randomIndex = random.nextInt(mySize);
        List<BloodPressureMeasurement> resultList = bloodPressureMeasurementService.getAll();
        BloodPressureMeasurement randomEntity = resultList.get(randomIndex);
        Assertions.assertNotNull(randomEntity);
        Long idSrc = randomEntity.getId();
        Assertions.assertNotNull(idSrc);
        return randomEntity;
    }

    @Test
    public void getOneTest(){
        log.info("TEST: getOneTest");
        resetTestData();
        BloodPressureMeasurement src = getRandomElement();
        Assertions.assertNotNull(src);
        Long idSrc = src.getId();
        BloodPressureMeasurement target = bloodPressureMeasurementService.getOne(idSrc);
        Long idTarget = target.getId();
        Assertions.assertNotNull(idTarget);
        Assertions.assertEquals(idSrc.longValue(), idTarget.longValue());
        Assertions.assertTrue(true);
    }

    @Test
    public void addTest(){
        log.info("TEST: addTest");
        resetTestData();
        List<BloodPressureMeasurement> srcListe = this.getTestData();
        List<BloodPressureMeasurement> moreTestDataToAdd = this.getTestDataToAdd();
        for(BloodPressureMeasurement m : moreTestDataToAdd){
            bloodPressureMeasurementService.add(m);
        }
        Assertions.assertEquals(testDataHowManyTestData,srcListe.size(),"srcListe.size()=="+testDataHowManyTestData);
        moreTestDataToAdd = this.getTestDataToAdd();
        srcListe.addAll(moreTestDataToAdd);
        List<BloodPressureMeasurement> resultList = bloodPressureMeasurementService.getAll();
        Assertions.assertNotNull(resultList,"resultList != null");
        Assertions.assertEquals(srcListe.size(),resultList.size(),"srcListe.size()==resultList.size()");
        Assertions.assertEquals(testDataHowManyPlustestDataToAdd,srcListe.size(),"srcListe.size()=="+testDataHowManyPlustestDataToAdd);
        Assertions.assertEquals(testDataHowManyPlustestDataToAdd,resultList.size(),"resultList.size()==,"+testDataHowManyPlustestDataToAdd);
        for(int i = 0; i < testDataHowManyPlustestDataToAdd; i++){
            BloodPressureMeasurement src = srcListe.get(i);
            BloodPressureMeasurement target = resultList.get(i);
            BloodPressureMeasurementTest.assertTransientEqualsPersistent(src,target);
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void updateTest(){
        log.info("TEST: getAllPageTest");
        resetTestData();
        BloodPressureMeasurement src = getRandomElement();
        Assertions.assertNotNull(src);
        String situation = "Xfcfdcdcrd";
        src.setSituation(situation);
        BloodPressureMeasurement target = bloodPressureMeasurementService.update(src);
        BloodPressureMeasurementTest.assertEquals(src,target);
        BloodPressureMeasurementTest.assertEqualsUuid(src,target);
        BloodPressureMeasurementTest.assertEqualsCreated(src,target);
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteTest(){
        log.info("TEST: deleteTest");
        resetTestData();
        BloodPressureMeasurement src = getRandomElement();
        Assertions.assertNotNull(src);
        Long id = src.getId();
        Assertions.assertNotNull(id);
        long countBefore = bloodPressureMeasurementService.count();
        bloodPressureMeasurementService.delete(src);
        long countAfter = bloodPressureMeasurementService.count();
        Assertions.assertEquals(countBefore-1,countAfter,"count after deöete");
        Optional<BloodPressureMeasurement> target = bloodPressureMeasurementService.findById(id);
        Assertions.assertTrue(target.isEmpty());
        Assertions.assertTrue(true);
    }

    @Test
    public void getAllPagedTest(){
        log.info("TEST: getAllPagedTest");
        deletePersistentTestData();
        persistMuchTestData();
        int page = 1;
        int size = 10;
        String[] fields ={"updated","created"};
        Sort sort = Sort.by(Sort.Direction.DESC, fields);
        Pageable pageable = PageRequest.of(page,size,sort);
        int getTotalPagesExpected = 22;
        long getTotalElementsExpected = 212L;
        int getSizeExpected = 10;
        int getNumberOfElementsExpected = 10;
        int getNumberOfElementsExpectedLastPage = 2;
        int getNumberExpected = 1;
        Page<BloodPressureMeasurement> resultPage;
        do {
            resultPage = bloodPressureMeasurementService.getAll(pageable);
            Assertions.assertEquals(getTotalPagesExpected, resultPage.getTotalPages(), " resultPage.getTotalPages()");
            Assertions.assertEquals(getTotalElementsExpected, resultPage.getTotalElements(), "resultPage.getTotalElements()");
            Assertions.assertEquals(getNumberExpected, resultPage.getNumber(), "resultPage.getNumber()");
            Assertions.assertEquals(getSizeExpected, resultPage.getSize(), "resultPage.getSize()");
            if(resultPage.isLast()){
                Assertions.assertEquals(getNumberOfElementsExpectedLastPage, resultPage.getNumberOfElements(), "resultPage.getNumberOfElements()");
            } else {
                Assertions.assertEquals(getNumberOfElementsExpected, resultPage.getNumberOfElements(), "resultPage.getNumberOfElements()");
            }
            pageable = resultPage.nextPageable();
            getNumberExpected++;
        } while (resultPage.hasNext());
        Assertions.assertTrue(true);
    }

}
