package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
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
        ArgumentWrapper arg_ = new ArgumentWrapper(argumentWrappers_.get(0).getValue());
        ArgumentWrapper argArr_ = new ArgumentWrapper(argumentWrappers_.get(1).getValue());
        CustList<ArgumentWrapper> arguments_ = new CustList<ArgumentWrapper>(arg_, argArr_);
        ArgumentListCall argList_ = new ArgumentListCall(arguments_);
        ExecTypeFunction formatObjectTwoPair_ = execBlocks.getFormatObjectTwoPair();
        ExecTemplates.wrapAndCall(new ExecOverrideInfo(new ExecFormattedRootBlock(formatObjectTwoPair_.getType(), aliasFormatType),formatObjectTwoPair_), NullStruct.NULL_VALUE, _cont, _stackCall, argList_);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
