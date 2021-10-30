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
import code.expressionlanguage.utilcompo.*;
import code.util.CustList;
import code.util.core.StringUtil;

public final class FctThreadPrint1 extends FctThreadPrintAbs {
    private final ExecutingBlocks execBlocks;
    private final String aliasFormatType;

    public FctThreadPrint1(CustAliases _custAliases, ExecutingBlocks _execBlocks, String _aliasFormatType) {
        super(_custAliases);
        this.execBlocks = _execBlocks;
        this.aliasFormatType = _aliasFormatType;
    }

    @Override
    protected ArgumentWrapper pr(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Argument arg_ = new Argument(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct());
        ArgumentListCall argList_ = new ArgumentListCall(new CustList<Argument>(arg_));
        ExecTypeFunction formatObjectPair_ = execBlocks.getFormatObjectPair();
        ExecTemplates.wrapAndCall(formatObjectPair_, new ExecFormattedRootBlock(formatObjectPair_.getType(), aliasFormatType),Argument.createVoid(), _cont, _stackCall, argList_);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
