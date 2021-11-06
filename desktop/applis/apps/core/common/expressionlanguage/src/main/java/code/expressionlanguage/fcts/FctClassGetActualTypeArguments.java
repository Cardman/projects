package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class FctClassGetActualTypeArguments extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ClassMetaInfo instanceClass_ = (ClassMetaInfo) _instance;
        return new ArgumentWrapper(buildArrClass(_cont, ClassMetaInfo.getExtendedClassMetaInfoInit(_cont,instanceClass_)));
    }
}
