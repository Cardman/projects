package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stacks.AbruptCallingFinally;
import code.expressionlanguage.structs.Struct;

public interface CallingFinally {

    void removeBlockFinally(ContextEl _conf);
    AbruptCallingFinally newAbruptCallingFinally(Struct _struct);
}
