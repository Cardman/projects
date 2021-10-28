package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.RadioButtonStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.CustList;

public final class FctRadio2 extends FctCompoCtor {
    private final String aliasRadio;
    public FctRadio2(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasRadio) {
        super(_custAliases, _guiEx);
        this.aliasRadio = _aliasRadio;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        return new ArgumentWrapper(new RadioButtonStruct(aliasRadio, argumentWrappers_.get(0).getValue().getStruct(), argumentWrappers_.get(1).getValue().getStruct(),_guiEx.getWindow().getCompoFactory()));
    }
}
