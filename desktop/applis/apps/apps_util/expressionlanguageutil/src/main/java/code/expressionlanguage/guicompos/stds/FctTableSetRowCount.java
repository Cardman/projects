package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.AfterChangingTableSelectState;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.TableStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

public final class FctTableSetRowCount implements StdCaller {
    private final String id;
    public FctTableSetRowCount(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        TableStruct inst_ = (TableStruct) _instance;
        Struct r_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        int[] arr_ = inst_.setRowCount(r_, _stackCall);
        notif(_stackCall,inst_,arr_, id + ":" + ((NumberStruct)r_).intStruct());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }

    static void notif(StackCall _stackCall, TableStruct _inst, int[] _arr, String _intro) {
        AbsLogDbg log_ = _stackCall.getStopper().getLogger();
        if (log_ != null) {
            if (_arr.length > 0) {
                log_.log(_intro +"=>"+ _arr[0]+","+ _arr[_arr.length-1]);
                _stackCall.setCallingState(new AfterChangingTableSelectState(_inst, _arr[0], _arr[_arr.length-1]));
            } else {
                log_.log(_intro);
            }
        }
    }

}
