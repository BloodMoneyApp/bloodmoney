package org.woehlke.bloodmoney.measurements;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.UUID;

/**
 * H2 Datatypes: http://www.h2database.com/html/datatypes.html
 * PostgreSQL Datatypes: https://www.postgresql.org/docs/11/datatype.html
 * Using JAX-RS with JAXB: https://docs.oracle.com/javaee/7/tutorial/jaxrs-advanced007.htm
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
            name = "idx_created_timestamp",
            columnList = "created_timestamp"
        ),
        @Index(
            name = "idx_created_timestamp_updated",
            columnList = "created_timestamp_updated"
        ),
        @Index(
            name = "idx_measurement_uuid",
            columnList = "uuid",
            unique = true
        )
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transientBloodPressureMeasurement", propOrder = {
    "id",
    "uuid",
    "version",
    "systolicTopNumber",
    "diastolicBottomNumber",
    "pulse"
})
public class BloodPressureMeasurement implements Serializable {

    private static final long serialVersionUID = 2676529613061169122L;

    @Id
    @GeneratedValue(generator = "measurement_generator")
    @SequenceGenerator(
        name = "measurement_generator",
        sequenceName = "measurement_sequence",
        initialValue = 1000
    )
    @XmlElement
    private Long id;

    @XmlElement(required = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid", columnDefinition = "uuid", unique = true)
    private UUID uuid;

    @XmlElement(required = true)
    @Version
    @Column
    private Long version;

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

    @XmlElement(required = true)
    @NotNull
    @CsvBindByName
    @Column(name = "systolic_top_number")
    private Integer systolicTopNumber;

    @XmlElement(required = true)
    @NotNull
    @CsvBindByName
    @Column(name = "diastolic_bottom_number")
    private Integer diastolicBottomNumber;

    @XmlElement(required = true)
    @NotNull
    @CsvBindByName
    @Column
    private Integer pulse;

    @XmlElement(required = true)
    @Nullable
    @CsvBindByName
    @Column(name = "situation", nullable = false)
    private String situation;

    @XmlElement
    @Nullable
    @CsvBindByName
    @Column(name = "created_by_device_with_ip")
    private String ip;

    @XmlElement
    @Nullable
    @CsvBindByName
    @Column(name = "created_by_device_with_hostname")
    private String hostname;

    @XmlElement
    @Nullable
    @CsvBindByName
    @Column(name = "created_by_device_with_hostname_canonical")
    private String hostnameCanonical;


    @Nullable
    @CsvBindByName
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP")
    private LocalDateTime created;

    @Nullable
    @CsvBindByName
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_timestamp_updated", columnDefinition = "TIMESTAMP")
    private LocalDateTime updated;


    @Transient
    public static String ZONE_ID__ECT__EUROPE_PARIS = "Europe/Paris";

    public static BloodPressureMeasurement getInstance(String situation) {
        Integer systolicTopNumber = 120;
        Integer diastolicBottomNumber = 80;
        Integer pulse = 68;
        String ip = "undefined";
        String hostname =  "undefined";
        String hostnameCanonical = "undefined";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            ip = localHost.getHostAddress();
            hostname = localHost.getHostName();
            hostnameCanonical = localHost.getCanonicalHostName();
        } catch (UnknownHostException e){}
        ZoneId zone = ZoneId.of(ZONE_ID__ECT__EUROPE_PARIS);
        LocalDate today = LocalDate.now(zone);
        LocalTime now = LocalTime.now(zone);
        LocalDateTime dateTimeNow = LocalDateTime.now(zone);
        UUID uuid = UUID.randomUUID();
        BloodPressureMeasurement o = new BloodPressureMeasurement();
        o.setDate(today);
        o.setTime(now);
        o.setSystolicTopNumber(systolicTopNumber);
        o.setDiastolicBottomNumber(diastolicBottomNumber);
        o.setPulse(pulse);
        o.setSituation(situation);
        o.setUuid(uuid);
        o.setCreated(dateTimeNow);
        o.setUpdated(dateTimeNow);
        o.setIp(ip);
        o.setHostname(hostname);
        o.setHostnameCanonical(hostnameCanonical);
        return o;
    }

    public static BloodPressureMeasurement getInstance() {
        String situation = "New Measurement";
        return BloodPressureMeasurement.getInstance(situation);
    }

}
