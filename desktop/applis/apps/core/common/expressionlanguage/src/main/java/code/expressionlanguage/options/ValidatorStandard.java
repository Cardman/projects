package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.stds.ErrorCat;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecDotOperation;
import code.expressionlanguage.exec.opers.ExecFctOperation;
import code.expressionlanguage.exec.opers.ExecInternVariableOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ValidatorStandard {
    private ValidatorStandard() {
    }

    public static void validatePrimitiveContents(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String value_ = e.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyPrimitive(),key_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getAnalyzing().addStdError(err_);
                continue;
            }
            if (_cont.getAnalyzing().getKeyWords().isKeyWord(value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getPrimitiveKeyWord(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getAnalyzing().addStdError(err_);
            }
            for (char c: value_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharPrimitive(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(value_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstPrimitive(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getAnalyzing().addStdError(err_);
            }
        }
    }

    public static void validatePrimitiveDuplicates(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicatePrimtive(),v_,e.getKey()));
                err_.setErrCat(ErrorCat.DUPLICATE_PRIMITIVE_WORD);
                _cont.getAnalyzing().addStdError(err_);
            }
        }
    }

    public static void validateRefTypeContents(ContextEl _cont, StringMap<String> _list, StringMap<String> _prims) {
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
        LgNames stds_ = _cont.getAnalyzing().getStandards();
        StringList allPkgs_ = new StringList();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String value_ = e.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyRefType(),key_));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getAnalyzing().addStdError(err_);
                continue;
            }
            for (String p : StringList.splitChars(value_, '.')) {
                if (p.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyRefTypeIn(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                    continue;
                }
                if (StringList.contains(_prims.values(), p)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getRefTypePrimitive(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                }
                if (_cont.getAnalyzing().getKeyWords().isKeyWord(p)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getRefTypeKeyWord(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                }
                for (char c: p.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharRefType(),key_,value_));
                        err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                        _cont.getAnalyzing().addStdError(err_);
                        break;
                    }
                }
                if (StringExpUtil.isDigit(p.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstRefType(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                }
            }
            String pkg_ = StandardType.getPackagePart(value_);
            if (pkg_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyPkgRefType(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getAnalyzing().addStdError(err_);
            }
            allPkgs_.add(pkg_);
        }
        boolean exNonEmpty_ = false;
        for (String p: allPkgs_) {
            if (StringList.quickEq(stds_.getDefaultPkg(), p)) {
                exNonEmpty_ = true;
            }
        }
        if (!exNonEmpty_) {
            //ERROR
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getDefaultPkgNoMatch(), stds_.getDefaultPkg()));
            err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
            _cont.getAnalyzing().addStdError(err_);
        }
        for (String k: _list.values()) {
            if (stds_.getDefaultPkg().contains(k)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDefaultPkgRefType(),k, stds_.getDefaultPkg()));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getAnalyzing().addStdError(err_);
            }
        }
    }

    public static boolean validateRefTypeDuplicates(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        boolean dup_ = false;
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateRefType(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_TYPE_WORD);
                _cont.getAnalyzing().addStdError(err_);
                dup_ = true;
            }
        }
        return dup_;
    }

    public static void validateMethodsContents(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims){
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            CustList<KeyValueMemberName> list_ = e.getValue();
            checkContent(_cont, _prims, list_, e.getKey());
        }
    }

    public static void validateParamtersContents(ContextEl _cont, CustList<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims){
        for (CustList<KeyValueMemberName> e: _methods) {
            checkContent(_cont,_prims,e,"");
        }
    }

    private static void checkContent(ContextEl _cont, StringMap<String> _prims, CustList<KeyValueMemberName> list_, String _key) {
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
        for (KeyValueMemberName f: list_) {
            String key_ = f.getKey();
            String value_ = f.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyMethod(),key_, _key));
                err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                _cont.getAnalyzing().addStdError(err_);
                continue;
            }
            if (_cont.getAnalyzing().getKeyWords().isKeyWordNotVar(value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getMethodKeyWord(),value_,key_, _key));
                err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                _cont.getAnalyzing().addStdError(err_);
            }
            if (StringList.contains(_prims.values(), value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getMethodPrimitive(),value_,key_, _key));
                err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                _cont.getAnalyzing().addStdError(err_);
            }
            for (char c: value_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharMethod(),value_, _key,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(value_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstMethod(),value_, _key,Character.toString(value_.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                _cont.getAnalyzing().addStdError(err_);
            }
        }
    }
    public static void validateMethodsDuplicates(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods){
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            CustList<KeyValueMemberName> value_ = e.getValue();
            checkDuplicates(_cont, value_);
        }
    }
    public static void validateParamtersDuplicates(ContextEl _cont, CustList<CustList<KeyValueMemberName>> _methods){
        for (CustList<KeyValueMemberName> e: _methods) {
            checkDuplicates(_cont, e);
        }
    }
    private static void checkDuplicates(ContextEl _cont, CustList<KeyValueMemberName> value_) {
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
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
                _cont.getAnalyzing().addStdError(err_);
            }
        }
    }

    public static void validateMergedDuplicates(ContextEl _cont, CustList<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
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
                    _cont.getAnalyzing().addStdError(err_);
                }
            }
        }
    }

    public static void validateFieldsContents(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims){
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                String key_ = f.getKey();
                String value_ = f.getValue();
                if (value_.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyField(),key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                    continue;
                }
                if (_cont.getAnalyzing().getKeyWords().isKeyWord(value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getFieldKeyWord(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                }
                if (StringList.contains(_prims.values(), value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getFieldPrimitive(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                }
                for (char c: value_.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharField(),value_,e.getKey(),key_));
                        err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                        _cont.getAnalyzing().addStdError(err_);
                        break;
                    }
                }
                if (StringExpUtil.isDigit(value_.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstField(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                }
            }
        }
    }

    public static void validateFieldsDuplicates(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
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
                    _cont.getAnalyzing().addStdError(err_);
                }
            }
        }
    }

    public static void validateVarTypesContents(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims){
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                String key_ = f.getKey();
                String value_ = f.getValue();
                if (value_.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyVarType(),key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                    continue;
                }
                if (_cont.getAnalyzing().getKeyWords().isKeyWord(value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getVarTypeKeyWord(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                }
                if (StringList.contains(_prims.values(), value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getVarTypePrimitive(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                }
                for (char c: value_.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharVarType(),value_,e.getKey(),key_));
                        err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                        _cont.getAnalyzing().addStdError(err_);
                        break;
                    }
                }
                if (StringExpUtil.isDigit(value_.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstVarType(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getAnalyzing().addStdError(err_);
                }
            }
        }
    }

    public static void validateVarTypesDuplicates(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalyzing().getAnalysisMessages();
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
                    _cont.getAnalyzing().addStdError(err_);
                }
            }
        }
    }

    public static void setupOverrides(ContextEl _cont) {
        StringList pkgs_ = new StringList();
        StringList pkgsBase_ = new StringList();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        for (StandardType r: page_.getStandards().getStandards().values()) {
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
        buildInherits(_cont);
    }

    public static void buildInherits(ContextEl _context){
        for (EntryCust<String, StandardType> s: _context.getAnalyzing().getStandards().getStandards().entryList()) {
            buildInherits(s.getValue(), _context);
        }
    }

    private static void buildInherits(StandardType _type, ContextEl _context) {
        feedSupers(_type,_context,_type.getAllSuperTypes());
    }

    private static void feedSupers(StandardType _type, ContextEl _context, StringList _types) {
        StringList currentSuperTypes_ = new StringList(_type.getDirectSuperTypes());
        _types.addAllElts(currentSuperTypes_);
        while (true) {
            StringList newSuperTypes_ = new StringList();
            for (String c: currentSuperTypes_) {
                StandardType st_ = _context.getAnalyzing().getStandards().getStandards().getVal(c);
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

    public static String checkExactType(ContextEl _an, int _loc, String _in, String _orig) {
        if (!_in.isEmpty()) {
            return _in;
        }
        AnalyzedPageEl page_ = _an.getAnalyzing();
        int rc_ = page_.getLocalizer().getCurrentLocationIndex() + _loc;
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFileName(page_.getLocalizer().getCurrentFileName());
        un_.setIndexFile(rc_);
        //original type len
        un_.buildError(_an.getAnalyzing().getAnalysisMessages().getUnknownType(),
                _orig);
        page_.getLocalizer().addError(un_);
        return page_.getStandards().getAliasObject();
    }

    public static IterableAnalysisResult getCustomTypeBase(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        StringMap<StringList> vars_ = page_.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        LgNames stds_ = page_.getStandards();
        for (String f: _names) {
            String iterable_ = stds_.getAliasIterable();
            String type_ = AnaTemplates.getGeneric(f,iterable_,_context,mapping_);
            if (!type_.isEmpty()) {
                out_.add(type_);
            }
        }
        return new IterableAnalysisResult(out_);
    }

    public static IterableAnalysisResult getCustomTableType(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        StringMap<StringList> vars_ = page_.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        for (String f: _names) {
            String iterable_ = stds_.getAliasIterableTable();
            String type_ = AnaTemplates.getGeneric(f,iterable_,_context,mapping_);
            if (!type_.isEmpty()) {
                out_.add(type_);
            }
        }
        return new IterableAnalysisResult(out_);
    }

    public static ResultErrorStd getSimpleResultBase(AnalyzedPageEl _conf, ClassField _classField) {
        LgNames lgNames_ = _conf.getStandards();
        return getSimpleResultBase(_classField, lgNames_);
    }

    public static ResultErrorStd getSimpleResultBase(ClassField _classField, LgNames lgNames_) {
        ResultErrorStd result_ = new ResultErrorStd();
        String type_ = _classField.getClassName();
        String name_ = _classField.getFieldName();
        String charType_ = lgNames_.getAliasCharacter();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        if (StringList.quickEq(type_, charType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new CharStruct(Character.MIN_VALUE));
            } else {
                result_.setResult(new CharStruct(Character.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, byteType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new ByteStruct(Byte.MIN_VALUE));
            } else {
                result_.setResult(new ByteStruct(Byte.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, shortType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new ShortStruct(Short.MIN_VALUE));
            } else {
                result_.setResult(new ShortStruct(Short.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, intType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new IntStruct(Integer.MIN_VALUE));
            } else {
                result_.setResult(new IntStruct(Integer.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, longType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new LongStruct(Long.MIN_VALUE));
            } else {
                result_.setResult(new LongStruct(Long.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, floatType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new FloatStruct(Float.MIN_VALUE));
            } else if (StringList.quickEq(name_, lgNames_.getAliasMinusInfinityField())) {
                result_.setResult(new FloatStruct(Float.NEGATIVE_INFINITY));
            } else if (StringList.quickEq(name_, lgNames_.getAliasPlusInfinityField())) {
                result_.setResult(new FloatStruct(Float.POSITIVE_INFINITY));
            } else if (StringList.quickEq(name_, lgNames_.getAliasNanField())) {
                result_.setResult(new FloatStruct(Float.NaN));
            } else {
                result_.setResult(new FloatStruct(Float.MAX_VALUE));
            }
        } else {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new DoubleStruct(Double.MIN_VALUE));
            } else if (StringList.quickEq(name_, lgNames_.getAliasMinusInfinityField())) {
                result_.setResult(new DoubleStruct(Double.NEGATIVE_INFINITY));
            } else if (StringList.quickEq(name_, lgNames_.getAliasPlusInfinityField())) {
                result_.setResult(new DoubleStruct(Double.POSITIVE_INFINITY));
            } else if (StringList.quickEq(name_, lgNames_.getAliasNanField())) {
                result_.setResult(new DoubleStruct(Double.NaN));
            } else {
                result_.setResult(new DoubleStruct(Double.MAX_VALUE));
            }
        }
        return result_;
    }

    public static void buildIterable(AnalyzedPageEl analyzing) {
        //local names
        AnalyzedPageEl page_ = analyzing;
        LgNames stds_ = page_.getStandards();
        page_.setCurrentBlock(null);
        page_.setCurrentAnaBlock(null);
        Classes cl_ = page_.getClasses();
        String next_ = stds_.getAliasNext();
        String hasNext_ = stds_.getAliasHasNext();
        String nextPair_ = stds_.getAliasNextPair();
        String hasNextPair_ = stds_.getAliasHasNextPair();
        StringList l_ = new StringList();
        String locName_ = tr(l_, analyzing);
        cl_.setIteratorVarCust(locName_);
        String iterator_ = stds_.getAliasIterator();
        cl_.setExpsIteratorCust(newCall(cl_.getIteratorVarCust(),StringList.concat(stds_.getAliasIterable(),"<?>"),
                new ClassMethodId(stds_.getAliasIterable(),new MethodId(MethodAccessKind.INSTANCE,iterator_, new StringList())),
                StringList.concat(stds_.getAliasIteratorType(),"<?>"), analyzing));
        locName_ = tr(l_, analyzing);
        cl_.setHasNextVarCust(locName_);
        cl_.setExpsHasNextCust(newCall(cl_.getHasNextVarCust(),StringList.concat(stds_.getAliasIteratorType(),"<?>"),
                new ClassMethodId(stds_.getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,hasNext_, new StringList())),
                stds_.getAliasPrimBoolean(), analyzing));
        locName_ = tr(l_, analyzing);
        cl_.setNextVarCust(locName_);
        cl_.setExpsNextCust(newCall(cl_.getNextVarCust(),StringList.concat(stds_.getAliasIteratorType(),"<?>"),
                new ClassMethodId(stds_.getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,next_, new StringList())),
                stds_.getAliasObject(), analyzing));

        cl_.setIteratorTableVarCust(locName_);
        String iteratorTable_ = stds_.getAliasIteratorTable();
        cl_.setExpsIteratorTableCust(newCall(cl_.getIteratorTableVarCust(),StringList.concat(stds_.getAliasIterableTable(),"<?,?>"),
                new ClassMethodId(stds_.getAliasIterableTable(),new MethodId(MethodAccessKind.INSTANCE,iteratorTable_, new StringList())),
                StringList.concat(stds_.getAliasIteratorTableType(),"<?,?>"), analyzing));
        locName_ = tr(l_, analyzing);
        cl_.setHasNextPairVarCust(locName_);
        cl_.setExpsHasNextPairCust(newCall(cl_.getHasNextPairVarCust(),StringList.concat(stds_.getAliasIteratorTableType(),"<?,?>"),
                new ClassMethodId(stds_.getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,hasNextPair_, new StringList())),
                stds_.getAliasPrimBoolean(), analyzing));
        locName_ = tr(l_, analyzing);
        cl_.setNextPairVarCust(locName_);
        cl_.setExpsNextPairCust(newCall(cl_.getNextPairVarCust(),StringList.concat(stds_.getAliasIteratorTableType(),"<?,?>"),
                new ClassMethodId(stds_.getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,nextPair_, new StringList())),
                StringList.concat(stds_.getAliasPairType(),"<?,?>"), analyzing));
        locName_ = tr(l_, analyzing);
        cl_.setFirstVarCust(locName_);
        String first_ = stds_.getAliasGetFirst();
        cl_.setExpsFirstCust(newCall(cl_.getFirstVarCust(),StringList.concat(stds_.getAliasPairType(),"<?,?>"),
                new ClassMethodId(stds_.getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,first_, new StringList())),
                stds_.getAliasObject(), analyzing));
        locName_ = tr(l_, analyzing);
        cl_.setSecondVarCust(locName_);
        String second_ = stds_.getAliasGetSecond();
        cl_.setExpsSecondCust(newCall(cl_.getSecondVarCust(),StringList.concat(stds_.getAliasPairType(),"<?,?>"),
                new ClassMethodId(stds_.getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,second_, new StringList())),
                stds_.getAliasObject(), analyzing));
        String id_ = StringExpUtil.getIdFromAllTypes(stds_.getAliasSeedDoubleGenerator());
        ExecRootBlock classBody_ = cl_.getClassBody(id_);
        cl_.setSeedDoubleGenerator(classBody_);
        String nameToCall_ = stds_.getAliasSeedGet();
        MethodId idMet_ = new MethodId(MethodAccessKind.INSTANCE, nameToCall_, new StringList());
        ExecNamedFunctionBlock fct_ = ExecBlock.getMethodBodiesById(classBody_, idMet_).first();
        cl_.setSeedDoublePick(fct_);
        id_ = StringExpUtil.getIdFromAllTypes(stds_.getAliasSeedGenerator());
        classBody_ = cl_.getClassBody(id_);
        cl_.setSeedGenerator(classBody_);
        idMet_ = new MethodId(MethodAccessKind.INSTANCE, nameToCall_, new StringList(stds_.getAliasPrimLong()));
        fct_ = ExecBlock.getMethodBodiesById(classBody_, idMet_).first();
        cl_.setSeedPick(fct_);
    }

    private static CustList<ExecOperationNode> newCall(String _varPrevious, String _previous,
                                                       ClassMethodId _id,
                                                       String _res, AnalyzedPageEl _analyzing) {
        CustList<ExecOperationNode> ops_ = new CustList<ExecOperationNode>();
        ExecDotOperation dot_ = new ExecDotOperation(0,new ExecClassArgumentMatching(_res),2);
        ExecInternVariableOperation r_ = new ExecInternVariableOperation(0,new ExecClassArgumentMatching(_previous),0,_varPrevious);
        ops_.add(r_);
        dot_.appendChild(r_);
        String id_ = StringExpUtil.getIdFromAllTypes(_id.getClassName());
        ExecRootBlock classBody_ = _analyzing.getClasses().getClassBody(id_);
        ExecNamedFunctionBlock fct_ = ExecBlock.getMethodBodiesById(classBody_, _id.getConstraints()).first();
        ExecFctOperation f_ = new ExecFctOperation(new ExecClassArgumentMatching(_res),_id,1,1,fct_,classBody_);
        dot_.appendChild(f_);
        r_.setSiblingSet(f_);
        ops_.add(f_);
        ops_.add(dot_);
        return ops_;
    }

    public static String tr(StringList _list, AnalyzedPageEl _analyzing) {
        CustList<String> allKeysWords_ = _analyzing.getKeyWords().allKeyWords().values();
        allKeysWords_.addAllElts(_analyzing.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_analyzing.getStandards().getAliasVoid());
        allKeysWords_.addAllElts(_list);
        String candidate_ = "tmp";
        int index_ = 0;
        while (StringList.contains(allKeysWords_,candidate_)) {
            candidate_ = StringList.concatNbs("tmp",index_);
            index_++;
        }
        _list.add(candidate_);
        return candidate_;
    }
}
