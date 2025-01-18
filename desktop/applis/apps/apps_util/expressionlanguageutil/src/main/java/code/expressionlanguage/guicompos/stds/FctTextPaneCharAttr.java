package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class FctTextPaneCharAttr implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        TextPaneStruct inst_ = (TextPaneStruct) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        inst_.setCharacterAttributes(argumentWrappers_.get(0).getValue(),argumentWrappers_.get(1).getValue(),argumentWrappers_.get(2).getValue(),argumentWrappers_.get(3).getValue());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
