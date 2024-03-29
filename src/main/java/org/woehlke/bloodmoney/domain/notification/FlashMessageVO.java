package org.woehlke.bloodmoney.domain.notification;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FlashMessageVO implements Serializable {

  static final long serialVersionUID = 5616312717653223576L;

  private String message;

  private String moreInfo;

  private FlashMessageSeverity severity;

  private Date raised;

}
