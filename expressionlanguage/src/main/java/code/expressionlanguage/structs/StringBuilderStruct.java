package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.StringList;

public final class StringBuilderStruct extends CharSequenceStruct {

    private final StringBuilder instance;

    public StringBuilderStruct(StringBuilder _instance) {
        instance = _instance;
    }
    public static void instantiate(Analyzable _cont, ResultErrorStd _res, ConstructorId _method, Struct... _args) {
        StringList list_ = _method.getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String intPrimType_ = lgNames_.getAliasPrimInteger();
        if (list_.size() == 0) {
            newStringBuilderStruct(_res);
            return;
        }
        if (StringList.quickEq(list_.first(), intPrimType_)) {
            newStringBuilderStructByNumber((NumberStruct) _args[0], lgNames_, _res);
            return;
        }
        newStringBuilderStructByString(_args[0], lgNames_, _res);
    }
    private static void newStringBuilderStruct(ResultErrorStd _res) {
        _res.setResult(new StringBuilderStruct(new StringBuilder()));
    }
    private static void newStringBuilderStructByString(Struct _arg, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_arg instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct arg_ = (CharSequenceStruct) _arg;
        _res.setResult(new StringBuilderStruct(new StringBuilder(arg_.getInstance())));
    }
    private static void newStringBuilderStructByNumber(NumberStruct _arg, LgNames _stds, ResultErrorStd _res) {
        int one_ = _arg.getInstance().intValue();
        if (one_ < 0) {
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringBuilderStruct(new StringBuilder(one_)));
    }

