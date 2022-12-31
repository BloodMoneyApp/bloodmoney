package org.woehlke.bloodmoney.frontend;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.domain.measurements.MeasurementEntity;
import org.woehlke.bloodmoney.domain.measurements.MeasurementService;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/measurement/export")
@SessionAttributes("userSession")
public class MeasurementExportController {

  private final MeasurementService measurementService;
  private final BloodMoneyProperties bloodMoneyProperties;

  @Autowired
  public MeasurementExportController(
    MeasurementService measurementService,
    BloodMoneyProperties bloodMoneyProperties
  ) {
    this.measurementService = measurementService;
    this.bloodMoneyProperties = bloodMoneyProperties;
  }

  @RequestMapping(path = "/all", method = RequestMethod.GET)
  @ResponseBody
  public void exportAll(HttpServletResponse response) throws Exception {
    this.exportCSV(response);
  }

  @RequestMapping(path = "/all/csv", method = RequestMethod.GET)
  @ResponseBody
  public void exportCSV(HttpServletResponse response) throws Exception {
    //set file name and content type
    response.setContentType("text/csv");
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
      "attachment; filename=\"" + this.bloodMoneyProperties.getWebConfig().getExportFilename() + "\"");
    //create a csv writer
    StatefulBeanToCsv<MeasurementEntity> writer = new StatefulBeanToCsvBuilder<MeasurementEntity>(response.getWriter())
      .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
      .withSeparator(this.bloodMoneyProperties.getWebConfig().getExportFilenameSeparator().charAt(0))
      .withOrderedResults(false)
      .build();
    //write all Measurements to csv file
    writer.write(measurementService.getAll());
  }

  @RequestMapping(path = "/all/xml", method = RequestMethod.GET)
  @ResponseBody
  public void exportXML(HttpServletResponse response) throws Exception {
    //set file name and content type
    response.setContentType("text/csv");
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
      "attachment; filename=\"" + this.bloodMoneyProperties.getWebConfig().getExportFilename() + "\"");
    //create a csv writer
    StatefulBeanToCsv<MeasurementEntity> writer = new StatefulBeanToCsvBuilder<MeasurementEntity>(response.getWriter())
      .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
      .withSeparator(this.bloodMoneyProperties.getWebConfig().getExportFilenameSeparator().charAt(0))
      .withOrderedResults(false)
      .build();
    //write all Measurements to csv file
    writer.write(measurementService.getAll());
  }

  @RequestMapping(path = "/all/json", method = RequestMethod.GET)
  @ResponseBody
  public void exportJSON(HttpServletResponse response) throws Exception {
    //set file name and content type
    response.setContentType("text/csv");
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
      "attachment; filename=\"" + this.bloodMoneyProperties.getWebConfig().getExportFilename() + "\"");
    //create a csv writer
    StatefulBeanToCsv<MeasurementEntity> writer = new StatefulBeanToCsvBuilder<MeasurementEntity>(response.getWriter())
      .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
      .withSeparator(this.bloodMoneyProperties.getWebConfig().getExportFilenameSeparator().charAt(0))
      .withOrderedResults(false)
      .build();
    //write all Measurements to csv file
    writer.write(measurementService.getAll());
  }
}
