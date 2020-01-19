package org.woehlke.bloodmoney.frontend.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Stack;

public class UserSession implements Serializable {

    private static final long serialVersionUID = -9184465632849128728L;

    private Stack<FlashMessage> stack = new Stack<>();

    private Boolean devTesting;

    public Stack<FlashMessage> getStack() {
        return stack;
    }

    public void setStack(Stack<FlashMessage> stack) {
        this.stack = stack;
    }

    public Boolean getDevTesting() {
        return devTesting;
    }

    public void setDevTesting(Boolean devTesting) {
        this.devTesting = devTesting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSession)) return false;
        UserSession that = (UserSession) o;
        return getStack().equals(that.getStack()) &&
            getDevTesting().equals(that.getDevTesting());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStack(), getDevTesting());
    }
}
