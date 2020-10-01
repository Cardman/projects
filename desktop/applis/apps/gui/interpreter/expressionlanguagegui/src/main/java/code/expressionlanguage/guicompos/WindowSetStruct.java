package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.*;
import code.util.CustList;

import java.util.concurrent.ConcurrentHashMap;

public final class WindowSetStruct extends WithoutParentIdStruct implements Struct {
    private ConcurrentHashMap<WindowStruct,Struct> elementSet = new ConcurrentHashMap<WindowStruct,Struct>();

    private final boolean writable;

    public WindowSetStruct(boolean _writable) {
        writable = _writable;
    }

    public ArrayStruct toSnapshotArray(ContextEl _contextEl) {
        CustList<WindowStruct> instantKeys_ = new CustList<WindowStruct>();
        for (WindowStruct s: elementSet.keySet()) {
            instantKeys_.add(s);
        }
        String thClass_ = ((LgNamesGui) _contextEl.getStandards()).getGuiAliases().getAliasWindow();
        int len_ = instantKeys_.size();
        ArrayStruct arr_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(thClass_));
        for (int i = 0; i < len_; i++) {
            Struct e_ = instantKeys_.get(i);
            _contextEl.getInitializingTypeInfos().addSensibleField(this,e_);
            arr_.set(i, e_);
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
        return ((LgNamesGui)_contextEl.getStandards()).getGuiAliases().getAliasWindowSet();
    }

}
