package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;

public interface RendMethodCallingFinally extends RendWithEl {
    void removeBlockFinally(Configuration _conf, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack);
}
