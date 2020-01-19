package org.woehlke.bloodmoney.oodm.model;

import com.opencsv.bean.CsvBindByName;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.woehlke.bloodmoney.oodm.model.parts.AuditModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Objects;

@Entity
@Table(name = "measurement",
        indexes = {
                @Index(name = "idx_measurement_date", columnList = "measurement_date")
        }
)
public class BloodPressureMeasurement extends AuditModel implements Serializable {

    private static final long serialVersionUID = 2676529613061169122L;

    @Id
    @GeneratedValue(generator = "measurement_generator")
    @SequenceGenerator(
            name = "measurement_generator",
            sequenceName = "measurement_sequence",
            initialValue = 1000
    )
    private Long id;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getSystolicTopNumber() {
        return systolicTopNumber;
    }

    public void setSystolicTopNumber(Integer systolicTopNumber) {
        this.systolicTopNumber = systolicTopNumber;
    }

    public Integer getDiastolicBottomNumber() {
        return diastolicBottomNumber;
    }

    public void setDiastolicBottomNumber(Integer diastolicBottomNumber) {
        this.diastolicBottomNumber = diastolicBottomNumber;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BloodPressureMeasurement)) return false;
        BloodPressureMeasurement that = (BloodPressureMeasurement) o;
        return Objects.equals(getId(), that.getId()) &&
                getDate().equals(that.getDate()) &&
                getTime().equals(that.getTime()) &&
                getSystolicTopNumber().equals(that.getSystolicTopNumber()) &&
                getDiastolicBottomNumber().equals(that.getDiastolicBottomNumber()) &&
                getPulse().equals(that.getPulse()) &&
                Objects.equals(getSituation(), that.getSituation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getTime(), getSystolicTopNumber(), getDiastolicBottomNumber(), getPulse(), getSituation());
    }

    @Override
    public String toString() {
        return "BloodPressureMeasurement{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", systolicTopNumber=" + systolicTopNumber +
                ", diastolicBottomNumber=" + diastolicBottomNumber +
                ", pulse=" + pulse +
                ", situation='" + situation + '\'' +
                '}';
    }
}
