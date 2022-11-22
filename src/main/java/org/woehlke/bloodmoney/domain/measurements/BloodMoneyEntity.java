package org.woehlke.bloodmoney.domain.measurements;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public interface BloodMoneyEntity extends Serializable {

    UUID getUuid();
    LocalDateTime getCreated();
    LocalDateTime getUpdated();

    String getIp();
    String getHostname();
    String getHostnameCanonical();

    void prepareNew();
}
