package code.gui.document;

import code.expressionlanguage.ContextEl;

public final class NativeContextCreator implements AbstractContextCreator {

    @Override
    public ContextEl newContext(ContextEl _context) {
        return _context;
    }

    @Override
    public ContextEl removeContext(ContextEl _context) {
        return _context;
    }
}
