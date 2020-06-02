package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.formathtml.stacks.RendAbruptCallingFinally;

public interface RendCallingFinally {

    void removeBlockFinally(Configuration _conf);
    RendAbruptCallingFinally newAbruptCallingFinally(Struct _struct);
}