    public static void calculate(ExecutableCode _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        String aliasPrimChar_ = lgNames_.getAliasPrimChar();
        StringBuilderStruct one_ = (StringBuilderStruct) _struct;
        if (StringList.quickEq(name_, lgNames_.getAliasAppend())) {
            if (list_.size() == 1 && StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_))) {
                one_.appendChars(_args[0], _cont, _res);
                return;
            }
            if (list_.size() == 1) {
                one_.append((DisplayableStruct) _args[0], _cont, _res);
                return;
            }
            if (StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar()))) {
                one_.appendChars(_args[0], (NumberStruct) _args[1], (NumberStruct) _args[2], _cont, _res);
                return;
            }
            one_.append((DisplayableStruct) _args[0], (NumberStruct) _args[1], (NumberStruct) _args[2], _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasCapacity())) {
            one_.capacity(_cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasClear())) {
            one_.clear(_cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasDelete())) {
            one_.delete((NumberStruct) _args[0], (NumberStruct) _args[1], _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasDeleteCharAt())) {
            one_.deleteCharAt((NumberStruct) _args[0], _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasEnsureCapacity())) {
            one_.ensureCapacity((NumberStruct) _args[0], _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasInsert())) {
            if (list_.size() == 2 && StringList.quickEq(list_.get(1), PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_))) {
                one_.insertChars((NumberStruct)_args[0], _args[1], _cont, _res);
                return;
            }
            if (list_.size() == 2) {
                one_.insert((NumberStruct)_args[0],(DisplayableStruct) _args[1], _cont, _res);
                return;
            }
            if (StringList.quickEq(list_.get(1), PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar()))) {
                one_.insertChars((NumberStruct)_args[0], _args[1], (NumberStruct)_args[2], (NumberStruct)_args[3], _cont, _res);
                return;
            }
            one_.insert((NumberStruct)_args[0],(DisplayableStruct) _args[1], (NumberStruct)_args[2], (NumberStruct)_args[3], _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplace())) {
            one_.replace((NumberStruct)_args[0], (NumberStruct)_args[1], (CharSequenceStruct) _args[2], _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReverse())) {
            one_.reverse(_cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSetCharAt())) {
            one_.setCharAt((NumberStruct) _args[0],(CharStruct) _args[1], _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSetLength())) {
            one_.setLength((NumberStruct) _args[0], _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasTrimToSize())) {
            one_.trimToSize(_cont, _res);
        }
    }
    private void ensureCapacity(NumberStruct _minimumCapacity, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        instance.ensureCapacity(_minimumCapacity.getInstance().intValue());
        _out.setResult(NullStruct.NULL_VALUE);
    }

    private void trimToSize(ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        instance.trimToSize();
        _out.setResult(NullStruct.NULL_VALUE);
    }

    private void setLength(NumberStruct _newLength, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int newLength_ = _newLength.getInstance().intValue();
        if (newLength_ < 0) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        instance.setLength(newLength_);
        _out.setResult(this);
    }

    private void append(DisplayableStruct _s, NumberStruct _start, NumberStruct _end, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        String toApp_= _s.getDisplayedString(_an).getInstance();
        int start_ = _start.getInstance().intValue();
        int end_ = _end.getInstance().intValue();
        if (start_ < 0 || end_ < 0 || start_ > end_ || end_ > toApp_.length()) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        instance.append(toApp_, start_, end_);
        _out.setResult(this);
    }

    private void appendChars(Struct _str, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        if (!(_str instanceof ArrayStruct)) {
            _out.setError(lgNames_.getAliasNullPe());
            return;
        }
        Struct[] arr_ = ((ArrayStruct)_str).getInstance();
        int len_ = arr_.length;
        char[] chars_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            chars_[i] = ((CharStruct)arr_[i]).getChar();
        }
        instance.append(chars_);
        _out.setResult(this);
    }

    private void appendChars(Struct _str, NumberStruct _offset, NumberStruct _len, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        if (!(_str instanceof ArrayStruct)) {
            _out.setError(lgNames_.getAliasNullPe());
            return;
        }
        int offset_ = _offset.getInstance().intValue();
        int len_ = _len.getInstance().intValue();
        Struct[] arr_ = ((ArrayStruct)_str).getInstance();
        int lenChar_ = arr_.length;
        if (offset_ < 0 || len_ < 0 || offset_ + len_ > lenChar_) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        char[] chars_ = new char[lenChar_];
        for (int i = 0; i < len_; i++) {
            chars_[i] = ((CharStruct)arr_[i]).getChar();
        }
        instance.append(chars_, offset_, len_);
        _out.setResult(this);
    }

    private void append(DisplayableStruct _b, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        instance.append(_b.getDisplayedString(_an).getInstance());
        _out.setResult(this);
    }

    private void delete(NumberStruct _start, NumberStruct _end, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int start_ = _start.getInstance().intValue();
        int end_ = _end.getInstance().intValue();
        if (start_ < 0 || start_ > instance.length() || start_ > end_) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        instance.delete(start_, end_);
        _out.setResult(this);
    }

    private void deleteCharAt(NumberStruct _index, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int index_ = _index.getInstance().intValue();
        if (index_ < 0 || index_ >= instance.length()) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        instance.deleteCharAt(index_);
        _out.setResult(this);
    }

    private void replace(NumberStruct _start, NumberStruct _end, Struct _str, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int start_ = _start.getInstance().intValue();
        int end_ = _end.getInstance().intValue();
        if (start_ < 0 || start_ > instance.length() || start_ > end_) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        if (!(_str instanceof CharSequenceStruct)) {
            _out.setError(lgNames_.getAliasNullPe());
            return;
        }
        CharSequenceStruct ch_ = (CharSequenceStruct) _str;
        instance.replace(start_, end_, ch_.getInstance().toString());
        _out.setResult(this);
    }

    private void insertChars(NumberStruct _index, Struct _str, NumberStruct _offset, NumberStruct _len, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int index_ = _index.getInstance().intValue();
        if (index_ < 0 || index_ > instance.length()) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        if (!(_str instanceof ArrayStruct)) {
            _out.setError(lgNames_.getAliasNullPe());
            return;
        }
        Struct[] arr_ = ((ArrayStruct)_str).getInstance();
        int lenArr_ = arr_.length;
        char[] chars_ = new char[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            chars_[i] = ((CharStruct)arr_[i]).getChar();
        }
        int offset_ = _offset.getInstance().intValue();
        int len_ = _len.getInstance().intValue();
        if (offset_ < 0 || len_ < 0 || offset_ + len_ > chars_.length) {
            _out.setError(lgNames_.getAliasNullPe());
            return;
        }
        instance.insert(index_, chars_, offset_, len_);
        _out.setResult(this);
    }

    private void insertChars(NumberStruct _offset, Struct _str, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int index_ = _offset.getInstance().intValue();
        if (index_ < 0 || index_ > instance.length()) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        if (!(_str instanceof ArrayStruct)) {
            _out.setError(lgNames_.getAliasNullPe());
            return;
        }
        Struct[] arr_ = ((ArrayStruct)_str).getInstance();
        int len_ = arr_.length;
        char[] chars_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            chars_[i] = ((CharStruct)arr_[i]).getChar();
        }
        instance.insert(index_, chars_);
        _out.setResult(this);
    }

    private void insert(NumberStruct _dstOffset,DisplayableStruct _s, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int index_ = _dstOffset.getInstance().intValue();
        if (index_ < 0 || index_ > instance.length()) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        instance.insert(index_, _s.getDisplayedString(_an));
        _out.setResult(this);
    }

    private void insert(NumberStruct _dstOffset, DisplayableStruct _s, NumberStruct _start,
            NumberStruct _end, ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        String toApp_= _s.getDisplayedString(_an).getInstance();
        int index_ = _dstOffset.getInstance().intValue();
        if (index_ < 0 || index_ > instance.length()) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        int start_ = _start.getInstance().intValue();
        int end_ = _end.getInstance().intValue();
        if (start_ < 0 || end_ < 0 || start_ > end_ || end_ > toApp_.length()) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        instance.insert(index_, toApp_, start_, end_);
        _out.setResult(this);
    }

    private void clear(ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        instance.delete(0, instance.length());
        _out.setResult(this);
    }
    private void reverse(ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        instance.reverse();
        _out.setResult(this);
    }

    private void setCharAt(NumberStruct _index, CharStruct _ch,ExecutableCode _an, ResultErrorStd _out) {
        ContextEl cont_ = _an.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(this)) {
            cont_.failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int index_ = _index.getInstance().intValue();
        if (index_ < 0 || index_ >= instance.length()) {
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        instance.setCharAt(index_, _ch.getChar());
        _out.setResult(this);
    }

    private void capacity(ExecutableCode _an, ResultErrorStd _out) {
        _out.setResult(new IntStruct(instance.capacity()));
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasStringBuilder();
    }

    @Override
    public StringBuilder getInstance() {
        return instance;
    }


}
