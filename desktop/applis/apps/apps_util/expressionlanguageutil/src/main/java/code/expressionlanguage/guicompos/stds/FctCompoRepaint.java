package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctCompoRepaint implements StdCaller {
    private final GuiExecutingBlocks guiEx;
    private final String aliasPaint;

    public FctCompoRepaint(GuiExecutingBlocks _guiEx, String _aliasPaint) {
        this.guiEx = _guiEx;
        this.aliasPaint = _aliasPaint;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ArgumentWrapper arg_ = new ArgumentWrapper(_instance);
        CustList<ArgumentWrapper> args_ = new CustList<ArgumentWrapper>(arg_);
        ArgumentListCall argList_ = new ArgumentListCall(args_);
        ExecTemplates.wrapAndCall(new ExecOverrideInfo(new ExecFormattedRootBlock(guiEx.getPairPaintMethod().getType(),aliasPaint),guiEx.getPairPaintMethod()), NullStruct.NULL_VALUE, _cont, _stackCall, argList_);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
