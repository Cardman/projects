package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.ProgressBarStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctProgBar extends FctCompoCtor {
    private final String aliasProgBar;

    public FctProgBar(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasProgBar) {
        super(_custAliases, _guiEx);
        this.aliasProgBar = _aliasProgBar;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new ProgressBarStruct(aliasProgBar,_guiEx.getWindow().getCompoFactory()));
    }
}
