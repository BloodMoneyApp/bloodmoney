package org.woehlke.bloodmoney.frontend.api;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.oodm.model.BloodPressureMeasurement;
import org.woehlke.bloodmoney.oodm.services.BloodPressureMeasurementService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/measurement/export")
@SessionAttributes("userSession")
public class ExportRestController {

    private final BloodPressureMeasurementService bloodPressureMeasurementService;

    @Autowired
    public ExportRestController(BloodPressureMeasurementService bloodPressureMeasurementService) {
        this.bloodPressureMeasurementService = bloodPressureMeasurementService;
    }

    private final static char SEPARATOR = ';';
    private final static String FILENAME = "measurements.csv";

    @GetMapping("/all")
    @ResponseBody
    public void exportCSV(HttpServletResponse response) throws Exception  {
        //set file name and content type

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + FILENAME + "\"");

        //create a csv writer
        StatefulBeanToCsv<BloodPressureMeasurement> writer = new StatefulBeanToCsvBuilder<BloodPressureMeasurement>(response.getWriter())
            .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
            .withSeparator(SEPARATOR)
            .withOrderedResults(false)
            .build();

        //write all users to csv file
        writer.write(bloodPressureMeasurementService.getAll());
    }
}
