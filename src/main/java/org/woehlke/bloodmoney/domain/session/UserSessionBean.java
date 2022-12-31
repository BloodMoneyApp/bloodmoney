package org.woehlke.bloodmoney.domain.session;

import lombok.*;
import org.woehlke.bloodmoney.domain.notification.FlashMessageVO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Stack;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionBean implements Serializable {

  private static final long serialVersionUID = -9184465632849128728L;

  @NotNull
  private Stack<FlashMessageVO> stack = new Stack<>();

  @NotNull
  private Boolean devTesting;

}
