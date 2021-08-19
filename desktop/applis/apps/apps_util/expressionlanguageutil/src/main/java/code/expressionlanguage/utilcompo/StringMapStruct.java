package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.structs.*;
import code.threads.AbstractConcurrentMap;
import code.util.CustList;

import java.util.Map.Entry;

public final class StringMapStruct extends WithoutParentIdStruct implements Struct {

    private final AbstractConcurrentMap<String,Struct> elementSet;

    public StringMapStruct(AbstractInterceptor _concurrentFactory) {
        elementSet = _concurrentFactory.newMapStringStruct();
    }
    public ArrayStruct toSnapshotKeys(ContextEl _contextEl) {
        CustList<String> instantKeys_ = new CustList<String>();
        for (String s: elementSet.keySet()) {
            instantKeys_.add(s);
        }
        String thClass_ = _contextEl.getStandards().getContent().getCharSeq().getAliasString();
        int len_ = instantKeys_.size();
        ArrayStruct arr_ = new ArrayStruct(len_, StringExpUtil.getPrettyArrayType(thClass_));
        for (int i = 0; i < len_; i++) {
            String e_ = instantKeys_.get(i);
            arr_.set(i, new StringStruct(e_));
        }
        return arr_;
    }
    public Struct toSnapshotKeys(ContextEl _contextEl, StackCall _stackCall,Struct _value) {
        if (_value == NullStruct.NULL_VALUE) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_contextEl, _contextEl.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
            return NullStruct.NULL_VALUE;
        }
        CustList<EntryMapStringStruct> instantKeys_ = new CustList<EntryMapStringStruct>();
        for (Entry<String, Struct> s: elementSet.entrySet()) {
            instantKeys_.add(new EntryMapStringStruct(s));
        }
        CustList<String> instantKeysStr_ = new CustList<String>();
        for (EntryMapStringStruct s: instantKeys_) {
            if (_value.sameReference(s.value(_stackCall))) {
                instantKeysStr_.add(s.keyIntern());
            }
        }
        String thClass_ = _contextEl.getStandards().getContent().getCharSeq().getAliasString();
        int len_ = instantKeysStr_.size();
        ArrayStruct arr_ = new ArrayStruct(len_, StringExpUtil.getPrettyArrayType(thClass_));
        for (int i = 0; i < len_; i++) {
            String e_ = instantKeysStr_.get(i);
            arr_.set(i, new StringStruct(e_));
        }
        return arr_;
    }

    public BooleanStruct containsKey(ContextEl _contextEl, StackCall _stack, Struct _str) {
        if (!(_str instanceof StringStruct)) {
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_contextEl, _contextEl.getStandards().getContent().getCoreNames().getAliasNullPe(), _stack)));
            return BooleanStruct.of(false);
        }
        String k_ = ((StringStruct)_str).getInstance();
        return BooleanStruct.of(elementSet.containsKey(k_));
    }
    public ArrayStruct toSnapshotValues(ContextEl _contextEl, StackCall _stackCall) {
        CustList<Struct> instantValues_ = new CustList<Struct>();
        for (Struct s: elementSet.values()) {
            instantValues_.add(s);
        }
        String thClass_ = _contextEl.getStandards().getContent().getCoreNames().getAliasObject();
        int len_ = instantValues_.size();
        ArrayStruct arr_ = new ArrayStruct(len_, StringExpUtil.getPrettyArrayType(thClass_));
        for (int i = 0; i < len_; i++) {
            Struct e_ = instantValues_.get(i);
            _stackCall.getInitializingTypeInfos().addSensibleField(this,e_);
            arr_.set(i, e_);
        }
        return arr_;
    }

    public BooleanStruct containsValue(ContextEl _contextEl, StackCall _stack, Struct _str) {
        if (_str == NullStruct.NULL_VALUE) {
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_contextEl, _contextEl.getStandards().getContent().getCoreNames().getAliasNullPe(), _stack)));
            return BooleanStruct.of(false);
        }
        for (Struct s: elementSet.values()) {
            if (_str.sameReference(s)) {
                return BooleanStruct.of(true);
            }
        }
        return BooleanStruct.of(false);
    }
    public ArrayStruct toSnapshot(ContextEl _contextEl, StackCall _stackCall) {
        CustList<Struct> instantValues_ = new CustList<Struct>();
        for (Entry<String, Struct> s: elementSet.entrySet()) {
            instantValues_.add(new EntryMapStringStruct(s));
        }
        String thClass_ = ((LgNamesWithNewAliases)_contextEl.getStandards()).getCustAliases().getAliasEntryStringObject();
        int len_ = instantValues_.size();
        ArrayStruct arr_ = new ArrayStruct(len_, StringExpUtil.getPrettyArrayType(thClass_));
        for (int i = 0; i < len_; i++) {
            Struct e_ = instantValues_.get(i);
            _stackCall.getInitializingTypeInfos().addSensibleField(this,e_);
            arr_.set(i, e_);
        }
        return arr_;
    }
    public Struct put(ContextEl _contextEl, StackCall _stack, Struct _key, Struct _value) {
        if (_stack.getInitializingTypeInfos().isContainedSensibleFields(this)) {
            _stack.getInitializingTypeInfos().failInitEnums();
            return NullStruct.NULL_VALUE;
        }
        if (!(_key instanceof StringStruct) || _value == NullStruct.NULL_VALUE) {
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_contextEl, _contextEl.getStandards().getContent().getCoreNames().getAliasNullPe(), _stack)));
            return NullStruct.NULL_VALUE;
        }
        String k_ = ((StringStruct)_key).getInstance();
        return elementSet.put(k_, _value);
    }
    public Struct putIfAbs(ContextEl _contextEl, StackCall _stack, Struct _key, Struct _value) {
        if (_stack.getInitializingTypeInfos().isContainedSensibleFields(this)) {
            _stack.getInitializingTypeInfos().failInitEnums();
            return NullStruct.NULL_VALUE;
        }
        if (!(_key instanceof StringStruct) || _value == NullStruct.NULL_VALUE) {
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_contextEl, _contextEl.getStandards().getContent().getCoreNames().getAliasNullPe(), _stack)));
            return NullStruct.NULL_VALUE;
        }
        String k_ = ((StringStruct)_key).getInstance();
        return elementSet.putIfAbsent(k_, _value);
    }
    public Struct get(ContextEl _contextEl, StackCall _stack, Struct _key) {
        if (!(_key instanceof StringStruct)) {
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_contextEl, _contextEl.getStandards().getContent().getCoreNames().getAliasNullPe(), _stack)));
            return NullStruct.NULL_VALUE;
        }
        String k_ = ((StringStruct)_key).getInstance();
        Struct struct_ = elementSet.get(k_);
        _stack.getInitializingTypeInfos().addSensibleField(this,struct_);
        return struct_;
    }
    public Struct remove(ContextEl _contextEl, StackCall _stack, Struct _key) {
        if (_stack.getInitializingTypeInfos().isContainedSensibleFields(this)) {
            _stack.getInitializingTypeInfos().failInitEnums();
            return NullStruct.NULL_VALUE;
        }
        if (!(_key instanceof StringStruct)) {
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_contextEl, _contextEl.getStandards().getContent().getCoreNames().getAliasNullPe(), _stack)));
            return NullStruct.NULL_VALUE;
        }
        String k_ = ((StringStruct)_key).getInstance();
        return elementSet.remove(k_);
    }
    public Struct replace(ContextEl _contextEl, StackCall _stack, Struct _key, Struct _value) {
        if (_stack.getInitializingTypeInfos().isContainedSensibleFields(this)) {
            _stack.getInitializingTypeInfos().failInitEnums();
            return NullStruct.NULL_VALUE;
        }
        if (!(_key instanceof StringStruct) || _value == NullStruct.NULL_VALUE) {
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_contextEl, _contextEl.getStandards().getContent().getCoreNames().getAliasNullPe(), _stack)));
            return NullStruct.NULL_VALUE;
        }
        String k_ = ((StringStruct)_key).getInstance();
        return elementSet.replace(k_,_value);
    }
    public BooleanStruct isEmpty() {
        return BooleanStruct.of(elementSet.isEmpty());
    }
    public IntStruct size() {
        return new IntStruct(elementSet.size());
    }
    public void clear(StackCall _stack) {
        if (_stack.getInitializingTypeInfos().isContainedSensibleFields(this)) {
            _stack.getInitializingTypeInfos().failInitEnums();
            return;
        }
        elementSet.clear();
    }
    public void putAll(ContextEl _contextEl, StackCall _stack,Struct _map) {
        if (_stack.getInitializingTypeInfos().isContainedSensibleFields(this)) {
            _stack.getInitializingTypeInfos().failInitEnums();
            return;
        }
        if (!(_map instanceof StringMapStruct)) {
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_contextEl, _contextEl.getStandards().getContent().getCoreNames().getAliasNullPe(), _stack)));
            return;
        }
        StringMapStruct map_=(StringMapStruct)_map;
        for (Entry<String, Struct> s: map_.elementSet.entrySet()) {
            elementSet.put(s.getKey(),s.getValue());
        }
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesWithNewAliases)_contextEl.getStandards()).getCustAliases().getAliasTableStringObject();
    }
}
