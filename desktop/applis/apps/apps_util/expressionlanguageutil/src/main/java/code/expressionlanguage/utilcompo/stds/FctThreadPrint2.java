package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.ExecutingBlocks;
import code.expressionlanguage.utilcompo.FileInfos;
import code.util.CustList;

public final class FctThreadPrint2 extends FctThreadPrintAbs {
    private final ExecutingBlocks execBlocks;
    private final String aliasFormatType;

    public FctThreadPrint2(CustAliases _custAliases, ExecutingBlocks _execBlocks, String _aliasFormatType) {
        super(_custAliases);
        this.execBlocks = _execBlocks;
        this.aliasFormatType = _aliasFormatType;
    }

    @Override
    protected ArgumentWrapper pr(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Argument arg_ = new Argument(argumentWrappers_.get(0).getValue().getStruct());
        Argument argArr_ = new Argument(argumentWrappers_.get(1).getValue().getStruct());
        ArgumentListCall argList_ = new ArgumentListCall(new CustList<Argument>(arg_,argArr_));
        ExecTypeFunction formatObjectTwoPair_ = execBlocks.getFormatObjectTwoPair();
        ExecTemplates.wrapAndCall(formatObjectTwoPair_, new ExecFormattedRootBlock(formatObjectTwoPair_.getType(), aliasFormatType),Argument.createVoid(), _cont, _stackCall, argList_);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
