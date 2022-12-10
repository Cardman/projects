package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.WindowStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.events.AbsWindowListener;
import code.util.CustList;

public final class FctWindowGetList implements StdCaller {
    private final String aliasWindowListener;

    public FctWindowGetList(String _aliasWindowListener) {
        this.aliasWindowListener = _aliasWindowListener;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        WindowStruct inst_ = (WindowStruct) _instance;
        CustList<Struct> user_ = new CustList<Struct>();
        for (AbsWindowListener w: inst_.getWindowListeners()) {
            if (w instanceof Struct) {
                user_.add((Struct)w);
            }
        }
        int len_ = user_.size();
        ArrayStruct arr_ = new ArrayStruct(len_, StringExpUtil.getPrettyArrayType(aliasWindowListener));
        for (int i = 0; i< len_; i++) {
            arr_.set(i, user_.get(i));
        }
        return new ArgumentWrapper(arr_);
    }
}
