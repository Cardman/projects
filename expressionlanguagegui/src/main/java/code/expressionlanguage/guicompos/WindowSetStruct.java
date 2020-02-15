package code.expressionlanguage.guicompos;

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

public final class WindowSetStruct implements Struct {
    private IdMap<WindowStruct,Struct> elementSet = new IdMap<WindowStruct,Struct>();
    private final AbstractLock lock = LockFactory.newLock();
    private boolean writable;

    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean _writable) {
        writable = _writable;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    public ArrayStruct toSnapshotArray(ExecutableCode _contextEl) {
        CustList<WindowStruct> instantKeys_;
        try {
            lock.lock((Locking) _contextEl);
            instantKeys_ = elementSet.getKeys();
        } finally {
            lock.unlock((Locking) _contextEl);
        }
        String thClass_ = ((LgNamesGui)_contextEl.getStandards()).getAliasWindow();
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
    public void add(Struct _key, boolean _ch, GuiContextEl _rCont) {
        if (!writable && _ch) {
            return;
        }
        if (!(_key instanceof WindowStruct)) {
            return;
        }
        try {
            lock.lock(_rCont);
            elementSet.put((WindowStruct) _key,NullStruct.NULL_VALUE);
        } finally {
            lock.unlock(_rCont);
        }
    }
    public void remove(Struct _key, boolean _ch, GuiContextEl _rCont) {
        if (!writable && _ch) {
            return;
        }
        if (!(_key instanceof WindowStruct)) {
            return;
        }
        try {
            lock.lock(_rCont);
            elementSet.removeKey((WindowStruct) _key);
        } finally {
            lock.unlock(_rCont);
        }
    }
    public Struct contains(Struct _key, GuiContextEl _rCont) {
        if (!(_key instanceof WindowStruct)) {
            return new BooleanStruct(false);
        }
        try {
            lock.lock(_rCont);
            return new BooleanStruct(elementSet.contains((WindowStruct) _key));
        } finally {
            lock.unlock(_rCont);
        }
    }
    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui)_contextEl).getAliasWindowSet();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
