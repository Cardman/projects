package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;

public final class FctInputIsEditable implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        TxtComponentStruct in_ = (TxtComponentStruct) _instance;
        return new ArgumentWrapper(in_.isEditable());
    }
}
