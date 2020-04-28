package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.files.FileResolver;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

import static org.junit.Assert.assertTrue;

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
        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = InitializationLgNames.buildStdOne(opt_);
        } else {
            ct_ = InitializationLgNames.buildStdOne(_m[0], opt_);
        }
        return ct_;
    }

    protected static ContextEl contextElCoverageDefault() {
        Options opt_ = new Options();
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        ct_.setCovering(true);
        return ct_;
    }

    protected static ContextEl contextElCoverageReadOnlyDef() {
        Options opt_ = new Options();
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

    protected static ContextEl contextElCoverageDisplayDef() {
        Options opt_ = new Options();
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        ct_.setCovering(true);
        ct_.getStandards().getDisplayedStrings().setTrueString("\"");
        ct_.getStandards().getDisplayedStrings().setFalseString("&");
        return ct_;
    }

    protected static ContextEl contextElCoverageOtherIniDef() {
        Options opt_ = new Options();
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

    protected static ContextEl contextElSingle(int... _m) {
        Options opt_ = new Options();
        
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
        ContextEl ct_ = InitializationLgNames.buildStdEnums(opt_);
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
        opt_.setReadOnly(true);
        opt_.setFailIfNotAllInit(true);
        ContextEl ct_ = InitializationLgNames.buildStdOne(opt_);
        return ct_;
    }
    protected static ContextEl contextElToString() {
        Options opt_ = new Options();
        ContextEl ct_ = InitializationLgNames.buildStdToString(opt_);
        return ct_;
    }
    protected static ContextEl contextElExp() {
        Options opt_ = new Options();
        ContextEl ct_ = InitializationLgNames.buildStdExp(opt_);
        return ct_;
    }
    protected static ContextEl contextEnElDefault() {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdOne("en", opt_);
    }
    protected static ContextEl contextFrElDefault() {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdOne("fr", opt_);
    }
    protected static ContextEl contextFrElDefaultReadOnly() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        return InitializationLgNames.buildStdOne("fr", opt_);
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

    protected static ContextEl contextElTypes(String... _types) {
        Options opt_ = new Options();
        for (String t: _types) {
            opt_.getTypesInit().add(t);
        }
        return InitializationLgNames.buildStdOne(opt_);
    }

    protected static ContextEl getFrContextEl() {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdOne("fr",opt_);
    }
    protected static ContextEl contextEl(int... _m) {
        Options opt_ = new Options();
        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = InitializationLgNames.buildStdOne(opt_);
        } else {
            ct_ = InitializationLgNames.buildStdOne(_m[0], opt_);
        }
        return ct_;
    }
    protected static ContextEl contextElSingleDot(int... _m) {
        Options opt_ = new Options();

        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = InitializationLgNames.buildStdOne(opt_);
        } else {
            ct_ = InitializationLgNames.buildStdOne(_m[0], opt_);
        }
        return ct_;
    }

    protected static ContextEl getSimpleContextEl() {
        Options opt_ = new Options();


        return InitializationLgNames.buildStdOne(opt_);
    }

    protected static ContextEl getEnContextEl() {
        Options opt_ = new Options();


        return InitializationLgNames.buildStdOne("en", opt_);
    }
    protected void failValidate(StringMap<String> _files) {
        Options opt_ = new Options();

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateWithoutInit(_files,cont_);
        Classes classes_ = cont_.getClasses();
        assertTrue(classes_.displayErrors(), !cont_.isEmptyErrors());
    }
    protected static ContextEl contextElSingleDotDefault(int... _m) {
        Options opt_ = new Options();

        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = InitializationLgNames.buildStdOne(opt_);
        } else {
            ct_ = InitializationLgNames.buildStdOne(_m[0], opt_);
        }
        return ct_;
    }
    protected static ContextEl contextEnElSingleDotDefault() {
        Options opt_ = new Options();

        return InitializationLgNames.buildStdOne("en",opt_);
    }
    protected ContextEl validateStaticFields(StringMap<String> _files) {
        Options opt_ = new Options();

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        parseCustomFiles(_files, cont_);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        Classes.validateInheritingClasses(cont_);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        Classes.validateIds(cont_);
        Classes.validateOverridingInherit(cont_);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.initStaticFields(cont_);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        return cont_;
    }

    protected ContextEl unfullValidateInheritingClasses(StringMap<String> _files) {
        Options opt_ = new Options();

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        parseCustomFiles(_files, cont_);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        Classes.validateInheritingClasses(cont_);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        return cont_;
    }
    protected ContextEl unfullValidateInheritingClassesSingle(StringMap<String> _files) {
        Options opt_ = new Options();

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        parseCustomFiles(_files, cont_);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        Classes.validateInheritingClasses(cont_);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        return cont_;
    }

    protected void failValidateInheritingClasses(StringMap<String> _files) {
        Options opt_ = new Options();
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        parseCustomFiles(_files, cont_);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        Classes.validateInheritingClasses(cont_);
        assertTrue(classes_.displayErrors(), !cont_.isEmptyErrors());
    }

    protected void failValidateInheritingClassesSingle(StringMap<String> _files) {
        Options opt_ = new Options();

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        parseCustomFiles(_files, cont_);
        Classes classes_ = cont_.getClasses();
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        Classes.validateInheritingClasses(cont_);
        assertTrue(classes_.displayErrors(), !cont_.isEmptyErrors());
    }

    protected static void parseCustomFiles(StringMap<String> _files, ContextEl _cont) {
        _cont.setAnalyzing();
        Classes.buildPredefinedBracesBodies(_cont);
        CustList<RootBlock> foundTypes_ = _cont.getAnalyzing().getFoundTypes();
        _cont.setAnalyzing();
        _cont.getAnalyzing().getPreviousFoundTypes().addAllElts(foundTypes_);
        Classes.tryBuildBracedClassesBodies(_files, _cont, false);
    }

    protected static ContextEl getRootContextEl() {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdOne(opt_);
    }

    protected static ContextEl simpleCtx() {
        Options opt_ = new Options();
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        LgNames stds_ = cont_.getStandards();
        cont_.setAnalyzing();
        for (EntryCust<String, String> e: stds_.buildFiles(cont_).entryList()) {
            String name_ = e.getKey();
            String content_ = e.getValue();
            FileResolver.parseFile(name_, content_, true, cont_);
        }
        return cont_;
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
