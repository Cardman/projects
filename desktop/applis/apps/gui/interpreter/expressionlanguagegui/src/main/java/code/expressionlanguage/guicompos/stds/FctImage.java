package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.ImageStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctImage implements StdCaller {
    private final GuiExecutingBlocks guiEx;

    public FctImage(GuiExecutingBlocks _guiEx) {
        this.guiEx = _guiEx;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct w_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct h_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct a_ = argumentWrappers_.get(2).getValue().getStruct();
        return new ArgumentWrapper(new ImageStruct(guiEx.getImageFactory(),((NumberStruct)w_).intStruct(),((NumberStruct)h_).intStruct(), BooleanStruct.isTrue(a_)));
    }
}
