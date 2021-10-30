package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.StringList;

public final class FctFileRoots extends FctFileAbs {
    public FctFileRoots(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringList roots_ = _infos.getFileSystem().getRoots((RunnableContextEl) _cont);
        int len_ = roots_.size();
        ArrayStruct arr_ = new ArrayStruct(len_, StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getCharSeq().getAliasString()));
        for (int i = 0; i < len_; i++) {
            arr_.set(i, new StringStruct(roots_.get(i)));
        }
        return new ArgumentWrapper(arr_);
    }

    @Override
    public boolean guard(ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return true;
    }
}
