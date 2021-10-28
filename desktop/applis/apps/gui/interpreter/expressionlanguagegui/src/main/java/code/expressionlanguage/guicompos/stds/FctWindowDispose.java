package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.FrameStruct;
import code.expressionlanguage.guicompos.GuiContextEl;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.WindowStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctWindowDispose implements StdCaller {
    private final GuiExecutingBlocks guiEx;

    public FctWindowDispose(GuiExecutingBlocks _guiEx) {
        this.guiEx = _guiEx;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        WindowStruct inst_ = (WindowStruct) _instance;
        if (inst_ instanceof FrameStruct && ((FrameStruct)inst_).getCommonFrame().isMainFrame()) {
            ((GuiContextEl)_cont).disposeAll(guiEx, _stackCall);
        } else {
            ((GuiContextEl)_cont).getGuiInit().getWindows().remove(inst_,false);
            inst_.dispose();
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
