package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public interface MethodCallingFinally {

    void removeBlockFinally(StackCall _stackCall, AbstractPageEl _page);

    int getOff();
}
