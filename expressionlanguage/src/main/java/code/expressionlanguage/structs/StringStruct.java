package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;

public final class StringStruct extends CharSequenceStruct {

    private final char[] instance;

    public StringStruct(String _instance) {
        this(_instance.toCharArray());
    }

    public StringStruct(char[] _instance) {
        instance = _instance;
    }

    public static void instantiate(Analyzable _cont, ResultErrorStd _res, ConstructorId _method, Struct... _args) {
        StringList list_ = _method.getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String bytePrimType_ = lgNames_.getAliasPrimByte();
        String charPrimType_ = lgNames_.getAliasPrimChar();
        String nbType_ = lgNames_.getAliasNumber();
        String stringType_ = lgNames_.getAliasString();
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
        }
    }
    private static void newStringStructByCharArray(Struct _arg, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_arg.isNull()) {
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
        _res.setResult(new StringStruct(arr_));
    }

    private static void newStringStructByByteArray(Struct _arg, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_arg.isNull()) {
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
    public static void calculate(Analyzable _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            ((StringStruct)_struct).calculate(_cont, _res, _method, _args);
            return;
        }
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String nbType_ = lgNames_.getAliasNumber();
        String stringType_ = lgNames_.getAliasString();
        String booleanPrimType_ = lgNames_.getAliasPrimBoolean();
        if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
            StringStruct first_ = (StringStruct) _args[0];
            first_.compareTo(_args[1], lgNames_, _res);
            return;
        }
    }
    private void calculate(Analyzable _cont, ResultErrorStd _res, ClassMethodId _method, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String nbType_ = lgNames_.getAliasNumber();
        String stringType_ = lgNames_.getAliasString();
        String booleanPrimType_ = lgNames_.getAliasPrimBoolean();
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
    }
    private void length(ResultErrorStd _res) {
        _res.setResult(new IntStruct(instance.length));
    }

    private void isEmpty(ResultErrorStd _res) {
        _res.setResult(new BooleanStruct(instance.length == 0));
    }

    private void charAt(Struct _index, LgNames _stds, ResultErrorStd _res) {
        NumberStruct nb_ = (NumberStruct)_index;
        int ind_ = nb_.getInstance().intValue();
        String badIndex_ = _stds.getAliasBadIndex();
        if (ind_ < 0 || ind_ >= instance.length) {
            _res.setError(badIndex_);
            return;
        }
        _res.setResult(new CharStruct(instance[ind_]));
    }

    private void getBytes(LgNames _stds, ResultErrorStd _res) {
        String bytePrim_ = _stds.getAliasPrimByte();
        Numbers<Byte> list_ = StringList.encodeList(instance);
        bytePrim_ = PrimitiveTypeUtil.getPrettyArrayType(bytePrim_);
        int len_ = list_.size();
        Struct[] strArr_ = new Struct[len_];
        for (int i = 0; i < len_; i++) {
            strArr_[i] = new ByteStruct(list_.get(i));
        }
        _res.setResult(new ArrayStruct(strArr_, bytePrim_));
    }

    private void compareTo(Struct _anotherString, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_anotherString.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct st_ = (StringStruct)_anotherString;
        int lenOne_ = instance.length;
        int lenTwo_ = st_.instance.length;
        int lim_ = Math.min(lenOne_, lenTwo_);
        for (int i = 0; i < lim_; i++) {
            char cOne_ = instance[i];
            char cTwo_ = st_.instance[i];
            if (cOne_ != cTwo_) {
                _res.setResult(new IntStruct(cOne_ - cTwo_));
                return;
            }
        }
        _res.setResult(new IntStruct(lenOne_ - lenTwo_));
    }

    private void regionMatches(NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
            NumberStruct _len, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_other.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct other_ = (StringStruct) _other;
        int comLen_ = _len.getInstance().intValue();
        int to_ = _toffset.getInstance().intValue();
        int po_ = _ooffset.getInstance().intValue();
        if (po_ < 0 || to_ < 0 || to_ > instance.length - comLen_
                || po_ > other_.instance.length - comLen_) {
            _res.setResult(new BooleanStruct(false));
            return;
        }
        while (comLen_ > 0) {
            comLen_--;
            if (instance[to_] != other_.instance[po_]) {
                _res.setResult(new BooleanStruct(false));
                return;
            }
            to_++;
            po_++;
        }
        _res.setResult(new BooleanStruct(true));
    }

    private void startsWith(Struct _prefix, LgNames _stds, ResultErrorStd _res) {
        startsWith(_prefix,new IntStruct(0), _stds, _res);
    }

    private void endsWith(Struct _suffix, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_suffix.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct suffix_ = (StringStruct) _suffix;
        startsWith(_suffix,new IntStruct(instance.length - suffix_.instance.length), _stds, _res);
    }
    private void startsWith(Struct _prefix, NumberStruct _toffset, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_prefix.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct pref_ = (StringStruct) _prefix;
        int to_ = _toffset.getInstance().intValue();
        int po_ = 0;
        int pLen_ = pref_.instance.length;
        if (to_ < 0 || to_ > instance.length - pLen_) {
            _res.setResult(new BooleanStruct(false));
            return;
        }
        pLen_--;
        while (pLen_ >= 0) {
            int ind_ = to_;
            int prefInd_ = po_;
            if (instance[ind_] != pref_.instance[prefInd_]) {
                _res.setResult(new BooleanStruct(false));
                return;
            }
            to_++;
            po_++;
            pLen_--;
        }
        _res.setResult(new BooleanStruct(true));
    }

    private void indexOf(Struct _ch, ResultErrorStd _res) {
        indexOf(_ch,new IntStruct(0),_res);
    }

    private void indexOf(Struct _ch, Struct _fromIndex, ResultErrorStd _res) {
        NumberStruct ch_ = (NumberStruct) _ch;
        int int_ = ch_.getInstance().intValue();
        NumberStruct index_ = (NumberStruct) _fromIndex;
        int from_ = index_.getInstance().intValue();
        if (from_ < 0) {
            from_ = 0;
        }
        int len_ = instance.length;
        for (int i = from_; i < len_; i++) {
            if (instance[i] == int_) {
                _res.setResult(new IntStruct(i));
                return;
            }
        }
        _res.setResult(new IntStruct(-1));
    }
    //getAliasFormat,replaceMult,getAliasSplit chars

    private void contains(Struct _str, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_str.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        CharSequenceStruct arg_ = (CharSequenceStruct) _str;
        arg_.toStringStruct(_stds, _res);
        StringStruct strRep_ = (StringStruct) _res.getResult();
        indexOfString(strRep_, _stds, _res);
        Struct resLoc_ = _res.getResult();
        if (resLoc_ instanceof NumberStruct) {
            int index_ = ((NumberStruct)resLoc_).getInstance().intValue();
            _res.setResult(new BooleanStruct(index_ > -1));
        }
    }

    private void indexOfString(Struct _str, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_str.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        indexOfString(_str,new IntStruct(0), _stds, _res);
    }

    private void indexOfString(Struct _str, NumberStruct _fromIndex, LgNames _stds, ResultErrorStd _res) {
        int from_ = _fromIndex.getInstance().intValue();
        String nullPe_ = _stds.getAliasNullPe();
        if (_str.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct str_ = (StringStruct) _str;
        _res.setResult(new IntStruct(indexOf(instance, 0, instance.length,
                str_.instance, 0, str_.instance.length, from_)));
    }
    static int indexOf(char[] _source, int _sourceOffset, int _sourceCount,
            char[] _target, int _targetOffset, int _targetCount,
            int _fromIndex) {
        int fromIndex_ = _fromIndex;
        if (fromIndex_ >= _sourceCount) {
            if (_targetCount == 0) {
                return _sourceCount;
            }
            return -1;
        }
        if (fromIndex_ < 0) {
            fromIndex_ = 0;
        }
        if (_targetCount == 0) {
            return fromIndex_;
        }

        char first_  = _target[_targetOffset];
        int max_ = _sourceOffset + _sourceCount - _targetCount;

        int i_ = _sourceOffset + fromIndex_;
        while (i_ <= max_) {
            if (_source[i_] != first_) {
                i_++;
                while (i_ <= max_) {
                    if (_source[i_] == first_) {
                        break;
                    }
                    i_++;
                    continue;
                }
            }
            if (i_ <= max_) {
                int j_ = i_ + 1;
                int end_ = j_ + _targetCount - 1;
                int k_ = _targetOffset + 1;
                while (j_ < end_ && _source[j_] == _target[k_]) {
                    j_++;
                    k_++;
                }
                if (j_ == end_) {
                    return i_ - _sourceOffset;
                }
            }
            i_++;
        }
        return -1;
    }

    private void lastIndexOf(Struct _ch, ResultErrorStd _res) {
        lastIndexOf(_ch, new IntStruct(instance.length),_res);
    }

    private void lastIndexOf(Struct _ch, Struct _fromIndex, ResultErrorStd _res) {
        NumberStruct ch_ = (NumberStruct) _ch;
        int int_ = ch_.getInstance().intValue();
        NumberStruct index_ = (NumberStruct) _fromIndex;
        int from_ = index_.getInstance().intValue();
        int len_ = instance.length;
        if (from_ >= len_) {
            from_ = len_ - 1;
        }
        for (int i = from_; i >= 0; i--) {
            if (instance[i] == int_) {
                _res.setResult(new IntStruct(i));
                return;
            }
        }
        _res.setResult(new IntStruct(-1));
    }

    private void lastIndexOfString(Struct _str, LgNames _stds, ResultErrorStd _res) {
        lastIndexOfString(_str,new IntStruct(instance.length), _stds, _res);
    }

    private void lastIndexOfString(Struct _str, NumberStruct _fromIndex, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_str.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct str_ = (StringStruct)_str;
        int from_ = _fromIndex.getInstance().intValue();
        _res.setResult(new IntStruct(lastIndexOf(instance, 0, instance.length,
                str_.instance, 0, str_.instance.length, from_)));
    }
    static int lastIndexOf(char[] _source, int _sourceOffset, int _sourceCount,
            char[] _target, int _targetOffset, int _targetCount,
            int _fromIndex) {
        int fromIndex_ = _fromIndex;
        int rightIndex = _sourceCount - _targetCount;
        if (fromIndex_ < 0) {
            return -1;
        }
        if (fromIndex_ > rightIndex) {
            fromIndex_ = rightIndex;
        }
        if (_targetCount == 0) {
            return fromIndex_;
        }

        int strLastIndex_ = _targetOffset + _targetCount - 1;
        char strLastChar_ = _target[strLastIndex_];
        int min_ = _sourceOffset + _targetCount - 1;
        int i_ = min_ + fromIndex_;
        while (true) {
            while (i_ >= min_ && _source[i_] != strLastChar_) {
                i_--;
            }
            if (i_ < min_) {
                return -1;
            }
            int j_ = i_ - 1;
            int start_ = j_ - (_targetCount - 1);
            int k_ = strLastIndex_ - 1;

            boolean goToBegin_ = false;
            while (j_ > start_) {
                int srcInd_ = j_;
                int targetInd_ = k_;
                if (_source[srcInd_] != _target[targetInd_]) {
                    i_--;
                    goToBegin_ = true;
                    break;
                }
                j_--;
                k_--;
            }
            if (goToBegin_) {
                continue;
            }
            return start_ - _sourceOffset + 1;
        }
    }
    private void substring(NumberStruct _beginIndex, LgNames _stds, ResultErrorStd _res) {
        substring(_beginIndex, new IntStruct(instance.length), _stds, _res);
    }

    private void substring(NumberStruct _beginIndex, NumberStruct _endIndex, LgNames _stds, ResultErrorStd _res) {
        int begin_ = _beginIndex.getInstance().intValue();
        int end_ = _endIndex.getInstance().intValue();
        if (begin_ < 0 || end_ < 0 || end_ > instance.length || begin_ > end_) {
            _res.setError(_stds.getAliasBadIndex());
            return;
        }
        if (begin_ == 0 && instance.length == end_) {
            _res.setResult(this);
            return;
        }
        int count_ = end_ - begin_;
        char[] res_ = new char[count_];
        for (int i = 0; i < count_; i++) {
            res_[i] = instance[i + begin_];
        }
        _res.setResult(new StringStruct(res_));
    }

    private void replace(CharStruct _oldChar, CharStruct _newChar, ResultErrorStd _res) {
        char oldChar_ = _oldChar.getChar();
        char newChar_ = _newChar.getChar();
        if (oldChar_ == newChar_) {
            _res.setResult(this);
            return;
        }
        int len_ = instance.length;
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            char cur_ = instance[i];
            if (cur_ == oldChar_) {
                arr_[i] = newChar_;
            } else {
                arr_[i] = cur_;
            }
        }
        _res.setResult(new StringStruct(arr_));
    }

    private void replaceString(Struct _oldChar, Struct _newChar, ResultErrorStd _res) {
        char[] old_;
        if (_oldChar instanceof StringStruct) {
            old_ = ((StringStruct)_oldChar).instance;
        } else {
            old_ = null;
        }
        char[] new_;
        if (_newChar instanceof StringStruct) {
            new_ = ((StringStruct)_newChar).instance;
        } else {
            new_ = null;
        }
        char[] out_ = replace(instance, old_, new_);
        _res.setResult(new StringStruct(out_));
    }
    private static char[] replace(char[] _string, char[] _old, char[] _new) {
        if (_old == null) {
            return _string;
        }
        if (_new == null) {
            if (_old.length == 0) {
                StringBuilder list_ = new StringBuilder();
                list_.append(_new);
                for (char c: _string) {
                    list_.append(c);
                }
                return list_.toString().toCharArray();
            }
            StringBuilder list_ = new StringBuilder();
            int i_ = 0;
            int len_ = _string.length;
            int index_ = -1;
            while (i_ < len_) {
                index_ = indexOf(_string, 0, _string.length,
                        _old, 0, _old.length, i_);
                if (index_ < 0) {
                    break;
                }
                int lenChars_ = index_ - i_;
                char[] toAppend_ = new char[lenChars_];
                for (int i = i_; i < index_; i++) {
                    toAppend_[i - i_] = _string[i];
                }
                list_.append(toAppend_);
                i_ = index_ + _old.length;
            }
            if (i_ <= len_) {
                int lenStr_ = _string.length;
                int lenChars_ = lenStr_ - i_;
                char[] toAppend_ = new char[lenChars_];
                for (int i = i_; i < lenStr_; i++) {
                    toAppend_[i - i_] = _string[i];
                }
                list_.append(toAppend_);
            }
            return list_.toString().toCharArray();
        }
        if (_old.length == 0) {
            StringBuilder list_ = new StringBuilder();
            list_.append(_new);
            for (char c: _string) {
                list_.append(c);
                list_.append(_new);
            }
            return list_.toString().toCharArray();
        }
        StringBuilder list_ = new StringBuilder();
        int i_ = 0;
        int len_ = _string.length;
        int index_ = -1;
        while (i_ < len_) {
            index_ = indexOf(_string, 0, _string.length,
                    _old, 0, _old.length, i_);
            if (index_ < 0) {
                break;
            }
            int lenChars_ = index_ - i_;
            char[] toAppend_ = new char[lenChars_];
            for (int i = i_; i < index_; i++) {
                toAppend_[i - i_] = _string[i];
            }
            list_.append(toAppend_);
            list_.append(_new);
            i_ = index_ + _old.length;
        }
        if (i_ <= len_) {
            int lenStr_ = _string.length;
            int lenChars_ = lenStr_ - i_;
            char[] toAppend_ = new char[lenChars_];
            for (int i = i_; i < lenStr_; i++) {
                toAppend_[i - i_] = _string[i];
            }
            list_.append(toAppend_);
        }
        return list_.toString().toCharArray();
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
        char[][] parts_ = splitChars(instance, ch_);
        int lenArr_ = parts_.length;
        if (lim_ >= 0) {
            lenArr_ = lim_;
        }
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_[i]);
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = PrimitiveTypeUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }
    private void splitChars(Struct _seps, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_seps.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        Struct[] arrStructSep_ = arrSep_.getInstance();
        int lenSeps_ = arrStructSep_.length;
        char[] seps_ = new char[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrStructSep_[i];
            if (curSep_.isNull()) {
                _res.setError(nullPe_);
                return;
            }
            seps_[i] = ((CharStruct)curSep_).getChar();
        }
        for (Struct s: arrStructSep_) {
            if (s.isNull()) {
                _res.setError(nullPe_);
                return;
            }
        }
        char[][] parts_ = splitChars(instance, seps_);
        int lenArr_ = parts_.length;
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_[i]);
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = PrimitiveTypeUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }
    private static char[][] splitChars(char[] _string, char... _separators) {
        CustList<char[]> l_ = new CustList<char[]>();
        int len_ = _string.length;
        StringBuilder str_ = new StringBuilder();
        for (int i = 0; i < len_; i++) {
            boolean isSep_ = false;
            char cur_ = _string[i];
            for (char s: _separators) {
                if (s == cur_) {
                    isSep_ = true;
                    break;
                }
            }
            if (isSep_) {
                l_.add(str_.toString().toCharArray());
                str_ = new StringBuilder();
            } else {
                str_.append(cur_);
            }
        }
        l_.add(str_.toString().toCharArray());
        int lenList_ = l_.size();
        char[][] res_ = new char[lenList_][];
        for (int i = 0; i <lenList_; i++) {
            res_[i] = l_.get(i);
        }
        return res_;
    }
    private void splitSingleString(Struct _sep, LgNames _stds, ResultErrorStd _res) {
        splitSingleString(_sep, new IntStruct(-1), _stds, _res);
    }
    private void splitStrings(Struct _seps, LgNames _stds, ResultErrorStd _res) {
        splitStrings(new IntStruct(-1), _seps, _stds, _res);
    }
    private void splitStrings(NumberStruct _lim, Struct _seps, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_seps.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        Struct[] arrStructSep_ = arrSep_.getInstance();
        int lenSeps_ = arrStructSep_.length;
        char[][] seps_ = new char[lenSeps_][];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrStructSep_[i];
            if (curSep_.isNull()) {
                _res.setError(nullPe_);
                return;
            }
            seps_[i] = ((StringStruct)curSep_).instance;
        }
        for (Struct s: arrStructSep_) {
            if (s.isNull()) {
                _res.setError(nullPe_);
                return;
            }
        }
        int lim_ = _lim.getInstance().intValue();
        if (lim_ < -1) {
            lim_ = -1;
        }
        char[][] parts_ = splitStrings(instance, seps_);
        int lenArr_ = parts_.length;
        if (lim_ >= 0) {
            lenArr_ = lim_;
        }
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_[i]);
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = PrimitiveTypeUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }
    private void splitSingleString(Struct _sep, NumberStruct _lim, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (_sep.isNull()) {
            _res.setError(nullPe_);
            return;
        }
        int lim_ = _lim.getInstance().intValue();
        if (lim_ < -1) {
            lim_ = -1;
        }
        StringStruct str_ = (StringStruct)_sep;
        char[][] parts_ = splitStrings(instance, str_.instance);
        int lenArr_ = parts_.length;
        if (lim_ >= 0) {
            lenArr_ = lim_;
        }
        Struct[] arr_ = new Struct[lenArr_];
        for (int i = 0; i < lenArr_; i++) {
            arr_[i] = new StringStruct(parts_[i]);
        }
        String aliasString_ = _stds.getAliasString();
        aliasString_ = PrimitiveTypeUtil.getPrettyArrayType(aliasString_);
        _res.setResult(new ArrayStruct(arr_, aliasString_));
    }
    
    private static char[][] splitStringsSep(char[] _string, char[]... _separators) {
        CustList<char[]> l_ = new CustList<char[]>();
        int len_ = _string.length;
        char[] sub_ = _string;
        int subLen_ = sub_.length;
        while (true) {
            char[] candidate_ = null;
            int minIndex_ = len_;
            int maxLength_ = 0;
            for (char[] s: _separators) {
                int sepLen_ = s.length;
                int locIndex_ = indexOf(sub_, 0, subLen_, s, 0, sepLen_, 0);
                if (locIndex_ < 0) {
                    continue;
                }
                if (locIndex_ > minIndex_) {
                    continue;
                }
                int locLength_ = s.length;
                if (locIndex_ < minIndex_) {
                    minIndex_ = locIndex_;
                    maxLength_ = locLength_;
                    candidate_ = s;
                    continue;
                }
                if (locLength_ > maxLength_) {
                    maxLength_ = locLength_;
                    candidate_ = s;
                }
            }
            if (candidate_ == null) {
                l_.add(sub_);
                break;
            }
            char[] part_ = new char[minIndex_];
            for (int i = 0; i < minIndex_; i++) {
                part_[i] = sub_[i];
            }
            l_.add(part_);
            l_.add(candidate_);
            int nextOffset_ = minIndex_ + maxLength_;
            int nextLength_ = subLen_ - nextOffset_;
            char[] next_ = new char[nextLength_];
            for (int i = nextOffset_; i < subLen_; i++) {
                next_[i-nextOffset_] = sub_[i];
            }
            sub_ = next_;
            subLen_ = sub_.length;
        }
        int lenList_ = l_.size();
        char[][] res_ = new char[lenList_][];
        for (int i = 0; i <lenList_; i++) {
            res_[i] = l_.get(i);
        }
        return res_;
    }
    private static char[][] splitStrings(char[] _string, char[]... _separators) {
        CustList<char[]> l_ = new CustList<char[]>();
        int len_ = _string.length;
        char[] sub_ = _string;
        int subLen_ = sub_.length;
        while (true) {
            char[] candidate_ = null;
            int minIndex_ = len_;
            int maxLength_ = 0;
            for (char[] s: _separators) {
                int sepLen_ = s.length;
                int locIndex_ = indexOf(sub_, 0, subLen_, s, 0, sepLen_, 0);
                if (locIndex_ < 0) {
                    continue;
                }
                if (locIndex_ > minIndex_) {
                    continue;
                }
                int locLength_ = s.length;
                if (locIndex_ < minIndex_) {
                    minIndex_ = locIndex_;
                    maxLength_ = locLength_;
                    candidate_ = s;
                    continue;
                }
                if (locLength_ > maxLength_) {
                    maxLength_ = locLength_;
                    candidate_ = s;
                }
            }
            if (candidate_ == null) {
                l_.add(sub_);
                break;
            }
            char[] part_ = new char[minIndex_];
            for (int i = 0; i < minIndex_; i++) {
                part_[i] = sub_[i];
            }
            l_.add(part_);
            int nextOffset_ = minIndex_ + maxLength_;
            int nextLength_ = subLen_ - nextOffset_;
            char[] next_ = new char[nextLength_];
            for (int i = nextOffset_; i < subLen_; i++) {
                next_[i-nextOffset_] = sub_[i];
            }
            sub_ = next_;
            subLen_ = sub_.length;
        }
        int lenList_ = l_.size();
        char[][] res_ = new char[lenList_][];
        for (int i = 0; i <lenList_; i++) {
            res_[i] = l_.get(i);
        }
        return res_;
    }
    private void trim(ResultErrorStd _res) {
        int len_ = instance.length;
        if (len_ == 0) {
            _res.setResult(new StringStruct(new char[0]));
            return;
        }
        int firstInclude_ = 0;
        int last_ = len_ - 1;
        while (firstInclude_ <= last_) {
            char cur_ = instance[firstInclude_];
            if (cur_ > ' ') {
                break;
            }
            firstInclude_++;
        }
        if (firstInclude_ > last_) {
            _res.setResult(new StringStruct(new char[0]));
            return;
        }
        while (true) {
            char cur_ = instance[last_];
            if (cur_ > ' ') {
                break;
            }
            last_--;
        }
        int lenTrim_ = last_ + 1 - firstInclude_;
        char[] tr_ = new char[lenTrim_];
        for (int i = firstInclude_; i <= last_; i++) {
            tr_[i - firstInclude_] = instance[i];
        }
        _res.setResult(new StringStruct(tr_));
    }

    private void toCharArray(LgNames _stds,ResultErrorStd _res) {
        String aliasChar_ = _stds.getAliasPrimChar();
        aliasChar_ = PrimitiveTypeUtil.getPrettyArrayType(aliasChar_);
        int len_ = instance.length;
        Struct[] arrOut_ = new Struct[len_];
        for (int i = 0; i <= len_; i++) {
            arrOut_[i] = new CharStruct(instance[i]);
        }
        ArrayStruct arr_ = new ArrayStruct(arrOut_,aliasChar_);
        _res.setResult(arr_);
    }
    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasString();
    }

    @Override
    public String getInstance() {
        return new String(instance);
    }

    @Override
    protected void toStringStruct(LgNames _stds, ResultErrorStd _res) {
        _res.setResult(this);
    }
    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public boolean isArray() {
        return false;
    }
}
