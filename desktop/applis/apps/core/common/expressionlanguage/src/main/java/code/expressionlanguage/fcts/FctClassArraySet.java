package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctClassArraySet extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct arg_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct index_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct val_ = argumentWrappers_.get(2).getValue().getStruct();
        ExecArrayTemplates.setElement(arg_, index_, val_, _cont, _stackCall);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
