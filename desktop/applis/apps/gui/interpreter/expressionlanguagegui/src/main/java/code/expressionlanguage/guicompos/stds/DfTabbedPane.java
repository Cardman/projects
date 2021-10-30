package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.guicompos.TabbedPaneStruct;
import code.expressionlanguage.guicompos.WindowFull;
import code.expressionlanguage.utilcompo.CustAliases;

public final class DfTabbedPane extends DfCompoCtor {
    private final String aliasTabbedPane;

    public DfTabbedPane(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasTabbedPane) {
        super(_custAliases, _guiEx);
        this.aliasTabbedPane = _aliasTabbedPane;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        WindowFull window_ = ((LgNamesGui) _cont.getStandards()).getGuiExecutingBlocks().getWindow();
        return new ArgumentWrapper(new TabbedPaneStruct(aliasTabbedPane, window_.getCompoFactory()));
    }
}
