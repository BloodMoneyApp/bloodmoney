package org.woehlke.bloodmoney.measurements;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

/**
 * H2 Datatypes: http://www.h2database.com/html/datatypes.html
 * PostgreSQL Datatypes: https://www.postgresql.org/docs/11/datatype.html
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "measurement",
    indexes = {
        @Index(
            name = "idx_measurement_date",
            columnList = "measurement_date"
        ),
        @Index(
            name = "idx_measurement_timestamp",
            columnList = "measurement_timestamp"
        ),
        @Index(
            name = "idx_measurement_uuid",
            columnList = "uuid",
            unique = true
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

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid", columnDefinition = "uuid", unique = true)
    private UUID uuid;

    @Version
    @Column
    private Long version;

    @Nullable
    @CsvBindByName
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "measurement_timestamp", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

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
    //@Length(max=65535)
    @Column(name = "situation", nullable = true)
    private String situation;

    public static BloodPressureMeasurement getInstance(){
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        UUID uuid = UUID.randomUUID();
        BloodPressureMeasurement o = new BloodPressureMeasurement();
        o.setUuid(uuid);
        o.setDate(today);
        o.setTime(now);
        o.setSystolicTopNumber(120);
        o.setDiastolicBottomNumber(80);
        o.setPulse(68);
        o.setSituation("New Measurement");
        return o;
    }

}
