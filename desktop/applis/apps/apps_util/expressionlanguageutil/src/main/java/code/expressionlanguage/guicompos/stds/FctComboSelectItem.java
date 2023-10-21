package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.AddRowComboState;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicComboStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

public final class FctComboSelectItem implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        GraphicComboStruct inst_ = (GraphicComboStruct) _instance;
        inst_.selectItem((NumberStruct)_firstArgs.getArgumentWrappers().get(0).getValue().getStruct());
        _stackCall.setCallingState(new AddRowComboState(inst_,"",true));
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
