package code.vi.prot.impl;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;

public final class DefErrGenerator implements AbsErrGenerator {
    @Override
    public Struct generate(String _cl, ContextEl _owner, StackCall _stackCall) {
        return new ErrorStruct(_owner, _cl, _stackCall);
    }
}
