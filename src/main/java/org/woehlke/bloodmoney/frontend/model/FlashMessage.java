package org.woehlke.bloodmoney.frontend.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlashMessage implements Serializable {

    private static final long serialVersionUID = 5616312717653223576L;

    private String message;

    private String moreInfo;

    private FlashMessageSeverity severity;

    private Date raised;

}
