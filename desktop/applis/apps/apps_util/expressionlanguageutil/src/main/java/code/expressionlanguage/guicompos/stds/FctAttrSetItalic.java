package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;

public final class FctAttrSetItalic implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AttrSetStruct in_ = (AttrSetStruct) _instance;
        in_.addItalic(_firstArgs.getArgumentWrappers().get(0).getValue());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
