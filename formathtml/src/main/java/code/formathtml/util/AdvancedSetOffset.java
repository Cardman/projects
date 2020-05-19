package code.formathtml.util;

import code.expressionlanguage.AbstractSetOffset;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;

public class AdvancedSetOffset implements AbstractSetOffset {
    private final Configuration context;

    public AdvancedSetOffset(Configuration context) {
        this.context = context;
    }

    @Override
    public void setOffset(int _offset) {
        context.setOffset(_offset);
    }
}
