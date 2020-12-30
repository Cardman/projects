package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;

public interface RendMethodCallingFinally {
    void removeBlockFinally(Configuration _conf, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack);
}
