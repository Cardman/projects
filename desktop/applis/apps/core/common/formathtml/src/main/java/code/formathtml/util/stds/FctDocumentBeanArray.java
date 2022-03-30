package code.formathtml.util.stds;

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
import code.formathtml.util.RendExecutingBlocks;
import code.util.StringMap;

public final class FctDocumentBeanArray implements StdCaller {
    private final RendExecutingBlocks executingBlocks;

    public FctDocumentBeanArray(RendExecutingBlocks _ex) {
        this.executingBlocks = _ex;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringMap<Struct> renders_ = executingBlocks.getBuiltBeans();
        int size_ = renders_.size();
        ArrayStruct arr_ = new ArrayStruct(size_, StringExpUtil.getPrettyArrayType(_cont.getStandards().getCharSeq().getAliasString()));
        for (int i = 0; i < size_; i++) {
            arr_.set(i, new StringStruct(renders_.getKey(i)));
        }
        return new ArgumentWrapper(arr_);
    }
}
