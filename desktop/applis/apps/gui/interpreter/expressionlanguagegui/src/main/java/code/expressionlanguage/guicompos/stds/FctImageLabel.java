package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.PreparedLabelStruct;
import code.expressionlanguage.guicompos.WindowFull;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctImageLabel implements StdCaller {
    private final CustAliases custAliases;
    private final GuiExecutingBlocks guiExecutingBlocks;

    public FctImageLabel(CustAliases custAliases, GuiExecutingBlocks _guiEx) {
        this.custAliases = custAliases;
        guiExecutingBlocks = _guiEx;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        PreparedLabelStruct txt_ = (PreparedLabelStruct) _instance;
        WindowFull window_ = guiExecutingBlocks.getWindow();
        txt_.setImage(window_.getImageFactory(),_firstArgs.getArgumentWrappers().get(0).getValue().getStruct());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
