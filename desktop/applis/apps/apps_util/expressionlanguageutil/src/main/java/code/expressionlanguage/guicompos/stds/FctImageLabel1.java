package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.PreparedLabelStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctImageLabel1 extends FctCompoCtor {
    private final String aliasImageLabel;

    public FctImageLabel1(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasImageLabel) {
        super(_custAliases, _guiEx);
        this.aliasImageLabel = _aliasImageLabel;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new PreparedLabelStruct(_guiEx.getImageFactory(),_guiEx.getCompoFactory(),_firstArgs.getArgumentWrappers().get(0).getValue(),aliasImageLabel));
    }
}
