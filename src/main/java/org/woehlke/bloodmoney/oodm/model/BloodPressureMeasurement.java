package org.woehlke.bloodmoney.oodm.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(
    name = "measurement",
    indexes = {
        @Index(
            name = "idx_measurement_date",
            columnList = "measurement_date"
        )
    }
)
public class BloodPressureMeasurement implements Serializable {

    private static final long serialVersionUID = 2676529613061169122L;

    @Id
    @GeneratedValue(generator = "measurement_generator")
    @SequenceGenerator(
        name = "measurement_generator",
        sequenceName = "measurement_sequence",
        initialValue = 1000
    )
    private Long id;

    @NotNull
    @CsvBindByName
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "measurement_date", columnDefinition = "DATE")
    private LocalDate date;

    @NotNull
    @CsvBindByName
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "measurement_time", columnDefinition = "TIME")
    private LocalTime time;

    @NotNull
    @CsvBindByName
    @Column(name = "systolic_top_number")
    private Integer systolicTopNumber;

    @NotNull
    @CsvBindByName
    @Column(name = "diastolic_bottom_number")
    private Integer diastolicBottomNumber;

    @NotNull
    @CsvBindByName
    @Column
    private Integer pulse;

    @Nullable
    @CsvBindByName
    @Length(max=65535)
    @Column(name = "situation", nullable = true, length = 65535, columnDefinition="TEXT")
    private String situation;

    public static BloodPressureMeasurement getInstance(){
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
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
