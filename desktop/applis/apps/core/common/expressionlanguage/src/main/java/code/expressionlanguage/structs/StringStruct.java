package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.Replacement;
import code.util.StringList;

public final class StringStruct extends CharSequenceStruct {

    private final String instance;

    public StringStruct(String _instance) {
        instance = _instance;
    }

    public boolean sameReference(Struct _other) {
        if (!(_other instanceof StringStruct)) {
            return false;
        }
        return sameEq(this, _other);
    }
    public static void instantiate(LgNames _stds, ResultErrorStd _res, ConstructorId _method, Struct... _args) {
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
        StringBuilderStruct arg_ = ApplyCoreMethodUtil.getStrBuilder(_arg);
        _res.setResult(new StringStruct(arg_.getInstance().toString()));
    }

    public static void calculateString(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            AnaApplyCoreMethodUtil.getString(_struct).calculateLocString(_cont, _res, _method, _args);
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
            first_.compareToString(_args[1], lgNames_, _res);
            return;
        }
        Struct arg_ = getArg(list_, _args);
        if (isDisplay(list_, arg_)) {
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

    private static boolean isDisplay(StringList list_, Struct arg_) {
        return list_.size() == 1 && arg_ instanceof DisplayableStruct;
    }

    public static Struct calculateString(ContextEl _cont, ClassMethodId _method, Struct _struct, Struct... _args) {
        if (!_method.getConstraints().isStaticMethod()) {
            return AnaApplyCoreMethodUtil.getString(_struct).calculateLocString(_cont, _method, _args);
        }
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
            Struct arg_ = _args[0];
            if (!(arg_ instanceof StringStruct)) {
                return null;
            }
            StringStruct first_ = (StringStruct) arg_;
            return first_.compareToString(_args[1]);
        }
        Struct arg_ = getArg(list_, _args);
        if (isDisplay(list_, arg_)) {
            return ExecCatOperation.getDisplayable(new Argument(arg_), _cont).getDisplayedString(_cont);
        }
        if (!(arg_ instanceof ArrayStruct)) {
            return null;
        }
        return tryGetCharArray(list_, (ArrayStruct) arg_, _args);
    }

