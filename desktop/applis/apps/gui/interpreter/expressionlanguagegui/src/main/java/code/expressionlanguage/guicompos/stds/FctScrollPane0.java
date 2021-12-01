package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.ScrollPaneStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctScrollPane0 extends FctCompoCtor {
    private final String aliasScrollPane;

    public FctScrollPane0(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasScrollPane) {
        super(_custAliases, _guiEx);
        this.aliasScrollPane = _aliasScrollPane;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(ScrollPaneStruct.newScroll(aliasScrollPane,_guiEx.getCompoFactory()));
    }
}
