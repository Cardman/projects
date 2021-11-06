package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecInfoBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class FctFieldGetDeclaredAnonymousTypes extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        FieldMetaInfo field_ = (FieldMetaInfo) _instance;
        ExecInfoBlock annotableBlock_ = field_.getAnnotableBlock();
        StringList methods_ = new StringList();
        if (annotableBlock_ != null) {
            for (ExecRootBlock c: annotableBlock_.getElementContent().getContainer().getAnonymous()) {
                methods_.add(c.getFullName());
            }
        }
        ArrayStruct str_ = getTypes(_cont, methods_);
        return new ArgumentWrapper(str_);
    }
}
