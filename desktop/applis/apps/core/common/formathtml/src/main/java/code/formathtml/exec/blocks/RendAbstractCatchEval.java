package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;

public abstract class RendAbstractCatchEval extends RendParentBlock implements RendEval {

    protected void procCatch(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processCatch(_cont,_stds,_ctx,_rendStack,this);
    }

}
