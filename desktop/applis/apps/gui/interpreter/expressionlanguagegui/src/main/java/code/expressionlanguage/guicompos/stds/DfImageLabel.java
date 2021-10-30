package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.guicompos.PreparedLabelStruct;
import code.expressionlanguage.guicompos.WindowFull;
import code.expressionlanguage.utilcompo.CustAliases;

public final class DfImageLabel extends DfCompoCtor {
    private final String aliasImageLabel;

    public DfImageLabel(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasImageLabel) {
        super(_custAliases, _guiEx);
        this.aliasImageLabel = _aliasImageLabel;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        WindowFull window_ = ((LgNamesGui) _cont.getStandards()).getGuiExecutingBlocks().getWindow();
        return new ArgumentWrapper(new PreparedLabelStruct(window_.getImageFactory(),aliasImageLabel));
    }
}
