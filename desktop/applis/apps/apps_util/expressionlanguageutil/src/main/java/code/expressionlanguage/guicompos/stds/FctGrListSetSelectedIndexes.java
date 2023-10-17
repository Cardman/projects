package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.AfterChangingGraphicListInfoState;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.Ints;

public final class FctGrListSetSelectedIndexes implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Ints selectedIndexes_ = new Ints();
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!(arg_ instanceof ArrayStruct)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        for (Struct s : ((ArrayStruct)arg_).list()) {
            selectedIndexes_.add(((NumberStruct) s).intStruct());
        }
        ((GraphicListStruct) _instance).getGrList().select(selectedIndexes_);
        _stackCall.setCallingState(new AfterChangingGraphicListInfoState((GraphicListStruct) _instance));
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
