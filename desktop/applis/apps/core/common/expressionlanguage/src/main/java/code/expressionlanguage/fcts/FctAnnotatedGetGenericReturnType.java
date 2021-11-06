package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.AbAnMeStruct;
import code.expressionlanguage.structs.Struct;

public final class FctAnnotatedGetGenericReturnType extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbAnMeStruct annotated_ = (AbAnMeStruct) _instance;
        return new ArgumentWrapper(annotated_.typeGene(_cont));
    }
}
