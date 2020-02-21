package org.woehlke.bloodmoney.frontend.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.application.BloodMoneyProperties;
import org.woehlke.bloodmoney.oodm.model.BloodPressureMeasurement;
import org.woehlke.bloodmoney.oodm.services.BloodPressureMeasurementService;

import javax.servlet.http.HttpServletResponse;

@Log
@RestController
@RequestMapping("/measurement/export")
@SessionAttributes("userSession")
public class ExportRestController {

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
    public ExportRestController(
        BloodPressureMeasurementService bloodPressureMeasurementService,
        BloodMoneyProperties bloodMoneyProperties
    ) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
        this.bloodMoneyProperties = bloodMoneyProperties;
    }
}
