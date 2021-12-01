package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.FrameStruct;
import code.expressionlanguage.guicompos.GuiContextEl;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.utilcompo.CustAliases;

public final class DfFrame extends DfCompoCtor {

    public DfFrame(CustAliases _custAliases, GuiExecutingBlocks _guiEx) {
        super(_custAliases, _guiEx);
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        FrameStruct fr_ = new FrameStruct(((LgNamesGui)_cont.getStandards()).getGuiExecutingBlocks().getLightFrameFactory().newOtherFrame());
        ((GuiContextEl)_cont).getGuiInit().getWindows().add(fr_,false);
        return new ArgumentWrapper(fr_);
    }
}
