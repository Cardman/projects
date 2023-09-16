package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;

public final class DirectParentRetriever implements IntParentRetriever {
    private final Struct parent;

    public DirectParentRetriever(Struct _d) {
        this.parent = _d;
    }

    @Override
    public boolean retrieve(ContextEl _conf, StackCall _stackCall) {
        return true;
    }

    public Struct getParent() {
        return getOriginalInstance();
    }

    @Override
    public Struct getOriginalInstance() {
        return parent;
    }
}
