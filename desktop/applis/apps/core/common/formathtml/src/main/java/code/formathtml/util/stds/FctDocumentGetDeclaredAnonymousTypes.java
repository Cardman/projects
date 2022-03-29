package code.formathtml.util.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.fcts.FctReflection;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.DocumentStruct;
import code.util.StringList;

public final class FctDocumentGetDeclaredAnonymousTypes extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        DocumentStruct meth_ = (DocumentStruct) _instance;
        StringList methods_ = new StringList();
        RendDocumentBlock callee_ = meth_.getDocInfo();
        fetchAnonymous(methods_, callee_);
        ArrayStruct str_ = getTypes(_cont, methods_);
        return new ArgumentWrapper(str_);
    }
}
