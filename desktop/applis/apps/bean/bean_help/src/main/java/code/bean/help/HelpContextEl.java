package code.bean.help;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class HelpContextEl extends ContextEl {

    public HelpContextEl() {
        super(null);
    }

    @Override
    public boolean callsOrException(StackCall _stack) {
        return false;
    }
}
