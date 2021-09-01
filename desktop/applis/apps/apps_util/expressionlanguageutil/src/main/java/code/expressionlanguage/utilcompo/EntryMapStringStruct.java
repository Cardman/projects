package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.structs.*;

import java.util.Map.Entry;

public final class EntryMapStringStruct extends WithoutParentIdStruct implements Struct {
    private final Entry<String,Struct> entry;
    private final StringMapStruct owner;
    EntryMapStringStruct(Entry<String,Struct> _entry, StringMapStruct _owner) {
        entry = _entry;
        owner = _owner;
    }

    public StringStruct key() {
        return new StringStruct(keyIntern());
    }

    public String keyIntern() {
        return entry.getKey();
    }
    public Struct value(StackCall _stackCall) {
        Struct value_ = entry.getValue();
        _stackCall.getInitializingTypeInfos().addSensibleField(this,value_);
        return value_;
    }

    public Struct value(ContextEl _contextEl, StackCall _stack,Struct _v) {
        if (_stack.getInitializingTypeInfos().isContainedSensibleFields(this)) {
            _stack.getInitializingTypeInfos().failInitEnums();
            return NullStruct.NULL_VALUE;
        }
        if (_v == NullStruct.NULL_VALUE) {
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_contextEl, _contextEl.getStandards().getContent().getCoreNames().getAliasNullPe(), _stack)));
            return NullStruct.NULL_VALUE;
        }
        return entry.setValue(_v);
    }

    public StringMapStruct getOwner() {
        return owner;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesWithNewAliases)_contextEl.getStandards()).getCustAliases().getAliasEntryStringObject();
    }

}
