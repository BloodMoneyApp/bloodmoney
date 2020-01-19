package org.woehlke.bloodmoney.oodm.model.parts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"rowCreatedAt", "rowUpdatedAt"},
        allowGetters = true
)
public abstract class AuditModel implements Serializable {

    private static final long serialVersionUID = -3868580213496794922L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "row_created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date rowCreatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "row_updated_at", nullable = false)
    @LastModifiedDate
    private Date rowUpdatedAt;

    public Date getRowCreatedAt() {
        return rowCreatedAt;
    }

    public void setRowCreatedAt(Date rowCreatedAt) {
        this.rowCreatedAt = rowCreatedAt;
    }

    public Date getRowUpdatedAt() {
        return rowUpdatedAt;
    }

    public void setRowUpdatedAt(Date rowUpdatedAt) {
        this.rowUpdatedAt = rowUpdatedAt;
    }

    @Override
    public String toString() {
        return "AuditModel{" +
                "rowCreatedAt=" + rowCreatedAt +
                ", rowUpdatedAt=" + rowUpdatedAt +
                '}';
    }

}
