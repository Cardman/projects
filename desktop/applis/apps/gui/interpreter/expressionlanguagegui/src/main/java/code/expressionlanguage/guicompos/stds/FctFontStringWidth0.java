package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.FontStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.ImageStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctFontStringWidth0 implements StdCaller {
    private final GuiExecutingBlocks guiEx;
    public FctFontStringWidth0(GuiExecutingBlocks _guiEx) {
        this.guiEx = _guiEx;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct first_ = argumentWrappers_.get(0).getValue().getStruct();
        if (!(first_ instanceof ImageStruct)) {
            return new ArgumentWrapper(new IntStruct(-1));
        }
        Struct font_ = ((ImageStruct) first_).getFont();
        if (!(font_ instanceof FontStruct)) {
            return new ArgumentWrapper(new IntStruct(-1));
        }
        FontStruct f_ = (FontStruct) font_;
        return new ArgumentWrapper(guiEx.stringWidth(f_, argumentWrappers_.get(1).getValue().getStruct()));
    }
}
