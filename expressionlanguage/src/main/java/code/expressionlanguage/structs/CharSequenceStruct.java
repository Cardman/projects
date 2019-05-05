package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.StringList;

public abstract class CharSequenceStruct implements DisplayableStruct, ExportableStringStruct, RealInstanceStruct {
    @Override
    public final Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof CharSequenceStruct)) {
            return false;
        }
        CharSequenceStruct other_ = (CharSequenceStruct) _other;
        CharSequence first_ = getInstance();
        CharSequence second_ = other_.getInstance();
        int len_ = first_.length();
        if (len_ != second_.length()) {
            return false;
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (first_.charAt(i) != second_.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public static void calculateCharSeq(Analyzable _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            ((CharSequenceStruct) _struct).calculateLocCharSeq(_cont, _res, _method, _args);
            return;
        }
        _res.setResult(new BooleanStruct(_args[0].sameReference(_args[1])));
    }
    private void calculateLocCharSeq(Analyzable _cont, ResultErrorStd _res, ClassMethodId _method, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
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
                if (!(_args[0] instanceof NumberStruct)) {
                    indexOfString(_args[0], lgNames_, _res);
                    return;
                }
                indexOf(_args[0], _res);
                return;
            }
            if (!(_args[0] instanceof NumberStruct)) {
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
                if (!(_args[0] instanceof NumberStruct)) {
                    lastIndexOfString(_args[0], lgNames_, _res);
                    return;
                }
                lastIndexOf(_args[0], _res);
                return;
            }
            if (!(_args[0] instanceof NumberStruct)) {
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
        if (StringList.quickEq(name_, lgNames_.getAliasSplit())) {
            if (list_.size() == 1) {
                if (!(_args[0] instanceof CharStruct)) {
                    splitSingleString(_args[0], lgNames_, _res);
                    return;
                }
                splitSingleChar((CharStruct)_args[0], lgNames_, _res);
                return;
            }
            if (!(_args[0] instanceof CharStruct)) {
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
        if (StringList.quickEq(name_, lgNames_.getAliasFormat())) {
            format(_args[0], lgNames_, _res);
            return;
        }
        _res.setResult(getDisplayedString(_cont));
    }
    private void length(ResultErrorStd _res) {
        _res.setResult(new IntStruct(getInstance().length()));
    }

    private void isEmpty(ResultErrorStd _res) {
        _res.setResult(new BooleanStruct(getInstance().length() == 0));
    }

    private void charAt(Struct _index, LgNames _stds, ResultErrorStd _res) {
        NumberStruct nb_ = (NumberStruct)_index;
        int ind_ = nb_.getInstance().intValue();
        String badIndex_ = _stds.getAliasBadIndex();
        CharSequence seq_ = getInstance();
        if (ind_ < 0 || ind_ >= seq_.length()) {
            if (ind_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(ind_),"<0"));
            } else {
                _res.setErrorMessage(StringList.concat(Long.toString(ind_),">=", Long.toString(seq_.length())));
            }
            _res.setError(badIndex_);
            return;
        }
        _res.setResult(new CharStruct(seq_.charAt(ind_)));
    }

    private void getBytes(LgNames _stds, ResultErrorStd _res) {
        String bytePrim_ = _stds.getAliasPrimByte();
        CharSequence seq_ = getInstance();
        byte[] list_ = StringList.encode(seq_);
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
        if (!(_anotherString instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequence seq_ = getInstance();
        CharSequenceStruct st_ = (CharSequenceStruct)_anotherString;
        _res.setResult(new IntStruct(seq_.toString().compareTo(st_.getInstance().toString())));
    }

    private void regionMatches(NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
            NumberStruct _len, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_other instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequence seq_ = getInstance();
        CharSequenceStruct other_ = (CharSequenceStruct) _other;
        int comLen_ = _len.getInstance().intValue();
        int to_ = _toffset.getInstance().intValue();
        int po_ = _ooffset.getInstance().intValue();
        _res.setResult(new BooleanStruct(seq_.toString().regionMatches(to_, other_.getInstance().toString(), po_, comLen_)));
    }

    private void startsWith(Struct _prefix, LgNames _stds, ResultErrorStd _res) {
        startsWith(_prefix,new IntStruct(0), _stds, _res);
    }

    private void endsWith(Struct _suffix, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_suffix instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequence seq_ = getInstance();
        CharSequenceStruct suffix_ = (CharSequenceStruct) _suffix;
        _res.setResult(new BooleanStruct(seq_.toString().endsWith(suffix_.getInstance().toString())));
    }
    private void startsWith(Struct _prefix, NumberStruct _toffset, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_prefix instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequence seq_ = getInstance();
        CharSequenceStruct pref_ = (CharSequenceStruct) _prefix;
        int to_ = _toffset.getInstance().intValue();
        _res.setResult(new BooleanStruct(seq_.toString().startsWith(pref_.getInstance().toString(), to_)));
    }

    private void indexOf(Struct _ch, ResultErrorStd _res) {
        indexOf(_ch,new IntStruct(0),_res);
    }

    private void indexOf(Struct _ch, Struct _fromIndex, ResultErrorStd _res) {
        CharSequence seq_ = getInstance();
        NumberStruct ch_ = (NumberStruct) _ch;
        int int_ = ch_.getInstance().intValue();
        NumberStruct index_ = (NumberStruct) _fromIndex;
        int from_ = index_.getInstance().intValue();
        _res.setResult(new IntStruct(seq_.toString().indexOf(int_, from_)));
    }
    //getAliasFormat,replaceMult,getAliasSplit chars

    private void contains(Struct _str, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequence seq_ = getInstance();
        CharSequenceStruct arg_ = (CharSequenceStruct) _str;
        _res.setResult(new BooleanStruct(seq_.toString().contains(arg_.getInstance())));
    }

    private void indexOfString(Struct _str, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequence seq_ = getInstance();
        CharSequenceStruct str_ = (CharSequenceStruct) _str;
        _res.setResult(new IntStruct(seq_.toString().indexOf(str_.getInstance().toString())));
    }

    private void indexOfString(Struct _str, NumberStruct _fromIndex, LgNames _stds, ResultErrorStd _res) {
        int from_ = _fromIndex.getInstance().intValue();
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequence seq_ = getInstance();
        CharSequenceStruct str_ = (CharSequenceStruct) _str;
        _res.setResult(new IntStruct(seq_.toString().indexOf(str_.getInstance().toString(), from_)));
    }

    private void lastIndexOf(Struct _ch, ResultErrorStd _res) {
        CharSequence seq_ = getInstance();
        lastIndexOf(_ch, new IntStruct(seq_.length()),_res);
    }

    private void lastIndexOf(Struct _ch, Struct _fromIndex, ResultErrorStd _res) {
        NumberStruct ch_ = (NumberStruct) _ch;
        int int_ = ch_.getInstance().intValue();
        NumberStruct index_ = (NumberStruct) _fromIndex;
        int from_ = index_.getInstance().intValue();
        CharSequence seq_ = getInstance();
        _res.setResult(new IntStruct(seq_.toString().lastIndexOf(int_, from_)));
    }

    private void lastIndexOfString(Struct _str, LgNames _stds, ResultErrorStd _res) {
        CharSequence seq_ = getInstance();
        lastIndexOfString(_str,new IntStruct(seq_.length()), _stds, _res);
    }

    private void lastIndexOfString(Struct _str, NumberStruct _fromIndex, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_str instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequence seq_ = getInstance();
        CharSequenceStruct str_ = (CharSequenceStruct)_str;
        int from_ = _fromIndex.getInstance().intValue();
        _res.setResult(new IntStruct(seq_.toString().lastIndexOf(str_.getInstance().toString(), from_)));
    }
    private void substring(NumberStruct _beginIndex, LgNames _stds, ResultErrorStd _res) {
        CharSequence seq_ = getInstance();
        substring(_beginIndex, new IntStruct(seq_.length()), _stds, _res);
    }

    private void substring(NumberStruct _beginIndex, NumberStruct _endIndex, LgNames _stds, ResultErrorStd _res) {
        int begin_ = _beginIndex.getInstance().intValue();
        int end_ = _endIndex.getInstance().intValue();
        CharSequence seq_ = getInstance();
        if (begin_ < 0 || end_ > seq_.length() || begin_ > end_) {
            if (begin_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(begin_),"<0"));
            } else if (end_ > seq_.length()) {
                _res.setErrorMessage(StringList.concat(Long.toString(end_),">", Long.toString(seq_.length())));
            } else {
                _res.setErrorMessage(StringList.concat(Long.toString(begin_),">", Long.toString(end_)));
            }
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        _res.setResult(new StringStruct(seq_.subSequence(begin_, end_).toString()));
    }

    private void splitSingleChar(CharStruct _sep, LgNames _stds, ResultErrorStd _res) {
        splitSingleChar(_sep, new IntStruct(-1), _stds, _res);
    }
    private void splitSingleChar(CharStruct _sep, NumberStruct _lim, LgNames _stds, ResultErrorStd _res) {
        CharSequence seq_ = getInstance();
        int lim_ = _lim.getInstance().intValue();
        if (lim_ < -1) {
            lim_ = -1;
        }
        char ch_ = _sep.getChar();
        StringList parts_ = StringList.splitChars(seq_.toString(), ch_);
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
        CharSequence seq_ = getInstance();
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
            seps_[i] = ((CharStruct)curSep_).getChar();
        }
        StringList parts_ = StringList.splitChars(seq_.toString(), seps_);
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
        CharSequence seq_ = getInstance();
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
            seps_[i] = ((CharSequenceStruct)curSep_).getInstance().toString();
        }
        int lim_ = _lim.getInstance().intValue();
        if (lim_ < -1) {
            lim_ = -1;
        }
        StringList parts_ = StringList.splitStrings(seq_.toString(), seps_);
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
        if (!(_sep instanceof CharSequenceStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequence seq_ = getInstance();
        int lim_ = _lim.getInstance().intValue();
        if (lim_ < -1) {
            lim_ = -1;
        }
        CharSequenceStruct str_ = (CharSequenceStruct)_sep;
        StringList parts_ = StringList.splitStrings(seq_.toString(), str_.getInstance().toString());
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
        CharSequence seq_ = getInstance();
        _res.setResult(new StringStruct(seq_.toString().trim()));
    }

    private void toCharArray(LgNames _stds,ResultErrorStd _res) {
        CharSequence seq_ = getInstance();
        String aliasChar_ = _stds.getAliasPrimChar();
        aliasChar_ = PrimitiveTypeUtil.getPrettyArrayType(aliasChar_);
        int len_ = seq_.length();
        Struct[] arrOut_ = new Struct[len_];
        for (int i = 0; i < len_; i++) {
            arrOut_[i] = new CharStruct(seq_.charAt(i));
        }
        ArrayStruct arr_ = new ArrayStruct(arrOut_,aliasChar_);
        _res.setResult(arr_);
    }

    private void format(Struct _seps, LgNames _stds,ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_seps instanceof ArrayStruct)) {
            _res.setError(nullPe_);
            return;
        }
        CharSequence seq_ = getInstance();
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
            seps_[i] = ((CharSequenceStruct)curSep_).getInstance().toString();
        }
        _res.setResult(new StringStruct(StringList.simpleStringsFormat(seq_.toString(), seps_)));
    }
    
    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        return new StringStruct(getInstance().toString());
    }

    @Override
    public StringStruct exportValue() {
        StringBuilder out_ = new StringBuilder();
        out_.append("\"");
        for (char c: getInstance().toString().toCharArray()) {
            if (c == '"' || c == '\\') {
                out_.append("\\");
                out_.append(c);
                continue;
            }
            if (c == 0) {
                out_.append("\\u0000");
                continue;
            }
            if (c < 16) {
                out_.append("\\u000");
                out_.append(Integer.toHexString(c));
                continue;
            }
            if (c < 31) {
                out_.append("\\u00");
                out_.append(Integer.toHexString(c));
                continue;
            }
            out_.append(c);
        }
        out_.append("\"");
        return new StringStruct(out_.toString());
    }

    @Override
    public abstract CharSequence getInstance();
}
