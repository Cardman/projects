package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.Classes;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.stds.ErrorCat;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.exec.opers.ExecDotOperation;
import code.expressionlanguage.exec.opers.ExecFctOperation;
import code.expressionlanguage.exec.opers.ExecInternVariableOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ValidatorStandard {
    private ValidatorStandard() {
    }

    public static void validatePrimitiveContents(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String value_ = e.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyPrimitive(),key_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            if (_cont.getKeyWords().isKeyWord(value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getPrimitiveKeyWord(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
            }
            for (char c: value_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharPrimitive(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                    _cont.getClasses().addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(value_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstPrimitive(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }

    public static void validatePrimitiveDuplicates(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicatePrimtive(),v_,e.getKey()));
                err_.setErrCat(ErrorCat.DUPLICATE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }

    public static void validateRefTypeContents(ContextEl _cont, StringMap<String> _list, StringMap<String> _prims) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        LgNames stds_ = _cont.getStandards();
        StringList allPkgs_ = new StringList();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String value_ = e.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyRefType(),key_));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            for (String p : StringList.splitChars(value_, '.')) {
                if (p.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyRefTypeIn(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (StringList.contains(_prims.values(), p)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getRefTypePrimitive(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (_cont.getKeyWords().isKeyWord(p)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getRefTypeKeyWord(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: p.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharRefType(),key_,value_));
                        err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (StringExpUtil.isDigit(p.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstRefType(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
            String pkg_ = StandardType.getPackagePart(value_);
            if (pkg_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyPkgRefType(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
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
            _cont.getClasses().addStdError(err_);
        }
        for (String k: _list.values()) {
            if (stds_.getDefaultPkg().contains(k)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDefaultPkgRefType(),k, stds_.getDefaultPkg()));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }

    public static boolean validateRefTypeDuplicates(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        boolean dup_ = false;
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateRefType(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
                dup_ = true;
            }
        }
        return dup_;
    }

    public static void validateMethodsContents(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                String key_ = f.getKey();
                String value_ = f.getValue();
                if (value_.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyMethod(),key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (_cont.getKeyWords().isKeyWordNotVar(value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getMethodKeyWord(),value_,key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (StringList.contains(_prims.values(), value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getMethodPrimitive(),value_,key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: value_.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharMethod(),value_,e.getKey(),Character.toString(c)));
                        err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (StringExpUtil.isDigit(value_.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstMethod(),value_,e.getKey(),Character.toString(value_.charAt(0))));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }

    public static void validateMethodsDuplicates(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            StringList keyWords_ = new StringList();
            for (KeyValueMemberName f: e.getValue()) {
                keyWords_.add(f.getValue());
            }
            if (keyWords_.hasDuplicates()) {
                for (KeyValueMemberName f: e.getValue()) {
                    String v_ = f.getValue();
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateMethod(),v_));
                    err_.setErrCat(ErrorCat.DUPLICATE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }

    public static void validateMergedDuplicates(ContextEl _cont, CustList<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
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
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }

    public static void validateFieldsContents(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                String key_ = f.getKey();
                String value_ = f.getValue();
                if (value_.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyField(),key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (_cont.getKeyWords().isKeyWord(value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getFieldKeyWord(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (StringList.contains(_prims.values(), value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getFieldPrimitive(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: value_.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharField(),value_,e.getKey(),key_));
                        err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (StringExpUtil.isDigit(value_.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstField(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }

    public static void validateFieldsDuplicates(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
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
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }

    public static void validateVarTypesContents(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                String key_ = f.getKey();
                String value_ = f.getValue();
                if (value_.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyVarType(),key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (_cont.getKeyWords().isKeyWord(value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getVarTypeKeyWord(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (StringList.contains(_prims.values(), value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getVarTypePrimitive(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: value_.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharVarType(),value_,e.getKey(),key_));
                        err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (StringExpUtil.isDigit(value_.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstVarType(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }

    public static void validateVarTypesDuplicates(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
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
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }

    public static void setupOverrides(ContextEl _cont) {
        StringList pkgs_ = new StringList();
        for (StandardType r: _cont.getStandards().getStandards().values()) {
            String pkg_ = r.getPackageName();
            StringBuilder id_ = new StringBuilder();
            for (String p: StringList.splitChars(pkg_, '.')) {
                id_.append(p);
                pkgs_.add(id_.toString());
                id_.append('.');
            }
        }
        pkgs_.removeDuplicates();
        _cont.getClasses().getPackagesFound().addAllElts(pkgs_);
        _cont.setAnalyzing();
        buildInherits(_cont);
    }

    public static void buildInherits(ContextEl _context){
        for (EntryCust<String, StandardType> s: _context.getStandards().getStandards().entryList()) {
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
                StandardType st_ = _context.getStandards().getStandards().getVal(c);
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
        int rc_ = _an.getAnalyzing().getLocalizer().getCurrentLocationIndex() + _loc;
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFileName(_an.getAnalyzing().getLocalizer().getCurrentFileName());
        un_.setIndexFile(rc_);
        //original type len
        un_.buildError(_an.getAnalysisMessages().getUnknownType(),
                _orig);
        _an.getAnalyzing().getLocalizer().addError(un_);
        return _an.getStandards().getAliasObject();
    }

    public static IterableAnalysisResult getCustomTypeBase(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        StringMap<StringList> vars_ = _context.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        LgNames stds_ = _context.getStandards();
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
        LgNames stds_ = _context.getStandards();
        StringMap<StringList> vars_ = _context.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
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

    public static ResultErrorStd getSimpleResultBase(ContextEl _conf, ClassField _classField) {
        ResultErrorStd result_ = new ResultErrorStd();
        String type_ = _classField.getClassName();
        String name_ = _classField.getFieldName();
        LgNames lgNames_ = _conf.getStandards();
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

    public static void buildIterable(ContextEl _context) {
        //local names
        LgNames stds_ = _context.getStandards();
        _context.getAnalyzing().setCurrentBlock(null);
        _context.getAnalyzing().setCurrentAnaBlock(null);
        Classes cl_ = _context.getClasses();
        String next_ = stds_.getAliasNext();
        String hasNext_ = stds_.getAliasHasNext();
        String nextPair_ = stds_.getAliasNextPair();
        String hasNextPair_ = stds_.getAliasHasNextPair();
        StringList l_ = new StringList();
        String locName_ = tr(l_,_context);
        cl_.setIteratorVarCust(locName_);
        String iterator_ = stds_.getAliasIterator();
        cl_.setExpsIteratorCust(newCall(cl_.getIteratorVarCust(),StringList.concat(stds_.getAliasIterable(),"<?>"),
                new ClassMethodId(stds_.getAliasIterable(),new MethodId(MethodAccessKind.INSTANCE,iterator_, new StringList())),
                StringList.concat(stds_.getAliasIteratorType(),"<?>")));
        locName_ = tr(l_,_context);
        cl_.setHasNextVarCust(locName_);
        cl_.setExpsHasNextCust(newCall(cl_.getHasNextVarCust(),StringList.concat(stds_.getAliasIteratorType(),"<?>"),
                new ClassMethodId(stds_.getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,hasNext_, new StringList())),
                stds_.getAliasPrimBoolean()));
        locName_ = tr(l_,_context);
        cl_.setNextVarCust(locName_);
        cl_.setExpsNextCust(newCall(cl_.getNextVarCust(),StringList.concat(stds_.getAliasIteratorType(),"<?>"),
                new ClassMethodId(stds_.getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,next_, new StringList())),
                stds_.getAliasObject()));

        cl_.setIteratorTableVarCust(locName_);
        String iteratorTable_ = stds_.getAliasIteratorTable();
        cl_.setExpsIteratorTableCust(newCall(cl_.getIteratorTableVarCust(),StringList.concat(stds_.getAliasIterableTable(),"<?,?>"),
                new ClassMethodId(stds_.getAliasIterableTable(),new MethodId(MethodAccessKind.INSTANCE,iteratorTable_, new StringList())),
                StringList.concat(stds_.getAliasIteratorTableType(),"<?,?>")));
        locName_ = tr(l_,_context);
        cl_.setHasNextPairVarCust(locName_);
        cl_.setExpsHasNextPairCust(newCall(cl_.getHasNextPairVarCust(),StringList.concat(stds_.getAliasIteratorTableType(),"<?,?>"),
                new ClassMethodId(stds_.getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,hasNextPair_, new StringList())),
                stds_.getAliasPrimBoolean()));
        locName_ = tr(l_,_context);
        cl_.setNextPairVarCust(locName_);
        cl_.setExpsNextPairCust(newCall(cl_.getNextPairVarCust(),StringList.concat(stds_.getAliasIteratorTableType(),"<?,?>"),
                new ClassMethodId(stds_.getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,nextPair_, new StringList())),
                StringList.concat(stds_.getAliasPairType(),"<?,?>")));
        locName_ = tr(l_,_context);
        cl_.setFirstVarCust(locName_);
        String first_ = stds_.getAliasGetFirst();
        cl_.setExpsFirstCust(newCall(cl_.getFirstVarCust(),StringList.concat(stds_.getAliasPairType(),"<?,?>"),
                new ClassMethodId(stds_.getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,first_, new StringList())),
                stds_.getAliasObject()));
        locName_ = tr(l_,_context);
        cl_.setSecondVarCust(locName_);
        String second_ = stds_.getAliasGetSecond();
        cl_.setExpsSecondCust(newCall(cl_.getSecondVarCust(),StringList.concat(stds_.getAliasPairType(),"<?,?>"),
                new ClassMethodId(stds_.getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,second_, new StringList())),
                stds_.getAliasObject()));
    }

    private static CustList<ExecOperationNode> newCall(String _varPrevious, String _previous,
                                                       ClassMethodId _id,
                                                       String _res) {
        CustList<ExecOperationNode> ops_ = new CustList<ExecOperationNode>();
        ExecDotOperation dot_ = new ExecDotOperation(0,new ClassArgumentMatching(_res),2);
        ExecInternVariableOperation r_ = new ExecInternVariableOperation(0,new ClassArgumentMatching(_previous),0,_varPrevious);
        ops_.add(r_);
        dot_.appendChild(r_);
        ExecFctOperation f_ = new ExecFctOperation(new ClassArgumentMatching(_res),_id,1,1);
        dot_.appendChild(f_);
        r_.setSiblingSet(f_);
        ops_.add(f_);
        ops_.add(dot_);
        return ops_;
    }

    public static String tr(StringList _list, ContextEl _context) {
        CustList<String> allKeysWords_ = _context.getKeyWords().allKeyWords().values();
        allKeysWords_.addAllElts(_context.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_context.getStandards().getAliasVoid());
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
