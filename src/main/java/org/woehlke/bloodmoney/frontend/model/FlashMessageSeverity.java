package org.woehlke.bloodmoney.frontend.model;

import java.io.Serializable;

public enum FlashMessageSeverity implements Serializable {

    PRIMARY,
    SECONDARY,
    SUCCESS,
    DANGER,
    WARNING,
    INFO,
    LIGHT,
    DARK;

    public String value(){
        return this.name().toLowerCase();
    }
}
