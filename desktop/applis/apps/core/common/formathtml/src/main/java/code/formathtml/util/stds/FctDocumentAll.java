package code.formathtml.util.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.DocumentStruct;
import code.formathtml.util.RendExecutingBlocks;
import code.util.StringMap;

public final class FctDocumentAll implements StdCaller {
    private final RendExecutingBlocks executingBlocks;
    private final String aliasDoc;

    public FctDocumentAll(RendExecutingBlocks _ex,String _doc) {
        this.executingBlocks = _ex;
        this.aliasDoc = _doc;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringMap<RendDocumentBlock> renders_ = executingBlocks.getRenders();
        int size_ = renders_.size();
        ArrayStruct arr_ = new ArrayStruct(size_, StringExpUtil.getPrettyArrayType(aliasDoc));
        for (int i = 0; i < size_; i++) {
            arr_.set(i,  new DocumentStruct(aliasDoc,renders_.getValue(i).getFileBlock().getFileName(),renders_.getValue(i).getDecl()));
        }
        return new ArgumentWrapper(arr_);
    }
}
