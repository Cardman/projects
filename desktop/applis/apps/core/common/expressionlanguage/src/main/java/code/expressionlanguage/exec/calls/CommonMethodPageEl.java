package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.Struct;

public final class CommonMethodPageEl extends AbstractCommonMethodPageEl {

    public CommonMethodPageEl(ExecFormattedRootBlock _glClass) {
        super(_glClass);
    }

    public void initReturnType(Struct _right) {
        if (_right != null) {
            setReturnedArgument(_right);
        }
    }

    public void blockRootType(ExecTypeFunction _pair) {
        setBlockRoot(_pair.getFct());
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack){
        //method walk through
        commonTageBase(_context, _stack,null);
    }

}
