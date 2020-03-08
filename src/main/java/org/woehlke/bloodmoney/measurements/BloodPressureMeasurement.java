package org.woehlke.bloodmoney.measurements;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement
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
    @Column(name = "uuid", columnDefinition = "uuid", unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @XmlElement(required = true)
    @Version
    @Column
    private Long version;

    @XmlElement(required = true)
    @Nullable
    @CsvBindByName
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "measurement_date", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @XmlElement(required = true)
    @Nullable
    @CsvBindByName
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "measurement_time", columnDefinition = "TIME", nullable = false)
    private LocalTime time;

    @XmlElement(required = true)
    @NotNull
    @CsvBindByName
    @Column(name = "systolic_top_number", nullable = false)
    private Integer systolicTopNumber;

    @XmlElement(required = true)
    @NotNull
    @CsvBindByName
    @Column(name = "diastolic_bottom_number", nullable = false)
    private Integer diastolicBottomNumber;

    @XmlElement(required = true)
    @NotNull
    @CsvBindByName
    @Column
    private Integer pulse;

    @XmlElement(required = true)
    @NotNull
    @CsvBindByName
    @Column(name = "situation", nullable = false)
    private String situation;

    @XmlElement(required = true)
    @Nullable
    @CsvBindByName
    @Column(name = "created_by_device_with_ip", nullable = false)
    private String ip;

    @XmlElement
    @Nullable
    @CsvBindByName
    @Column(name = "created_by_device_with_hostname", nullable = false)
    private String hostname;

    @XmlElement
    @Nullable
    @CsvBindByName
    @Column(name = "created_by_device_with_hostname_canonical", nullable = false)
    private String hostnameCanonical;

    @XmlElement(required = true)
    @Nullable
    @CsvBindByName
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime created;

    @XmlElement
    @Nullable
    @CsvBindByName
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_timestamp_updated", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updated;

    @Transient
    public static String ZONE_ID__ECT__EUROPE_PARIS = "Europe/Paris";

    public void prepareNew(){
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
        this.setDate(today);
        this.setTime(now);
        this.setUuid(uuid);
        this.setCreated(dateTimeNow);
        this.setUpdated(dateTimeNow);
        this.setIp(ip);
        this.setHostname(hostname);
        this.setHostnameCanonical(hostnameCanonical);
    }

    public static BloodPressureMeasurement getInstance(String situation) {
        Integer systolicTopNumber = 120;
        Integer diastolicBottomNumber = 80;
        Integer pulse = 68;
        BloodPressureMeasurement o = new BloodPressureMeasurement();
        o.setSystolicTopNumber(systolicTopNumber);
        o.setDiastolicBottomNumber(diastolicBottomNumber);
        o.setPulse(pulse);
        o.setSituation(situation);
        o.prepareNew();
        return o;
    }

    public static BloodPressureMeasurement getInstance() {
        String situation = "New Measurement";
        return BloodPressureMeasurement.getInstance(situation);
    }

}
