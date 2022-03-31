package code.formathtml.exec.stacks;

import code.formathtml.exec.blocks.RendMethodCallingFinally;

public final class RendAbruptCallingFinally {
    private final RendMethodCallingFinally callingFinally;

    public RendAbruptCallingFinally(RendMethodCallingFinally _callingFinally) {
        callingFinally = _callingFinally;
    }

    public RendMethodCallingFinally getCallingFinally() {
        return callingFinally;
    }
}
