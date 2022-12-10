package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.FontStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;

public final class FctFontStringWidth1 implements StdCaller {
    private final GuiExecutingBlocks guiEx;
    public FctFontStringWidth1(GuiExecutingBlocks _guiEx) {
        this.guiEx = _guiEx;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        FontStruct f_ = (FontStruct) _instance;
        return new ArgumentWrapper(guiEx.stringWidth(f_, _firstArgs.getArgumentWrappers().get(0).getValue().getStruct()));
    }
}
