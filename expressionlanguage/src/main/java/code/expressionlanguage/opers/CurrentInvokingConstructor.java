package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public final class CurrentInvokingConstructor extends AbstractInvokingConstructor {

    public CurrentInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(Analyzable _conf) {
        String clCurName_ = _conf.getGlobalClass();
        return new ClassArgumentMatching(clCurName_);
    }

}
