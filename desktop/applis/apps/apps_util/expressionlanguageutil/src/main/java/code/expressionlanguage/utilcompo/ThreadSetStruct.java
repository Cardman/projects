package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;
import code.util.CustList;

public final class ThreadSetStruct extends WithoutParentIdStruct {

    private final AbstractConcurrentMap<AbstractThread,Struct> elementSet;

    public ThreadSetStruct(AbstractInterceptor _concurrent) {
        elementSet = _concurrent.newMapAbstractThreadStruct();
    }
    public ArrayStruct toSnapshotArray(ContextEl _contextEl, StackCall _stackCall) {
        CustList<Struct> instantKeys_ = new CustList<Struct>();
        for (Struct s: elementSet.values()) {
            instantKeys_.add(s);
        }
        String thClass_ = ((LgNamesWithNewAliases)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasThread();
        int len_ = instantKeys_.size();
        ArrayStruct arr_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(thClass_));
        for (int i = 0; i < len_; i++) {
            Struct e_ = instantKeys_.get(i);
            _stackCall.getInitializingTypeInfos().addSensibleField(this,e_);
            arr_.set(i, e_);
        }
        return arr_;
    }
    public void add(Struct _key) {
        if (!(_key instanceof AbsThreadStruct)) {
            return;
        }
        elementSet.put(((AbsThreadStruct) _key).getThread(),_key);
    }
    public void remove(Struct _key) {
        if (!(_key instanceof AbsThreadStruct)) {
            return;
        }
        elementSet.remove(((AbsThreadStruct) _key).getThread());
    }
    public Struct contains(Struct _key) {
        if (!(_key instanceof AbsThreadStruct)) {
            return BooleanStruct.of(false);
        }
        return BooleanStruct.of(elementSet.containsKey(((AbsThreadStruct) _key).getThread()));
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesWithNewAliases)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasThreadSet();
    }

}
