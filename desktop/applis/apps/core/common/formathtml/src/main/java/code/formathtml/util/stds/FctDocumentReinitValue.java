package code.formathtml.util.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.RendExecutingBlocks;
import code.util.StringMap;

public final class FctDocumentReinitValue implements StdCaller {
    private final RendExecutingBlocks executingBlocks;

    public FctDocumentReinitValue(RendExecutingBlocks _ex) {
        this.executingBlocks = _ex;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringMap<Struct> renders_ = executingBlocks.getBuiltReinit();
        StringStruct str_= (StringStruct) _firstArgs.getArgumentWrappers().get(0).getValue();
        return new ArgumentWrapper(ArgumentListCall.getNull(renders_.getVal(str_.getInstance())));
    }
}
