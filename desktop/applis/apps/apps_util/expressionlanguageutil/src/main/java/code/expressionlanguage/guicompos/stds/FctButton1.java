package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.AbsButtonStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.PlainButtonStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctButton1 extends FctCompoCtor {
    private final String aliasButton;

    public FctButton1(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasButton) {
        super(_custAliases, _guiEx);
        this.aliasButton = _aliasButton;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        String txt_ = AbsButtonStruct.getValue(_firstArgs.getArgumentWrappers().get(0).getValue());
        return new ArgumentWrapper(new PlainButtonStruct(aliasButton,_guiEx.getCompoFactory().newPlainButton(txt_)));
    }
}
