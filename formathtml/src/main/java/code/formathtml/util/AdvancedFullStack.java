package code.formathtml.util;

import code.expressionlanguage.AbstractFullStack;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.ArrayStruct;
import code.formathtml.Configuration;

public final class AdvancedFullStack implements AbstractFullStack {
    private final Configuration context;

    public AdvancedFullStack(Configuration context) {
        this.context = context;
    }

    @Override
    public ArrayStruct newStackTraceElementArray() {
        return context.newStackTraceElementArray();
    }
}
