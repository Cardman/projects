package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.TableStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctTableClearSelection implements StdCaller {
    private final String id;
    public FctTableClearSelection(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        TableStruct inst_ = (TableStruct) _instance;
        int[] arr_ = inst_.clearSelection(_stackCall);
        FctTableSetRowCount.notif(_stackCall,inst_,arr_, id);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }

}
