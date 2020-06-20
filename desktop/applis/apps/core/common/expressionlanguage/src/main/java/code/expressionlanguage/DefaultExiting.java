package code.expressionlanguage;

import code.expressionlanguage.exec.ExecutingUtil;

public final class DefaultExiting implements AbstractExiting {
    private final ContextEl context;

    public DefaultExiting(ContextEl context) {
        this.context = context;
    }

    @Override
    public boolean hasToExit(String _className) {
        return ExecutingUtil.hasToExit(context,_className);
    }
}
