package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.TableStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class FctTableAddInterval implements StdCaller {
    private final String id;
    public FctTableAddInterval(String _i) {
        id = _i;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        TableStruct inst_ = (TableStruct) _instance;
        CustList<ArgumentWrapper> aw_ = _firstArgs.getArgumentWrappers();
        Struct a_ = aw_.get(0).getValue();
        Struct l_ = aw_.get(1).getValue();
        if (NumberUtil.signum(((NumberStruct)a_).intStruct())+NumberUtil.signum(((NumberStruct)l_).intStruct()) < 2) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        int[] arr_ = inst_.addSelectInterval(a_, l_, _stackCall);
        FctTableSetRowCount.notif(_stackCall, inst_, arr_, id + ":" + ((NumberStruct) a_).intStruct() + "," + ((NumberStruct) l_).intStruct());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }

}
