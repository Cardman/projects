package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

import java.util.concurrent.ConcurrentHashMap;

public final class WindowSetStruct implements Struct {
    private ConcurrentHashMap<WindowStruct,Struct> elementSet = new ConcurrentHashMap<WindowStruct,Struct>();

    private final boolean writable;

    public WindowSetStruct(boolean _writable) {
        writable = _writable;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    public ArrayStruct toSnapshotArray(ContextEl _contextEl) {
        CustList<WindowStruct> instantKeys_ = new CustList<WindowStruct>();
        for (WindowStruct s: elementSet.keySet()) {
            instantKeys_.add(s);
        }
        String thClass_ = ((LgNamesGui)_contextEl.getStandards()).getAliasWindow();
        int len_ = instantKeys_.size();
        Struct[] innArr_ = new Struct[len_];
        ArrayStruct arr_ = new ArrayStruct(innArr_,PrimitiveTypeUtil.getPrettyArrayType(thClass_));
        for (int i = 0; i < len_; i++) {
            Struct e_ = instantKeys_.get(i);
            _contextEl.getInitializingTypeInfos().addSensibleField(this,e_);
            innArr_[i] = e_;
        }
        return arr_;
    }
    public void add(Struct _key, boolean _ch) {
        if (!writable && _ch) {
            return;
        }
        if (!(_key instanceof WindowStruct)) {
            return;
        }
        elementSet.put((WindowStruct) _key,NullStruct.NULL_VALUE);
    }
    public void remove(Struct _key, boolean _ch) {
        if (!writable && _ch) {
            return;
        }
        if (!(_key instanceof WindowStruct)) {
            return;
        }
        elementSet.remove(_key);
    }
    public Struct contains(Struct _key) {
        if (!(_key instanceof WindowStruct)) {
            return BooleanStruct.of(false);
        }
        return BooleanStruct.of(elementSet.containsKey(_key));
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui)_contextEl.getStandards()).getAliasWindowSet();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
