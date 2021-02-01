package code.gui.document;

import code.expressionlanguage.ContextEl;

public interface AbstractContextCreator {
    ContextEl newContext(ContextEl _context);
    ContextEl removeContext(ContextEl _context);
}
