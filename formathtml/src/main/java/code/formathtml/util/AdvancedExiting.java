package code.formathtml.util;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;

public final class AdvancedExiting implements AbstractExiting {
    private final Configuration context;

    public AdvancedExiting(Configuration context) {
        this.context = context;
    }

    @Override
    public boolean hasToExit(String _className) {
        return context.hasToExit(_className);
    }
}
