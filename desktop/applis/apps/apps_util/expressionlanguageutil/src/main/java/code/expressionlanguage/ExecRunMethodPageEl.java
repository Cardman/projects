package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractBasicReflectPageEl;
import code.expressionlanguage.structs.Struct;

public final class ExecRunMethodPageEl extends AbstractBasicReflectPageEl {
    private final Struct instance;
    private AbsPrepareCustomEvents callEvts;

    public ExecRunMethodPageEl(Struct _g) {
        super(true);
        instance = _g;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (callEvts == null) {
            callEvts = new ExecRunPrepareCustomEvents();
        }
        return !callEvts.call(_context, _stack, instance, 0);
    }

}
