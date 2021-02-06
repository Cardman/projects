package code.gui.initialize;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.structs.Struct;

public final class StrConv {

    private StrConv() {
    }
    public static String convertStr(Struct _str, ContextEl _r) {
        Argument out_ = new Argument(_str);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING, _r);
        out_ = ExecOperationNode.processString(out_, _r, stackCall_);
        CallingState state_ = stackCall_.getCallingState();
        if (state_ instanceof CustomFoundMethod) {
            CustomFoundMethod method_ = (CustomFoundMethod) state_;
            out_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(),method_.getPair(), method_.getArguments(), _r, stackCall_).getValue();
        }
        return ExecCatOperation.getDisplayable(out_, _r).getInstance();
    }
}
