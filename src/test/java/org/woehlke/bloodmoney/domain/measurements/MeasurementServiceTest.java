package org.woehlke.bloodmoney.domain.measurements;

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
@ActiveProfiles("default")
@SpringBootTest
public class MeasurementServiceTest {

    private final MeasurementService measurementService;
    private final BloodMoneyProperties bloodMoneyProperties;

    private final List<BloodPressureMeasurementEntity> testData;
    private final List<BloodPressureMeasurementEntity> testDataToAdd;
    private final int testDataHowManyPlustestDataToAdd;
    private final int testDataHowManyTestData;

    @Autowired
    public MeasurementServiceTest(
        MeasurementService measurementService,
        BloodMoneyProperties bloodMoneyProperties) {
        this.measurementService = measurementService;
        this.bloodMoneyProperties = bloodMoneyProperties;
        this.testDataHowManyTestData = this.bloodMoneyProperties.getTestDataHowManyTestData();
        this.testData = new ArrayList<>();
        this.testDataToAdd = new ArrayList<>();
        for(int i = 0; i < testDataHowManyTestData; i++){
            String situation = "LfdNr "+i+" New Measurement";
            BloodPressureMeasurementEntity m = BloodPressureMeasurementEntity.getInstance(situation);
            this.testData.add(m);
        }
        String situation = "added more Testdata";
        BloodPressureMeasurementEntity m1 = BloodPressureMeasurementEntity.getInstance();
        BloodPressureMeasurementEntity m2 = BloodPressureMeasurementEntity.getInstance(situation);
        testDataToAdd.add(m1);
        testDataToAdd.add(m2);
        testDataHowManyPlustestDataToAdd = testDataHowManyTestData + testDataToAdd.size();
        ZoneId zoneId = ZoneId.of(BloodPressureMeasurementEntity.ZONE_ID__ECT__EUROPE_PARIS);
        TimeZone.setDefault(TimeZone.getTimeZone(zoneId.getId()));
    }

    private void deletePersistentTestData(){
        measurementService.deleteAll();
    }

    private void persistTestData(){
        List<BloodPressureMeasurementEntity> srcListe = this.getTestData();
        Assertions.assertNotNull(srcListe);
        for(BloodPressureMeasurementEntity m:srcListe){
            measurementService.add(m);
        }
    }

