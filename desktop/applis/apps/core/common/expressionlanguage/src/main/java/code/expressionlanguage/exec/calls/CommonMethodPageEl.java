package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class CommonMethodPageEl extends AbstractCommonMethodPageEl {

    public CommonMethodPageEl(Argument _gl, ExecFormattedRootBlock _glClass) {
        super(_gl, _glClass);
    }

    public void initReturnType(Argument _right) {
        if (_right != null) {
            setReturnedArgument(_right);
        }
    }

    public void blockRootType(ExecTypeFunction _pair) {
        setBlockRootType(_pair.getType());
        setBlockRoot(_pair.getFct());
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack){
        //method walk through
        ExecBlock en_ = getBlock();
        processTagsBase(_context,_stack,en_);
    }

}
