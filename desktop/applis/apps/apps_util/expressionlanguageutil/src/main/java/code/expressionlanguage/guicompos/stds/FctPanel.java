package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.PanelStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctPanel extends FctCompoCtor {
    private final String aliasPanel;

    public FctPanel(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasPanel) {
        super(_custAliases, _guiEx);
        this.aliasPanel = _aliasPanel;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(PanelStruct.newFlow(aliasPanel, _guiEx.getCompoFactory()));
    }
}