    private void persistMuchTestData(){
        List<BloodPressureMeasurementEntity> moreTestData = this.getTestData();
        String situation;
        int i;
        for(i = 0; i < testDataHowManyTestData*20; i++){
            situation = "LfdNr "+i+" New Measurement";
            BloodPressureMeasurementEntity m = BloodPressureMeasurementEntity.getInstance(situation);
            moreTestData.add(m);
        }
        i++;
        situation = "LfdNr "+i+" New Measurement - added more Testdata";
        BloodPressureMeasurementEntity m1 = BloodPressureMeasurementEntity.getInstance();
        BloodPressureMeasurementEntity m2 = BloodPressureMeasurementEntity.getInstance(situation);
        moreTestData.add(m1);
        moreTestData.add(m2);
        for(BloodPressureMeasurementEntity m : moreTestData){
            measurementService.add(m);
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
        List<BloodPressureMeasurementEntity> resultList = measurementService.getAll();
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
        List<BloodPressureMeasurementEntity> resultList = measurementService.getAll();
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
        List<BloodPressureMeasurementEntity> srcListe = this.getTestData();
        List<BloodPressureMeasurementEntity> resultList = measurementService.getAll();
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(srcListe.size(),resultList.size(),"srcListe.size()==resultList.size()");
        Assertions.assertEquals(testDataHowManyTestData,srcListe.size(),"srcListe.size()=="+testDataHowManyTestData);
        Assertions.assertEquals(testDataHowManyTestData,resultList.size(),"resultList.size()==,"+testDataHowManyTestData);
        for(int i = 0; i < testDataHowManyTestData; i++){
            BloodPressureMeasurementEntity src = srcListe.get(i);
            BloodPressureMeasurementEntity target = resultList.get(i);
            BloodPressureMeasurementTest.assertTransientEqualsPersistent(src,target);
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void getAllListAndAllFieldsNonNullTest(){
        log.info("TEST: getAllListAndAllFieldsNonNullTest");
        resetTestData();
        List<BloodPressureMeasurementEntity> srcListe = this.getTestData();
        List<BloodPressureMeasurementEntity> resultList = measurementService.getAll();
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(srcListe.size(),resultList.size(),"srcListe.size()==resultList.size()");
        Assertions.assertEquals(testDataHowManyTestData,srcListe.size(),"srcListe.size()=="+testDataHowManyTestData);
        Assertions.assertEquals(testDataHowManyTestData,resultList.size(),"resultList.size()==,"+testDataHowManyTestData);
        for(int i = 0; i < testDataHowManyTestData; i++){
            BloodPressureMeasurementEntity src = srcListe.get(i);
            BloodPressureMeasurementEntity target = resultList.get(i);
            BloodPressureMeasurementTest.assertTransientEqualsPersistent(src,target);
            BloodPressureMeasurementTest.assertAllFieldNonNullForPersistent(target);
        }
        Assertions.assertTrue(true);
    }

    private BloodPressureMeasurementEntity getRandomElement(){
        long size = measurementService.count();
        int mySize = Long.valueOf(size % Long.valueOf(Integer.MAX_VALUE)).intValue();
        Random random = new Random();
        int randomIndex = random.nextInt(mySize);
        List<BloodPressureMeasurementEntity> resultList = measurementService.getAll();
        BloodPressureMeasurementEntity randomEntity = resultList.get(randomIndex);
        Assertions.assertNotNull(randomEntity);
        Long idSrc = randomEntity.getId();
        Assertions.assertNotNull(idSrc);
        return randomEntity;
    }

    @Test
    public void getOneTest(){
        log.info("TEST: getOneTest");
        resetTestData();
        BloodPressureMeasurementEntity src = getRandomElement();
        Assertions.assertNotNull(src);
        Long idSrc = src.getId();
        BloodPressureMeasurementEntity target = measurementService.getOne(idSrc);
        Long idTarget = target.getId();
        Assertions.assertNotNull(idTarget);
        Assertions.assertEquals(idSrc.longValue(), idTarget.longValue());
        Assertions.assertTrue(true);
    }

    @Test
    public void addTest(){
        log.info("TEST: addTest");
        resetTestData();
        List<BloodPressureMeasurementEntity> srcListe = this.getTestData();
        List<BloodPressureMeasurementEntity> moreTestDataToAdd = this.getTestDataToAdd();
        for(BloodPressureMeasurementEntity m : moreTestDataToAdd){
            measurementService.add(m);
        }
        Assertions.assertEquals(testDataHowManyTestData,srcListe.size(),"srcListe.size()=="+testDataHowManyTestData);
        moreTestDataToAdd = this.getTestDataToAdd();
        srcListe.addAll(moreTestDataToAdd);
        List<BloodPressureMeasurementEntity> resultList = measurementService.getAll();
        Assertions.assertNotNull(resultList,"resultList != null");
        Assertions.assertEquals(srcListe.size(),resultList.size(),"srcListe.size()==resultList.size()");
        Assertions.assertEquals(testDataHowManyPlustestDataToAdd,srcListe.size(),"srcListe.size()=="+testDataHowManyPlustestDataToAdd);
        Assertions.assertEquals(testDataHowManyPlustestDataToAdd,resultList.size(),"resultList.size()==,"+testDataHowManyPlustestDataToAdd);
        for(int i = 0; i < testDataHowManyPlustestDataToAdd; i++){
            BloodPressureMeasurementEntity src = srcListe.get(i);
            BloodPressureMeasurementEntity target = resultList.get(i);
            BloodPressureMeasurementTest.assertTransientEqualsPersistent(src,target);
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void updateTest(){
        log.info("TEST: getAllPageTest");
        resetTestData();
        BloodPressureMeasurementEntity src = getRandomElement();
        Assertions.assertNotNull(src);
        String situation = "Xfcfdcdcrd";
        src.setSituation(situation);
        BloodPressureMeasurementEntity target = measurementService.update(src,src.getId());
        BloodPressureMeasurementTest.assertEquals(src,target);
        BloodPressureMeasurementTest.assertEqualsUuid(src,target);
        BloodPressureMeasurementTest.assertEqualsCreated(src,target);
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteTest(){
        log.info("TEST: deleteTest");
        resetTestData();
        BloodPressureMeasurementEntity src = getRandomElement();
        Assertions.assertNotNull(src);
        Long id = src.getId();
        Assertions.assertNotNull(id);
        long countBefore = measurementService.count();
        measurementService.delete(src);
        long countAfter = measurementService.count();
        Assertions.assertEquals(countBefore-1,countAfter,"count after deöete");
        Optional<BloodPressureMeasurementEntity> target = measurementService.findById(id);
        Assertions.assertFalse(target.isPresent());
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
        int getTotalPagesExpected = 64;
        long getTotalElementsExpected = 632L;
        int getSizeExpected = 10;
        int getNumberOfElementsExpected = 10;
        int getNumberOfElementsExpectedLastPage = 2;
        int getNumberExpected = 1;
        Page<BloodPressureMeasurementEntity> resultPage;
        do {
            resultPage = measurementService.getAll(pageable);
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
