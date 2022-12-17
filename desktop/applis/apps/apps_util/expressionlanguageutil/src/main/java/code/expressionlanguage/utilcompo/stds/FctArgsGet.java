package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.StringList;

public final class FctArgsGet implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringList args_ = ((RunnableContextEl) _cont).getArgs();
        int size_ = args_.size();
        ArrayStruct arr_ = new ArrayStruct(size_, StringExpUtil.getPrettyArrayType(_cont.getStandards().getCharSeq().getAliasString()));
        for (int i = 0; i < size_; i++) {
            arr_.set(i, new StringStruct(args_.get(i)));
        }
        return new ArgumentWrapper(arr_);
    }
}
