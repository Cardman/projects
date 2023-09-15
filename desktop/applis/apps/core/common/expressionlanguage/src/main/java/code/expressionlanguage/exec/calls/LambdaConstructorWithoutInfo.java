package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LambdaConstructorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Ints;
import code.util.core.StringUtil;

public final class LambdaConstructorWithoutInfo extends AbstractBasicReflectPageEl {
    private boolean checkElement;
    private final LambdaConstructorStruct lambdaConstructorStruct;
    private final ArgumentListCall arguments;
    public LambdaConstructorWithoutInfo(LambdaConstructorStruct _lms, ArgumentListCall _l) {
        super(true);
        lambdaConstructorStruct = _lms;
        arguments = _l;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        checkElement = true;
        if (_stack.getStopper().isStopAtRef(_context, _stack)) {
            return false;
        }
        checkElement = false;
        Argument arg_ = initArray(_context,_stack,arguments.getArguments(),lambdaConstructorStruct);
        if (_context.callsOrException(_stack)) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

    public ArgumentListCall getArguments() {
        return arguments;
    }

    public LambdaConstructorStruct getLambdaConstructorStruct() {
        return lambdaConstructorStruct;
    }

    public boolean isCheckElement() {
        return checkElement;
    }

    private static Argument initArray(ContextEl _conf, StackCall _stackCall, CustList<Argument> _values, LambdaConstructorStruct _l) {
        Ints dims_ = new Ints();
        for (Argument a: _values) {
            int dim_ = NumParsers.convertToNumber(a.getStruct()).intStruct();
            dims_.add(dim_);
        }
        String c_ = StringUtil.nullToEmpty(_l.getFormClassName());
        Struct res_ = ExecArrayTemplates.newCustomArrayOrExc(c_, dims_, _conf, _stackCall);
        if (res_ instanceof ErrorStruct) {
            _stackCall.setCallingState(new CustomFoundExc(res_));
            return new Argument();
        }
        return new Argument(res_);
    }

}
