package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.fcts.FctReflection;
import code.expressionlanguage.fcts.FctUtil;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctNbRateAbs extends FctNbRateComAbs {

    public FctNbRateAbs(AbsFctRate _m) {
        super(_m);
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        return parse(argumentWrappers_.get(0).getValue().getStruct(), _cont, _stackCall);
    }

    private ArgumentWrapper parse(Struct _arg, ContextEl _context, StackCall _stackCall) {
        if (!(_arg instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(FctReflection.getNpe(_context, _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        String one_ = NumParsers.getCharSeq(_arg).toStringInstance();
        if (!getManageStr().valid(one_)) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getFormatError(_context, FctUtil.getNumberRadixMessage(one_, 10), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(getManageStr().build(one_));
    }

}
