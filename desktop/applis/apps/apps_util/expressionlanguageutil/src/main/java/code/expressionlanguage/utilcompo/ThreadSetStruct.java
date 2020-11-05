package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.*;
import code.util.CustList;

import java.util.concurrent.ConcurrentHashMap;

public final class ThreadSetStruct extends WithoutParentIdStruct implements Struct {

    private ConcurrentHashMap<Thread,Struct> elementSet = new ConcurrentHashMap<Thread,Struct>();

    public ArrayStruct toSnapshotArray(ContextEl _contextEl) {
        CustList<Struct> instantKeys_ = new CustList<Struct>();
        for (Struct s: elementSet.values()) {
            instantKeys_.add(s);
        }
        String thClass_ = ((LgNamesWithNewAliases)_contextEl.getStandards()).getCustAliases().getAliasThread();
        int len_ = instantKeys_.size();
        ArrayStruct arr_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(thClass_));
        for (int i = 0; i < len_; i++) {
            Struct e_ = instantKeys_.get(i);
            _contextEl.getInitializingTypeInfos().addSensibleField(this,e_);
            arr_.set(i, e_);
        }
        return arr_;
    }
    public void add(Struct _key) {
        if (!(_key instanceof ThreadStruct)) {
            return;
        }
        elementSet.put(((ThreadStruct) _key).getThread(),_key);
    }
    public void remove(Struct _key) {
        if (!(_key instanceof ThreadStruct)) {
            return;
        }
        elementSet.remove(((ThreadStruct) _key).getThread());
    }
    public Struct contains(Struct _key) {
        if (!(_key instanceof ThreadStruct)) {
            return BooleanStruct.of(false);
        }
        return BooleanStruct.of(elementSet.containsKey(((ThreadStruct) _key).getThread()));
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesWithNewAliases)_contextEl.getStandards()).getCustAliases().getAliasThreadSet();
    }

}
