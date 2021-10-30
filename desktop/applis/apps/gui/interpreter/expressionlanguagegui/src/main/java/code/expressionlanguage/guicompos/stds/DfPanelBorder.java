package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.guicompos.PanelBorderStruct;
import code.expressionlanguage.guicompos.WindowFull;
import code.expressionlanguage.utilcompo.CustAliases;

public final class DfPanelBorder extends DfCompoCtor {
    private final String aliasPanelBorder;

    public DfPanelBorder(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasPanelBorder) {
        super(_custAliases, _guiEx);
        this.aliasPanelBorder = _aliasPanelBorder;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        WindowFull window_ = ((LgNamesGui) _cont.getStandards()).getGuiExecutingBlocks().getWindow();
        return new ArgumentWrapper(PanelBorderStruct.newBorder(aliasPanelBorder, window_.getCompoFactory()));
    }
}
