package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;

public final class CurrentInvokingConstructor extends AbstractInvokingConstructor {

    public CurrentInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(ContextEl _conf) {
        String clCurName_ = _conf.getAnalyzing().getGlobalClass();
        return new ClassArgumentMatching(clCurName_);
    }

}
