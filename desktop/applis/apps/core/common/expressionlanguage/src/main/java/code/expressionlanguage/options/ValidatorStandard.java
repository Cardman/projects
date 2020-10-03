package code.expressionlanguage.options;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.stds.ErrorCat;
import code.expressionlanguage.analyze.errors.stds.StdWordError;
import code.expressionlanguage.stds.AliasNumber;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

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
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyPrimitive(),key_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _page.addStdError(err_);
                continue;
            }
            if (_page.getKeyWords().isKeyWord(value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getPrimitiveKeyWord(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _page.addStdError(err_);
            }
            for (char c: value_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharPrimitive(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                    _page.addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(value_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstPrimitive(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
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
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicatePrimtive(),v_,e.getKey()));
                err_.setErrCat(ErrorCat.DUPLICATE_PRIMITIVE_WORD);
                _page.addStdError(err_);
            }
        }
    }

    public static void validateRefTypeContents(StringMap<String> _list, StringMap<String> _prims, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList allPkgs_ = new StringList();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String value_ = e.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyRefType(),key_));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _page.addStdError(err_);
                continue;
            }
            for (String p : StringList.splitChars(value_, '.')) {
                if (p.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyRefTypeIn(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _page.addStdError(err_);
                    continue;
                }
                if (StringList.contains(_prims.values(), p)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getRefTypePrimitive(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _page.addStdError(err_);
                }
                if (_page.getKeyWords().isKeyWord(p)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getRefTypeKeyWord(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _page.addStdError(err_);
                }
                for (char c: p.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharRefType(),key_,value_));
                        err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                        _page.addStdError(err_);
                        break;
                    }
                }
                if (StringExpUtil.isDigit(p.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstRefType(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _page.addStdError(err_);
                }
            }
            String pkg_ = StandardType.getPackagePart(value_);
            if (pkg_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyPkgRefType(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _page.addStdError(err_);
            }
            allPkgs_.add(pkg_);
        }
        boolean exNonEmpty_ = false;
        for (String p: allPkgs_) {
            if (StringList.quickEq(_page.getDefaultPkg(), p)) {
                exNonEmpty_ = true;
            }
        }
        if (!exNonEmpty_) {
            //ERROR
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getDefaultPkgNoMatch(), _page.getDefaultPkg()));
            err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
            _page.addStdError(err_);
        }
        for (String k: _list.values()) {
            if (_page.getDefaultPkg().contains(k)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDefaultPkgRefType(),k, _page.getDefaultPkg()));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _page.addStdError(err_);
            }
        }
    }

    public static boolean validateRefTypeDuplicates(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        boolean dup_ = false;
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateRefType(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_TYPE_WORD);
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

    private static void checkContent(StringMap<String> _prims, CustList<KeyValueMemberName> list_, String _key, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (KeyValueMemberName f: list_) {
            String key_ = f.getKey();
            String value_ = f.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyMethod(),key_, _key));
                err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                _page.addStdError(err_);
                continue;
            }
            if (_page.getKeyWords().isKeyWordNotVar(value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getMethodKeyWord(),value_,key_, _key));
                err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                _page.addStdError(err_);
            }
            if (StringList.contains(_prims.values(), value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getMethodPrimitive(),value_,key_, _key));
                err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                _page.addStdError(err_);
            }
            for (char c: value_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharMethod(),value_, _key,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _page.addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(value_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstMethod(),value_, _key,Character.toString(value_.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
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
    private static void checkDuplicates(CustList<KeyValueMemberName> value_, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList keyWords_ = new StringList();
        for (KeyValueMemberName f: value_) {
            keyWords_.add(f.getValue());
        }
        if (keyWords_.hasDuplicates()) {
            for (KeyValueMemberName f: value_) {
                String v_ = f.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateMethod(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_METHOD_WORD);
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
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateMergedMethod(),v_,f.getKey()));
                    err_.setErrCat(ErrorCat.DUPLICATE_METHOD_WORD);
                    _page.addStdError(err_);
                }
            }
        }
    }

    public static void validateFieldsContents(StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims, AnalyzedPageEl _page){
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                String key_ = f.getKey();
                String value_ = f.getValue();
                if (value_.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyField(),key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _page.addStdError(err_);
                    continue;
                }
                if (_page.getKeyWords().isKeyWord(value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getFieldKeyWord(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _page.addStdError(err_);
                }
                if (StringList.contains(_prims.values(), value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getFieldPrimitive(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _page.addStdError(err_);
                }
                for (char c: value_.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharField(),value_,e.getKey(),key_));
                        err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                        _page.addStdError(err_);
                        break;
                    }
                }
                if (StringExpUtil.isDigit(value_.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstField(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _page.addStdError(err_);
                }
            }
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
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateField(),v_,e.getKey()));
                    err_.setErrCat(ErrorCat.DUPLICATE_FIELD_WORD);
                    _page.addStdError(err_);
                }
            }
        }
    }

    public static void validateVarTypesContents(StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims, AnalyzedPageEl _page){
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                String key_ = f.getKey();
                String value_ = f.getValue();
                if (value_.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyVarType(),key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _page.addStdError(err_);
                    continue;
                }
                if (_page.getKeyWords().isKeyWord(value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getVarTypeKeyWord(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _page.addStdError(err_);
                }
                if (StringList.contains(_prims.values(), value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getVarTypePrimitive(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _page.addStdError(err_);
                }
                for (char c: value_.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharVarType(),value_,e.getKey(),key_));
                        err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                        _page.addStdError(err_);
                        break;
                    }
                }
                if (StringExpUtil.isDigit(value_.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstVarType(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _page.addStdError(err_);
                }
            }
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
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateVarType(),v_,e.getKey()));
                    err_.setErrCat(ErrorCat.DUPLICATE_VAR_TYPE_WORD);
                    _page.addStdError(err_);
                }
            }
        }
    }

    public static void setupOverrides(AnalyzedPageEl _page) {
        StringList pkgs_ = new StringList();
        StringList pkgsBase_ = new StringList();
        AnalyzedPageEl page_ = _page;
        for (StandardType r: page_.getStandardsTypes().values()) {
            String pkg_ = r.getPackageName();
            int until_ = Math.max(0, pkg_.indexOf('.'));
            pkgsBase_.add(pkg_.substring(0,until_));
            StringBuilder id_ = new StringBuilder();
            for (String p: StringList.splitChars(pkg_, '.')) {
                id_.append(p);
                pkgs_.add(id_.toString());
                id_.append('.');
            }
        }
        pkgs_.removeDuplicates();
        pkgsBase_.removeDuplicates();
        page_.getHeaders().getPackagesFound().addAllElts(pkgs_);
        page_.getHeaders().getBasePackagesFound().addAllElts(pkgsBase_);
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

    public static String checkExactType(int _loc, String _in, String _orig, AnalyzedPageEl _page) {
        if (!_in.isEmpty()) {
            return _in;
        }
        int rc_ = _page.getLocalizer().getCurrentLocationIndex() + _loc;
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFileName(_page.getLocalizer().getCurrentFileName());
        un_.setIndexFile(rc_);
        //original type len
        un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                _orig);
        _page.getLocalizer().addError(un_);
        return _page.getAliasObject();
    }

    public static Struct getSimpleResultBase(ClassField _classField, AliasNumber _nbAlias) {
        String type_ = _classField.getClassName();
        String name_ = _classField.getFieldName();
        String charType_ = _nbAlias.getAliasCharacter();
        String byteType_ = _nbAlias.getAliasByte();
        String shortType_ = _nbAlias.getAliasShort();
        String intType_ = _nbAlias.getAliasInteger();
        String longType_ = _nbAlias.getAliasLong();
        String floatType_ = _nbAlias.getAliasFloat();
        if (StringList.quickEq(type_, charType_)) {
            if (StringList.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new CharStruct(Character.MIN_VALUE));
            } else {
                return(new CharStruct(Character.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, byteType_)) {
            if (StringList.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new ByteStruct(Byte.MIN_VALUE));
            } else {
                return(new ByteStruct(Byte.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, shortType_)) {
            if (StringList.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new ShortStruct(Short.MIN_VALUE));
            } else {
                return(new ShortStruct(Short.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, intType_)) {
            if (StringList.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new IntStruct(Integer.MIN_VALUE));
            } else {
                return(new IntStruct(Integer.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, longType_)) {
            if (StringList.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new LongStruct(Long.MIN_VALUE));
            } else {
                return(new LongStruct(Long.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, floatType_)) {
            if (StringList.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return(new FloatStruct(Float.MIN_VALUE));
            } else if (StringList.quickEq(name_, _nbAlias.getAliasMinusInfinityField())) {
                return(new FloatStruct(Float.NEGATIVE_INFINITY));
            } else if (StringList.quickEq(name_, _nbAlias.getAliasPlusInfinityField())) {
                return(new FloatStruct(Float.POSITIVE_INFINITY));
            } else if (StringList.quickEq(name_, _nbAlias.getAliasNanField())) {
                return(new FloatStruct(Float.NaN));
            } else {
                return(new FloatStruct(Float.MAX_VALUE));
            }
        } else {
            if (StringList.quickEq(name_, _nbAlias.getAliasMinValueField())) {
                return (new DoubleStruct(Double.MIN_VALUE));
            } else if (StringList.quickEq(name_, _nbAlias.getAliasMinusInfinityField())) {
                return (new DoubleStruct(Double.NEGATIVE_INFINITY));
            } else if (StringList.quickEq(name_, _nbAlias.getAliasPlusInfinityField())) {
                return (new DoubleStruct(Double.POSITIVE_INFINITY));
            } else if (StringList.quickEq(name_, _nbAlias.getAliasNanField())) {
                return (new DoubleStruct(Double.NaN));
            } else {
                return (new DoubleStruct(Double.MAX_VALUE));
            }
        }
    }

    public static String tr(StringList _list) {
        String candidate_ = tr(_list,"tmp");
        _list.add(candidate_);
        return candidate_;
    }

    public static String tr(CustList<String> _list, String _var) {
        String candidate_ = _var;
        int index_ = 0;
        while (StringList.contains(_list,candidate_)) {
            candidate_ = StringList.concatNbs(_var,index_);
            index_++;
        }
        return candidate_;
    }
}
