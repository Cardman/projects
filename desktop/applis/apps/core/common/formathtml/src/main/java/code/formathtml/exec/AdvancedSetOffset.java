package code.formathtml.exec;

import code.expressionlanguage.AbstractSetOffset;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;

public final class AdvancedSetOffset implements AbstractSetOffset {
    private final RendStackCall rendStackCall;

    public AdvancedSetOffset(RendStackCall _rendStackCall) {
        rendStackCall = _rendStackCall;
    }

    @Override
    public void setOffset(StackCall _stack, int _offset) {
        rendStackCall.setOffset(_offset);
    }
}
