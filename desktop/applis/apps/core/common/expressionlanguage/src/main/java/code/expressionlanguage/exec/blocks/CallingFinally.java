package code.expressionlanguage.exec.blocks;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.stacks.AbruptCallingFinally;
import code.expressionlanguage.structs.Struct;

public interface CallingFinally {

    void removeBlockFinally(ContextEl _conf);
    AbruptCallingFinally newAbruptCallingFinally(Struct _struct);
}