    private static Struct getArg(StringList list_, Struct[] _args) {
        Struct arg_;
        if (list_.size() == 1) {
            arg_ = _args[0];
        } else {
            arg_ = _args[2];
        }
        return arg_;
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
        if (okArray(arr_, one_, two_)) {
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
    private static Struct tryGetCharArray(StringList _list, ArrayStruct _arg, Struct[] _args) {
        Struct[] argArr_ = _arg.getInstance();
        int len_ = argArr_.length;
        char[] arr_ = new char[len_];
        for (int i = 0; i < len_; i++) {
            arr_[i] = ClassArgumentMatching.convertToChar(argArr_[i]).getChar();
        }
        if (_list.size() == 1) {
            return new StringStruct(String.valueOf(arr_));
        }
        int one_ = (ClassArgumentMatching.convertToNumber(_args[0])).intStruct();
        int two_ = (ClassArgumentMatching.convertToNumber(_args[1])).intStruct();
        if (okArray(arr_, one_, two_)) {
            return null;
        }
        return new StringStruct(String.valueOf(arr_,one_,two_));
    }

    private static boolean okArray(char[] arr_, int one_, int two_) {
        return one_ < 0 || two_ < 0 || one_ + two_ > arr_.length;
    }

    private void calculateLocString(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String stringType_ = lgNames_.getAliasString();
        if (StringList.quickEq(name_, lgNames_.getAliasRegionMatches())) {
            regionMatches(ClassArgumentMatching.convertToBoolean(_args[0]),ClassArgumentMatching.convertToNumber(_args[1]), _args[2],
                    ClassArgumentMatching.convertToNumber(_args[3]), ClassArgumentMatching.convertToNumber(_args[4]), lgNames_, _res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplace())) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                replaceString(_args[0], _args[1], _res);
                return;
            }
            replace(ClassArgumentMatching.convertToChar(_args[0]), ClassArgumentMatching.convertToChar(_args[1]), _res);
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

    private Struct calculateLocString(ContextEl _cont, ClassMethodId _method, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        String stringType_ = lgNames_.getAliasString();
        if (StringList.quickEq(name_, lgNames_.getAliasRegionMatches())) {
            return regionMatches(ClassArgumentMatching.convertToBoolean(_args[0]),ClassArgumentMatching.convertToNumber(_args[1]), _args[2],
                    ClassArgumentMatching.convertToNumber(_args[3]), ClassArgumentMatching.convertToNumber(_args[4]));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplace())) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                return replaceString(_args[0], _args[1]);
            }
            return replace(ClassArgumentMatching.convertToChar(_args[0]), ClassArgumentMatching.convertToChar(_args[1]));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasReplaceMultiple())) {
            return replaceMultiple(_args[0]);
        }
        String one_ = getInstance();
        if (StringList.quickEq(name_, lgNames_.getAliasCompareToIgnoreCase())) {
            Struct two_ = _args[0];
            if (!(two_ instanceof StringStruct)) {
                return null;
            }
            StringStruct t_ = (StringStruct) two_;
            return new IntStruct(one_.compareToIgnoreCase(t_.getInstance()));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasEqualsIgnoreCase())) {
            Struct two_ = _args[0];
            if (!(two_ instanceof StringStruct)) {
                return BooleanStruct.of(false);
            }
            StringStruct t_ = (StringStruct) two_;
            return BooleanStruct.of(one_.equalsIgnoreCase(t_.getInstance()));
        }
        if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
            return new StringStruct(StringList.toLowerCase(one_));
        }
        return new StringStruct(StringList.toUpperCase(one_));
    }

    private void compareToString(Struct _anotherString, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_anotherString instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct st_ = (StringStruct)_anotherString;
        _res.setResult(new IntStruct(instance.compareTo(st_.instance)));
    }

    private Struct compareToString(Struct _anotherString) {
        if (!(_anotherString instanceof StringStruct)) {
            return null;
        }
        StringStruct st_ = (StringStruct)_anotherString;
        return new IntStruct(instance.compareTo(st_.instance));
    }
    private void regionMatches(BooleanStruct _case,NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
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
        _res.setResult(BooleanStruct.of(instance.regionMatches(case_,to_, other_.instance, po_, comLen_)));
    }

    private Struct regionMatches(BooleanStruct _case, NumberStruct _toffset, Struct _other, NumberStruct _ooffset,
                                 NumberStruct _len) {
        if (!(_other instanceof StringStruct)) {
            return null;
        }
        boolean case_ = BooleanStruct.isTrue(_case);
        StringStruct other_ = (StringStruct) _other;
        int comLen_ = _len.intStruct();
        int to_ = _toffset.intStruct();
        int po_ = _ooffset.intStruct();
        return BooleanStruct.of(instance.regionMatches(case_,to_, other_.instance, po_, comLen_));
    }

    private void replace(CharStruct _oldChar, CharStruct _newChar, ResultErrorStd _res) {
        char oldChar_ = _oldChar.getChar();
        char newChar_ = _newChar.getChar();
        _res.setResult(new StringStruct(instance.replace(oldChar_, newChar_)));
    }

    private Struct replace(CharStruct _oldChar, CharStruct _newChar) {
        char oldChar_ = _oldChar.getChar();
        char newChar_ = _newChar.getChar();
        return new StringStruct(instance.replace(oldChar_, newChar_));
    }
    private void replaceString(Struct _oldChar, Struct _newChar, ResultErrorStd _res) {
        String old_;
        old_ = getString(_oldChar);
        String new_;
        new_ = getString(_newChar);
        String out_ = StringList.replace(instance, old_, new_);
        _res.setResult(new StringStruct(out_));
    }

    private Struct replaceString(Struct _oldChar, Struct _newChar) {
        String old_;
        old_ = getString(_oldChar);
        String new_;
        new_ = getString(_newChar);
        String out_ = StringList.replace(instance, old_, new_);
        return new StringStruct(out_);
    }

    private static String getString(Struct _oldChar) {
        String old_;
        if (_oldChar instanceof StringStruct) {
            old_ = ((StringStruct)_oldChar).instance;
        } else {
            old_ = null;
        }
        return old_;
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
            seps_[i] = ApplyCoreMethodUtil.getReplacement(curSep_).getInstance();
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

    private Struct replaceMultiple(Struct _seps) {
        if (!(_seps instanceof ArrayStruct)) {
            return null;
        }
        ArrayStruct arrSep_ = (ArrayStruct) _seps;
        Struct[] arrStructSep_ = arrSep_.getInstance();
        int lenSeps_ = arrStructSep_.length;
        Replacement[] seps_ = new Replacement[lenSeps_];
        for (int i = 0; i < lenSeps_; i++) {
            Struct curSep_ = arrStructSep_[i];
            if (!(curSep_ instanceof ReplacementStruct)) {
                return null;
            }
            seps_[i] = ApplyCoreMethodUtil.getReplacement(curSep_).getInstance();
            if (seps_[i].getNewString() == null) {
                return null;
            }
            if (seps_[i].getOldString() == null) {
                return null;
            }
        }
        return new StringStruct(StringList.replaceMult(instance, seps_));
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getAliasString();
    }

    public String getInstance() {
        return instance;
    }

    @Override
    public int length() {
        return instance.length();
    }
    @Override
    public char charAt(int _i) {
        return instance.charAt(_i);
    }

    @Override
    public String toStringInstance() {
        return instance;
    }

    @Override
    public String substring(int _i, int _j) {
        return instance.substring(_i,_j);
    }
}
