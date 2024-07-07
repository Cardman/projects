package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public interface AbstractParamReflectCheckerStepping {
    boolean checkParams(AbstractRefectCommonMethodPageEl _page,ContextEl _context, StackCall _stack);
    boolean postArg(AbstractRefectCommonMethodPageEl _page,StackCall _stack);
}
