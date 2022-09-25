package code.expressionlanguage.options;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.analyze.errors.stds.StdWordError;
import code.expressionlanguage.stds.AliasNumberType;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ValidatorStandard {
    private ValidatorStandard() {
    }

    public static void validatePrimitiveContents(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String value_ = e.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyPrimitive(),key_));
                _page.addStdError(err_);
                continue;
            }
            if (_page.getKeyWords().isKeyWord(value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getPrimitiveKeyWord(),key_,value_));
                _page.addStdError(err_);
            }
            for (char c: value_.toCharArray()) {
                if (!StringExpUtil.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordCharPrimitive(),key_,value_));
                    _page.addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(value_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirstPrimitive(),key_,value_));
                _page.addStdError(err_);
            }
        }
    }

    public static void validatePrimitiveDuplicates(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicatePrimtive(),v_,e.getKey()));
                _page.addStdError(err_);
            }
        }
    }

    public static void validateRefTypeContents(StringMap<String> _list, StringMap<String> _prims, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList allPkgs_ = new StringList();
        for (EntryCust<String,String> e: _list.entryList()) {
            validateRefTypeContent(_prims, _page, allPkgs_, e);
        }
        boolean exNonEmpty_ = false;
        for (String p: allPkgs_) {
            if (StringUtil.quickEq(_page.getDefaultPkg(), p)) {
                exNonEmpty_ = true;
            }
        }
        if (!exNonEmpty_) {
            //ERROR
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getDefaultPkgNoMatch(), _page.getDefaultPkg()));
            _page.addStdError(err_);
        }
        for (String k: _list.values()) {
            if (_page.getDefaultPkg().contains(k)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDefaultPkgRefType(),k, _page.getDefaultPkg()));
                _page.addStdError(err_);
            }
        }
    }

    private static void validateRefTypeContent(StringMap<String> _prims, AnalyzedPageEl _page, StringList _allPkgs, EntryCust<String, String> _e) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        String key_ = _e.getKey();
        String value_ = _e.getValue();
        if (value_.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyRefType(),key_));
            _page.addStdError(err_);
            return;
        }
        for (String p : StringUtil.splitChars(value_, '.')) {
            if (p.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyRefTypeIn(),key_,value_));
                _page.addStdError(err_);
                continue;
            }
            if (StringUtil.contains(_prims.values(), p)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getRefTypePrimitive(),key_,value_));
                _page.addStdError(err_);
            }
            if (_page.getKeyWords().isKeyWord(p)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getRefTypeKeyWord(),key_,value_));
                _page.addStdError(err_);
            }
            for (char c: p.toCharArray()) {
                if (!StringExpUtil.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordCharRefType(),key_,value_));
                    _page.addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(p.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirstRefType(),key_,value_));
                _page.addStdError(err_);
            }
        }
        checkPkg(_page, _allPkgs, key_, value_);
    }

    private static void checkPkg(AnalyzedPageEl _page, StringList _allPkgs, String _key, String _value) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        String pkg_ = StandardType.getPackagePart(_value);
        if (pkg_.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyPkgRefType(), _key, _value));
            _page.addStdError(err_);
        }
        _allPkgs.add(pkg_);
    }

    public static boolean validateRefTypeDuplicates(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        boolean dup_ = false;
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateRefType(),v_));
                _page.addStdError(err_);
                dup_ = true;
            }
        }
        return dup_;
    }

    public static void validateMethodsContents(StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims, AnalyzedPageEl _page){
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            CustList<KeyValueMemberName> list_ = e.getValue();
            checkContent(_prims, list_, e.getKey(), _page);
        }
    }

    public static void validateParamtersContents(CustList<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims, AnalyzedPageEl _page){
        for (CustList<KeyValueMemberName> e: _methods) {
            checkContent(_prims,e,"", _page);
        }
    }

    private static void checkContent(StringMap<String> _prims, CustList<KeyValueMemberName> _list, String _key, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (KeyValueMemberName f: _list) {
            String key_ = f.getKey();
            String value_ = f.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyMethod(),key_, _key));
                _page.addStdError(err_);
                continue;
            }
            if (_page.getKeyWords().isKeyWordNotVar(value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getMethodKeyWord(),value_,key_, _key));
                _page.addStdError(err_);
            }
            if (StringUtil.contains(_prims.values(), value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getMethodPrimitive(),value_,key_, _key));
                _page.addStdError(err_);
            }
            for (char c: value_.toCharArray()) {
                if (!StringExpUtil.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordCharMethod(),value_, _key,Character.toString(c)));
                    _page.addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(value_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirstMethod(),value_, _key,Character.toString(value_.charAt(0))));
                _page.addStdError(err_);
            }
        }
    }
    public static void validateMethodsDuplicates(StringMap<CustList<KeyValueMemberName>> _methods, AnalyzedPageEl _page){
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            CustList<KeyValueMemberName> value_ = e.getValue();
            checkDuplicates(value_, _page);
        }
    }
    public static void validateParamtersDuplicates(CustList<CustList<KeyValueMemberName>> _methods, AnalyzedPageEl _page){
        for (CustList<KeyValueMemberName> e: _methods) {
            checkDuplicates(e, _page);
        }
    }
    private static void checkDuplicates(CustList<KeyValueMemberName> _value, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList keyWords_ = new StringList();
        for (KeyValueMemberName f: _value) {
            keyWords_.add(f.getValue());
        }
        if (keyWords_.hasDuplicates()) {
            for (KeyValueMemberName f: _value) {
                String v_ = f.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateMethod(),v_));
                _page.addStdError(err_);
            }
        }
    }

    public static void validateMergedDuplicates(CustList<CustList<KeyValueMemberName>> _methods, AnalyzedPageEl _page){
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (CustList<KeyValueMemberName> e: _methods) {
            StringList keyWords_ = new StringList();
            for (KeyValueMemberName f: e) {
                keyWords_.add(f.getValue());
            }
            if (keyWords_.hasDuplicates()) {
                for (KeyValueMemberName f: e) {
                    String v_ = f.getValue();
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateMergedMethod(),v_,f.getKey()));
                    _page.addStdError(err_);
                }
            }
        }
    }

    public static void validateFieldsContents(StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims, AnalyzedPageEl _page){
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                validateFieldContent(_prims, _page, e, f);
            }
        }
    }

    private static void validateFieldContent(StringMap<String> _prims, AnalyzedPageEl _page, EntryCust<String, CustList<KeyValueMemberName>> _list, KeyValueMemberName _e) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        String key_ = _e.getKey();
        String value_ = _e.getValue();
        if (value_.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyField(),key_, _list.getKey()));
            _page.addStdError(err_);
            return;
        }
        if (_page.getKeyWords().isKeyWord(value_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getFieldKeyWord(),value_, _list.getKey(),key_));
            _page.addStdError(err_);
        }
        if (StringUtil.contains(_prims.values(), value_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getFieldPrimitive(),value_, _list.getKey(),key_));
            _page.addStdError(err_);
        }
        for (char c: value_.toCharArray()) {
            if (!StringExpUtil.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordCharField(),value_, _list.getKey(),key_));
                _page.addStdError(err_);
                break;
            }
        }
        if (StringExpUtil.isDigit(value_.charAt(0))) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirstField(),value_, _list.getKey(),key_));
            _page.addStdError(err_);
        }
    }

    public static void validateFieldsDuplicates(StringMap<CustList<KeyValueMemberName>> _methods, AnalyzedPageEl _page){
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            StringList keyWords_ = new StringList();
            for (KeyValueMemberName f: e.getValue()) {
                keyWords_.add(f.getValue());
            }
            if (keyWords_.hasDuplicates()) {
                for (KeyValueMemberName f: e.getValue()) {
                    String v_ = f.getValue();
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateField(),v_,e.getKey()));
                    _page.addStdError(err_);
                }
            }
        }
    }

    public static void validateVarTypesContents(StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims, AnalyzedPageEl _page){
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                validateVarTypeContent(_prims, _page, e, f);
            }
        }
    }

    private static void validateVarTypeContent(StringMap<String> _prims, AnalyzedPageEl _page, EntryCust<String, CustList<KeyValueMemberName>> _list, KeyValueMemberName _e) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        String key_ = _e.getKey();
        String value_ = _e.getValue();
        if (value_.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyVarType(),key_, _list.getKey()));
            _page.addStdError(err_);
            return;
        }
        if (_page.getKeyWords().isKeyWord(value_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getVarTypeKeyWord(),value_, _list.getKey(),key_));
            _page.addStdError(err_);
        }
        if (StringUtil.contains(_prims.values(), value_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getVarTypePrimitive(),value_, _list.getKey(),key_));
            _page.addStdError(err_);
        }
        for (char c: value_.toCharArray()) {
            if (!StringExpUtil.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordCharVarType(),value_, _list.getKey(),key_));
                _page.addStdError(err_);
                break;
            }
        }
        if (StringExpUtil.isDigit(value_.charAt(0))) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirstVarType(),value_, _list.getKey(),key_));
            _page.addStdError(err_);
        }
    }

    public static void validateVarTypesDuplicates(StringMap<CustList<KeyValueMemberName>> _methods, AnalyzedPageEl _page){
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            StringList keyWords_ = new StringList();
            for (KeyValueMemberName f: e.getValue()) {
                keyWords_.add(f.getValue());
            }
            if (keyWords_.hasDuplicates()) {
                for (KeyValueMemberName f: e.getValue()) {
                    String v_ = f.getValue();
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateVarType(),v_,e.getKey()));
                    _page.addStdError(err_);
                }
            }
        }
    }

    public static void setupOverrides(AnalyzedPageEl _page) {
        StringList pkgs_ = new StringList();
        StringList pkgsBase_ = new StringList();
        for (StandardType r: _page.getStandardsTypes().values()) {
            String pkg_ = r.getPackageName();
            int until_ = NumberUtil.max(0, pkg_.indexOf('.'));
            pkgsBase_.add(pkg_.substring(0,until_));
            StringBuilder id_ = new StringBuilder();
            for (String p: StringUtil.splitChars(pkg_, '.')) {
                id_.append(p);
                pkgs_.add(id_.toString());
                id_.append('.');
            }
        }
        pkgs_.removeDuplicates();
        pkgsBase_.removeDuplicates();
        _page.getHeaders().getPackagesFound().addAllElts(pkgs_);
        _page.getHeaders().getBasePackagesFound().addAllElts(pkgsBase_);
        buildInherits(_page);
    }

    public static void buildInherits(AnalyzedPageEl _page){
        for (EntryCust<String, StandardType> s: _page.getStandardsTypes().entryList()) {
            buildInherits(s.getValue(), _page);
        }
    }

    private static void buildInherits(StandardType _type, AnalyzedPageEl _page) {
        feedSupers(_type, _type.getAllSuperTypes(), _page);
    }

    private static void feedSupers(StandardType _type, StringList _types, AnalyzedPageEl _page) {
        StringList currentSuperTypes_ = new StringList(_type.getDirectSuperTypes());
        _types.addAllElts(currentSuperTypes_);
        while (true) {
            StringList newSuperTypes_ = new StringList();
            for (String c: currentSuperTypes_) {
                StandardType st_ = _page.getStandardsTypes().getVal(c);
                for (String s: st_.getDirectSuperTypes()) {
                    newSuperTypes_.add(s);
                    _types.add(s);
                }
            }
            if (newSuperTypes_.isEmpty()) {
                break;
            }
            currentSuperTypes_ = newSuperTypes_;
        }
        _types.removeDuplicates();
    }

    public static Struct getSimpleResultBase(ClassField _classField, AliasNumberType _nbAlias) {
        String type_ = _classField.getClassName();
        String name_ = _classField.getFieldName();
        String charType_ = _nbAlias.getAliasCharacter();
        String byteType_ = _nbAlias.getAliasByte();
        String shortType_ = _nbAlias.getAliasShort();
        String intType_ = _nbAlias.getAliasInteger();
        String longType_ = _nbAlias.getAliasLong();
        if (StringUtil.quickEq(type_, charType_)) {
            if (StringUtil.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new CharStruct(Character.MIN_VALUE));
            }
            return(new CharStruct(Character.MAX_VALUE));
        }
        if (StringUtil.quickEq(type_, byteType_)) {
            if (StringUtil.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new ByteStruct(Byte.MIN_VALUE));
            }
            return(new ByteStruct(Byte.MAX_VALUE));
        }
        if (StringUtil.quickEq(type_, shortType_)) {
            if (StringUtil.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new ShortStruct(Short.MIN_VALUE));
            }
            return(new ShortStruct(Short.MAX_VALUE));
        }
        if (StringUtil.quickEq(type_, intType_)) {
            if (StringUtil.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new IntStruct(Integer.MIN_VALUE));
            }
            return(new IntStruct(Integer.MAX_VALUE));
        }
        if (StringUtil.quickEq(type_, longType_)) {
            if (StringUtil.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new LongStruct(Long.MIN_VALUE));
            }
            return(new LongStruct(Long.MAX_VALUE));
        }
        return getAbsRealNumberStruct(_nbAlias, type_, name_);
    }

    private static AbsRealNumberStruct getAbsRealNumberStruct(AliasNumberType _nbAlias, String _type, String _name) {
        String floatType_ = _nbAlias.getAliasFloat();
        if (StringUtil.quickEq(_type, floatType_)) {
            return fl(_nbAlias, _name);
        }
        return db(_nbAlias, _name);
    }

    private static FloatStruct fl(AliasNumberType _nbAlias, String _name) {
        if (StringUtil.quickEq(_name, _nbAlias.getAliasMinValueField())) {
            return (new FloatStruct(Float.MIN_VALUE));
        }
        if (StringUtil.quickEq(_name, _nbAlias.getAliasMinusInfinityField())) {
            return (new FloatStruct(Float.NEGATIVE_INFINITY));
        }
        if (StringUtil.quickEq(_name, _nbAlias.getAliasPlusInfinityField())) {
            return (new FloatStruct(Float.POSITIVE_INFINITY));
        }
        if (StringUtil.quickEq(_name, _nbAlias.getAliasNanField())) {
            return (new FloatStruct(Float.NaN));
        }
        return (new FloatStruct(Float.MAX_VALUE));
    }

    private static DoubleStruct db(AliasNumberType _nbAlias, String _name) {
        if (StringUtil.quickEq(_name, _nbAlias.getAliasMinValueField())) {
            return (new DoubleStruct(Double.MIN_VALUE));
        }
        if (StringUtil.quickEq(_name, _nbAlias.getAliasMinusInfinityField())) {
            return (new DoubleStruct(Double.NEGATIVE_INFINITY));
        }
        if (StringUtil.quickEq(_name, _nbAlias.getAliasPlusInfinityField())) {
            return (new DoubleStruct(Double.POSITIVE_INFINITY));
        }
        if (StringUtil.quickEq(_name, _nbAlias.getAliasNanField())) {
            return (new DoubleStruct(Double.NaN));
        }
        return (new DoubleStruct(Double.MAX_VALUE));
    }

    public static String tr(StringList _list) {
        String candidate_ = tr(_list,"tmp");
        _list.add(candidate_);
        return candidate_;
    }

    public static String tr(CustList<String> _list, String _var) {
        String candidate_ = _var;
        int index_ = 0;
        while (StringUtil.contains(_list,candidate_)) {
            candidate_ = StringUtil.concatNbs(_var,index_);
            index_++;
        }
        return candidate_;
    }
}
