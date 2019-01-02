package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.Replacement;
import code.util.StringList;

public final class StringStruct extends CharSequenceStruct {

    private final String instance;

    public StringStruct(String _instance) {
        instance = _instance;
    }

    public static void instantiate(Analyzable _cont, ResultErrorStd _res, ConstructorId _method, Struct... _args) {
        StringList list_ = _method.getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String bytePrimType_ = lgNames_.getAliasPrimByte();
        String charPrimType_ = lgNames_.getAliasPrimChar();
        if (list_.size() == 0) {
            newStringStruct(_res);
            return;
        }
        if (list_.size() == 1) {
            String arrBytes_ = PrimitiveTypeUtil.getPrettyArrayType(bytePrimType_);
            if (StringList.quickEq(list_.first(), arrBytes_)) {
                newStringStructByByteArray(_args[0], lgNames_, _res);
                return;
            }
            String arrChars_ = PrimitiveTypeUtil.getPrettyArrayType(charPrimType_);
            if (StringList.quickEq(list_.first(), arrChars_)) {
                newStringStructByCharArray(_args[0], lgNames_, _res);
                return;
            }
            newStringBuilderStruct(_args[0], lgNames_, _res);
            return;
        }
        String arrBytes_ = PrimitiveTypeUtil.getPrettyArrayType(bytePrimType_);
        if (StringList.quickEq(list_.first(), arrBytes_)) {
            newStringStructByByteArray(_args[0], _args[1], _args[2], lgNames_, _res);
            return;
        }
        newStringStructByCharArray(_args[0], _args[1], _args[2], lgNames_, _res);
    }
    private static void newStringStruct(ResultErrorStd _res) {
        _res.setResult(new StringStruct(""));
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
            arr_[i] = ((CharStruct)argArr_[i]).getChar();
        }
        _res.setResult(new StringStruct(new String(arr_)));
    }
    private static void newStringStructByCharArray(Struct _arg, Struct _one, Struct _two,LgNames _stds, ResultErrorStd _res) {
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
            arr_[i] = ((CharStruct)argArr_[i]).getChar();
        }
        int one_ = ((NumberStruct) _one).getInstance().intValue();
        int two_ = ((NumberStruct) _two).getInstance().intValue();
        if (one_ < 0 || two_ < 0 || one_ > arr_.length - two_) {
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
            arr_[i] = ((NumberStruct)argArr_[i]).getInstance().byteValue();
        }
        String chars_ = StringList.decode(arr_);
        if (chars_ == null) {
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringStruct(chars_));
    }
    private static void newStringStructByByteArray(Struct _arg, Struct _one, Struct _two,LgNames _stds, ResultErrorStd _res) {
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
            arr_[i] = ((NumberStruct)argArr_[i]).getInstance().byteValue();
        }
        int one_ = ((NumberStruct) _one).getInstance().intValue();
        int two_ = ((NumberStruct) _two).getInstance().intValue();
        if (one_ < 0 || two_ < 0 || one_ > arr_.length - two_) {
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        String chars_ = StringList.decode(arr_, one_, two_);
        if (chars_ == null) {
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
        StringBuilderStruct arg_ = (StringBuilderStruct) _arg;
        _res.setResult(new StringStruct(arg_.getInstance().toString()));
    }

    public static void calculate(Analyzable _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            ((StringStruct)_struct).calculate(_cont, _res, _method, _args);
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
            first_.compareTo(_args[1], lgNames_, _res);
            return;
        }
        Struct arg_ = _args[0];
        if (list_.size() == 1) {
            if (arg_ instanceof DisplayableStruct) {
                _res.setResult(((DisplayableStruct)arg_).getDisplayedString(_cont));
                return;
            }
            if (!(arg_ instanceof ArrayStruct)) {
                String nullPe_ = lgNames_.getAliasNullPe();
                _res.setError(nullPe_);
                return;
            }
            ArrayStruct chArr_ = (ArrayStruct) arg_;
            Struct[] argArr_ = chArr_.getInstance();
            int len_ = argArr_.length;
            char[] arr_ = new char[len_];
            for (int i = 0; i < len_; i++) {
                arr_[i] = ((CharStruct)argArr_[i]).getChar();
            }
            _res.setResult(new StringStruct(String.valueOf(arr_)));
            return;
        }
        ArrayStruct chArr_ = (ArrayStruct) arg_;
        Struct[] argArr_ = chArr_.getInstance();
        int len_ = argArr_.length;
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = ((CharStruct)argArr_[i]).getChar();
        }
        int one_ = ((NumberStruct)_args[0]).getInstance().intValue();
        int two_ = ((NumberStruct)_args[1]).getInstance().intValue();
        if (one_ < 0 || two_ < 0 || one_ + two_ > arr_.length) {
            _res.setError(lgNames_.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringStruct(String.valueOf(arr_,one_,two_)));
    }
    private void calculate(Analyzable _cont, ResultErrorStd _res, ClassMethodId _method, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String stringType_ = lgNames_.getAliasString();
        if (StringList.quickEq(name_, lgNames_.getAliasLength())) {
            length(_res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasIsEmpty())) {
            isEmpty(_res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasCharAt())) {
            charAt(_args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasGetBytes())) {
            getBytes(lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
            compareTo(_args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasRegionMatches())) {
            regionMatches((NumberStruct)_args[0], _args[1], (NumberStruct)_args[2], (NumberStruct)_args[3], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasStartsWith())) {
            if (list_.size() == 1) {
                startsWith(_args[0], lgNames_, _res);
                return;
            }
            startsWith(_args[0], (NumberStruct)_args[1], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasEndsWith())) {
            endsWith(_args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasIndexOf())) {
            if (list_.size() == 1) {
                if (StringList.quickEq(list_.first(), stringType_)) {
                    indexOfString(_args[0], lgNames_, _res);
                    return;
                }
                indexOf(_args[0], _res);
                return;
            }
            if (StringList.quickEq(list_.first(), stringType_)) {
                indexOfString(_args[0], (NumberStruct) _args[1], lgNames_, _res);
                return;
            }
            indexOf(_args[0], _args[1], _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasContains())) {
            contains(_args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasLastIndexOf())) {
            if (list_.size() == 1) {
                if (StringList.quickEq(list_.first(), stringType_)) {
                    lastIndexOfString(_args[0], lgNames_, _res);
                    return;
                }
                lastIndexOf(_args[0], _res);
                return;
            }
            if (StringList.quickEq(list_.first(), stringType_)) {
                lastIndexOfString(_args[0], (NumberStruct) _args[1], lgNames_, _res);
                return;
            }
            lastIndexOf(_args[0], _args[1], _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSubstring()) || StringList.quickEq(name_, lgNames_.getAliasSubSequence())) {
            if (list_.size() == 1) {
                substring((NumberStruct) _args[0], lgNames_, _res);
                return;
            }
            substring((NumberStruct) _args[0], (NumberStruct) _args[1], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplace())) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                replaceString(_args[0], _args[1], _res);
                return;
            }
            replace((CharStruct)_args[0], (CharStruct)_args[1], _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSplit())) {
            if (list_.size() == 1) {
                if (StringList.quickEq(list_.first(), stringType_)) {
                    splitSingleString(_args[0], lgNames_, _res);
                    return;
                }
                splitSingleChar((CharStruct)_args[0], lgNames_, _res);
                return;
            }
            if (StringList.quickEq(list_.first(), stringType_)) {
                splitSingleString(_args[0], (NumberStruct) _args[1], lgNames_, _res);
                return;
            }
            splitSingleChar((CharStruct)_args[0], (NumberStruct) _args[1], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasTrim())) {
            trim(_res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasToCharArray())) {
            toCharArray(lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSplitStrings())) {
            if (list_.size() == 1) {
                splitStrings(_args[0], lgNames_, _res);
                return;
            }
            splitStrings((NumberStruct) _args[0], _args[1], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSplitChars())) {
            splitChars(_args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
            eq(_args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasFormat())) {
            format(_args[0], lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplaceMultiple())) {
            replaceMultiple(_args[0], lgNames_, _res);
            return;
        }
    }
    private void length(ResultErrorStd _res) {
        _res.setResult(new IntStruct(instance.length()));
    }

    private void isEmpty(ResultErrorStd _res) {
        _res.setResult(new BooleanStruct(instance.isEmpty()));
    }

    private void charAt(Struct _index, LgNames _stds, ResultErrorStd _res) {
        NumberStruct nb_ = (NumberStruct)_index;
        int ind_ = nb_.getInstance().intValue();
        String badIndex_ = _stds.getAliasBadIndex();
        if (ind_ < 0 || ind_ >= instance.length()) {
            _res.setError(badIndex_);
            return;
        }
        _res.setResult(new CharStruct(instance.charAt(ind_)));
    }

    private void getBytes(LgNames _stds, ResultErrorStd _res) {
        String bytePrim_ = _stds.getAliasPrimByte();
        byte[] list_ = StringList.encode(instance);
        bytePrim_ = PrimitiveTypeUtil.getPrettyArrayType(bytePrim_);
        int len_ = list_.length;
        Struct[] strArr_ = new Struct[len_];
        for (int i = 0; i < len_; i++) {
            strArr_[i] = new ByteStruct(list_[i]);
        }
        _res.setResult(new ArrayStruct(strArr_, bytePrim_));
    }

    private void compareTo(Struct _anotherString, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_anotherString instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct st_ = (StringStruct)_anotherString;
        _res.setResult(new IntStruct(instance.compareTo(st_.instance)));
    }

    private void regionMatches(NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
            NumberStruct _len, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_other instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct other_ = (StringStruct) _other;
        int comLen_ = _len.getInstance().intValue();
        int to_ = _toffset.getInstance().intValue();
        int po_ = _ooffset.getInstance().intValue();
        _res.setResult(new BooleanStruct(instance.regionMatches(to_, other_.instance, po_, comLen_)));
    }

    private void startsWith(Struct _prefix, LgNames _stds, ResultErrorStd _res) {
        startsWith(_prefix,new IntStruct(0), _stds, _res);
    }

    private void endsWith(Struct _suffix, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_suffix instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct suffix_ = (StringStruct) _suffix;
        _res.setResult(new BooleanStruct(instance.endsWith(suffix_.instance)));
    }
    private void startsWith(Struct _prefix, NumberStruct _toffset, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_prefix instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct pref_ = (StringStruct) _prefix;
        int to_ = _toffset.getInstance().intValue();
        _res.setResult(new BooleanStruct(instance.startsWith(pref_.instance, to_)));
    }

    private void indexOf(Struct _ch, ResultErrorStd _res) {
        indexOf(_ch,new IntStruct(0),_res);
    }

    private void indexOf(Struct _ch, Struct _fromIndex, ResultErrorStd _res) {
        NumberStruct ch_ = (NumberStruct) _ch;
        int int_ = ch_.getInstance().intValue();
        NumberStruct index_ = (NumberStruct) _fromIndex;
        int from_ = index_.getInstance().intValue();
        _res.setResult(new IntStruct(instance.indexOf(int_, from_)));
    }
    //getAliasFormat,replaceMult,getAliasSplit chars

    private void contains(Struct _str, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct arg_ = (CharSequenceStruct) _str;
        _res.setResult(new BooleanStruct(instance.contains(arg_.getInstance())));
    }

    private void indexOfString(Struct _str, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct str_ = (StringStruct) _str;
        _res.setResult(new IntStruct(instance.indexOf(str_.instance)));
    }

    private void indexOfString(Struct _str, NumberStruct _fromIndex, LgNames _stds, ResultErrorStd _res) {
        int from_ = _fromIndex.getInstance().intValue();
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct str_ = (StringStruct) _str;
        _res.setResult(new IntStruct(instance.indexOf(str_.instance, from_)));
    }

    private void lastIndexOf(Struct _ch, ResultErrorStd _res) {
        lastIndexOf(_ch, new IntStruct(instance.length()),_res);
    }

    private void lastIndexOf(Struct _ch, Struct _fromIndex, ResultErrorStd _res) {
        NumberStruct ch_ = (NumberStruct) _ch;
        int int_ = ch_.getInstance().intValue();
        NumberStruct index_ = (NumberStruct) _fromIndex;
        int from_ = index_.getInstance().intValue();
        _res.setResult(new IntStruct(instance.lastIndexOf(int_, from_)));
    }

    private void lastIndexOfString(Struct _str, LgNames _stds, ResultErrorStd _res) {
        lastIndexOfString(_str,new IntStruct(instance.length()), _stds, _res);
    }

    private void lastIndexOfString(Struct _str, NumberStruct _fromIndex, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct str_ = (StringStruct)_str;
        int from_ = _fromIndex.getInstance().intValue();
        _res.setResult(new IntStruct(instance.lastIndexOf(str_.instance, from_)));
    }
    private void substring(NumberStruct _beginIndex, LgNames _stds, ResultErrorStd _res) {
        substring(_beginIndex, new IntStruct(instance.length()), _stds, _res);
    }

    private void substring(NumberStruct _beginIndex, NumberStruct _endIndex, LgNames _stds, ResultErrorStd _res) {
        int begin_ = _beginIndex.getInstance().intValue();
        int end_ = _endIndex.getInstance().intValue();
        if (begin_ < 0 || end_ < 0 || end_ > instance.length() || begin_ > end_) {
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringStruct(instance.substring(begin_, end_)));
    }

    private void replace(CharStruct _oldChar, CharStruct _newChar, ResultErrorStd _res) {
        char oldChar_ = _oldChar.getChar();
        char newChar_ = _newChar.getChar();
        _res.setResult(new StringStruct(instance.replace(oldChar_, newChar_)));
    }

    private void replaceString(Struct _oldChar, Struct _newChar, ResultErrorStd _res) {
        String old_;
        if (_oldChar instanceof StringStruct) {
            old_ = ((StringStruct)_oldChar).instance;
        } else {
            old_ = null;
        }
        String new_;
        if (_newChar instanceof StringStruct) {
            new_ = ((StringStruct)_newChar).instance;
        } else {
            new_ = null;
        }
        String out_ = StringList.replace(instance, old_, new_);
        _res.setResult(new StringStruct(out_));
    }
    private void splitSingleChar(CharStruct _sep, LgNames _stds, ResultErrorStd _res) {
        splitSingleChar(_sep, new IntStruct(-1), _stds, _res);
    }
    private void splitSingleChar(CharStruct _sep, NumberStruct _lim, LgNames _stds, ResultErrorStd _res) {
        int lim_ = _lim.getInstance().intValue();
        if (lim_ < -1) {
            lim_ = -1;
        }
        char ch_ = _sep.getChar();
        StringList parts_ = StringList.splitChars(instance, ch_);
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = lim_;
        }
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_.get(i));
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = PrimitiveTypeUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }
    private void splitChars(Struct _seps, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_seps instanceof ArrayStruct)) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        Struct[] arrStructSep_ = arrSep_.getInstance();
        int lenSeps_ = arrStructSep_.length;
        Character[] seps_ = new Character[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrStructSep_[i];
            if (!(curSep_ instanceof CharStruct)) {
                _res.setError(nullPe_);
                return;
            }
            seps_[i] = ((CharStruct)curSep_).getChar();
        }
        StringList parts_ = StringList.splitChars(instance, seps_);
        int lenArr_ = parts_.size();
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_.get(i));
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = PrimitiveTypeUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }
    private void splitSingleString(Struct _sep, LgNames _stds, ResultErrorStd _res) {
        splitSingleString(_sep, new IntStruct(-1), _stds, _res);
    }
    private void splitStrings(Struct _seps, LgNames _stds, ResultErrorStd _res) {
        splitStrings(new IntStruct(-1), _seps, _stds, _res);
    }
    private void splitStrings(NumberStruct _lim, Struct _seps, LgNames _stds, ResultErrorStd _res) {
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
            if (!(curSep_ instanceof StringStruct)) {
                _res.setError(nullPe_);
                return;
            }
            seps_[i] = ((StringStruct)curSep_).instance;
        }
        int lim_ = _lim.getInstance().intValue();
        if (lim_ < -1) {
            lim_ = -1;
        }
        StringList parts_ = StringList.splitStrings(instance, seps_);
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = lim_;
        }
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_.get(i));
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = PrimitiveTypeUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }
    private void splitSingleString(Struct _sep, NumberStruct _lim, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_sep instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        int lim_ = _lim.getInstance().intValue();
        if (lim_ < -1) {
            lim_ = -1;
        }
        StringStruct str_ = (StringStruct)_sep;
        StringList parts_ = StringList.splitStrings(instance, str_.instance);
        int lenArr_ = parts_.size();
        if (lim_ >= 0) {
            lenArr_ = lim_;
        }
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_.get(i));
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = PrimitiveTypeUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }
    
    private void trim(ResultErrorStd _res) {
        _res.setResult(new StringStruct(instance.trim()));
    }

    private void toCharArray(LgNames _stds,ResultErrorStd _res) {
        String aliasChar_ = _stds.getAliasPrimChar();
        aliasChar_ = PrimitiveTypeUtil.getPrettyArrayType(aliasChar_);
        int len_ = instance.length();
        Struct[] arrOut_ = new Struct[len_];
        for (int i = 0; i <= len_; i++) {
            arrOut_[i] = new CharStruct(instance.charAt(i));
        }
        ArrayStruct arr_ = new ArrayStruct(arrOut_,aliasChar_);
        _res.setResult(arr_);
    }
    private void eq(Struct _sep, LgNames _stds,ResultErrorStd _res) {
        _res.setResult(new BooleanStruct(sameReference(_sep)));
    }
    private void format(Struct _seps, LgNames _stds,ResultErrorStd _res) {
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
            if (!(curSep_ instanceof StringStruct)) {
                _res.setError(nullPe_);
                return;
            }
            seps_[i] = ((StringStruct)curSep_).instance;
        }
        _res.setResult(new StringStruct(StringList.simpleStringsFormat(instance, seps_)));
    }
    private void replaceMultiple(Struct _seps, LgNames _stds,ResultErrorStd _res) {
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
            seps_[i] = ((ReplacementStruct)curSep_).getInstance();
        }
        _res.setResult(new StringStruct(StringList.replaceMult(instance, seps_)));
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasString();
    }

    @Override
    public String getInstance() {
        return instance;
    }

}
