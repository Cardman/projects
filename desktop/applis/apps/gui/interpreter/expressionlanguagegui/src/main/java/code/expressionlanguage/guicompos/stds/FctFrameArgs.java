package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.StringList;

public final class FctFrameArgs implements StdCaller {
    private final CustAliases custAliases;
    private final GuiExecutingBlocks guiEx;

    public FctFrameArgs(CustAliases _custAliases, GuiExecutingBlocks _guiEx) {
        this.custAliases = _custAliases;
        this.guiEx = _guiEx;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        StringList mainArgs_ = guiEx.getMainArgs();
        String typeStr_ = _cont.getStandards().getContent().getCharSeq().getAliasString();
        typeStr_ = StringExpUtil.getPrettyArrayType(typeStr_);
        int len_ = mainArgs_.size();
        ArrayStruct result_ = new ArrayStruct(len_, typeStr_);
        for (int i = 0; i < len_; i++) {
            result_.set(i, new StringStruct(mainArgs_.get(i)));
        }
        return new ArgumentWrapper(result_);
    }
}
