package code.formathtml.util;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;

public final class AdvancedExiting implements AbstractExiting {
    private final Configuration context;

    public AdvancedExiting(Configuration context) {
        this.context = context;
    }

    @Override
    public boolean hasToExit(String _className) {
        return hasToExit(_className,null);
    }

    @Override
    public boolean hasToExit(String _className, Argument _arg) {
        return context.hasToExit(_className,_arg);
    }
}
