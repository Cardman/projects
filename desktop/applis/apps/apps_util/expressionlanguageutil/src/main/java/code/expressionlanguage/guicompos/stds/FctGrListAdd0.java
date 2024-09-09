package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.AddRowGraphicListState;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctGrListAdd0 implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        _stackCall.setCallingState(new AddRowGraphicListState((GraphicListStruct)_instance, ((NumberStruct)argumentWrappers_.get(0).getValue()).intStruct(),argumentWrappers_.get(1).getValue()));
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
