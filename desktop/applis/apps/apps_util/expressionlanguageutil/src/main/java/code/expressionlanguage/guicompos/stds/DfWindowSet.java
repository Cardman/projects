package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.WindowSetStruct;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.utilcompo.CustAliases;

public final class DfWindowSet implements DfInstancer {
    private final CustAliases custAliases;

    public DfWindowSet(CustAliases _custAliases) {
        this.custAliases = _custAliases;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        WindowSetStruct set_ = new WindowSetStruct(true,custAliases.getInterceptor());
        return new ArgumentWrapper(set_);
    }
}
