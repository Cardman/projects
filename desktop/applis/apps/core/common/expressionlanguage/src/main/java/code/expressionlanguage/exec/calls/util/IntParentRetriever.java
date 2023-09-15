package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;

public interface IntParentRetriever {
    boolean retrieve(ContextEl _conf, StackCall _stackCall);
    Struct getParent();
}
