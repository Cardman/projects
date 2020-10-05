package code.gui.document;

import code.expressionlanguage.ContextEl;

public final class NativeContextCreator implements AbstractContextCreator {

    @Override
    public ContextEl newContext(ContextEl _context) {
        return _context;
    }

    @Override
    public void removeContext(ContextEl _context) {

    }
}
