package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.CustList;

public final class FctConfirmOk0 implements StdCaller {
    private final CustAliases custAliases;
    private final GuiExecutingBlocks guiEx;

    public FctConfirmOk0(CustAliases _custAliases, GuiExecutingBlocks _guiEx) {
        this.custAliases = _custAliases;
        this.guiEx = _guiEx;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        return new ArgumentWrapper(guiEx.getAnswerOk(argumentWrappers_.get(0).getValue(), argumentWrappers_.get(1).getValue(), argumentWrappers_.get(2).getValue(), argumentWrappers_.get(3).getValue()));
    }
}
