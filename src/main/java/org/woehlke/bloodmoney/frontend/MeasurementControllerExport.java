package org.woehlke.bloodmoney.frontend;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.domain.measurements.BloodPressureMeasurementEntity;
import org.woehlke.bloodmoney.domain.measurements.BloodPressureMeasurementService;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/measurement/export")
@SessionAttributes("userSession")
public class MeasurementControllerExport {

    @GetMapping("/all")
    @ResponseBody
    public void exportAll(HttpServletResponse response) throws Exception  {
        this.exportCSV(response);
    }

    @GetMapping("/all/csv")
    @ResponseBody
    public void exportCSV(HttpServletResponse response) throws Exception  {
        //set file name and content type
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + this.bloodMoneyProperties.getWebConfig().getExportFilename() + "\"");
        //create a csv writer
        StatefulBeanToCsv<BloodPressureMeasurementEntity> writer = new StatefulBeanToCsvBuilder<BloodPressureMeasurementEntity>(response.getWriter())
            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
            .withSeparator(this.bloodMoneyProperties.getWebConfig().getExportFilenameSeparator().charAt(0))
            .withOrderedResults(false)
            .build();
        //write all Measurements to csv file
        writer.write(bloodPressureMeasurementService.getAll());
    }

    @GetMapping("/all/xml")
    @ResponseBody
    public void exportXML(HttpServletResponse response) throws Exception  {
        //set file name and content type
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + this.bloodMoneyProperties.getWebConfig().getExportFilename() + "\"");
        //create a csv writer
        StatefulBeanToCsv<BloodPressureMeasurementEntity> writer = new StatefulBeanToCsvBuilder<BloodPressureMeasurementEntity>(response.getWriter())
            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
            .withSeparator(this.bloodMoneyProperties.getWebConfig().getExportFilenameSeparator().charAt(0))
            .withOrderedResults(false)
            .build();
        //write all Measurements to csv file
        writer.write(bloodPressureMeasurementService.getAll());
    }

    @GetMapping("/all/json")
    @ResponseBody
    public void exportJSON(HttpServletResponse response) throws Exception  {
        //set file name and content type
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + this.bloodMoneyProperties.getWebConfig().getExportFilename() + "\"");
        //create a csv writer
        StatefulBeanToCsv<BloodPressureMeasurementEntity> writer = new StatefulBeanToCsvBuilder<BloodPressureMeasurementEntity>(response.getWriter())
            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
            .withSeparator(this.bloodMoneyProperties.getWebConfig().getExportFilenameSeparator().charAt(0))
            .withOrderedResults(false)
            .build();
        //write all Measurements to csv file
        writer.write(bloodPressureMeasurementService.getAll());
    }

    private final BloodPressureMeasurementService bloodPressureMeasurementService;
    private final BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    public MeasurementControllerExport(
        BloodPressureMeasurementService bloodPressureMeasurementService,
        BloodMoneyProperties bloodMoneyProperties
    ) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.bloodMoneyProperties = bloodMoneyProperties;
    }
}
