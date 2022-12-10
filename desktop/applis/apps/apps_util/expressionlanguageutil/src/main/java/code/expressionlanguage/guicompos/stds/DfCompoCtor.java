package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.utilcompo.CustAliases;

public abstract class DfCompoCtor implements DfInstancer {
    private final CustAliases custAliases;
    private final GuiExecutingBlocks guiEx;

    protected DfCompoCtor(CustAliases _custAliases, GuiExecutingBlocks _guiEx) {
        this.custAliases = _custAliases;
        this.guiEx = _guiEx;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return inst(guiEx,_exit, _cont, _firstArgs, _stackCall);
    }

    public abstract ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall);
}
