package org.woehlke.bloodmoney.frontend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class FlashMessage implements Serializable {

    private static final long serialVersionUID = 5616312717653223576L;

    private String message;

    private String moreInfo;

    private FlashMessageSeverity severity;

    private Date raised;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public FlashMessageSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(FlashMessageSeverity severity) {
        this.severity = severity;
    }

    public Date getRaised() {
        return raised;
    }

    public void setRaised(Date raised) {
        this.raised = raised;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlashMessage that = (FlashMessage) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(moreInfo, that.moreInfo) &&
                Objects.equals(severity, that.severity) &&
                Objects.equals(raised, that.raised);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, moreInfo, severity, raised);
    }

    @Override
    public String toString() {
        return "FlashMessage{" +
                "message='" + message + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                ", severity='" + severity + '\'' +
                ", raised=" + raised +
                '}';
    }
}
