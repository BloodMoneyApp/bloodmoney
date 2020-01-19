package org.woehlke.bloodmoney.oodm.model.parts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.woehlke.bloodmoney.oodm.model.UserAccount;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Optional;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"rowCreatedAt", "rowUpdatedAt"},
        allowGetters = true
)
public class AuditModel implements Auditable<UserAccount, Long, LocalTime>, Serializable {

    private static final long serialVersionUID = -3868580213496794922L;

    @Id
    @GeneratedValue(generator = "measurement_generator")
    @SequenceGenerator(
        name = "measurement_generator",
        sequenceName = "measurement_sequence",
        initialValue = 1000
    )
    private Long id;

    private UserAccount createdBy;

    private UserAccount lastModifiedBy;

    @LastModifiedDate
    @Column(name = "row_last_Modified_at", columnDefinition = "TIME WITH TIME ZONE", nullable = false)
    private LocalTime lastModifiedDate;

    @CreatedDate
    @Column(name = "row_created_at", columnDefinition = "TIME WITH TIME ZONE", nullable = false, updatable = false)
    private LocalTime creationDate;


    @Override
    public Optional<UserAccount> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    @Override
    public void setCreatedBy(UserAccount createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Optional<LocalTime> getCreatedDate() {
        return Optional.ofNullable(creationDate);
    }

    @Override
    public void setCreatedDate(LocalTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public Optional<UserAccount> getLastModifiedBy() {
        return Optional.ofNullable(this.lastModifiedBy);
    }

    @Override
    public void setLastModifiedBy(UserAccount lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public Optional<LocalTime> getLastModifiedDate() {
        return Optional.ofNullable(lastModifiedDate);
    }

    @Override
    public void setLastModifiedDate(LocalTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id==null;
    }
}
