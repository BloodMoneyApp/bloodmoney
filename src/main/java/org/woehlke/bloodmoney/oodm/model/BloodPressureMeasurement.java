package org.woehlke.bloodmoney.oodm.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.woehlke.bloodmoney.oodm.model.parts.AuditModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(
    name = "measurement",
    indexes = {
        @Index(name = "idx_measurement_date", columnList = "measurement_date")
    }
)
public class BloodPressureMeasurement extends AuditModel implements Serializable {

    private static final long serialVersionUID = 2676529613061169122L;

    @CsvBindByName
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "measurement_date", columnDefinition = "DATE")
    private LocalDate date;

    @CsvBindByName
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "measurement_time", columnDefinition = "TIME WITH TIME ZONE")
    private LocalTime time;

    @CsvBindByName
    @Column
    private Integer systolicTopNumber;

    @CsvBindByName
    @Column
    private Integer diastolicBottomNumber;

    @CsvBindByName
    @Column
    private Integer pulse;

    @CsvBindByName
    @Length(max=65535)
    @Column(name = "situation", nullable = true, length = 65535, columnDefinition="text")
    private String situation;

    public static BloodPressureMeasurement getInstance(){
        ZoneId zone =ZoneId.of("Europe/Paris");
        LocalDate today =  LocalDate.now(zone);
        LocalTime now = LocalTime.now(zone);
        BloodPressureMeasurement o = new BloodPressureMeasurement();
        o.setDate(today);
        o.setTime(now);
        o.setSystolicTopNumber(120);
        o.setDiastolicBottomNumber(80);
        o.setPulse(68);
        o.setSituation("New Measurement");
        return o;
    }

}
