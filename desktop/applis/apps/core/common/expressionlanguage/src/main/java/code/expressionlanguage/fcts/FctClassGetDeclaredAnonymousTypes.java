package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class FctClassGetDeclaredAnonymousTypes extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ClassMetaInfo instanceClass_ = (ClassMetaInfo) _instance;
        StringList methods_ = new StringList();
        ExecRootBlock callee_ = instanceClass_.getFormatted().getRootBlock();
        fetchAnonymous(methods_, callee_);
        ArrayStruct str_ = getTypes(_cont, methods_);
        return new ArgumentWrapper(str_);
    }
}
