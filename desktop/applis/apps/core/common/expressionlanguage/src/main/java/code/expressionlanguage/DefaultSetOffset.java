package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;

public final class DefaultSetOffset implements AbstractSetOffset {

    @Override
    public void setOffset(StackCall _stack, int _offset) {
        _stack.setOffset(_offset);
    }
}
