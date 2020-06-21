package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class AliasCharSequence {

    private String aliasCharSequence;
    private String aliasString;
    private String aliasLength;
    private String aliasCharAt;
    private String aliasToCharArray;
    private String aliasSplit;
    private String aliasSplitStrings;
    private String aliasSplitChars;
    private String aliasReplace;
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

    public static void calculateString(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            StringStruct str_ = AnaApplyCoreMethodUtil.getString(_struct);
            calculateLocString(str_,_cont, _res, _method, _args);
            return;
        }
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
            Struct arg_ = _args[0];
            if (!(arg_ instanceof StringStruct)) {
                String nullPe_ = lgNames_.getAliasNullPe();
                _res.setError(nullPe_);
                return;
            }
            StringStruct first_ = (StringStruct) arg_;
            compareToString(first_,_args[1], lgNames_, _res);
            return;
        }
        Struct arg_ = NumParsers.getArg(list_, _args);
        if (NumParsers.isDisplay(list_, arg_)) {
            _res.setResult(ExecCatOperation.getDisplayable(new Argument(arg_), _cont).getDisplayedString(_cont));
            return;
        }
        if (!(arg_ instanceof ArrayStruct)) {
            String nullPe_ = lgNames_.getAliasNullPe();
            _res.setError(nullPe_);
            return;
        }
        tryGetCharArray(_res, list_, lgNames_, (ArrayStruct) arg_, _args);
    }

    private static void tryGetCharArray(ResultErrorStd _res, StringList _list, LgNames _lgNames, ArrayStruct _arg, Struct[] _args) {
        Struct[] argArr_ = _arg.getInstance();
        int len_ = argArr_.length;
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = ClassArgumentMatching.convertToChar(argArr_[i]).getChar();
        }
        if (_list.size() == 1) {
            _res.setResult(new StringStruct(String.valueOf(arr_)));
            return;
        }
        int one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
        int two_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
        if (NumParsers.okArray(arr_, one_, two_)) {
            if (one_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(one_),"<0"));
            } else if (two_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(two_),"<0"));
            } else {
                _res.setErrorMessage(StringList.concat(Long.toString((long)one_ + two_),">", Long.toString(arr_.length)));
            }
            _res.setError(_lgNames.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringStruct(String.valueOf(arr_,one_,two_)));
    }

    private static void calculateLocString(StringStruct _str, ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String stringType_ = lgNames_.getAliasString();
        if (StringList.quickEq(name_, lgNames_.getAliasRegionMatches())) {
            regionMatches(_str,ClassArgumentMatching.convertToBoolean(_args[0]),ClassArgumentMatching.convertToNumber(_args[1]), _args[2],
                    ClassArgumentMatching.convertToNumber(_args[3]), ClassArgumentMatching.convertToNumber(_args[4]), lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplace())) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                replaceString(_str,_args[0], _args[1], _res);
                return;
            }
            replace(_str,ClassArgumentMatching.convertToChar(_args[0]), ClassArgumentMatching.convertToChar(_args[1]), _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplaceMultiple())) {
            replaceMultiple(_str,_args[0], lgNames_, _res);
            return;
        }
        String one_ = _str.getInstance();
        if (StringList.quickEq(name_, lgNames_.getAliasCompareToIgnoreCase())) {
            Struct two_ = _args[0];
            if (!(two_ instanceof StringStruct)) {
                _res.setError(lgNames_.getAliasNullPe());
            } else {
                StringStruct t_ = (StringStruct) two_;
                _res.setResult(new IntStruct(one_.compareToIgnoreCase(t_.getInstance())));
            }
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasEqualsIgnoreCase())) {
            Struct two_ = _args[0];
            if (!(two_ instanceof StringStruct)) {
                _res.setResult(BooleanStruct.of(false));
            } else {
                StringStruct t_ = (StringStruct) two_;
                _res.setResult(BooleanStruct.of(one_.equalsIgnoreCase(t_.getInstance())));
            }
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
            _res.setResult(new StringStruct(StringList.toLowerCase(one_)));
            return;
        }
        _res.setResult(new StringStruct(StringList.toUpperCase(one_)));
    }

    private static void compareToString(StringStruct _str, Struct _anotherString, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_anotherString instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct st_ = (StringStruct)_anotherString;
        _res.setResult(new IntStruct(_str.getInstance().compareTo(st_.getInstance())));
    }

    private static void regionMatches(StringStruct _str, BooleanStruct _case, NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
                                      NumberStruct _len, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_other instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        boolean case_ = BooleanStruct.isTrue(_case);
        StringStruct other_ = (StringStruct) _other;
        int comLen_ = _len.intStruct();
        int to_ = _toffset.intStruct();
        int po_ = _ooffset.intStruct();
        _res.setResult(BooleanStruct.of(_str.getInstance().regionMatches(case_,to_, other_.getInstance(), po_, comLen_)));
    }

    private static void replace(StringStruct _str, CharStruct _oldChar, CharStruct _newChar, ResultErrorStd _res) {
        char oldChar_ = _oldChar.getChar();
        char newChar_ = _newChar.getChar();
        _res.setResult(new StringStruct(_str.getInstance().replace(oldChar_, newChar_)));
    }

    private static void replaceString(StringStruct _str, Struct _oldChar, Struct _newChar, ResultErrorStd _res) {
        String old_;
        old_ = NumParsers.getString(_oldChar);
        String new_;
        new_ = NumParsers.getString(_newChar);
        String out_ = StringList.replace(_str.getInstance(), old_, new_);
        _res.setResult(new StringStruct(out_));
    }

    private static void replaceMultiple(StringStruct _st, Struct _seps, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_seps instanceof ArrayStruct)) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        Struct[] arrStructSep_ = arrSep_.getInstance();
        int lenSeps_ = arrStructSep_.length;
        Replacement[] seps_ = new Replacement[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrStructSep_[i];
            if (!(curSep_ instanceof ReplacementStruct)) {
                _res.setError(nullPe_);
                return;
            }
            seps_[i] = NumParsers.getReplacement(curSep_).getInstance();
            if (seps_[i].getNewString() == null) {
                _res.setError(nullPe_);
                return;
            }
            if (seps_[i].getOldString() == null) {
                _res.setError(nullPe_);
                return;
            }
        }
        _res.setResult(new StringStruct(StringList.replaceMult(_st.getInstance(), seps_)));
    }

    public static void instantiateString(LgNames _stds, ResultErrorStd _res, ConstructorId _method, Struct... _args) {
        StringList list_ = _method.getParametersTypes();
        String bytePrimType_ = _stds.getAliasPrimByte();
        String charPrimType_ = _stds.getAliasPrimChar();
        if (list_.size() == 0) {
            _res.setResult(new StringStruct(""));
            return;
        }
        if (list_.size() == 1) {
            if (StringList.quickEq(list_.first(), bytePrimType_)) {
                newStringStructByByteArray(_args[0], _stds, _res);
                return;
            }
            if (StringList.quickEq(list_.first(), charPrimType_)) {
                newStringStructByCharArray(_args[0], _stds, _res);
                return;
            }
            newStringBuilderStruct(_args[0], _stds, _res);
            return;
        }
        if (StringList.quickEq(list_.last(), bytePrimType_)) {
            newStringStructByByteArray(_args[2], _args[0], _args[1], _stds, _res);
            return;
        }
        newStringStructByCharArray(_args[2], _args[0], _args[1], _stds, _res);
    }

    private static void newStringStructByCharArray(Struct _arg, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_arg instanceof ArrayStruct)) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct chArr_ = (ArrayStruct) _arg;
        Struct[] argArr_ = chArr_.getInstance();
        int len_ = argArr_.length;
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = ClassArgumentMatching.convertToChar(argArr_[i]).getChar();
        }
        _res.setResult(new StringStruct(new String(arr_)));
    }

    private static void newStringStructByCharArray(Struct _arg, Struct _one, Struct _two, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_arg instanceof ArrayStruct)) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct chArr_ = (ArrayStruct) _arg;
        Struct[] argArr_ = chArr_.getInstance();
        int len_ = argArr_.length;
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = ClassArgumentMatching.convertToChar(argArr_[i]).getChar();
        }
        int one_ = ClassArgumentMatching.convertToNumber(_one).intStruct();
        int two_ = ClassArgumentMatching.convertToNumber(_two).intStruct();
        if (one_ < 0 || two_ < 0 || one_ > arr_.length - two_) {
            if (one_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(one_),"<0"));
            } else if (two_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(two_),"<0"));
            } else {
                _res.setErrorMessage(StringList.concat(Long.toString((long)one_ + two_),">", Long.toString(arr_.length)));
            }
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringStruct(new String(arr_, one_, two_)));
    }

    private static void newStringStructByByteArray(Struct _arg, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_arg instanceof ArrayStruct)) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct chArr_ = (ArrayStruct) _arg;
        Struct[] argArr_ = chArr_.getInstance();
        int len_ = argArr_.length;
        byte[] arr_ = new byte[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = ClassArgumentMatching.convertToNumber(argArr_[i]).byteStruct();
        }
        String chars_ = StringList.decode(arr_);
        if (chars_ == null) {
            int index_ = StringList.badDecode(arr_, 0, len_);
            _res.setErrorMessage(Integer.toString(index_));
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringStruct(chars_));
    }

    private static void newStringStructByByteArray(Struct _arg, Struct _one, Struct _two, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_arg instanceof ArrayStruct)) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct chArr_ = (ArrayStruct) _arg;
        Struct[] argArr_ = chArr_.getInstance();
        int len_ = argArr_.length;
        byte[] arr_ = new byte[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = ClassArgumentMatching.convertToNumber(argArr_[i]).byteStruct();
        }
        int one_ = ClassArgumentMatching.convertToNumber(_one).intStruct();
        int two_ = ClassArgumentMatching.convertToNumber(_two).intStruct();
        if (one_ < 0 || two_ < 0 || one_ > arr_.length - two_) {
            if (one_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(one_),"<0"));
            } else if (two_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(two_),"<0"));
            } else {
                _res.setErrorMessage(StringList.concat(Long.toString((long)one_ + two_),">", Long.toString(arr_.length)));
            }
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        String chars_ = StringList.decode(arr_, one_, two_);
        if (chars_ == null) {
            int index_ = StringList.badDecode(arr_, one_, two_);
            _res.setErrorMessage(Integer.toString(index_));
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringStruct(chars_));
    }

    private static void newStringBuilderStruct(Struct _arg, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_arg instanceof StringBuilderStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringBuilderStruct arg_ = NumParsers.getStrBuilder(_arg);
        _res.setResult(new StringStruct(arg_.getInstance().toString()));
    }

    public static void instantiateStringBuilder(ContextEl _cont, ResultErrorStd _res, ConstructorId _method, Struct... _args) {
        StringList list_ = _method.getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String intPrimType_ = lgNames_.getAliasPrimInteger();
        if (list_.size() == 0) {
            newStringBuilderStruct(_res);
            return;
        }
        if (StringList.quickEq(list_.first(), intPrimType_)) {
            newStringBuilderStructByNumber(ClassArgumentMatching.convertToNumber(_args[0]), lgNames_, _res);
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
        CharSequenceStruct arg_ = NumParsers.getCharSeq(_arg);
        _res.setResult(new StringBuilderStruct(new StringBuilder(arg_.toStringInstance())));
    }

    private static void newStringBuilderStructByNumber(NumberStruct _arg, LgNames _stds, ResultErrorStd _res) {
        int one_ = _arg.intStruct();
        if (one_ < 0) {
            _res.setErrorMessage(Integer.toString(one_));
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringBuilderStruct(new StringBuilder(one_)));
    }

    public static void calculateStrBuilder(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
        LgNames lgNames_ = _cont.getStandards();
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        String aliasPrimChar_ = lgNames_.getAliasPrimChar();
        if (StringList.quickEq(name_, lgNames_.getAliasSame())) {
            _res.setResult(BooleanStruct.of(_args[0] == _args[1]));
            return;
        }
        StringBuilderStruct one_ = NumParsers.getStrBuilder(_struct);
        if (StringList.quickEq(name_, lgNames_.getAliasAppend())) {
            if (list_.size() == 1 && StringList.quickEq(list_.first(), StringExpUtil.getPrettyArrayType(aliasPrimChar_))) {
                appendChars(one_,_args[0], _cont, _res);
                return;
            }
            if (list_.size() == 1) {
                append(one_,ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont), _cont, _res);
                return;
            }
            if (StringList.quickEq(list_.first(), StringExpUtil.getPrettyArrayType(lgNames_.getAliasPrimChar()))) {
                appendChars(one_,_args[0], ClassArgumentMatching.convertToNumber(_args[1]), ClassArgumentMatching.convertToNumber(_args[2]), _cont, _res);
                return;
            }
            append(one_,ExecCatOperation.getDisplayable(new Argument(_args[0]),_cont), ClassArgumentMatching.convertToNumber(_args[1]), ClassArgumentMatching.convertToNumber(_args[2]), _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasCapacity())) {
            capacity(one_,_res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasClear())) {
            clear(one_,_cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasDelete())) {
            delete(one_,ClassArgumentMatching.convertToNumber(_args[0]), ClassArgumentMatching.convertToNumber(_args[1]), _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasDeleteCharAt())) {
            deleteCharAt(one_,ClassArgumentMatching.convertToNumber(_args[0]), _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasEnsureCapacity())) {
            ensureCapacity(one_,ClassArgumentMatching.convertToNumber(_args[0]), _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasInsert())) {
            if (list_.size() == 2 && StringList.quickEq(list_.get(1), StringExpUtil.getPrettyArrayType(aliasPrimChar_))) {
                insertChars(one_,ClassArgumentMatching.convertToNumber(_args[0]), _args[1], _cont, _res);
                return;
            }
            if (list_.size() == 2) {
                insert(one_,ClassArgumentMatching.convertToNumber(_args[0]),
                        ExecCatOperation.getDisplayable(new Argument(_args[1]),_cont), _cont, _res);
                return;
            }
            if (StringList.quickEq(list_.get(1), StringExpUtil.getPrettyArrayType(lgNames_.getAliasPrimChar()))) {
                insertChars(one_,ClassArgumentMatching.convertToNumber(_args[0]), _args[1], ClassArgumentMatching.convertToNumber(_args[2]), ClassArgumentMatching.convertToNumber(_args[3]), _cont, _res);
                return;
            }
            insert(one_,ClassArgumentMatching.convertToNumber(_args[0]),
                    ExecCatOperation.getDisplayable(new Argument(_args[1]),_cont), ClassArgumentMatching.convertToNumber(_args[2]), ClassArgumentMatching.convertToNumber(_args[3]), _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplace())) {
            replace(one_,ClassArgumentMatching.convertToNumber(_args[0]), ClassArgumentMatching.convertToNumber(_args[1]), _args[2], _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReverse())) {
            reverse(one_,_cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSetCharAt())) {
            setCharAt(one_,ClassArgumentMatching.convertToNumber(_args[0]),ClassArgumentMatching.convertToChar(_args[1]), _cont, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSetLength())) {
            setLength(one_,ClassArgumentMatching.convertToNumber(_args[0]), _cont, _res);
            return;
        }
        trimToSize(one_,_cont, _res);
    }

    private static void ensureCapacity(StringBuilderStruct _instance, NumberStruct _minimumCapacity, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().ensureCapacity(_minimumCapacity.intStruct());
        _out.setResult(_instance);
    }

    private static void trimToSize(StringBuilderStruct _instance, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().trimToSize();
        _out.setResult(_instance);
    }

    private static void setLength(StringBuilderStruct _instance, NumberStruct _newLength, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int newLength_ = _newLength.intStruct();
        if (newLength_ < 0) {
            _out.setErrorMessage(StringList.concat(Long.toString(newLength_),"<0"));
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        _instance.getInstance().setLength(newLength_);
        _out.setResult(_instance);
    }

    private static void append(StringBuilderStruct _instance, DisplayableStruct _s, NumberStruct _start, NumberStruct _end, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        String toApp_= _s.getDisplayedString(_an).getInstance();
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || start_ > end_ || end_ > toApp_.length()) {
            if (start_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(start_),"<0"));
            } else if (start_ > end_){
                _out.setErrorMessage(StringList.concat(Long.toString(start_),">",Long.toString(end_)));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString(end_),">", Long.toString(toApp_.length())));
            }
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        _instance.getInstance().append(toApp_, start_, end_);
        _out.setResult(_instance);
    }

    private static void appendChars(StringBuilderStruct _instance, Struct _str, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
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
            chars_[i] = ClassArgumentMatching.convertToChar(arr_[i]).getChar();
        }
        _instance.getInstance().append(chars_);
        _out.setResult(_instance);
    }

    private static void appendChars(StringBuilderStruct _instance, Struct _str, NumberStruct _offset, NumberStruct _len, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        if (!(_str instanceof ArrayStruct)) {
            _out.setError(lgNames_.getAliasNullPe());
            return;
        }
        int offset_ = _offset.intStruct();
        int len_ = _len.intStruct();
        Struct[] arr_ = ((ArrayStruct)_str).getInstance();
        int lenChar_ = arr_.length;
        if (offset_ < 0 || len_ < 0 || offset_ + len_ > lenChar_) {
            if (offset_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(offset_),"<0"));
            } else if (len_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(len_),"<0"));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString((long)offset_ + len_),">", Long.toString(lenChar_)));
            }
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        char[] chars_ = new char[lenChar_];
        for (int i = 0; i < lenChar_; i++) {
            chars_[i] = ClassArgumentMatching.convertToChar(arr_[i]).getChar();
        }
        _instance.getInstance().append(chars_, offset_, len_);
        _out.setResult(_instance);
    }

    private static void append(StringBuilderStruct _instance, DisplayableStruct _b, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().append(_b.getDisplayedString(_an).getInstance());
        _out.setResult(_instance);
    }

    private static void delete(StringBuilderStruct _instance, NumberStruct _start, NumberStruct _end, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || start_ > _instance.getInstance().length() || start_ > end_) {
            if (start_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(start_),"<0"));
            } else if (start_ > _instance.getInstance().length()) {
                _out.setErrorMessage(StringList.concat(Long.toString(start_),">",Long.toString(_instance.getInstance().length())));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString(start_),">",Long.toString(end_)));
            }
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        _instance.getInstance().delete(start_, end_);
        _out.setResult(_instance);
    }

    private static void deleteCharAt(StringBuilderStruct _instance, NumberStruct _index, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int index_ = _index.intStruct();
        if (index_ < 0 || index_ >= _instance.getInstance().length()) {
            if (index_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),"<0"));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),">=",Long.toString(_instance.getInstance().length())));
            }
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        _instance.getInstance().deleteCharAt(index_);
        _out.setResult(_instance);
    }

    private static void replace(StringBuilderStruct _instance, NumberStruct _start, NumberStruct _end, Struct _str, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || start_ > _instance.getInstance().length() || start_ > end_) {
            if (start_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(start_),"<0"));
            } else if (start_ > _instance.getInstance().length()) {
                _out.setErrorMessage(StringList.concat(Long.toString(start_),">",Long.toString(_instance.getInstance().length())));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString(start_),">",Long.toString(end_)));
            }
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        if (!(_str instanceof CharSequenceStruct)) {
            _out.setError(lgNames_.getAliasNullPe());
            return;
        }
        CharSequenceStruct ch_ = NumParsers.getCharSeq(_str);
        _instance.getInstance().replace(start_, end_, ch_.toStringInstance());
        _out.setResult(_instance);
    }

    private static void insertChars(StringBuilderStruct _instance, NumberStruct _index, Struct _str, NumberStruct _offset, NumberStruct _len, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int index_ = _index.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),"<0"));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),">",Long.toString(_instance.getInstance().length())));
            }
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
            chars_[i] = ClassArgumentMatching.convertToChar(arr_[i]).getChar();
        }
        int offset_ = _offset.intStruct();
        int len_ = _len.intStruct();
        if (offset_ < 0 || len_ < 0 || offset_ + len_ > chars_.length) {
            if (offset_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(offset_),"<0"));
            } else if (len_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(len_),"<0"));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString((long)offset_ + len_),">", Long.toString(chars_.length)));
            }
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        _instance.getInstance().insert(index_, chars_, offset_, len_);
        _out.setResult(_instance);
    }

    private static void insertChars(StringBuilderStruct _instance, NumberStruct _offset, Struct _str, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int index_ = _offset.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),"<0"));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),">",Long.toString(_instance.getInstance().length())));
            }
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
            chars_[i] = ClassArgumentMatching.convertToChar(arr_[i]).getChar();
        }
        _instance.getInstance().insert(index_, chars_);
        _out.setResult(_instance);
    }

    private static void insert(StringBuilderStruct _instance, NumberStruct _dstOffset, DisplayableStruct _s, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int index_ = _dstOffset.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),"<0"));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),">",Long.toString(_instance.getInstance().length())));
            }
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        _instance.getInstance().insert(index_, _s.getDisplayedString(_an).getInstance());
        _out.setResult(_instance);
    }

    private static void insert(StringBuilderStruct _instance, NumberStruct _dstOffset, DisplayableStruct _s, NumberStruct _start,
                               NumberStruct _end, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        String toApp_= _s.getDisplayedString(_an).getInstance();
        int index_ = _dstOffset.intStruct();
        if (index_ < 0 || index_ > _instance.getInstance().length()) {
            if (index_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),"<0"));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),">",Long.toString(_instance.getInstance().length())));
            }
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || end_ < 0 || start_ > end_ || end_ > toApp_.length()) {
            if (start_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(start_),"<0"));
            } else if (end_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(end_),"<0"));
            } else if (start_ > end_) {
                _out.setErrorMessage(StringList.concat(Long.toString(start_),">",Long.toString(end_)));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString(end_),">", Long.toString(toApp_.length())));
            }
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        _instance.getInstance().insert(index_, toApp_, start_, end_);
        _out.setResult(_instance);
    }

    private static void clear(StringBuilderStruct _instance, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().delete(0, _instance.getInstance().length());
        _out.setResult(_instance);
    }

    private static void reverse(StringBuilderStruct _instance, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().reverse();
        _out.setResult(_instance);
    }

    private static void setCharAt(StringBuilderStruct _instance, NumberStruct _index, CharStruct _ch, ContextEl _an, ResultErrorStd _out) {
        ContextEl cont_ = _an;
        if (cont_.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            cont_.getInitializingTypeInfos().failInitEnums();
            return;
        }
        LgNames lgNames_ = cont_.getStandards();
        int index_ = _index.intStruct();
        if (index_ < 0 || index_ >= _instance.getInstance().length()) {
            if (index_ < 0) {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),"<0"));
            } else {
                _out.setErrorMessage(StringList.concat(Long.toString(index_),">=",Long.toString(_instance.getInstance().length())));
            }
            _out.setError(lgNames_.getAliasBadIndex());
            return;
        }
        _instance.getInstance().setCharAt(index_, _ch.getChar());
        _out.setResult(_instance);
    }

    private static void capacity(StringBuilderStruct _instance, ResultErrorStd _out) {
        _out.setResult(new IntStruct(_instance.getInstance().capacity()));
    }

    public static void calculateCharSeq(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            calculateLocCharSeq(NumParsers.getCharSeq(_struct), _cont, _res, _method, _args);
            return;
        }
        if (!(_args[0] instanceof CharSequenceStruct)) {
            _res.setResult(BooleanStruct.of(_args[1] == NullStruct.NULL_VALUE));
            return;
        }
        _res.setResult(BooleanStruct.of(NumParsers.sameEq(NumParsers.getCharSeq(_args[0]),_args[1])));
    }

    private static void calculateLocCharSeq(CharSequenceStruct _charSequence, ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(name_, lgNames_.getAliasLength())) {
            length(_charSequence, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasIsEmpty())) {
            isEmpty(_charSequence, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasCharAt())) {
            charAt(_charSequence, _args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasGetBytes())) {
            getBytes(_charSequence, lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
            compareTo(_charSequence, _args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasRegionMatches())) {
            regionMatches(_charSequence, ClassArgumentMatching.convertToNumber(_args[0]), _args[1], ClassArgumentMatching.convertToNumber(_args[2]), ClassArgumentMatching.convertToNumber(_args[3]), lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasStartsWith())) {
            if (list_.size() == 1) {
                startsWith(_charSequence, _args[0], lgNames_, _res);
                return;
            }
            startsWith(_charSequence, _args[0], ClassArgumentMatching.convertToNumber(_args[1]), lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasEndsWith())) {
            endsWith(_charSequence, _args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasIndexOf())) {
            if (list_.size() == 1) {
                if (!(_args[0] instanceof NumberStruct)) {
                    indexOfString(_charSequence, _args[0], lgNames_, _res);
                    return;
                }
                indexOf(_charSequence, _args[0], _res);
                return;
            }
            if (!(_args[0] instanceof NumberStruct)) {
                indexOfString(_charSequence, _args[0], ClassArgumentMatching.convertToNumber(_args[1]), lgNames_, _res);
                return;
            }
            indexOf(_charSequence, _args[0], _args[1], _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasContains())) {
            contains(_charSequence, _args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasLastIndexOf())) {
            if (list_.size() == 1) {
                if (!(_args[0] instanceof NumberStruct)) {
                    lastIndexOfString(_charSequence, _args[0], lgNames_, _res);
                    return;
                }
                lastIndexOf(_charSequence, _args[0], _res);
                return;
            }
            if (!(_args[0] instanceof NumberStruct)) {
                lastIndexOfString(_charSequence, _args[0], ClassArgumentMatching.convertToNumber(_args[1]), lgNames_, _res);
                return;
            }
            lastIndexOf(_charSequence, _args[0], _args[1], _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSubstring()) || StringList.quickEq(name_, lgNames_.getAliasSubSequence())) {
            if (list_.size() == 1) {
                substring(_charSequence, ClassArgumentMatching.convertToNumber(_args[0]), lgNames_, _res);
                return;
            }
            substring(_charSequence, ClassArgumentMatching.convertToNumber(_args[0]), ClassArgumentMatching.convertToNumber(_args[1]), lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSplit())) {
            if (list_.size() == 1) {
                if (!(_args[0] instanceof CharStruct)) {
                    splitSingleString(_charSequence, _args[0], lgNames_, _res);
                    return;
                }
                splitSingleChar(_charSequence, (CharStruct)_args[0], lgNames_, _res);
                return;
            }
            if (!(_args[0] instanceof CharStruct)) {
                splitSingleString(_charSequence, _args[0], ClassArgumentMatching.convertToNumber(_args[1]), lgNames_, _res);
                return;
            }
            splitSingleChar(_charSequence, (CharStruct)_args[0], ClassArgumentMatching.convertToNumber(_args[1]), lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasTrim())) {
            trim(_charSequence, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasToCharArray())) {
            toCharArray(_charSequence, lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSplitStrings())) {
            if (list_.size() == 1) {
                splitStrings(_charSequence, _args[0], lgNames_, _res);
                return;
            }
            splitStrings(_charSequence, ClassArgumentMatching.convertToNumber(_args[0]), _args[1], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSplitChars())) {
            splitChars(_charSequence, _args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasFormat())) {
            format(_charSequence, _args[0], lgNames_, _res);
            return;
        }
        _res.setResult(_charSequence.getDisplayedString(_cont));
    }

    private static void length(CharSequenceStruct _charSequence, ResultErrorStd _res) {
        _res.setResult(new IntStruct(_charSequence.length()));
    }

    private static void isEmpty(CharSequenceStruct _charSequence, ResultErrorStd _res) {
        _res.setResult(_charSequence.isEmpty());
    }

    private static void charAt(CharSequenceStruct _charSequence, Struct _index, LgNames _stds, ResultErrorStd _res) {
        NumberStruct nb_ = ClassArgumentMatching.convertToNumber(_index);
        int ind_ = nb_.intStruct();
        String badIndex_ = _stds.getAliasBadIndex();
        if (_charSequence.isValidIndex(ind_)) {
            if (ind_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(ind_),"<0"));
            } else {
                _res.setErrorMessage(StringList.concat(Long.toString(ind_),">=", Long.toString(_charSequence.length())));
            }
            _res.setError(badIndex_);
            return;
        }
        _res.setResult(new CharStruct(_charSequence.charAt(ind_)));
    }

    private static void getBytes(CharSequenceStruct _charSequence, LgNames _stds, ResultErrorStd _res) {
        String bytePrim_ = _stds.getAliasPrimByte();
        String seq_ = _charSequence.toStringInstance();
        byte[] list_ = StringList.encode(seq_);
        bytePrim_ = StringExpUtil.getPrettyArrayType(bytePrim_);
        int len_ = list_.length;
        Struct[] strArr_ = new Struct[len_];
        for (int i = 0; i < len_; i++) {
            strArr_[i] = new ByteStruct(list_[i]);
        }
        _res.setResult(new ArrayStruct(strArr_, bytePrim_));
    }

    private static void compareTo(CharSequenceStruct _charSequence, Struct _anotherString, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_anotherString instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct st_ = NumParsers.getCharSeq(_anotherString);
        _res.setResult(new IntStruct(_charSequence.toStringInstance().compareTo(st_.toStringInstance())));
    }

    private static void regionMatches(CharSequenceStruct _charSequence, NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
                                      NumberStruct _len, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_other instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct other_ = NumParsers.getCharSeq(_other);
        int comLen_ = _len.intStruct();
        int to_ = _toffset.intStruct();
        int po_ = _ooffset.intStruct();
        _res.setResult(BooleanStruct.of(_charSequence.toStringInstance().regionMatches(to_, other_.toStringInstance(), po_, comLen_)));
    }

    private static void startsWith(CharSequenceStruct _charSequence, Struct _prefix, LgNames _stds, ResultErrorStd _res) {
        startsWith(_charSequence, _prefix,new IntStruct(0), _stds, _res);
    }

    private static void endsWith(CharSequenceStruct _charSequence, Struct _suffix, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_suffix instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct suffix_ = NumParsers.getCharSeq(_suffix);
        _res.setResult(BooleanStruct.of(_charSequence.toStringInstance().endsWith(suffix_.toStringInstance())));
    }

    private static void startsWith(CharSequenceStruct _charSequence, Struct _prefix, NumberStruct _toffset, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_prefix instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct pref_ = NumParsers.getCharSeq(_prefix);
        int to_ = _toffset.intStruct();
        _res.setResult(BooleanStruct.of(_charSequence.toStringInstance().startsWith(pref_.toStringInstance(), to_)));
    }

    private static void indexOf(CharSequenceStruct _charSequence, Struct _ch, ResultErrorStd _res) {
        indexOf(_charSequence, _ch,new IntStruct(0),_res);
    }

    private static void indexOf(CharSequenceStruct _charSequence, Struct _ch, Struct _fromIndex, ResultErrorStd _res) {
        NumberStruct ch_ = ClassArgumentMatching.convertToNumber(_ch);
        int int_ = ch_.intStruct();
        NumberStruct index_ = ClassArgumentMatching.convertToNumber(_fromIndex);
        int from_ = index_.intStruct();
        _res.setResult(new IntStruct(_charSequence.toStringInstance().indexOf(int_, from_)));
    }

    private static void contains(CharSequenceStruct _charSequence, Struct _str, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct arg_ = NumParsers.getCharSeq(_str);
        _res.setResult(BooleanStruct.of(_charSequence.toStringInstance().contains(arg_.toStringInstance())));
    }

    private static void indexOfString(CharSequenceStruct _charSequence, Struct _str, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_str);
        _res.setResult(new IntStruct(_charSequence.toStringInstance().indexOf(str_.toStringInstance())));
    }

    private static void indexOfString(CharSequenceStruct _charSequence, Struct _str, NumberStruct _fromIndex, LgNames _stds, ResultErrorStd _res) {
        int from_ = _fromIndex.intStruct();
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_str);
        _res.setResult(new IntStruct(_charSequence.toStringInstance().indexOf(str_.toStringInstance(), from_)));
    }

    private static void lastIndexOf(CharSequenceStruct _charSequence, Struct _ch, ResultErrorStd _res) {
        lastIndexOf(_charSequence, _ch, new IntStruct(_charSequence.length()),_res);
    }

    private static void lastIndexOf(CharSequenceStruct _charSequence, Struct _ch, Struct _fromIndex, ResultErrorStd _res) {
        NumberStruct ch_ = ClassArgumentMatching.convertToNumber(_ch);
        int int_ = ch_.intStruct();
        NumberStruct index_ = ClassArgumentMatching.convertToNumber(_fromIndex);
        int from_ = index_.intStruct();
        _res.setResult(new IntStruct(_charSequence.toStringInstance().lastIndexOf(int_, from_)));
    }

    private static void lastIndexOfString(CharSequenceStruct _charSequence, Struct _str, LgNames _stds, ResultErrorStd _res) {
        lastIndexOfString(_charSequence, _str,new IntStruct(_charSequence.length()), _stds, _res);
    }

    private static void lastIndexOfString(CharSequenceStruct _charSequence, Struct _str, NumberStruct _fromIndex, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_str);
        int from_ = _fromIndex.intStruct();
        _res.setResult(new IntStruct(_charSequence.toStringInstance().lastIndexOf(str_.toStringInstance(), from_)));
    }

    private static void substring(CharSequenceStruct _charSequence, NumberStruct _beginIndex, LgNames _stds, ResultErrorStd _res) {
        substring(_charSequence, _beginIndex, new IntStruct(_charSequence.length()), _stds, _res);
    }

    private static void substring(CharSequenceStruct _charSequence, NumberStruct _beginIndex, NumberStruct _endIndex, LgNames _stds, ResultErrorStd _res) {
        int begin_ = _beginIndex.intStruct();
        int end_ = _endIndex.intStruct();
        if (_charSequence.isCorrectSub(begin_, end_)) {
            if (begin_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(begin_),"<0"));
            } else if (end_ > _charSequence.length()) {
                _res.setErrorMessage(StringList.concat(Long.toString(end_),">", Long.toString(_charSequence.length())));
            } else {
                _res.setErrorMessage(StringList.concat(Long.toString(begin_),">", Long.toString(end_)));
            }
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringStruct(_charSequence.substring(begin_, end_)));
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
        StringList parts_ = StringList.splitChars(_charSequence.toStringInstance(), ch_);
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = lim_;
        }
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_.get(i));
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }

    private static void splitChars(CharSequenceStruct _charSequence, Struct _seps, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_seps instanceof ArrayStruct)) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        Struct[] arrStructSep_ = arrSep_.getInstance();
        int lenSeps_ = arrStructSep_.length;
        char[] seps_ = new char[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrStructSep_[i];
            seps_[i] = ClassArgumentMatching.convertToChar(curSep_).getChar();
        }
        StringList parts_ = StringList.splitChars(_charSequence.toStringInstance(), seps_);
        int lenArr_ = parts_.size();
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_.get(i));
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }

    private static void splitSingleString(CharSequenceStruct _charSequence, Struct _sep, LgNames _stds, ResultErrorStd _res) {
        splitSingleString(_charSequence, _sep, new IntStruct(-1), _stds, _res);
    }

    private static void splitStrings(CharSequenceStruct _charSequence, Struct _seps, LgNames _stds, ResultErrorStd _res) {
        splitStrings(_charSequence, new IntStruct(-1), _seps, _stds, _res);
    }

    private static void splitStrings(CharSequenceStruct _charSequence, NumberStruct _lim, Struct _seps, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_seps instanceof ArrayStruct)) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        Struct[] arrStructSep_ = arrSep_.getInstance();
        int lenSeps_ = arrStructSep_.length;
        String[] seps_ = new String[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrStructSep_[i];
            if (!(curSep_ instanceof CharSequenceStruct)) {
                _res.setError(nullPe_);
                return;
            }
            seps_[i] = NumParsers.getCharSeq(curSep_).toStringInstance();
        }
        int lim_ = _lim.intStruct();
        if (lim_ < -1) {
            lim_ = -1;
        }
        StringList parts_ = StringList.splitStrings(_charSequence.toStringInstance(), seps_);
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = lim_;
        }
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_.get(i));
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }

    private static void splitSingleString(CharSequenceStruct _charSequence, Struct _sep, NumberStruct _lim, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_sep instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        int lim_ = _lim.intStruct();
        if (lim_ < -1) {
            lim_ = -1;
        }
        CharSequenceStruct str_ = NumParsers.getCharSeq(_sep);
        StringList parts_ = StringList.splitStrings(_charSequence.toStringInstance(), str_.toStringInstance());
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = lim_;
        }
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_.get(i));
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = StringExpUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }

    private static void trim(CharSequenceStruct _charSequence, ResultErrorStd _res) {
        _res.setResult(new StringStruct(_charSequence.toStringInstance().trim()));
    }

    private static void toCharArray(CharSequenceStruct _charSequence, LgNames _stds, ResultErrorStd _res) {
        String aliasChar_ = _stds.getAliasPrimChar();
        aliasChar_ = StringExpUtil.getPrettyArrayType(aliasChar_);
        int len_ = _charSequence.length();
        Struct[] arrOut_ = new Struct[len_];
        for (int i = 0; i < len_; i++) {
            arrOut_[i] = new CharStruct(_charSequence.charAt(i));
        }
        ArrayStruct arr_ = new ArrayStruct(arrOut_,aliasChar_);
        _res.setResult(arr_);
    }

    private static void format(CharSequenceStruct _charSequence, Struct _seps, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_seps instanceof ArrayStruct)) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        Struct[] arrStructSep_ = arrSep_.getInstance();
        int lenSeps_ = arrStructSep_.length;
        String[] seps_ = new String[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrStructSep_[i];
            if (!(curSep_ instanceof CharSequenceStruct)) {
                _res.setError(nullPe_);
                return;
            }
            seps_[i] = NumParsers.getCharSeq(curSep_).toStringInstance();
        }
        _res.setResult(new StringStruct(StringList.simpleStringsFormat(_charSequence.toStringInstance(), seps_)));
    }

    public void build(LgNames _lgNames) {
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        StringList params_;
        StringList noTypes_ = new StringList();
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        CustList<StandardConstructor> constructors_;
        StringMap<StandardField> fields_;
        StandardMethod method_;
        StandardConstructor ctor_;
        StandardType std_;
        std_ = new StandardInterface(aliasCharSequence, methods_, noTypes_);
        String aliasPrimBoolean_ = _lgNames.getAliasPrimBoolean();
        String aliasPrimDouble_ = _lgNames.getAliasPrimDouble();
        String aliasPrimFloat_ = _lgNames.getAliasPrimFloat();
        String aliasPrimLong_ = _lgNames.getAliasPrimLong();
        String aliasPrimInteger_ = _lgNames.getAliasPrimInteger();
        String aliasPrimChar_ = _lgNames.getAliasPrimChar();
        String aliasPrimShort_ = _lgNames.getAliasPrimShort();
        String aliasPrimByte_ = _lgNames.getAliasPrimByte();
        String aliasToString_ = _lgNames.getAliasToStringMethod();
        String aliasCompareTo_ = _lgNames.getAliasCompareTo();
        String aliasObject_ = _lgNames.getAliasObject();
        String aliasValueOf_ = _lgNames.getAliasValueOfMethod();
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasString, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasCompareTo_, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasContains, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasEndsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEmpty, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToCharArray, params_, StringExpUtil.getPrettyArrayType(aliasPrimChar_), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetBytes, params_, StringExpUtil.getPrettyArrayType(aliasPrimByte_), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasFormat, params_, aliasString, true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasSplitStrings, params_, StringExpUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasCharSequence);
        method_ = new StandardMethod(aliasSplitStrings, params_, StringExpUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasSplitChars, params_, StringExpUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasCharSequence, aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrim, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString_, params_, aliasString, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence,aliasCharSequence);
        method_ = new StandardMethod(_lgNames.getAliasEquals(), params_, _lgNames.getAliasPrimBoolean(), false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        standards_.put(aliasCharSequence, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasString, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasEqualsIgnoreCase, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasString);
        method_ = new StandardMethod(_lgNames.getAliasCompare(), params_, aliasPrimInteger_, false, MethodModifier.STATIC, std_);
        methods_.put(new MethodId(MethodAccessKind.STATIC, _lgNames.getAliasCompare(), params_), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasCompareToIgnoreCase, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasString);
        method_ = new StandardMethod(aliasReplace, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimChar_);
        method_ = new StandardMethod(aliasReplace, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasReplacement);
        method_ = new StandardMethod(aliasReplaceMultiple, params_, aliasString, true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_, aliasPrimInteger_, aliasString, aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToLowerCase, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToUpperCase, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimByte_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimShort_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, true, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_,aliasPrimChar_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, true, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimByte_);
        ctor_ = new StandardConstructor(params_, true, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_, aliasPrimByte_);
        ctor_ = new StandardConstructor(params_, true, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimChar_);
        ctor_ = new StandardConstructor(params_, true, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_, aliasPrimChar_);
        ctor_ = new StandardConstructor(params_, true, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        std_.getDirectInterfaces().add(aliasCharSequence);
        standards_.put(aliasString, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(aliasStringBuilder, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimByte_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimShort_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasStringBuilder);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasStringBuilder,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(aliasPrimChar_));
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(aliasPrimChar_),aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCapacity, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasDelete, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasDeleteCharAt, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasClear, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimBoolean_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimByte_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimShort_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimChar_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimLong_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimFloat_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimDouble_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasString);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasString,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasStringBuilder);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasStringBuilder,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, StringExpUtil.getPrettyArrayType(aliasPrimChar_));
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, StringExpUtil.getPrettyArrayType(aliasPrimChar_),aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReverse, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_,aliasCharSequence);
        method_ = new StandardMethod(aliasReplace, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimChar_);
        method_ = new StandardMethod(aliasSetCharAt, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasSetLength, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrimToSize, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasEnsureCapacity, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasStringBuilder,aliasStringBuilder);
        method_ = new StandardMethod(aliasSame, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger_);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasString);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        std_.getDirectInterfaces().add(aliasCharSequence);
        standards_.put(aliasStringBuilder, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(aliasReplacement, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetOldString, params_, aliasString, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetNewString, params_, aliasString, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence,aliasCharSequence);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        standards_.put(aliasReplacement, std_);
    }

    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_;
        result_ = new ResultErrorStd();
        Struct[] args_ = AnaApplyCoreMethodUtil.getObjects(_args);
        calculateStrBuilder(_cont, result_, _method, _struct, args_);
        return result_;
    }

    static ResultErrorStd invokeStdMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = AnaApplyCoreMethodUtil.getObjects(_args);
        LgNames lgNames_ = _cont.getStandards();
        String type_ = _method.getClassName();
        String stringType_ = lgNames_.getAliasString();
        if (StringList.quickEq(type_, stringType_)) {
            calculateString(_cont, result_, _method, _struct, args_);
            return result_;
        }
        calculateCharSeq(_cont, result_, _method, _struct, args_);
        return result_;
    }

    public String getAliasCharSequence() {
        return aliasCharSequence;
    }

    public void setAliasCharSequence(String _aliasCharSequence) {
        aliasCharSequence = _aliasCharSequence;
    }

    public String getAliasString() {
        return aliasString;
    }

    public void setAliasString(String _aliasString) {
        aliasString = _aliasString;
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


}
