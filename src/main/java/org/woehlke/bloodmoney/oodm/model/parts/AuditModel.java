package org.woehlke.bloodmoney.oodm.model.parts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.woehlke.bloodmoney.oodm.model.UserAccount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"lastModifiedDate", "creationDate"},
        allowGetters = true
)
public abstract class AuditModel implements Serializable {

    private static final long serialVersionUID = -3868580213496794922L;

    @CreatedBy
    private UserAccount createdBy;

    @LastModifiedBy
    private UserAccount lastModifiedBy;

    @UpdateTimestamp
    @NotNull
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "row_last_Modified_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    private LocalDateTime lastModifiedDate;

    @NotNull
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "row_created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE",  nullable = false, updatable = false)
    private LocalDateTime creationDate;

    public AuditModel() {
        ZoneId zone = ZoneId.of("Europe/Paris");
        creationDate = LocalDateTime.now(zone);
        lastModifiedDate = LocalDateTime.now(zone);
    }
}
