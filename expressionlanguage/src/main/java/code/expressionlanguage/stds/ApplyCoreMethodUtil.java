package code.expressionlanguage.stds;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.stds.ErrorCat;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.instr.DefaultProcessKeyWord;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.maths.montecarlo.AbMonteCarlo;
import code.util.*;

public class ApplyCoreMethodUtil {
    protected static final String LOC_VAR = ".";

    protected static final String PARS = "()";
    private ApplyCoreMethodUtil() {
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
            if (ContextEl.isDigit(value_.charAt(0))) {
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
                if (ContextEl.isDigit(p.charAt(0))) {
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
                if (ContextEl.isDigit(value_.charAt(0))) {
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
                if (ContextEl.isDigit(value_.charAt(0))) {
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
                if (ContextEl.isDigit(value_.charAt(0))) {
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
        TypeUtil.buildInherits(_cont);
    }
    public static String checkExactType(Analyzable _an,int _loc, String _in, String _orig) {
        if (!_in.isEmpty()) {
            return _in;
        }
        ContextEl ctx_ = _an.getContextEl();
        int rc_ = _an.getCurrentLocationIndex() + _loc;
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFileName(_an.getCurrentFileName());
        un_.setIndexFile(rc_);
        //original type len
        un_.buildError(ctx_.getAnalysisMessages().getUnknownType(),
                _orig);
        _an.addError(un_);
        return ctx_.getStandards().getAliasObject();
    }
    public static IterableAnalysisResult getCustomTypeBase(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        StringMap<StringList> vars_ = _context.getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        LgNames stds_ = _context.getStandards();
        for (String f: _names) {
            String iterable_ = stds_.getAliasIterable();
            String type_ = Templates.getGeneric(f,iterable_,_context,mapping_);
            if (!type_.isEmpty()) {
                out_.add(type_);
            }
        }
        return new IterableAnalysisResult(out_);
    }
    public static ResultErrorStd getSimpleResultBase(Analyzable _conf, ClassField _classField) {
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
            } else {
                result_.setResult(new FloatStruct(Float.MAX_VALUE));
            }
        } else {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new DoubleStruct(Double.MIN_VALUE));
            } else {
                result_.setResult(new DoubleStruct(Double.MAX_VALUE));
            }
        }
        return result_;
    }

    public static ResultErrorStd invokeBase(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument[] _args) {
        ResultErrorStd result_;
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        result_ = invokeStdMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        processError(_cont,result_);
        if (_cont.callsOrException()) {
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            Struct inst_ = args_[0];
            if (!(inst_ instanceof WithParentStruct)) {
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            WithParentStruct i_ = (WithParentStruct) inst_;
            Struct par_ = args_[1];
            if (!StringList.quickEq(i_.getParentClassName(),par_.getClassName(_cont))) {
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            if (_cont.getInitializingTypeInfos().isContainedSensibleFields(i_)) {
                _cont.getInitializingTypeInfos().failInitEnums();
                return result_;
            }
            i_.setParent(par_);
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        String mathType_ = lgNames_.getAliasMath();
        String stringUtil_ = lgNames_.getAliasStringUtil();
        if (StringList.quickEq(type_, stringUtil_)) {
            Argument a_ = new Argument(args_[0]);
            a_ = ExecOperationNode.processString(a_,_cont);
            if (_cont.getCallingState() == null) {
                result_.setResult(a_.getStruct());
            }
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            if (_cont.getInitializingTypeInfos().isInitEnums()) {
                _cont.getInitializingTypeInfos().failInitEnums();
                return result_;
            }
            /** mathematics "random" calls in order to facilitate uses,
             * despite of the difference between the JAVA names and the user choice names (parameterized in a text file)*/
            StringList paramList_ = _method.getConstraints().getParametersTypes();
            if (paramList_.isEmpty()) {
                result_.setResult(new DoubleStruct(Math.random()));
            } else {
                result_.setResult(new LongStruct(AbMonteCarlo.randomLong(ClassArgumentMatching.convertToNumber(args_[0]).longStruct())));
            }
            return result_;
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            result_ = AliasCharSequence.invokeMethod(_cont, _method, _struct, _args);
            processError(_cont, result_);
            return result_;
        }
        result_ = AliasReflection.invokeMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        processError(_cont,result_);
        if (_cont.callsOrException()) {
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _struct, _method, args_);
        processError(_cont,result_);
        return result_;
    }
    public static ResultErrorStd instanceBase(ContextEl _cont, ConstructorId _method, Argument[] _args) {
        ResultErrorStd result_;
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        result_ = newInstanceStd(_cont, _method, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            processError(_cont, result_);
            return result_;
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            StringBuilderStruct.instantiate(_cont, result_, _method, args_);
            processError(_cont, result_);
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _method, args_);
        processError(_cont,result_);
        return result_;
    }

    public static void processError(ContextEl _cont, ResultErrorStd _result) {
        if (_result.getError() != null) {
            String errMessage_ = _result.getErrorMessage();
            if (errMessage_ != null) {
                _cont.setException(new ErrorStruct(_cont,errMessage_,_result.getError()));
            } else {
                _cont.setException(new ErrorStruct(_cont,_result.getError()));
            }
        }
    }

    public static ResultErrorStd getOtherResultBase(ContextEl _cont, ClassMethodId _method, Struct[] _args) {
        ResultErrorStd result_ = new ResultErrorStd();

        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(name_, lgNames_.getAliasName())) {
            Struct str_ = _args[0];
            if (!(str_ instanceof EnumerableStruct)) {
                result_.setError(lgNames_.getAliasNullPe());
            } else {
                EnumerableStruct en_ = (EnumerableStruct) str_;
                result_.setResult(new StringStruct(en_.getName()));
            }
        } else {
            Struct str_ = _args[0];
            if (!(str_ instanceof EnumerableStruct)) {
                result_.setError(lgNames_.getAliasNullPe());
            } else {
                EnumerableStruct en_ = (EnumerableStruct) str_;
                result_.setResult(new IntStruct(en_.getOrdinal()));
            }
        }
        return result_;
    }
    public static Struct defaultMeta(ExecutableCode _conf, String _id, CustList<Argument> _firstArgs) {
        LgNames stds_ = _conf.getStandards();
        String aliasField_ = stds_.getAliasField();
        String aliasMethod_ = stds_.getAliasMethod();
        String aliasConstructor_ = stds_.getAliasConstructor();
        Struct previous_ = NullStruct.NULL_VALUE;
        if (!_firstArgs.isEmpty()) {
            previous_ = _firstArgs.first().getStruct();
        }
        if (StringList.quickEq(_id,aliasMethod_)) {
            return getMethod(previous_,stds_);
        }
        if (StringList.quickEq(_id,aliasConstructor_)) {
            return getCtor(previous_,stds_);
        }
        if (StringList.quickEq(_id,aliasField_)) {
            return getField(previous_,stds_);
        }
        return getClass(previous_,stds_);
    }
    public static AnnotatedStruct getAnnotated(Struct _struct, LgNames _stds) {
        if (_struct instanceof MethodMetaInfo) {
            return (MethodMetaInfo) _struct;
        }
        if (_struct instanceof ConstructorMetaInfo) {
            return (ConstructorMetaInfo) _struct;
        }
        if (_struct instanceof FieldMetaInfo) {
            return (FieldMetaInfo) _struct;
        }
        return getClass(_struct,_stds);
    }
    public static MethodMetaInfo getMethod(Struct _struct, LgNames _stds) {
        if (_struct instanceof MethodMetaInfo) {
            return (MethodMetaInfo) _struct;
        }
        return _stds.getMethodMetaInfo();
    }
    public static ConstructorMetaInfo getCtor(Struct _struct, LgNames _stds) {
        if (_struct instanceof ConstructorMetaInfo) {
            return (ConstructorMetaInfo) _struct;
        }
        return _stds.getConstructorMetaInfo();
    }
    public static FieldMetaInfo getField(Struct _struct, LgNames _stds) {
        if (_struct instanceof FieldMetaInfo) {
            return (FieldMetaInfo) _struct;
        }
        return _stds.getFieldMetaInfo();
    }
    public static ClassMetaInfo getClass(Struct _struct, LgNames _stds) {
        if (_struct instanceof ClassMetaInfo) {
            return (ClassMetaInfo) _struct;
        }
        return _stds.getClassMetaInfo();
    }

    public static ResultErrorStd invokeStdMethod(Analyzable _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        String mathType_ = lgNames_.getAliasMath();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String nbType_ = lgNames_.getAliasNumber();
        String stringType_ = lgNames_.getAliasString();
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, lgNames_.getAliasResources())) {
            if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesNames())) {
                result_.setResult(ResourcesStruct.getResourceNames(_cont));
            } else {
                result_.setResult(ResourcesStruct.getResource(_cont, getString(args_[0])));
            }
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            if (StringList.quickEq(name_, lgNames_.getAliasSameRef())) {
                result_.setResult(BooleanStruct.of(args_[0].sameReference(args_[1])));
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetParent())) {
                Struct arg_ = args_[0];
                Struct par_ = arg_.getParent();
                _cont.getContextEl().getInitializingTypeInfos().addSensibleField(arg_, par_);
                result_.setResult(par_);
                return result_;
            }
        }
        if (StringList.quickEq(type_, replType_)) {
            ReplacementStruct.calculate(_cont, result_, _method, _struct);
            return result_;
        }
        if (StringList.quickEq(type_, stringType_)
                || StringList.quickEq(type_, lgNames_.getAliasCharSequence())) {
            result_ = AliasCharSequence.invokeStdMethod(_cont, _method, _struct, _args);
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasStackTraceElement())) {
            ContextEl c_ = _cont.getContextEl();
            return AliasStackTraceElement.invokeMethod(c_, _method, _struct);
        }
        if (StringList.quickEq(type_, lgNames_.getAliasError())) {
            if (StringList.quickEq(name_, lgNames_.getAliasCurrentStack())) {
                ErroneousStruct err_;
                if (args_.length == 0) {
                    err_ = getError(_struct,_cont.getContextEl());
                    result_.setResult(err_.getStack());
                    return result_;
                }
                err_ = getError(args_[0],_cont.getContextEl());
                result_.setResult(err_.getFullStack());
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetMessage())) {
                ErroneousStruct err_ = getError(_struct,_cont.getContextEl());
                result_.setResult(err_.getMessage());
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetCause())) {
                ErroneousStruct err_ = getError(_struct,_cont.getContextEl());
                result_.setResult(err_.getCause());
                return result_;
            }
            ErroneousStruct err_;
            if (args_.length == 0) {
                err_ = getError(_struct,_cont.getContextEl());
                result_.setResult(err_.getDisplayedString(_cont));
                return result_;
            }
            err_ = getError(args_[0],_cont.getContextEl());
            result_.setResult(new StringStruct(err_.getStringRep(_cont,err_.getFullStack().getInstance())));
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            return AliasMath.invokeStdMethod(_cont, _method, _args);
        }
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || PrimitiveTypeUtil.isPureNumberClass(new ClassArgumentMatching(type_), lgNames_)
                || StringList.quickEq(type_, nbType_)) {
            NumberStruct.calculate(_cont, result_, _method, _struct, args_);
            return result_;
        }
        return result_;
    }
    public static ResultErrorStd newInstance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.instance(_cont,_method,_args);
    }

    public static ResultErrorStd newInstanceStd(Analyzable _cont, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String stringType_ = lgNames_.getAliasString();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, replType_)) {
            ReplacementStruct.instantiate(result_, args_);
            return result_;
        }
        if (StringList.quickEq(type_, stringType_)) {
            StringStruct.instantiate(lgNames_, result_, _method, args_);
            return result_;
        }
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            NumberStruct.instantiate(_cont, result_, _method, args_);
            return result_;
        }
        return result_;
    }
    public static Struct defaultInstance(ExecutableCode _conf, String _id, CustList<Argument> _firstArgs) {
        LgNames stds_ = _conf.getStandards();
        Struct previous_ = NullStruct.NULL_VALUE;
        if (!_firstArgs.isEmpty()) {
            previous_ = _firstArgs.first().getStruct();
        }
        if (PrimitiveTypeUtil.isPureNumberClass(new ClassArgumentMatching(_id),_conf)) {
            return PrimitiveTypeUtil.convertToNumber(new ClassArgumentMatching(_id),previous_,stds_);
        }
        String aliasBoolean_ = stds_.getAliasBoolean();
        if (StringList.quickEq(aliasBoolean_, _id)) {
            return ClassArgumentMatching.convertToBoolean(previous_);
        }
        String aliasString_ = stds_.getAliasString();
        String aliasStringBuilder_ = stds_.getAliasStringBuilder();
        if (StringList.quickEq(aliasString_, _id)) {
            return getString(previous_);
        }
        if (StringList.quickEq(aliasStringBuilder_, _id)) {
            return getStrBuilder(previous_);
        }
        String aliasRepl_ = stds_.getAliasReplacement();
        if (StringList.quickEq(aliasRepl_, _id)) {
            return getReplacement(previous_);
        }
        return stds_.defaultInstance(_conf,_id).getStruct();
    }

    static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }

    public static ReplacementStruct getReplacement(Struct _previous) {
        if (_previous instanceof ReplacementStruct) {
            return (ReplacementStruct) _previous;
        }
        Replacement r_ = new Replacement();
        r_.setOldString("");
        r_.setNewString("");
        return new ReplacementStruct(r_);
    }

    public static CharSequenceStruct getCharSeq(Struct _previous) {
        if (_previous instanceof StringBuilderStruct) {
            return (StringBuilderStruct) _previous;
        }
        return getString(_previous);
    }
    public static StringStruct getString(Struct _previous) {
        if (_previous instanceof StringStruct) {
            return (StringStruct) _previous;
        }
        return new StringStruct("");
    }

    public static StringBuilderStruct getStrBuilder(Struct _previous) {
        if (_previous instanceof StringBuilderStruct) {
            return (StringBuilderStruct) _previous;
        }
        return new StringBuilderStruct(new StringBuilder());
    }

    private static ErroneousStruct getError(Struct _err, ExecutableCode _cont) {
        if (_err instanceof ErroneousStruct) {
            return (ErroneousStruct) _err;
        }
        String null_ = _cont.getStandards().getAliasNullPe();
        return new ErrorStruct(_cont,null_);
    }

    public static DisplayableStruct getStringOfObjectBase(ContextEl _cont, Struct _arg) {
        String str_;
        if (_arg instanceof EnumerableStruct) {
            str_ = ((EnumerableStruct)_arg).getName();
        } else {
            str_ = _arg.getClassName(_cont);
        }
        return new StringStruct(str_);
    }

    public static String getNameOfEnum(Struct _arg) {
        if (_arg instanceof EnumerableStruct) {
            return ((EnumerableStruct)_arg).getName();
        }
        return ";";
    }

    public static void buildIterable(ContextEl _context) {
        //local names
        LgNames stds_ = _context.getStandards();
        _context.getAnalyzing().setCurrentBlock(null);
        _context.getAnalyzing().setCurrentAnaBlock(null);
        _context.getAnalyzing().setEnabledInternVars(true);
        Classes cl_ = _context.getClasses();
        String next_ = stds_.getAliasNext();
        String hasNext_ = stds_.getAliasHasNext();
        String nextPair_ = stds_.getAliasNextPair();
        String hasNextPair_ = stds_.getAliasHasNextPair();
        String locName_ = _context.getNextTempVar();
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasIterable(),"<?>"));
        _context.getAnalyzing().getInternVars().put(locName_, locVar_);
        cl_.setIteratorVarCust(locName_);
        String iterator_ = stds_.getAliasIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iterator_,PARS));
        cl_.setExpsIteratorCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasIteratorType(),"<?>"));
        _context.getAnalyzing().getInternVars().put(locName_, locVar_);
        cl_.setHasNextVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        cl_.setExpsHasNextCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasIteratorType(),"<?>"));
        _context.getAnalyzing().getInternVars().put(locName_, locVar_);
        cl_.setNextVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        cl_.setExpsNextCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));

        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasIterableTable(),"<?,?>"));
        _context.getAnalyzing().getInternVars().put(locName_, locVar_);
        cl_.setIteratorTableVarCust(locName_);
        String iteratorTable_ = stds_.getAliasIteratorTable();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iteratorTable_,PARS));
        cl_.setExpsIteratorTableCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasIteratorTableType(),"<?,?>"));
        _context.getAnalyzing().getInternVars().put(locName_, locVar_);
        cl_.setHasNextPairVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNextPair_,PARS));
        cl_.setExpsHasNextPairCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasIteratorTableType(),"<?,?>"));
        _context.getAnalyzing().getInternVars().put(locName_, locVar_);
        cl_.setNextPairVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(nextPair_,PARS));
        cl_.setExpsNextPairCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasPairType(),"<?,?>"));
        _context.getAnalyzing().getInternVars().put(locName_, locVar_);
        cl_.setFirstVarCust(locName_);
        String first_ = stds_.getAliasGetFirst();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(first_,PARS));
        cl_.setExpsFirstCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(stds_.getAliasPairType(),"<?,?>"));
        _context.getAnalyzing().getInternVars().put(locName_, locVar_);
        cl_.setSecondVarCust(locName_);
        String second_ = stds_.getAliasGetSecond();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(second_,PARS));
        cl_.setExpsSecondCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
    }
}
