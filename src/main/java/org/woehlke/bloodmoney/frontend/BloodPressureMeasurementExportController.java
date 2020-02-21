package org.woehlke.bloodmoney.frontend;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.application.BloodMoneyProperties;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurement;
import org.woehlke.bloodmoney.measurements.BloodPressureMeasurementService;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/measurement/export")
@SessionAttributes("userSession")
public class BloodPressureMeasurementExportController {

    @GetMapping("/all")
    @ResponseBody
    public void exportCSV(HttpServletResponse response) throws Exception  {
        //set file name and content type
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + this.bloodMoneyProperties.getWebConfig().getExportFilename() + "\"");
        //create a csv writer
        StatefulBeanToCsv<BloodPressureMeasurement> writer = new StatefulBeanToCsvBuilder<BloodPressureMeasurement>(response.getWriter())
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
    public BloodPressureMeasurementExportController(
        BloodPressureMeasurementService bloodPressureMeasurementService,
        BloodMoneyProperties bloodMoneyProperties
    ) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.bloodMoneyProperties = bloodMoneyProperties;
    }
}
