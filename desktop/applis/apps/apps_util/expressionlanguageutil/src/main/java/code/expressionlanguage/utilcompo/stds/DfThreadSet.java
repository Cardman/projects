package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.ThreadSetStruct;

public final class DfThreadSet implements DfInstancer {
    private final AbstractInterceptor interceptor;

    public DfThreadSet(AbstractInterceptor _interceptor) {
        this.interceptor = _interceptor;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ThreadSetStruct std_ = new ThreadSetStruct(interceptor);
        return new ArgumentWrapper(std_);
    }
}
