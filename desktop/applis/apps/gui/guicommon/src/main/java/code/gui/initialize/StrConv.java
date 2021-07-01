package code.gui.initialize;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
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
    public static String convertStr(Struct _str, ContextEl _r, StackCall _stackCall) {
        Argument out_ = new Argument(_str);
        out_ = ExecOperationNode.processString(out_, _r, _stackCall);
        CallingState state_ = _stackCall.getCallingState();
        if (state_ instanceof CustomFoundMethod) {
            CustomFoundMethod method_ = (CustomFoundMethod) state_;
            out_ = ProcessMethod.calculateArgument(method_, _r, _stackCall).getValue();
        }
        return ExecCatOperation.getDisplayable(out_, _r).getInstance();
    }
}
