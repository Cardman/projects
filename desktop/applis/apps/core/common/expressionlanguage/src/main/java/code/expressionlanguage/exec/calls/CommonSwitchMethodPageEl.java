package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class CommonSwitchMethodPageEl extends AbstractCommonMethodPageEl {
    private final Struct value;
    public CommonSwitchMethodPageEl(ExecFormattedRootBlock _glClass, Struct _value) {
        super(_glClass);
        value = _value;
    }

    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack){
        //method walk through
        commonTageBase(_context, _stack,value);
    }

}
