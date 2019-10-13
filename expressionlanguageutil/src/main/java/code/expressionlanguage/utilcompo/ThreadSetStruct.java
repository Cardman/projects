package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

import java.util.concurrent.ConcurrentHashMap;

public final class ThreadSetStruct implements Struct {
    private ConcurrentHashMap<ThreadStruct,Struct> elementSet = new ConcurrentHashMap<ThreadStruct,Struct>();
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    public ArrayStruct toSnapshotArray(ExecutableCode _contextEl) {
        CustList<Struct> instantKeys_ = new CustList<Struct>();
        for (Struct s: elementSet.keySet()) {
            instantKeys_.add(s);
        }
        String thClass_ = ((LgNamesUtils)_contextEl.getStandards()).getAliasThread();
        int len_ = instantKeys_.size();
        Struct[] innArr_ = new Struct[len_];
        ArrayStruct arr_ = new ArrayStruct(innArr_,PrimitiveTypeUtil.getPrettyArrayType(thClass_));
        for (int i = 0; i < len_; i++) {
            Struct e_ = instantKeys_.get(i);
            _contextEl.getContextEl().addSensibleField(this,e_);
            innArr_[i] = e_;
        }
        return arr_;
    }
    public void add(Struct _key) {
        if (!(_key instanceof ThreadStruct)) {
            return;
        }
        elementSet.put((ThreadStruct) _key,NullStruct.NULL_VALUE);
    }
    public void remove(Struct _key) {
        if (!(_key instanceof ThreadStruct)) {
            return;
        }
        elementSet.remove(_key);
    }
    public Struct contains(Struct _key) {
        if (!(_key instanceof ThreadStruct)) {
            return new BooleanStruct(false);
        }
        return new BooleanStruct(elementSet.containsKey(_key));
    }
    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getAliasThreadSet();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
