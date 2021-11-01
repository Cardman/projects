package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringDataUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.Replacement;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AliasCharSequenceType {

    private String aliasCharSequence;
    private String aliasCharSequenceToString;
    private String aliasCharSequenceCompareTo;
    private String aliasCharSequenceEquals;
    private String aliasString;
    private String aliasStringCompare;
    private String aliasStringValueOf;
    private String aliasLength;
    private String aliasCharAt;
    private String aliasToCharArray;
    private String aliasSplit;
    private String aliasSplitStrings;
    private String aliasSplitChars;
    private String aliasReplace;
    private String aliasReplaceString;
    private String aliasReplaceMultiple;
    private String aliasEqualsIgnoreCase;
    private String aliasCompareToIgnoreCase;
    private String aliasContains;
    private String aliasEndsWith;
    private String aliasStartsWith;
    private String aliasIndexOf;
    private String aliasFormat;
    private String aliasGetBytes;
    private String aliasIsEmpty;
    private String aliasLastIndexOf;
    private String aliasRegionMatches;
    private String aliasSubstring;
    private String aliasSubSequence;
    private String aliasToLowerCase;
    private String aliasToUpperCase;
    private String aliasTrim;

    private String aliasStringBuilder;
    private String aliasAppend;
    private String aliasCapacity;
    private String aliasClear;
    private String aliasDelete;
    private String aliasDeleteCharAt;
    private String aliasEnsureCapacity;
    private String aliasInsert;
    private String aliasReverse;
    private String aliasSetCharAt;
    private String aliasSetLength;
    private String aliasSame;
    private String aliasTrimToSize;

    private String aliasReplacement;
    private String aliasGetOldString;
    private String aliasGetNewString;
    private final AliasParamCharSequence params = new AliasParamCharSequence();

    public static void calculateString(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, StackCall _stackCall, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            StringStruct str_ = NumParsers.getString(_struct);
            calculateLocString(str_,_cont, _res, _method, _stackCall, _args);
            return;
        }
        String name_ = _method.getConstraints().getName();
        int nbParams_ = _method.getConstraints().getParametersTypesLength();
        LgNames lgNames_ = _cont.getStandards();
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasStringCompare())) {
            Struct arg_ = _args[0];
            if (!(arg_ instanceof StringStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
                return;
            }
            StringStruct first_ = (StringStruct) arg_;
            compareToString(first_,_args[1], _res, _cont, _stackCall);
            return;
        }
        Struct arg_ = NumParsers.getArg(nbParams_, _args);
        if (NumParsers.isDisplay(nbParams_, arg_)) {
            _res.setResult(ExecCatOperation.getDisplayable(new Argument(arg_), _cont));
            return;
        }
        if (!(arg_ instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
            return;
        }
        tryGetCharArray(_res, nbParams_, (ArrayStruct) arg_, _args, _cont, _stackCall);
    }

    private static void tryGetCharArray(ResultErrorStd _res, int _list, ArrayStruct _arg, Struct[] _args, ContextEl _context, StackCall _stackCall) {
        int len_ = _arg.getLength();
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = NumParsers.convertToChar(_arg.get(i)).getChar();
        }
        if (_list == 1) {
            _res.setResult(new StringStruct(String.valueOf(arr_)));
            return;
        }
        int one_ = (NumParsers.convertToNumber(_args[0])).intStruct();
        int two_ = (NumParsers.convertToNumber(_args[1])).intStruct();
        if (NumParsers.koArray(arr_, one_, two_)) {
            if (one_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_context, getBeginMessage(one_), _stackCall)));
            } else if (two_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_context, getBeginMessage(two_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_context, getBeginEndMessage(arr_.length, one_, two_), _stackCall)));
            }
            return;
        }
        _res.setResult(new StringStruct(String.valueOf(arr_,one_,two_)));
    }

    private static void calculateLocString(StringStruct _str, ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, StackCall _stackCall, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        String stringType_ = lgNames_.getContent().getCharSeq().getAliasString();
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasRegionMatches())) {
            regionMatches(_str, NumParsers.convertToBoolean(_args[0]), NumParsers.convertToNumber(_args[1]), _args[2],
                    NumParsers.convertToNumber(_args[3]), NumParsers.convertToNumber(_args[4]), _res, _cont, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasReplaceString())) {
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), stringType_)) {
                replaceString(_str,_args[0], _args[1], _res);
                return;
            }
            replace(_str, NumParsers.convertToChar(_args[0]), NumParsers.convertToChar(_args[1]), _res);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasReplaceMultiple())) {
            replaceMultiple(_str,_args[0], _res, _cont, _stackCall);
            return;
        }
        String one_ = _str.getInstance();
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasCompareToIgnoreCase())) {
            Struct two_ = _args[0];
            if (!(two_ instanceof StringStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_cont, _stackCall)));
            } else {
                StringStruct t_ = (StringStruct) two_;
                _res.setResult(new IntStruct(NumParsers.compareToIgnoreCase(one_,t_.getInstance())));
            }
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasEqualsIgnoreCase())) {
            Struct two_ = _args[0];
            if (!(two_ instanceof StringStruct)) {
                _res.setResult(BooleanStruct.of(false));
            } else {
                StringStruct t_ = (StringStruct) two_;
                _res.setResult(BooleanStruct.of(NumParsers.equalsIgnoreCase(one_,t_.getInstance())));
            }
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasToLowerCase())) {
            _res.setResult(new StringStruct(StringDataUtil.toLowerCase(one_)));
            return;
        }
        _res.setResult(new StringStruct(StringDataUtil.toUpperCase(one_)));
    }

    private static void compareToString(StringStruct _str, Struct _anotherString, ResultErrorStd _res, ContextEl _context, StackCall _stackCall) {
        if (!(_anotherString instanceof StringStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
            return;
        }
        StringStruct st_ = (StringStruct)_anotherString;
        _res.setResult(new IntStruct(StringUtil.compareStrings(_str.getInstance(),st_.getInstance())));
    }

    private static void regionMatches(StringStruct _str, BooleanStruct _case, NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
                                      NumberStruct _len, ResultErrorStd _res, ContextEl _context, StackCall _stackCall) {
        if (!(_other instanceof StringStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
            return;
        }
        boolean case_ = BooleanStruct.isTrue(_case);
        int to_ = _toffset.intStruct();
        StringStruct other_ = (StringStruct) _other;
        int po_ = _ooffset.intStruct();
        int comLen_ = _len.intStruct();
        _res.setResult(BooleanStruct.of(NumParsers.regionMatches(_str.getInstance(),case_,to_, other_.getInstance(), po_, comLen_)));
    }

    private static void replace(StringStruct _str, CharStruct _oldChar, CharStruct _newChar, ResultErrorStd _res) {
        char oldChar_ = _oldChar.getChar();
        char newChar_ = _newChar.getChar();
        _res.setResult(new StringStruct(_str.getInstance().replace(oldChar_, newChar_)));
    }

    private static void replaceString(StringStruct _str, Struct _oldChar, Struct _newChar, ResultErrorStd _res) {
        String old_;
        old_ = NumParsers.getStringValue(_oldChar);
        String new_;
        new_ = NumParsers.getStringValue(_newChar);
        String out_ = StringUtil.replace(_str.getInstance(), old_, new_);
        _res.setResult(new StringStruct(out_));
    }

    private static void replaceMultiple(StringStruct _st, Struct _seps, ResultErrorStd _res, ContextEl _context, StackCall _stackCall) {
        if (!(_seps instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        int lenSeps_ = arrSep_.getLength();
        Replacement[] seps_ = new Replacement[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrSep_.get(i);
            if (!(curSep_ instanceof ReplacementStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
                return;
            }
            seps_[i] = NumParsers.getReplacement(curSep_).getInstance();
            if (seps_[i].getNewString() == null) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
                return;
            }
            if (seps_[i].getOldString() == null) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
                return;
            }
        }
        _res.setResult(new StringStruct(StringUtil.replaceMult(_st.getInstance(), seps_)));
    }

    private static void calculateStrBuilder(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, StackCall _stackCall, Struct... _args) {
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _method.getConstraints().getName();
        String aliasPrimChar_ = lgNames_.getContent().getPrimTypes().getAliasPrimChar();
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasSame())) {
            _res.setResult(BooleanStruct.of(_args[0] == _args[1]));
            return;
        }
        StringBuilderStruct one_ = NumParsers.getStrBuilder(_struct);
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasAppend())) {
            if (_method.getConstraints().getParametersTypesLength() == 1 && StringUtil.quickEq(_method.getConstraints().getParametersType(0), StringExpUtil.getPrettyArrayType(aliasPrimChar_))) {
                appendChars(one_,_args[0], _cont, _res, _stackCall);
                return;
            }
            if (_method.getConstraints().getParametersTypesLength() == 1) {
                append(one_,ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont), _res, _stackCall);
                return;
            }
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(0), StringExpUtil.getPrettyArrayType(lgNames_.getContent().getPrimTypes().getAliasPrimChar()))) {
                appendChars(one_,_args[0], NumParsers.convertToNumber(_args[1]), NumParsers.convertToNumber(_args[2]), _cont, _res, _stackCall);
                return;
            }
            append(one_,ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont), NumParsers.convertToNumber(_args[1]), NumParsers.convertToNumber(_args[2]), _cont, _res, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasCapacity())) {
            capacity(one_,_res);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasClear())) {
            clear(one_, _res, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasDelete())) {
            delete(one_, NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]), _cont, _res, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasDeleteCharAt())) {
            deleteCharAt(one_, NumParsers.convertToNumber(_args[0]), _cont, _res, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasEnsureCapacity())) {
            ensureCapacity(one_, NumParsers.convertToNumber(_args[0]), _res, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasInsert())) {
            if (_method.getConstraints().getParametersTypesLength() == 2 && StringUtil.quickEq(_method.getConstraints().getParametersType(1), StringExpUtil.getPrettyArrayType(aliasPrimChar_))) {
                insertChars(one_, NumParsers.convertToNumber(_args[0]), _args[1], _cont, _res, _stackCall);
                return;
            }
            if (_method.getConstraints().getParametersTypesLength() == 2) {
                insert(one_, NumParsers.convertToNumber(_args[0]),
                        ExecCatOperation.getDisplayable(new Argument(_args[1]),_cont), _cont, _res, _stackCall);
                return;
            }
            if (StringUtil.quickEq(_method.getConstraints().getParametersType(1), StringExpUtil.getPrettyArrayType(lgNames_.getContent().getPrimTypes().getAliasPrimChar()))) {
                insertChars(one_, NumParsers.convertToNumber(_args[0]), _args[1], NumParsers.convertToNumber(_args[2]), NumParsers.convertToNumber(_args[3]), _cont, _res, _stackCall);
                return;
            }
            insert(one_, NumParsers.convertToNumber(_args[0]),
                    ExecCatOperation.getDisplayable(new Argument(_args[1]),_cont), NumParsers.convertToNumber(_args[2]), NumParsers.convertToNumber(_args[3]), _cont, _res, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasReplace())) {
            replace(one_, NumParsers.convertToNumber(_args[0]), NumParsers.convertToNumber(_args[1]), _args[2], _cont, _res, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasReverse())) {
            reverse(one_, _res, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasSetCharAt())) {
            setCharAt(one_, NumParsers.convertToNumber(_args[0]), NumParsers.convertToChar(_args[1]), _cont, _res, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasSetLength())) {
            setLength(one_, NumParsers.convertToNumber(_args[0]), _cont, _res, _stackCall);
            return;
        }
        trimToSize(one_, _res, _stackCall);
    }

    private static void ensureCapacity(StringBuilderStruct _instance, NumberStruct _minimumCapacity, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().ensureCapacity(_minimumCapacity.intStruct());
        _out.setResult(_instance);
    }

    private static void trimToSize(StringBuilderStruct _instance, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().trimToSize();
        _out.setResult(_instance);
    }

    private static void setLength(StringBuilderStruct _instance, NumberStruct _newLength, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int newLength_ = _newLength.intStruct();
        if (newLength_ < 0) {
            _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(newLength_), _stackCall)));
            return;
        }
        _instance.getInstance().setLength(newLength_);
        _out.setResult(_instance);
    }

    private static void append(StringBuilderStruct _instance, DisplayableStruct _s, NumberStruct _start, NumberStruct _end, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        String toApp_= _s.getDisplayedString(_an).getInstance();
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || start_ > end_ || end_ > toApp_.length()) {
            if (start_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(start_), _stackCall)));
            } else if (start_ > end_){
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(start_, ">", end_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(end_, ">", toApp_.length()), _stackCall)));
            }
            return;
        }
        _instance.getInstance().append(toApp_, start_, end_);
        _out.setResult(_instance);
    }

    private static void appendChars(StringBuilderStruct _instance, Struct _str, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        if (!(_str instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_an, _stackCall)));
            return;
        }
        int len_ =  ((ArrayStruct)_str).getLength();
        char[] chars_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            chars_[i] = NumParsers.convertToChar(((ArrayStruct)_str).get(i)).getChar();
        }
        _instance.getInstance().append(chars_);
        _out.setResult(_instance);
    }

    private static void appendChars(StringBuilderStruct _instance, Struct _str, NumberStruct _offset, NumberStruct _len, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        if (!(_str instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_an, _stackCall)));
            return;
        }
        int offset_ = _offset.intStruct();
        int len_ = _len.intStruct();
        int lenChar_ = ((ArrayStruct)_str).getLength();
        if (offset_ < 0 || len_ < 0 || offset_ + len_ > lenChar_) {
            if (offset_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(offset_), _stackCall)));
            } else if (len_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(len_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginEndMessage(lenChar_, offset_, len_), _stackCall)));
            }
            return;
        }
        char[] chars_ = new char[lenChar_];
        for (int i = 0; i < lenChar_; i++) {
            chars_[i] = NumParsers.convertToChar(((ArrayStruct)_str).get(i)).getChar();
        }
        _instance.getInstance().append(chars_, offset_, len_);
        _out.setResult(_instance);
    }

    private static void append(StringBuilderStruct _instance, StringStruct _b, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().append(_b.getInstance());
        _out.setResult(_instance);
    }

    private static void delete(StringBuilderStruct _instance, NumberStruct _start, NumberStruct _end, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || start_ > _instance.getInstance().length() || start_ > end_) {
            if (start_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(start_), _stackCall)));
            } else if (start_ > _instance.getInstance().length()) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(start_, ">", _instance.getInstance().length()), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(start_, ">", end_), _stackCall)));
            }
            return;
        }
        _instance.getInstance().delete(start_, end_);
        _out.setResult(_instance);
    }

    private static void deleteCharAt(StringBuilderStruct _instance, NumberStruct _index, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int index_ = _index.intStruct();
        if (index_ < 0 || index_ >= _instance.getInstance().length()) {
            if (index_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(index_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(index_, ">=", _instance.getInstance().length()), _stackCall)));
            }
            return;
        }
        _instance.getInstance().deleteCharAt(index_);
        _out.setResult(_instance);
    }

    private static void replace(StringBuilderStruct _instance, NumberStruct _start, NumberStruct _end, Struct _str, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || start_ > _instance.getInstance().length() || start_ > end_) {
            if (start_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(start_), _stackCall)));
            } else if (start_ > _instance.getInstance().length()) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(start_, ">", _instance.getInstance().length()), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(start_, ">", end_), _stackCall)));
            }
            return;
        }
        if (!(_str instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_an, _stackCall)));
            return;
        }
        CharSequenceStruct ch_ = NumParsers.getCharSeq(_str);
        _instance.getInstance().replace(start_, end_, ch_.toStringInstance());
        _out.setResult(_instance);
    }

    private static void insertChars(StringBuilderStruct _instance, NumberStruct _index, Struct _str, NumberStruct _offset, NumberStruct _len, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int index_ = _index.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(index_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(index_, ">", _instance.getInstance().length()), _stackCall)));
            }
            return;
        }
        if (!(_str instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_an, _stackCall)));
            return;
        }
        int lenArr_ = ((ArrayStruct)_str).getLength();
        char[] chars_ = new char[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            chars_[i] = NumParsers.convertToChar(((ArrayStruct)_str).get(i)).getChar();
        }
        int offset_ = _offset.intStruct();
        int len_ = _len.intStruct();
        if (offset_ < 0 || len_ < 0 || offset_ + len_ > chars_.length) {
            if (offset_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(offset_), _stackCall)));
            } else if (len_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(len_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginEndMessage(chars_.length, offset_, len_), _stackCall)));
            }
            return;
        }
        _instance.getInstance().insert(index_, chars_, offset_, len_);
        _out.setResult(_instance);
    }

    private static void insertChars(StringBuilderStruct _instance, NumberStruct _offset, Struct _str, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int index_ = _offset.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(index_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(index_, ">", _instance.getInstance().length()), _stackCall)));
            }
            return;
        }
        if (!(_str instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_an, _stackCall)));
            return;
        }
        int len_ = ((ArrayStruct) _str).getLength();
        char[] chars_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            chars_[i] = NumParsers.convertToChar(((ArrayStruct) _str).get(i)).getChar();
        }
        _instance.getInstance().insert(index_, chars_);
        _out.setResult(_instance);
    }

    private static void insert(StringBuilderStruct _instance, NumberStruct _dstOffset, StringStruct _s, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int index_ = _dstOffset.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(index_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(index_, ">", _instance.getInstance().length()), _stackCall)));
            }
            return;
        }
        _instance.getInstance().insert(index_, _s.getInstance());
        _out.setResult(_instance);
    }

    private static void insert(StringBuilderStruct _instance, NumberStruct _dstOffset, DisplayableStruct _s, NumberStruct _start,
                               NumberStruct _end, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        String toApp_= _s.getDisplayedString(_an).getInstance();
        int index_ = _dstOffset.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(index_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(index_, ">", _instance.getInstance().length()), _stackCall)));
            }
            return;
        }
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || end_ < 0 || start_ > end_ || end_ > toApp_.length()) {
            if (start_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(start_), _stackCall)));
            } else if (end_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(end_), _stackCall)));
            } else if (start_ > end_) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(start_, ">", end_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(end_, ">", toApp_.length()), _stackCall)));
            }
            return;
        }
        _instance.getInstance().insert(index_, toApp_, start_, end_);
        _out.setResult(_instance);
    }

    private static void clear(StringBuilderStruct _instance, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().delete(0, _instance.getInstance().length());
        _out.setResult(_instance);
    }

    private static void reverse(StringBuilderStruct _instance, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().reverse();
        _out.setResult(_instance);
    }

    private static void setCharAt(StringBuilderStruct _instance, NumberStruct _index, CharStruct _ch, ContextEl _an, ResultErrorStd _out, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int index_ = _index.intStruct();
        if (index_ < 0 || index_ >= _instance.getInstance().length()) {
            if (index_ < 0) {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getBeginMessage(index_), _stackCall)));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(getBadIndex(_an, getEndMessage(index_, ">=", _instance.getInstance().length()), _stackCall)));
            }
            return;
        }
        _instance.getInstance().setCharAt(index_, _ch.getChar());
        _out.setResult(_instance);
    }

    private static void capacity(StringBuilderStruct _instance, ResultErrorStd _out) {
        _out.setResult(new IntStruct(_instance.getInstance().capacity()));
    }

    private static void calculateCharSeq(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, StackCall _stackCall, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            calculateLocCharSeq(NumParsers.getCharSeq(_struct), _cont, _res, _method, _stackCall, _args);
            return;
        }
        if (!(_args[0] instanceof CharSequenceStruct)) {
            _res.setResult(BooleanStruct.of(_args[1] == NullStruct.NULL_VALUE));
            return;
        }
        _res.setResult(BooleanStruct.of(NumParsers.sameEq(NumParsers.getCharSeq(_args[0]),_args[1])));
    }

    private static void calculateLocCharSeq(CharSequenceStruct _charSequence, ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, StackCall _stackCall, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasGetBytes())) {
            getBytes(_charSequence, lgNames_, _res);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasRegionMatches())) {
            regionMatches(_charSequence, NumParsers.convertToNumber(_args[0]), _args[1], NumParsers.convertToNumber(_args[2]), NumParsers.convertToNumber(_args[3]), _res, _cont, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasSplit())) {
            if (_method.getConstraints().getParametersTypesLength() == 1) {
                if (!(_args[0] instanceof CharStruct)) {
                    splitSingleString(_charSequence, _args[0], lgNames_, _res, _cont, _stackCall);
                    return;
                }
                splitSingleChar(_charSequence, (CharStruct)_args[0], lgNames_, _res);
                return;
            }
            if (!(_args[0] instanceof CharStruct)) {
                splitSingleString(_charSequence, _args[0], NumParsers.convertToNumber(_args[1]), lgNames_, _res, _cont, _stackCall);
                return;
            }
            splitSingleChar(_charSequence, (CharStruct)_args[0], NumParsers.convertToNumber(_args[1]), lgNames_, _res);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasTrim())) {
            trim(_charSequence, _res);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasToCharArray())) {
            toCharArray(_charSequence, lgNames_, _res);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasSplitStrings())) {
            if (_method.getConstraints().getParametersTypesLength() == 1) {
                splitStrings(_charSequence, _args[0], lgNames_, _res, _cont, _stackCall);
                return;
            }
            splitStrings(_charSequence, NumParsers.convertToNumber(_args[0]), _args[1], lgNames_, _res, _cont, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasSplitChars())) {
            splitChars(_charSequence, _args[0], lgNames_, _res, _cont, _stackCall);
            return;
        }
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasFormat())) {
            format(_charSequence, _args[0], _res, _cont, _stackCall);
            return;
        }
        _res.setResult(_charSequence.getDisplayedString(_cont));
    }

    private static void getBytes(CharSequenceStruct _charSequence, LgNames _stds, ResultErrorStd _res) {
        String bytePrim_ = _stds.getContent().getPrimTypes().getAliasPrimByte();
        String seq_ = _charSequence.toStringInstance();
        byte[] list_ = StringUtil.encode(seq_);
        bytePrim_ = StringExpUtil.getPrettyArrayType(bytePrim_);
        int len_ = list_.length;
        ArrayStruct result_ = new ArrayStruct(len_, bytePrim_);
        for (int i = 0; i < len_; i++) {
            result_.set(i, new ByteStruct(list_[i]));
        }
        _res.setResult(result_);
    }

    private static void regionMatches(CharSequenceStruct _charSequence, NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
                                      NumberStruct _len, ResultErrorStd _res, ContextEl _context, StackCall _stackCall) {
        if (!(_other instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
            return;
        }
        int to_ = _toffset.intStruct();
        CharSequenceStruct other_ = NumParsers.getCharSeq(_other);
        int po_ = _ooffset.intStruct();
        int comLen_ = _len.intStruct();
        _res.setResult(BooleanStruct.of(NumParsers.regionMatches(_charSequence.toStringInstance(),to_, other_.toStringInstance(), po_, comLen_)));
    }

    private static void splitSingleChar(CharSequenceStruct _charSequence, CharStruct _sep, LgNames _stds, ResultErrorStd _res) {
        splitSingleChar(_charSequence, _sep, new IntStruct(-1), _stds, _res);
    }

    private static void splitSingleChar(CharSequenceStruct _charSequence, CharStruct _sep, NumberStruct _lim, LgNames _stds, ResultErrorStd _res) {
        int lim_ = _lim.intStruct();
        if (lim_ < -1) {
            lim_ = -1;
        }
        char ch_ = _sep.getChar();
        StringList parts_ = StringUtil.splitChars(_charSequence.toStringInstance(), ch_);
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = Math.min(lim_,lenArr_);
        }
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        ArrayStruct result_ = new ArrayStruct(lenArr_, aliasString_);
        for (int i = 0; i < lenArr_; i++) {
            result_.set(i, new StringStruct(parts_.get(i)));
        }
        _res.setResult(result_);
    }

    private static void splitChars(CharSequenceStruct _charSequence, Struct _seps, LgNames _stds, ResultErrorStd _res, ContextEl _context, StackCall _stackCall) {
        if (!(_seps instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        int lenSeps_ = arrSep_.getLength();
        char[] seps_ = new char[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrSep_.get(i);
            seps_[i] = NumParsers.convertToChar(curSep_).getChar();
        }
        StringList parts_ = StringUtil.splitChars(_charSequence.toStringInstance(), seps_);
        int lenArr_ = parts_.size();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        ArrayStruct result_ = new ArrayStruct(lenArr_, aliasString_);
        for (int i = 0; i < lenArr_; i++) {
            result_.set(i, new StringStruct(parts_.get(i)));
        }
        _res.setResult(result_);
    }

    private static void splitSingleString(CharSequenceStruct _charSequence, Struct _sep, LgNames _stds, ResultErrorStd _res, ContextEl _context, StackCall _stackCall) {
        splitSingleString(_charSequence, _sep, new IntStruct(-1), _stds, _res, _context, _stackCall);
    }

    private static void splitStrings(CharSequenceStruct _charSequence, Struct _seps, LgNames _stds, ResultErrorStd _res, ContextEl _context, StackCall _stackCall) {
        splitStrings(_charSequence, new IntStruct(-1), _seps, _stds, _res, _context, _stackCall);
    }

    private static void splitStrings(CharSequenceStruct _charSequence, NumberStruct _lim, Struct _seps, LgNames _stds, ResultErrorStd _res, ContextEl _context, StackCall _stackCall) {
        if (!(_seps instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        int lenSeps_ = arrSep_.getLength();
        String[] seps_ = new String[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrSep_.get(i);
            if (!(curSep_ instanceof CharSequenceStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
                return;
            }
            seps_[i] = NumParsers.getCharSeq(curSep_).toStringInstance();
        }
        int lim_ = _lim.intStruct();
        if (lim_ < -1) {
            lim_ = -1;
        }
        StringList parts_ = StringUtil.splitStrings(_charSequence.toStringInstance(), seps_);
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = Math.min(lim_,lenArr_);
        }
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        ArrayStruct result_ = new ArrayStruct(lenArr_, aliasString_);
        for (int i = 0; i < lenArr_; i++) {
            result_.set(i, new StringStruct(parts_.get(i)));
        }
        _res.setResult(result_);
    }

    private static void splitSingleString(CharSequenceStruct _charSequence, Struct _sep, NumberStruct _lim, LgNames _stds, ResultErrorStd _res, ContextEl _context, StackCall _stackCall) {
        if (!(_sep instanceof CharSequenceStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
            return;
        }
        int lim_ = _lim.intStruct();
        if (lim_ < -1) {
            lim_ = -1;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_sep);
        StringList parts_ = StringUtil.splitStrings(_charSequence.toStringInstance(), str_.toStringInstance());
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = Math.min(lim_,lenArr_);
        }
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        ArrayStruct result_ = new ArrayStruct(lenArr_, aliasString_);
        for (int i = 0; i < lenArr_; i++) {
            result_.set(i, new StringStruct(parts_.get(i)));
        }
        _res.setResult(result_);
    }

    private static void trim(CharSequenceStruct _charSequence, ResultErrorStd _res) {
        _res.setResult(new StringStruct(_charSequence.toStringInstance().trim()));
    }

    private static void toCharArray(CharSequenceStruct _charSequence, LgNames _stds, ResultErrorStd _res) {
        String aliasChar_ = _stds.getContent().getPrimTypes().getAliasPrimChar();
        aliasChar_ = StringExpUtil.getPrettyArrayType(aliasChar_);
        int len_ = _charSequence.length();
        ArrayStruct arr_ = new ArrayStruct(len_,aliasChar_);
        for (int i = 0; i < len_; i++) {
            arr_.set(i, new CharStruct(_charSequence.charAt(i)));
        }
        _res.setResult(arr_);
    }

    private static void format(CharSequenceStruct _charSequence, Struct _seps, ResultErrorStd _res, ContextEl _context, StackCall _stackCall) {
        if (!(_seps instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        int lenSeps_ = arrSep_.getLength();
        String[] seps_ = new String[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrSep_.get(i);
            if (!(curSep_ instanceof CharSequenceStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(getNpe(_context, _stackCall)));
                return;
            }
            seps_[i] = NumParsers.getCharSeq(curSep_).toStringInstance();
        }
        _res.setResult(new StringStruct(StringUtil.simpleStringsFormat(_charSequence.toStringInstance(), seps_)));
    }

    public static ErrorStruct getBadIndex(ContextEl _context, String _message, StackCall _stackCall) {
        return new ErrorStruct(_context, _message, _context.getStandards().getContent().getCoreNames().getAliasBadIndex(), _stackCall);
    }

    private static ErrorStruct getNpe(ContextEl _context, StackCall _stackCall) {
        return new ErrorStruct(_context, _context.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall);
    }

    public static void calculate(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct) {
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        ReplacementStruct rp_ = NumParsers.getReplacement(_struct);
        if (StringUtil.quickEq(name_, lgNames_.getContent().getCharSeq().getAliasGetNewString())) {
            String newStr_ = rp_.getInstance().getNewString();
            _res.setResult(Argument.wrapStr(newStr_));
            return;
        }
        String oldStr_ = rp_.getInstance().getOldString();
        _res.setResult(Argument.wrapStr(oldStr_));

    }

    public void build(LgNames _lgNames) {
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        StringList params_;
        StringList noTypes_ = new StringList();
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_;
        CustList<CstFieldInfo> fields_;
        StandardMethod method_;
        StandardConstructor ctor_;
        StandardType std_;
        std_ = new StandardInterface(aliasCharSequence, methods_, noTypes_);
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimDouble_ = _lgNames.getContent().getPrimTypes().getAliasPrimDouble();
        String aliasPrimFloat_ = _lgNames.getContent().getPrimTypes().getAliasPrimFloat();
        String aliasPrimLong_ = _lgNames.getContent().getPrimTypes().getAliasPrimLong();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasPrimChar_ = _lgNames.getContent().getPrimTypes().getAliasPrimChar();
        String aliasPrimShort_ = _lgNames.getContent().getPrimTypes().getAliasPrimShort();
        String aliasPrimByte_ = _lgNames.getContent().getPrimTypes().getAliasPrimByte();
        String aliasObject_ = _lgNames.getContent().getCoreNames().getAliasObject();
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasString, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0SubSequence0(),params.getAliasCharSequence0SubSequence1()),new FctCharSeqSubstring1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0CharAt0()),new FctCharSeqCharAt());
        methods_.add( method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger_, false, MethodModifier.NORMAL,new FctCharSeqLength());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0Substring0(),params.getAliasCharSequence0Substring1()),new FctCharSeqSubstring1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1Substring0()),new FctCharSeqSubstring0());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasCharSequenceCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0CompareTo0()),new FctCharSeqCompareTo());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasContains, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0Contains0()),new FctCharSeqContains());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0StartsWith0()),new FctCharSeqStartsWith0());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1StartsWith0(),params.getAliasCharSequence1StartsWith1()),new FctCharSeqStartsWith1());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasEndsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0EndsWith0()),new FctCharSeqEndsWith());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0IndexOf0()),new FctCharSeqIndexOf1());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1IndexOf0(),params.getAliasCharSequence1IndexOf1()),new FctCharSeqIndexOf3());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence2IndexOf0()),new FctCharSeqIndexOf0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence3IndexOf0(),params.getAliasCharSequence3IndexOf1()),new FctCharSeqIndexOf2());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0LastIndexOf0()),new FctCharSeqLastIndexOf1());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1LastIndexOf0(),params.getAliasCharSequence1LastIndexOf1()),new FctCharSeqLastIndexOf3());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence2LastIndexOf0()),new FctCharSeqLastIndexOf0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence3LastIndexOf0(),params.getAliasCharSequence3LastIndexOf1()),new FctCharSeqLastIndexOf2());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEmpty, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new FctCharSeqIsEmpty());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToCharArray, params_, StringExpUtil.getPrettyArrayType(aliasPrimChar_), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetBytes, params_, StringExpUtil.getPrettyArrayType(aliasPrimByte_), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasFormat, params_, aliasString, true, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0Format0()));
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0Split0()));
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1Split0(),params.getAliasCharSequence1Split1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence2Split0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence3Split0(),params.getAliasCharSequence3Split1()));
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasSplitStrings, params_, StringExpUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0SplitStrings0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasCharSequence);
        method_ = new StandardMethod(aliasSplitStrings, params_, StringExpUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1SplitStrings0(),params.getAliasCharSequence1SplitStrings1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasSplitChars, params_, StringExpUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0SplitChars0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasCharSequence, aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0RegionMatches0(),params.getAliasCharSequence0RegionMatches1(),params.getAliasCharSequence0RegionMatches2(),params.getAliasCharSequence0RegionMatches3()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrim, params_, aliasString, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCharSequenceToString, params_, aliasString, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence,aliasCharSequence);
        method_ = new StandardMethod(aliasCharSequenceEquals, params_, _lgNames.getContent().getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(params.getAliasCharSequence0Equals0(),params.getAliasCharSequence0Equals1()));
        methods_.add( method_);
        standards_.addEntry(aliasCharSequence, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasString, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL, new DfString());
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasEqualsIgnoreCase, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasString0EqualsIgnoreCase0()));
        methods_.add( method_);
        params_ = new StringList(aliasString, aliasString);
        method_ = new StandardMethod(aliasStringCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasString0Compare0(),params.getAliasString0Compare1()));
        methods_.add( method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasCompareToIgnoreCase, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasString0CompareToIgnoreCase0()));
        methods_.add( method_);
        params_ = new StringList(aliasString, aliasString);
        method_ = new StandardMethod(aliasReplaceString, params_, aliasString, false, MethodModifier.NORMAL,new StringList(params.getAliasString0ReplaceString0(),params.getAliasString0ReplaceString1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimChar_);
        method_ = new StandardMethod(aliasReplaceString, params_, aliasString, false, MethodModifier.NORMAL,new StringList(params.getAliasString1ReplaceString0(),params.getAliasString1ReplaceString1()));
        methods_.add( method_);
        params_ = new StringList(aliasReplacement);
        method_ = new StandardMethod(aliasReplaceMultiple, params_, aliasString, true, MethodModifier.NORMAL,new StringList(params.getAliasString0ReplaceMultiple0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_, aliasPrimInteger_, aliasString, aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasString0RegionMatches0(),params.getAliasString0RegionMatches1(),params.getAliasString0RegionMatches2(),params.getAliasString0RegionMatches3(),params.getAliasString0RegionMatches4()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToLowerCase, params_, aliasString, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToUpperCase, params_, aliasString, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString0ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimByte_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString1ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimShort_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString2ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString3ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString4ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString5ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString6ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString7ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, true, MethodModifier.STATIC,new StringList(params.getAliasString8ValueOfMethod0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_,aliasPrimChar_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, true, MethodModifier.STATIC,new StringList(params.getAliasString9ValueOfMethod0(),params.getAliasString9ValueOfMethod1(),params.getAliasString9ValueOfMethod2()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false, new FctString0());
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimByte_);
        ctor_ = new StandardConstructor(params_, true,new StringList(params.getAliasString0String0()),new FctString3());
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_, aliasPrimByte_);
        ctor_ = new StandardConstructor(params_, true,new StringList(params.getAliasString1String0(),params.getAliasString1String1(),params.getAliasString1String2()),new FctString5());
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimChar_);
        ctor_ = new StandardConstructor(params_, true,new StringList(params.getAliasString2String0()),new FctString2());
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_, aliasPrimChar_);
        ctor_ = new StandardConstructor(params_, true,new StringList(params.getAliasString3String0(),params.getAliasString3String1(),params.getAliasString3String2()),new FctString4());
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasString4String0()),new FctString1());
        constructors_.add(ctor_);
        std_.getDirectInterfaces().add(aliasCharSequence);
        standards_.addEntry(aliasString, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(aliasStringBuilder, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL, new DfStringBuilder());
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0Append0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimByte_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder1Append0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimShort_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder2Append0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder3Append0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder4Append0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder5Append0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder6Append0()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder7Append0()));
        methods_.add( method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder8Append0()));
        methods_.add( method_);
        params_ = new StringList(aliasString,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder9Append0(),params.getAliasStringBuilder9Append1(),params.getAliasStringBuilder9Append2()));
        methods_.add( method_);
        params_ = new StringList(aliasStringBuilder);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder10Append0()));
        methods_.add( method_);
        params_ = new StringList(aliasStringBuilder,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder11Append0(),params.getAliasStringBuilder11Append1(),params.getAliasStringBuilder11Append2()));
        methods_.add( method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(aliasPrimChar_));
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder12Append0()));
        methods_.add( method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(aliasPrimChar_),aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder13Append0(),params.getAliasStringBuilder13Append1(),params.getAliasStringBuilder13Append2()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCapacity, params_, aliasPrimInteger_, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasDelete, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0Delete0(),params.getAliasStringBuilder0Delete1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasDeleteCharAt, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0DeleteCharAt0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasClear, params_, aliasStringBuilder, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimBoolean_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0Insert0(),params.getAliasStringBuilder0Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimByte_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder1Insert0(),params.getAliasStringBuilder1Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimShort_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder2Insert0(),params.getAliasStringBuilder2Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimChar_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder3Insert0(),params.getAliasStringBuilder3Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder4Insert0(),params.getAliasStringBuilder4Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimLong_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder5Insert0(),params.getAliasStringBuilder5Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimFloat_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder6Insert0(),params.getAliasStringBuilder6Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimDouble_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder7Insert0(),params.getAliasStringBuilder7Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasString);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder8Insert0(),params.getAliasStringBuilder8Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasString,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder9Insert0(),params.getAliasStringBuilder9Insert1(),params.getAliasStringBuilder9Insert2(),params.getAliasStringBuilder9Insert3()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasStringBuilder);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder10Insert0(),params.getAliasStringBuilder10Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasStringBuilder,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder11Insert0(),params.getAliasStringBuilder11Insert1(),params.getAliasStringBuilder11Insert2(),params.getAliasStringBuilder11Insert3()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, StringExpUtil.getPrettyArrayType(aliasPrimChar_));
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder12Insert0(),params.getAliasStringBuilder12Insert1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, StringExpUtil.getPrettyArrayType(aliasPrimChar_),aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder13Insert0(),params.getAliasStringBuilder13Insert1(),params.getAliasStringBuilder13Insert2(),params.getAliasStringBuilder13Insert3()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReverse, params_, aliasStringBuilder, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_,aliasCharSequence);
        method_ = new StandardMethod(aliasReplace, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0Replace0(),params.getAliasStringBuilder0Replace1(),params.getAliasStringBuilder0Replace2()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimChar_);
        method_ = new StandardMethod(aliasSetCharAt, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0SetCharAt0(),params.getAliasStringBuilder0SetCharAt1()));
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasSetLength, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0SetLength0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrimToSize, params_, aliasStringBuilder, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasEnsureCapacity, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0EnsureCapacity0()));
        methods_.add( method_);
        params_ = new StringList(aliasStringBuilder,aliasStringBuilder);
        method_ = new StandardMethod(aliasSame, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasStringBuilder0Same0(),params.getAliasStringBuilder0Same1()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false, new FctStringBuilder0());
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasStringBuilder0StringBuilder0()), new FctStringBuilder2());
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger_);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasStringBuilder1StringBuilder0()), new FctStringBuilder1());
        constructors_.add(ctor_);
        params_ = new StringList(aliasString);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasStringBuilder2StringBuilder0()), new FctStringBuilder2());
        constructors_.add(ctor_);
        std_.getDirectInterfaces().add(aliasCharSequence);
        standards_.addEntry(aliasStringBuilder, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(aliasReplacement, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL, new DfReplacement());
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetOldString, params_, aliasString, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetNewString, params_, aliasString, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence,aliasCharSequence);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasReplacement0Replacement0(),params.getAliasReplacement0Replacement1()),new FctReplacement());
        constructors_.add(ctor_);
        standards_.addEntry(aliasReplacement, std_);
    }

    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, StackCall _stackCall, Argument... _args) {
        ResultErrorStd result_;
        result_ = new ResultErrorStd();
        Struct[] args_ = ExecHelper.getObjects(_args);
        calculateStrBuilder(_cont, result_, _method, _struct, _stackCall, args_);
        return result_;
    }

    static ResultErrorStd invokeStdMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, StackCall _stackCall, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = ExecHelper.getObjects(_args);
        LgNames lgNames_ = _cont.getStandards();
        String type_ = _method.getClassName();
        String stringType_ = lgNames_.getContent().getCharSeq().getAliasString();
        if (StringUtil.quickEq(type_, stringType_)) {
            calculateString(_cont, result_, _method, _struct, _stackCall, args_);
            return result_;
        }
        calculateCharSeq(_cont, result_, _method, _struct, _stackCall, args_);
        return result_;
    }

    public String getAliasCharSequence() {
        return aliasCharSequence;
    }

    public void setAliasCharSequence(String _aliasCharSequence) {
        aliasCharSequence = _aliasCharSequence;
    }

    public String getAliasCharSequenceToString() {
        return aliasCharSequenceToString;
    }

    public void setAliasCharSequenceToString(String _aliasCharSequenceToString) {
        this.aliasCharSequenceToString = _aliasCharSequenceToString;
    }

    public String getAliasCharSequenceCompareTo() {
        return aliasCharSequenceCompareTo;
    }

    public void setAliasCharSequenceCompareTo(String _aliasCharSequenceCompareTo) {
        this.aliasCharSequenceCompareTo = _aliasCharSequenceCompareTo;
    }

    public String getAliasCharSequenceEquals() {
        return aliasCharSequenceEquals;
    }

    public void setAliasCharSequenceEquals(String _aliasCharSequenceEquals) {
        this.aliasCharSequenceEquals = _aliasCharSequenceEquals;
    }

    public String getAliasString() {
        return aliasString;
    }

    public void setAliasString(String _aliasString) {
        aliasString = _aliasString;
    }

    public String getAliasStringCompare() {
        return aliasStringCompare;
    }

    public void setAliasStringCompare(String _aliasStringCompare) {
        this.aliasStringCompare = _aliasStringCompare;
    }

    public String getAliasStringValueOf() {
        return aliasStringValueOf;
    }

    public void setAliasStringValueOf(String _aliasStringValueOf) {
        this.aliasStringValueOf = _aliasStringValueOf;
    }

    public String getAliasLength() {
        return aliasLength;
    }

    public void setAliasLength(String _aliasLength) {
        aliasLength = _aliasLength;
    }

    public String getAliasCharAt() {
        return aliasCharAt;
    }

    public void setAliasCharAt(String _aliasCharAt) {
        aliasCharAt = _aliasCharAt;
    }

    public String getAliasToCharArray() {
        return aliasToCharArray;
    }

    public void setAliasToCharArray(String _aliasToCharArray) {
        aliasToCharArray = _aliasToCharArray;
    }

    public String getAliasSplit() {
        return aliasSplit;
    }

    public void setAliasSplit(String _aliasSplit) {
        aliasSplit = _aliasSplit;
    }

    public String getAliasSplitStrings() {
        return aliasSplitStrings;
    }

    public void setAliasSplitStrings(String _aliasSplitStrings) {
        aliasSplitStrings = _aliasSplitStrings;
    }

    public String getAliasSplitChars() {
        return aliasSplitChars;
    }

    public void setAliasSplitChars(String _aliasSplitChars) {
        aliasSplitChars = _aliasSplitChars;
    }

    public String getAliasReplace() {
        return aliasReplace;
    }

    public void setAliasReplace(String _aliasReplace) {
        aliasReplace = _aliasReplace;
    }

    public String getAliasReplaceString() {
        return aliasReplaceString;
    }

    public void setAliasReplaceString(String _aliasReplace) {
        aliasReplaceString = _aliasReplace;
    }

    public String getAliasReplaceMultiple() {
        return aliasReplaceMultiple;
    }

    public void setAliasReplaceMultiple(String _aliasReplaceMultiple) {
        aliasReplaceMultiple = _aliasReplaceMultiple;
    }

    public String getAliasEqualsIgnoreCase() {
        return aliasEqualsIgnoreCase;
    }

    public void setAliasEqualsIgnoreCase(String _aliasEqualsIgnoreCase) {
        aliasEqualsIgnoreCase = _aliasEqualsIgnoreCase;
    }

    public String getAliasCompareToIgnoreCase() {
        return aliasCompareToIgnoreCase;
    }

    public void setAliasCompareToIgnoreCase(String _aliasCompareToIgnoreCase) {
        aliasCompareToIgnoreCase = _aliasCompareToIgnoreCase;
    }

    public String getAliasContains() {
        return aliasContains;
    }

    public void setAliasContains(String _aliasContains) {
        aliasContains = _aliasContains;
    }

    public String getAliasEndsWith() {
        return aliasEndsWith;
    }

    public void setAliasEndsWith(String _aliasEndsWith) {
        aliasEndsWith = _aliasEndsWith;
    }

    public String getAliasStartsWith() {
        return aliasStartsWith;
    }

    public void setAliasStartsWith(String _aliasStartsWith) {
        aliasStartsWith = _aliasStartsWith;
    }

    public String getAliasIndexOf() {
        return aliasIndexOf;
    }

    public void setAliasIndexOf(String _aliasIndexOf) {
        aliasIndexOf = _aliasIndexOf;
    }

    public String getAliasFormat() {
        return aliasFormat;
    }

    public void setAliasFormat(String _aliasFormat) {
        aliasFormat = _aliasFormat;
    }

    public String getAliasGetBytes() {
        return aliasGetBytes;
    }

    public void setAliasGetBytes(String _aliasGetBytes) {
        aliasGetBytes = _aliasGetBytes;
    }

    public String getAliasIsEmpty() {
        return aliasIsEmpty;
    }

    public void setAliasIsEmpty(String _aliasIsEmpty) {
        aliasIsEmpty = _aliasIsEmpty;
    }

    public String getAliasLastIndexOf() {
        return aliasLastIndexOf;
    }

    public void setAliasLastIndexOf(String _aliasLastIndexOf) {
        aliasLastIndexOf = _aliasLastIndexOf;
    }

    public String getAliasRegionMatches() {
        return aliasRegionMatches;
    }

    public void setAliasRegionMatches(String _aliasRegionMatches) {
        aliasRegionMatches = _aliasRegionMatches;
    }

    public String getAliasSubstring() {
        return aliasSubstring;
    }

    public void setAliasSubstring(String _aliasSubstring) {
        aliasSubstring = _aliasSubstring;
    }

    public String getAliasSubSequence() {
        return aliasSubSequence;
    }

    public void setAliasSubSequence(String _aliasSubSequence) {
        aliasSubSequence = _aliasSubSequence;
    }

    public String getAliasToLowerCase() {
        return aliasToLowerCase;
    }

    public void setAliasToLowerCase(String _aliasToLowerCase) {
        aliasToLowerCase = _aliasToLowerCase;
    }

    public String getAliasToUpperCase() {
        return aliasToUpperCase;
    }

    public void setAliasToUpperCase(String _aliasToUpperCase) {
        aliasToUpperCase = _aliasToUpperCase;
    }

    public String getAliasTrim() {
        return aliasTrim;
    }

    public void setAliasTrim(String _aliasTrim) {
        aliasTrim = _aliasTrim;
    }

    public String getAliasStringBuilder() {
        return aliasStringBuilder;
    }

    public void setAliasStringBuilder(String _aliasStringBuilder) {
        aliasStringBuilder = _aliasStringBuilder;
    }

    public String getAliasAppend() {
        return aliasAppend;
    }

    public void setAliasAppend(String _aliasAppend) {
        aliasAppend = _aliasAppend;
    }

    public String getAliasCapacity() {
        return aliasCapacity;
    }

    public void setAliasCapacity(String _aliasCapacity) {
        aliasCapacity = _aliasCapacity;
    }

    public String getAliasClear() {
        return aliasClear;
    }

    public void setAliasClear(String _aliasClear) {
        aliasClear = _aliasClear;
    }

    public String getAliasDelete() {
        return aliasDelete;
    }

    public void setAliasDelete(String _aliasDelete) {
        aliasDelete = _aliasDelete;
    }

    public String getAliasDeleteCharAt() {
        return aliasDeleteCharAt;
    }

    public void setAliasDeleteCharAt(String _aliasDeleteCharAt) {
        aliasDeleteCharAt = _aliasDeleteCharAt;
    }

    public String getAliasEnsureCapacity() {
        return aliasEnsureCapacity;
    }

    public void setAliasEnsureCapacity(String _aliasEnsureCapacity) {
        aliasEnsureCapacity = _aliasEnsureCapacity;
    }

    public String getAliasInsert() {
        return aliasInsert;
    }

    public void setAliasInsert(String _aliasInsert) {
        aliasInsert = _aliasInsert;
    }

    public String getAliasReverse() {
        return aliasReverse;
    }

    public void setAliasReverse(String _aliasReverse) {
        aliasReverse = _aliasReverse;
    }

    public String getAliasSetCharAt() {
        return aliasSetCharAt;
    }

    public void setAliasSetCharAt(String _aliasSetCharAt) {
        aliasSetCharAt = _aliasSetCharAt;
    }

    public String getAliasSetLength() {
        return aliasSetLength;
    }

    public void setAliasSetLength(String _aliasSetLength) {
        aliasSetLength = _aliasSetLength;
    }

    public String getAliasSame() {
        return aliasSame;
    }

    public void setAliasSame(String _aliasSame) {
        aliasSame = _aliasSame;
    }

    public String getAliasTrimToSize() {
        return aliasTrimToSize;
    }

    public void setAliasTrimToSize(String _aliasTrimToSize) {
        aliasTrimToSize = _aliasTrimToSize;
    }

    public String getAliasReplacement() {
        return aliasReplacement;
    }

    public void setAliasReplacement(String _aliasReplacement) {
        aliasReplacement = _aliasReplacement;
    }

    public String getAliasGetOldString() {
        return aliasGetOldString;
    }

    public void setAliasGetOldString(String _aliasGetOldString) {
        aliasGetOldString = _aliasGetOldString;
    }

    public String getAliasGetNewString() {
        return aliasGetNewString;
    }

    public void setAliasGetNewString(String _aliasGetNewString) {
        aliasGetNewString = _aliasGetNewString;
    }


    public AliasParamCharSequence getParams() {
        return params;
    }

    public static String getEndMessage(int _end, String _s, int _length) {
        return StringUtil.concat(Long.toString(_end), _s, Long.toString(_length));
    }

    public static String getBeginMessage(int _begin) {
        return StringUtil.concat(Long.toString(_begin), "<0");
    }

    public static String getBeginEndMessage(int _len, long _one, int _two) {
        return StringUtil.concat(Long.toString(_one + _two), ">", Long.toString(_len));
    }

}
