package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public interface AbstractPreparer {

    boolean initType(ContextEl _context, StackCall _stack);
    boolean isAbstract(ContextEl _context, StackCall _stack);
    boolean isPolymorph(ContextEl _context, StackCall _stack);
}
