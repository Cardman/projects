package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.annotation.ExportAnnotationUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class FctAnnotationToStr extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        DisplayedStrings dis_ = _cont.getStandards().getDisplayedStrings();
        return new ArgumentWrapper(new StringStruct(ExportAnnotationUtil.exportAnnotation(
                dis_.getInfinity(),
                dis_.getNan(),
                dis_.getExponent(),_firstArgs.getArgumentWrappers().get(0).getValue().getStruct())));
    }
}
