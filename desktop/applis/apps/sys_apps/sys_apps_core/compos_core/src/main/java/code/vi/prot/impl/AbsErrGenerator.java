package code.vi.prot.impl;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;

public interface AbsErrGenerator {
    Struct generate(String _cl, ContextEl _owner, StackCall _stackCall);
}
