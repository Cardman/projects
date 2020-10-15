package code.formathtml.exec;

import code.expressionlanguage.AbstractSetOffset;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;

public final class AdvancedSetOffset implements AbstractSetOffset {
    private final Configuration context;

    public AdvancedSetOffset(Configuration _context) {
        this.context = _context;
    }

    @Override
    public void setOffset(int _offset) {
        context.setOffset(_offset);
    }
}
