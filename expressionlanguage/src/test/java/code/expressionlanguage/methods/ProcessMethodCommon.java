package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.VariableSuffix;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class ProcessMethodCommon {

    protected static final String ARR_NUMBER = "[java.lang.Number";
    protected static final String ARR_INTEGER = "[java.lang.Integer";
    protected static final String ARR_OBJECT = "[java.lang.Object";
    protected static final String ARR_ARR_OBJECT = "[[java.lang.Object";
    protected static final String ARR_CUST = "[pkg.ExThree";
    protected static final String ARR_ARR_CUST = "[[pkg.ExThree";
    protected static final String INTEGER = "java.lang.Integer";
    protected static final String STRING = "java.lang.String";
    protected static final String BOOLEAN = "java.lang.Boolean";

    protected static void validate(AnalysisMessages _mess, KeyWords _definedKw, LgNames _definedLgNames, StringMap<String> _files, ContextEl _contextEl) {
        ContextFactory.validate(_mess, _definedKw,_definedLgNames,_files,_contextEl,"src");
    }
    protected static Argument calculateArgument(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        MethodId fct_ = new MethodId(_method.getKind(), _method.getName(),_method.getParametersTypes());
        NamedFunctionBlock method_ = Classes.getMethodBodiesById(_cont, _class, fct_).first();
        Block firstChild_ = method_.getFirstChild();
        if (firstChild_ == null) {
            return new Argument();
        }
        Argument argGlLoc_ = new Argument();
        return ProcessMethod.calculateArgument(argGlLoc_, _class, fct_, _args, _cont,null);
    }

    protected static MethodId getMethodId(String _name, String..._classNames) {
        StringList cl_ = new StringList();
        for (String c: _classNames) {
            cl_.add(c);
        }
        return new MethodId(MethodAccessKind.STATIC, _name, cl_);
    }

    protected static Argument instanceArgument(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        int len_ = _id.getParametersTypes().size();
        StringList constraints_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = _id.getParametersTypes().get(i);
            constraints_.add(n_);
        }
        ConstructorId id_ = new ConstructorId(_id.getName(),constraints_, false);
        RootBlock type_ = _cont.getClasses().getClassBody(Templates.getIdFromAllTypes(_class));
        return ProcessMethod.instanceArgument(_class,type_, _global, id_, _args, _cont);
    }

    protected static ConstructorId getConstructorId(String _name, String..._classNames) {
        StringList cl_ = new StringList();
        for (String c: _classNames) {
            cl_.add(c);
        }
        return new ConstructorId(_name, cl_, false);
    }

    protected static ContextEl contextElOtherInit(int... _m) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        opt_.setInitializeStaticClassFirst(false);
        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = InitializationLgNames.buildStdOne(opt_);
        } else {
            ct_ = InitializationLgNames.buildStdOne(_m[0], opt_);
        }
        return ct_;
    }

    protected static ContextEl contextElCoverage() {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        ct_.setCovering(true);
        return ct_;
    }

    protected static ContextEl contextElCoverageReadOnly() {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        opt_.setReadOnly(true);
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        ct_.setCovering(true);
        return ct_;
    }

    protected static ContextEl contextElCoverageReadOnlyDefault() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        ContextEl ct_ = InitializationLgNames.buildStdOne("en", opt_);
        ct_.setCovering(true);
        return ct_;
    }
    protected static ContextEl contextElCoverageDisplay() {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        ct_.setCovering(true);
        ct_.getStandards().setTrueString("\"");
        ct_.getStandards().setFalseString("&");
        return ct_;
    }
    protected static ContextEl contextElCoverageOtherIni() {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        opt_.setInitializeStaticClassFirst(false);
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        ct_.setCovering(true);
        return ct_;
    }
    protected static ContextEl contextElCoverageEnDefault() {
        Options opt_ = new Options();
        ContextEl ct_ = InitializationLgNames.buildStdOne("en",opt_);
        ct_.setCovering(true);
        return ct_;
    }
    protected static ContextEl contextElCoverageReadOnlyEnDefault() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        ContextEl ct_ = InitializationLgNames.buildStdOne("en",opt_);
        ct_.setCovering(true);
        return ct_;
    }
    protected static ContextEl contextElCoverageEnDefaultSingle() {
        Options opt_ = new Options();
        
        ContextEl ct_ = InitializationLgNames.buildStdOne("en",opt_);
        ct_.setCovering(true);
        return ct_;
    }

    protected static ContextEl contextElCoverageEnDefaultOtherIni() {
        Options opt_ = new Options();
        opt_.setInitializeStaticClassFirst(false);
        ContextEl ct_ = InitializationLgNames.buildStdOne("en",opt_);
        ct_.setCovering(true);
        return ct_;
    }
    protected static ContextEl contextEl(int... _m) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = InitializationLgNames.buildStdOne(opt_);
        } else {
            ct_ = InitializationLgNames.buildStdOne(_m[0], opt_);
        }
        return ct_;
    }
    protected static ContextEl contextElSingle(int... _m) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = InitializationLgNames.buildStdOne(opt_);
        } else {
            ct_ = InitializationLgNames.buildStdOne(_m[0], opt_);
        }
        return ct_;
    }
    protected static ContextEl contextElEnum() {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl ct_ = InitializationLgNames.buildStdEnums(opt_);
        return ct_;
    }
    protected static ContextEl contextElReadOnly() {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        opt_.setReadOnly(true);
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        return ct_;
    }
    protected static ContextEl contextElReadOnly(VariableSuffix _suf) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(_suf);
        opt_.setReadOnly(true);
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        return ct_;
    }
    protected static ContextEl contextElReadOnlyDef() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        return ct_;
    }
    protected static ContextEl contextElReadOnlyDefault() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        ContextEl ct_ = InitializationLgNames.buildStdOne("en", opt_);
        return ct_;
    }
    protected static ContextEl contextElReadOnlyDefaultSingle() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        
        ContextEl ct_ = InitializationLgNames.buildStdOne("en", opt_);
        return ct_;
    }
    protected static ContextEl contextElReadOnlyMustInit() {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        opt_.setReadOnly(true);
        opt_.setFailIfNotAllInit(true);
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        return ct_;
    }
    protected static ContextEl contextElToString() {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl ct_ = InitializationLgNames.buildStdToString(opt_);
        return ct_;
    }
    protected static ContextEl contextEnElDefault() {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdOne("en", opt_);
    }
    protected static ContextEl contextEnElDefaultInternType() {
        Options opt_ = new Options();
        
        return InitializationLgNames.buildStdOne("en", opt_);
    }

    protected static ContextEl contextElDefault(int... _m) {
        Options opt_ = new Options();
        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = InitializationLgNames.buildStdOne(opt_);
        } else {
            ct_ = InitializationLgNames.buildStdOne(_m[0], opt_);
        }
        return ct_;
    }
    protected static ContextEl contextEl(VariableSuffix _suf,int... _m) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(_suf);
        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = InitializationLgNames.buildStdOne(opt_);
        } else {
            ct_ = InitializationLgNames.buildStdOne(_m[0], opt_);
        }
        return ct_;
    }
    protected static ContextEl contextElTypes(VariableSuffix _suf,String... _types) {
        Options opt_ = new Options();
        for (String t: _types) {
            opt_.getTypesInit().add(t);
        }
        return InitializationLgNames.buildStdOne(opt_);
    }
    protected static Struct getStruct(Struct _struct, ClassField _cl) {
        return ((FieldableStruct) _struct).getEntryStruct(_cl).getValue();
    }
    protected static Struct getException(ContextEl _cont) {
        CallingState str_ = _cont.getCallingState();
        if (str_ instanceof Struct) {
            return (Struct) str_;
        }
        return null;
    }
}
