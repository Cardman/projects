package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.util.NativeFct;

public abstract class RendNativeFct implements NativeFct {
    public boolean convert() {
        return stack().getCallingState() instanceof CustomFoundMethod;
    }
    public Argument calculateArgument(Argument _def, ContextEl _ct) {
        CallingState state_ = stack().getCallingState();
        if (state_ instanceof CustomFoundMethod) {
            CustomFoundMethod method_ = (CustomFoundMethod) state_;
            return ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(),method_.getPair(), method_.getArguments(), _ct, stack()).getValue();
        }
        return _def;
    }
    public boolean stop(ContextEl _ct) {
        return _ct.callsOrException(stack());
    }
    public abstract StackCall stack();
}
