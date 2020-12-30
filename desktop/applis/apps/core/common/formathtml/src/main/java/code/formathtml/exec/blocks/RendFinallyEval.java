package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;

public final class RendFinallyEval extends RendParentBlock implements RendEval {
    public RendFinallyEval(int _offsetTrim) {
        super(_offsetTrim);
    }


    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        processFinally(_cont,_stds,_ctx,this, _stack, _rendStack);
    }

}
