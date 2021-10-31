package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;

public final class DfNb implements DfInstancer {
    private final String nbClass;

    public DfNb(String _nbClass) {
        this.nbClass = _nbClass;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        LgNames stds_ = _cont.getStandards();
        byte cast_ = ExecClassArgumentMatching.getPrimitiveWrapCast(nbClass, stds_);
        Struct previous_ = DfObj.convert(_firstArgs);
        return new ArgumentWrapper(NumParsers.convertToNumber(cast_,previous_));
    }
}
