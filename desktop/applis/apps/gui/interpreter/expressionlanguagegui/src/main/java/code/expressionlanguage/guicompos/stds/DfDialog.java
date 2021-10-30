package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.DialogStruct;
import code.expressionlanguage.guicompos.GuiContextEl;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.utilcompo.CustAliases;

public final class DfDialog extends DfCompoCtor {

    public DfDialog(CustAliases _custAliases, GuiExecutingBlocks _guiEx) {
        super(_custAliases, _guiEx);
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        DialogStruct di_ = new DialogStruct(((LgNamesGui)_cont.getStandards()).getGuiExecutingBlocks().getWindow().getFrames().getFrameFactory().newOtherDialog());
        ((GuiContextEl)_cont).getGuiInit().getWindows().add(di_,false);
        return new ArgumentWrapper(di_);
    }
}
