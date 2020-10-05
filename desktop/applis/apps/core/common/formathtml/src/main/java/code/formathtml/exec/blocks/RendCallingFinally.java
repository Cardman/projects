package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.stacks.RendAbruptCallingFinally;
import code.formathtml.util.BeanLgNames;

public interface RendCallingFinally {

    void removeBlockFinally(Configuration _conf, BeanLgNames _stds, ContextEl _ctx);
    RendAbruptCallingFinally newAbruptCallingFinally(Struct _struct);
}
