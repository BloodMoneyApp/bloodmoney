package org.woehlke.bloodmoney.domain.measurements;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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
 * @see <a href="http://www.h2database.com/html/datatypes.html">H2 Datatypes</a>
 * @see <a href="https://www.postgresql.org/docs/11/datatype.html">PostgreSQL Datatypes</a>
 * @see <a href="https://docs.oracle.com/javaee/7/tutorial/jaxrs-advanced007.htm">Using JAX-RS with JAXB</a>
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
public class MeasurementEntity implements Serializable {

  static final long serialVersionUID = 2676529613061169122L;

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
  @Column
  private Integer weight;

  @XmlElement(required = true)
  @NotNull
  @CsvBindByName
  @Column(name = "situation", nullable = false, columnDefinition = "TEXT")
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

  public void merge(MeasurementEntity otherEntity) {
    this.systolicTopNumber = otherEntity.systolicTopNumber;
    this.diastolicBottomNumber = otherEntity.diastolicBottomNumber;
    this.pulse = otherEntity.pulse;
    this.date = otherEntity.date;
    this.time = otherEntity.time;
    this.weight = otherEntity.weight;
    this.situation = otherEntity.situation;
    ZoneId zone = ZoneId.of(ZONE_ID__ECT__EUROPE_PARIS);
    LocalDateTime dateTimeNow = LocalDateTime.now(zone);
    this.setUpdated(dateTimeNow);
  }

  public void prepareUpdated() {
    String ip = "undefined";
    String hostname = "undefined";
    String hostnameCanonical = "undefined";
    try {
      InetAddress localHost = InetAddress.getLocalHost();
      ip = localHost.getHostAddress();
      hostname = localHost.getHostName();
      hostnameCanonical = localHost.getCanonicalHostName();
    } catch (UnknownHostException e) {
    }
    ZoneId zone = ZoneId.of(ZONE_ID__ECT__EUROPE_PARIS);
    LocalDateTime dateTimeNow = LocalDateTime.now(zone);
    this.setUpdated(dateTimeNow);
    this.setIp(ip);
    this.setHostname(hostname);
    this.setHostnameCanonical(hostnameCanonical);
  }

  public void prepareNew() {
    String ip = "undefined";
    String hostname = "undefined";
    String hostnameCanonical = "undefined";
    try {
      InetAddress localHost = InetAddress.getLocalHost();
      ip = localHost.getHostAddress();
      hostname = localHost.getHostName();
      hostnameCanonical = localHost.getCanonicalHostName();
    } catch (UnknownHostException e) {
    }
    ZoneId zone = ZoneId.of(ZONE_ID__ECT__EUROPE_PARIS);
    LocalDate today = LocalDate.now(zone);
    LocalTime now = LocalTime.now(zone);
    LocalDateTime dateTimeNow = LocalDateTime.now(zone);
    UUID uuid = UUID.randomUUID();
    this.setUuid(uuid);
    this.setDate(today);
    this.setTime(now);
    this.setCreated(dateTimeNow);
    this.setUpdated(dateTimeNow);
    this.setIp(ip);
    this.setHostname(hostname);
    this.setHostnameCanonical(hostnameCanonical);
  }

  public static MeasurementEntity getInstance(String situation) {
    Integer systolicTopNumber = 120;
    Integer diastolicBottomNumber = 80;
    Integer pulse = 68;
    Integer weight = 86;
    MeasurementEntity o = new MeasurementEntity();
    o.setSystolicTopNumber(systolicTopNumber);
    o.setDiastolicBottomNumber(diastolicBottomNumber);
    o.setPulse(pulse);
    o.setSituation(situation);
    o.setWeight(weight);
    o.prepareNew();
    return o;
  }

  public static MeasurementEntity getInstance(
    Integer systolicTopNumber,
    Integer diastolicBottomNumber,
    Integer pulse,
    Integer weight,
    String situation,
    LocalDate date,
    LocalTime time
  ) {
    MeasurementEntity o = new MeasurementEntity();
    o.setSystolicTopNumber(systolicTopNumber);
    o.setDiastolicBottomNumber(diastolicBottomNumber);
    o.setPulse(pulse);
    o.setSituation(situation);
    o.setWeight(weight);
    o.setDate(date);
    o.setTime(time);
    o.prepareNew();
    return o;
  }

  public static MeasurementEntity getInstance() {
    String situation = "New Measurement";
    return MeasurementEntity.getInstance(situation);
  }

}
