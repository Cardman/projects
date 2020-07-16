package code.expressionlanguage.exec.blocks;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.stacks.AbruptCallingFinally;
import code.expressionlanguage.structs.Struct;

public interface MethodCallingFinally extends CallingFinally {

    void removeBlockFinally(ContextEl _conf);
}
