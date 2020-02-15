package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.stream.AbstractLock;
import code.stream.LockFactory;
import code.stream.Locking;
import code.util.CustList;
import code.util.IdMap;

public final class ThreadSetStruct implements Struct {

    private IdMap<Thread,Struct> elementSet = new IdMap<Thread,Struct>();
    private final AbstractLock lock = LockFactory.newLock();
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    public ArrayStruct toSnapshotArray(ExecutableCode _contextEl) {
        CustList<Thread> instant_;
        try {
            lock.lock((Locking) _contextEl);
            instant_ = new CustList<Thread>(elementSet.getKeys());
        } finally {
            lock.unlock((Locking) _contextEl);
        }
        CustList<Struct> instantKeys_ = new CustList<Struct>();
        for (Thread s: instant_) {
            instantKeys_.add(new ThreadStruct(s));
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
    public void add(Struct _key, RunnableContextEl _rCont) {
        if (!(_key instanceof ThreadStruct)) {
            return;
        }
        try {
            lock.lock(_rCont);
            elementSet.put(((ThreadStruct) _key).getThread(),NullStruct.NULL_VALUE);
        } finally {
            lock.unlock(_rCont);
        }
    }
    public void remove(Struct _key, RunnableContextEl _rCont) {
        if (!(_key instanceof ThreadStruct)) {
            return;
        }
        try {
            lock.lock(_rCont);
            elementSet.removeKey(((ThreadStruct) _key).getThread());
        } finally {
            lock.unlock(_rCont);
        }
    }
    public Struct contains(Struct _key, RunnableContextEl _rCont) {
        if (!(_key instanceof ThreadStruct)) {
            return new BooleanStruct(false);
        }
        try {
            lock.lock(_rCont);
            return new BooleanStruct(elementSet.contains(((ThreadStruct) _key).getThread()));
        } finally {
            lock.unlock(_rCont);
        }
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
