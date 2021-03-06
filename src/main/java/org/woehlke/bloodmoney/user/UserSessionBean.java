package org.woehlke.bloodmoney.user;

import lombok.*;
import org.woehlke.bloodmoney.common.FlashMessage;

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
    private Stack<FlashMessage> stack = new Stack<>();

    @NotNull
    private Boolean devTesting;

}
