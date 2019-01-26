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

    public static void instantiate(LgNames _stds, ResultErrorStd _res, ConstructorId _method, Struct... _args) {
        StringList list_ = _method.getParametersTypes();
        String bytePrimType_ = _stds.getAliasPrimByte();
        String charPrimType_ = _stds.getAliasPrimChar();
        if (list_.size() == 0) {
            newStringStruct(_res);
            return;
        }
        if (list_.size() == 1) {
            String arrBytes_ = PrimitiveTypeUtil.getPrettyArrayType(bytePrimType_);
            if (StringList.quickEq(list_.first(), arrBytes_)) {
                newStringStructByByteArray(_args[0], _stds, _res);
                return;
            }
            String arrChars_ = PrimitiveTypeUtil.getPrettyArrayType(charPrimType_);
            if (StringList.quickEq(list_.first(), arrChars_)) {
                newStringStructByCharArray(_args[0], _stds, _res);
                return;
            }
            newStringBuilderStruct(_args[0], _stds, _res);
            return;
        }
        String arrBytes_ = PrimitiveTypeUtil.getPrettyArrayType(bytePrimType_);
        if (StringList.quickEq(list_.first(), arrBytes_)) {
            newStringStructByByteArray(_args[0], _args[1], _args[2], _stds, _res);
            return;
        }
        newStringStructByCharArray(_args[0], _args[1], _args[2], _stds, _res);
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
            if (one_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(one_),"<0"));
            } else if (two_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(two_),"<0"));
            } else {
                _res.setErrorMessage(StringList.concat(Long.toString(one_ + two_),">", Long.toString(arr_.length)));
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
            arr_[i] = ((NumberStruct)argArr_[i]).getInstance().byteValue();
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
            if (one_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(one_),"<0"));
            } else if (two_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(two_),"<0"));
            } else {
                _res.setErrorMessage(StringList.concat(Long.toString(one_ + two_),">", Long.toString(arr_.length)));
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
        int one_ = ((NumberStruct)_args[1]).getInstance().intValue();
        int two_ = ((NumberStruct)_args[2]).getInstance().intValue();
        if (one_ < 0 || two_ < 0 || one_ + two_ > arr_.length) {
            if (one_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(one_),"<0"));
            } else if (two_ < 0) {
                _res.setErrorMessage(StringList.concat(Long.toString(two_),"<0"));
            } else {
                _res.setErrorMessage(StringList.concat(Long.toString(one_ + two_),">", Long.toString(arr_.length)));
            }
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
        if (StringList.quickEq(name_, lgNames_.getAliasRegionMatches())) {
            regionMatches((BooleanStruct) _args[0],(NumberStruct)_args[1], _args[2], (NumberStruct)_args[3], (NumberStruct)_args[4], lgNames_, _res);
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
        if (StringList.quickEq(name_, lgNames_.getAliasReplaceMultiple())) {
            replaceMultiple(_args[0], lgNames_, _res);
            return;
        }
        String one_ = getInstance();
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
                _res.setResult(new BooleanStruct(false));
            } else {
                StringStruct t_ = (StringStruct) two_;
                _res.setResult(new BooleanStruct(one_.equalsIgnoreCase(t_.getInstance())));
            }
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
            _res.setResult(new StringStruct(StringList.toLowerCase(one_)));
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasToUpperCase())) {
            _res.setResult(new StringStruct(StringList.toUpperCase(one_)));
        }
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

    private void regionMatches(BooleanStruct _case,NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
            NumberStruct _len, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_other instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        boolean case_ = _case.getInstance();
        StringStruct other_ = (StringStruct) _other;
        int comLen_ = _len.getInstance().intValue();
        int to_ = _toffset.getInstance().intValue();
        int po_ = _ooffset.getInstance().intValue();
        _res.setResult(new BooleanStruct(instance.regionMatches(case_,to_, other_.instance, po_, comLen_)));
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
            if (seps_[i].getNewString() == null) {
                _res.setError(nullPe_);
                return;
            }
            if (seps_[i].getOldString() == null) {
                _res.setError(nullPe_);
                return;
            }
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
