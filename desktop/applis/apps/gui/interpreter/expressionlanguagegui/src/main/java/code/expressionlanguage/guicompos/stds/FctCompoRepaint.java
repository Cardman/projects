package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
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
        Argument arg_ = new Argument(_instance);
        CustList<Argument> args_ = new CustList<Argument>(arg_);
        ArgumentListCall argList_ = ArgumentListCall.wrapCall(args_);
        ExecTemplates.wrapAndCall(guiEx.getPairPaintMethod(), new ExecFormattedRootBlock(guiEx.getPairPaintMethod().getType(),aliasPaint),Argument.createVoid(), _cont, _stackCall, argList_);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
