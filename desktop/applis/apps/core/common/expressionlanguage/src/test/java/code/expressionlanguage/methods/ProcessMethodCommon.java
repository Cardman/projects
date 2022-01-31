package code.expressionlanguage.methods;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.WarningShow;
import code.expressionlanguage.structs.*;
import code.util.*;
import code.util.core.IndexConstants;

public abstract class ProcessMethodCommon extends EquallableElUtil {

    protected static final String ARR_NUMBER = "[java.lang.Number";
    protected static final String ARR_INTEGER = "[java.lang.Integer";
    protected static final String ARR_OBJECT = "[java.lang.Object";
    protected static final String ARR_ARR_OBJECT = "[[java.lang.Object";
    protected static final String ARR_CUST = "[pkg.ExThree";
    protected static final String ARR_ARR_CUST = "[[pkg.ExThree";
    protected static final String INTEGER = "java.lang.Integer";
    protected static final String STRING = "java.lang.String";
    protected static final String BOOLEAN = "java.lang.Boolean";

    protected static ContextEl validate(StringMap<String> _files, Options _opt, AnalyzedPageEl _page, Forwards _forwards) {
        return ContextFactory.addResourcesAndValidate(_opt,_files, "src", _page, _forwards);
    }

    protected static Argument calculateError(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        ExecRootBlock classBody_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        feedParams(_args, _cont, method_, p_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        ProcessMethod.calculateArgument(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_,_class), new ExecTypeFunction(classBody_, method_), p_), _cont, stackCall_);
        CustomFoundExc excState_ = (CustomFoundExc) stackCall_.getCallingState();
        Struct exc_ = excState_.getStruct();
        assertNotNull(exc_);
        return new Argument(exc_);
    }
    protected static Argument calculateNormal(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        ExecRootBlock classBody_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        feedParams(_args, _cont, method_, p_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        Argument arg_ = ProcessMethod.calculateArgument(new CustomFoundMethod(argGlLoc_,new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_), _cont, stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        return arg_;
    }

    protected static MethodId getMethodId(String _name, String..._classNames) {
        StringList cl_ = new StringList(_classNames);
        return new MethodId(MethodAccessKind.STATIC, _name, cl_);
    }

    protected static Argument instanceError(String _class, Argument _global, ConstructorId _id, ContextEl _cont) {
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecConstructorBlock ctor_ = tryGet(type_, _id);
        assertNull(ctor_);
        Parameters p_ = new Parameters();
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        ProcessMethod.instanceArgument(_cont, stackCall_, new CustomFoundConstructor(new ExecFormattedRootBlock(type_,_class), new ExecTypeFunction(type_,ctor_), _global, p_));
        CustomFoundExc excState_ = (CustomFoundExc) stackCall_.getCallingState();
        Struct exc_ = excState_.getStruct();
        assertNotNull(exc_);
        return new Argument(exc_);
    }
    protected static Argument instanceNormal(String _class, Argument _global, ConstructorId _id, ContextEl _cont) {
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecConstructorBlock ctor_ = tryGet(type_, _id);
        assertNull(ctor_);
        Parameters p_ = new Parameters();
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        Argument arg_ = ProcessMethod.instanceArgument(_cont, stackCall_, new CustomFoundConstructor(new ExecFormattedRootBlock(type_, _class), new ExecTypeFunction(type_, ctor_), _global, p_)).getValue();
        assertNull(stackCall_.getCallingState());
        return arg_;
    }
    protected static Argument instanceNormalCtor(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecConstructorBlock ctor_ = get(type_, _id);
        Parameters p_ = new Parameters();
        feedParams(_args, _cont, ctor_, p_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        Argument arg_ = ProcessMethod.instanceArgument(_cont, stackCall_, new CustomFoundConstructor(new ExecFormattedRootBlock(type_, _class), new ExecTypeFunction(type_, ctor_), _global, p_)).getValue();
        assertNull(stackCall_.getCallingState());
        return arg_;
    }

    private static void feedParams(CustList<Argument> _args, ContextEl _cont, ExecNamedFunctionBlock _ctor, Parameters _p) {
        int i_ = IndexConstants.FIRST_INDEX;
        for (Argument a : _args) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(a.getStruct(), _cont);
            _p.getRefParameters().addEntry(_ctor.getParametersName(i_), new VariableWrapper(lv_));
            i_++;
        }
    }

    private static ExecConstructorBlock tryGet(ExecRootBlock _root, ConstructorId _id) {
        for (ExecBlock b: _root.getChildrenOthers()) {
            if (!(b instanceof ExecConstructorBlock)) {
                continue;
            }
            ExecConstructorBlock method_ = (ExecConstructorBlock) b;
            if (!method_.getId().eq(_id)) {
                continue;
            }
            return method_;
        }
        return null;
    }
    private static ExecConstructorBlock get(ExecRootBlock _root, ConstructorId _id) {
        CustList<ExecConstructorBlock> list_ = new CustList<ExecConstructorBlock>();
        for (ExecBlock b: _root.getChildrenOthers()) {
            if (!(b instanceof ExecConstructorBlock)) {
                continue;
            }
            ExecConstructorBlock method_ = (ExecConstructorBlock) b;
            if (!method_.getId().eq(_id)) {
                continue;
            }
            list_.add(method_);
        }
        return list_.first();
    }

    protected static ConstructorId getConstructorId(String _name, String..._classNames) {
        StringList cl_ = new StringList(_classNames);
        return new ConstructorId(_name, cl_, false);
    }

    private static AnalyzedTestContext contextElCoverageDefaultEnComment() {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }
    private static AnalyzedTestContext contextElCoverageDefaultComment() {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }
    private static AnalyzedTestContext contextElErrorReadOnlyDef() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }
    private static AnalyzedTestContext contextElErrorReadOnlyDefImpl() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        opt_.setDisplayImplicit(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    private static StringMap<String> getErrors(AnalyzedTestContext _report) {
        return _report.getAnalyzing().getMessages().getErrors();
    }

    private static StringMap<String> validateAndCheckReportErrors(StringMap<String> _files, AnalyzedTestContext _cont) {
        validate(_cont, _files);
        assertTrue(!isEmptyErrors(_cont));
        return getErrors(_cont);
    }

    private static StringMap<String> validateAndCheckReportWarnings(StringMap<String> _files, AnalyzedTestContext _cont) {
        validate(_cont, _files);
        return getErrors(_cont);
    }

    private static StringMap<String> validateAndCheckNoReportErrors(StringMap<String> _files, AnalyzedTestContext _cont) {
        validate(_cont, _files);
        assertTrue(isEmptyErrors(_cont));
        return getErrors(_cont);
    }

    protected static boolean covErr(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageDefAna();
        validate(cont_,_files);
        return !isEmptyErrors(cont_);
    }

    protected static StringMap<String> ctxNotErrReadOnly(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElErrorReadOnlyDef();
        return validateAndCheckNoReportErrors(_files, cont_);
    }
    protected static StringMap<String> ctxErrReadOnly(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElErrorReadOnlyDef();
        return validateAndCheckReportErrors(_files, cont_);
    }
    protected static StringMap<String> ctxErrReadOnlyImpl(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElErrorReadOnlyDefImpl();
        return validateAndCheckReportErrors(_files, cont_);
    }

    protected static StringMap<String> ctxErrStdReadOnly(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElErrorStdReadOnlyDef();
        return validateAndCheckReportErrors(_files, cont_);
    }

    protected static StringMap<String> ctxWarnStdReadOnly(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElErrorStdWarningDef();
        return validateAndCheckReportWarnings(_files, cont_);
    }

    protected static StringMap<String> ctxErrStdReadOnlyImpl(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElErrorStdReadOnlyDefImpl();
        return validateAndCheckReportErrors(_files, cont_);
    }
    protected static StringMap<String> ctxErrStdReadOnlyImpl2(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElErrorStdReadOnlyDefImpl2();
        return validateAndCheckReportErrors(_files, cont_);
    }
    private static AnalyzedTestContext contextElErrorStdReadOnlyDef() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }
    private static AnalyzedTestContext contextElErrorStdWarningDef() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        WarningShow warningShow_ = new WarningShow();
        warningShow_.setTernary(true);
        warningShow_.setUnusedParameterStaticMethod(true);
        opt_.setWarningShow(warningShow_);
        opt_.setGettingErrors(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }
    private static AnalyzedTestContext contextElCoverageReadOnlyDef() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }
    private static AnalyzedTestContext contextElErrorStdReadOnlyDefImpl() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        opt_.setDisplayImplicit(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }
    private static AnalyzedTestContext contextElErrorStdReadOnlyDefImpl2() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        opt_.setDisplayImplicit(true);
        opt_.setEncodeHeader(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }
    private static AnalyzedTestContext contextElCoverageReadOnlyDefImpl() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        opt_.setDisplayImplicit(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    private static AnalyzedTestContext contextElCoverageDisplayDef() {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        AnalyzedTestContext ct_ = InitializationLgNames.buildStdOneAna(opt_);
        ct_.getDisplayedStrings().setTrueString("\"");
        ct_.getDisplayedStrings().setFalseString("&");
        return ct_;
    }

    private static AnalyzedTestContext contextElCoverageDefAna() {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    protected static StringMap<String> export(ContextEl _cont) {
        return ExecFileBlock.export(_cont);
    }
    protected static ContextEl covEnReadOnly(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ontextElCoverageReadOnlyEn();
        return validateCovAndRet(_files, cont_);
    }

    protected static ContextEl covEnReadOnlyImpl(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ontextElCoverageReadOnlyEnImpl();
        return validateCovAndRet(_files, cont_);
    }

    protected static ContextEl covEnReadOnlyImpl2(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ontextElCoverageReadOnlyEnImpl2();
        return validateCovAndRet(_files, cont_);
    }

    private static AnalyzedTestContext ontextElCoverageReadOnlyEn() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }

    private static AnalyzedTestContext ontextElCoverageReadOnlyEnImpl() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        opt_.setDisplayImplicit(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }

    private static AnalyzedTestContext ontextElCoverageReadOnlyEnImpl2() {
        Options opt_ = newOptions();
        opt_.setEncodeHeader(true);
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        opt_.setDisplayImplicit(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }

    protected static ContextEl covEn(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageEnAna();
        return validateCovAndRet(_files, cont_);
    }

    protected static ContextEl covValEn(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageEnAna();
        return validateAndRet(_files, cont_);
    }

    private static AnalyzedTestContext contextElCoverageEnAna() {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }

    protected static ContextEl contextElEnum(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElEnumAna();
        return validateAndRet(_files, cont_);
    }

    private static AnalyzedTestContext contextElEnumAna() {
        Options opt_ = newOptions();
        return InitializationLgNames.buildStdEnumsAna(opt_);
    }

    protected static boolean ctxMustInitFail(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElReadOnlyMustInit();
        validateAll(_files, cont_);
        ReportedMessages reportedMessages_ = cont_.getAnalyzing().getMessages();
        reportedMessages_.displayErrors();
        return !reportedMessages_.isAllEmptyErrors();
    }

    protected static ContextEl validateAll(StringMap<String> _files, AnalyzedTestContext _cont) {
        return Classes.validateAll(_cont.getOpt(), _files, _cont.getAnalyzing(), _cont.getForwards());
    }

    protected static ContextEl ctxMustInit(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElReadOnlyMustInit();
        return validateAndRet(_files, cont_);
    }

    private static AnalyzedTestContext contextElReadOnlyMustInit() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    protected static ContextEl contextElToString(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElToStringAna();
        return validateAndRet(_files, cont_);
    }

    private static AnalyzedTestContext contextElToStringAna() {
        Options opt_ = newOptions();
        return InitializationLgNames.buildStdToStringAna(opt_);
    }

    protected static boolean ok(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ctxAna();
        validateAll(_files, cont_);
        return isEmptyErrors(cont_);
    }

    protected static ContextEl ctxNoErrExp(StringMap<String> _files) {
        AnalyzedTestContext ctx_ = contextElExp();
        return validateAndRet(_files, ctx_);
    }

    private static AnalyzedTestContext contextElExp() {
        Options opt_ = newOptions();
        return InitializationLgNames.buildStdExp(opt_);
    }

    private static AnalyzedTestContext ctxLgReadOnlyAna(String _lg, String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_, _types);
        return InitializationLgNames.buildStdOneAna(_lg, opt_);
    }

    protected static ContextEl contextElDefault(StringMap<String> _files, int _i) {
        AnalyzedTestContext cont_ = contextElDefault(_i);
        return validateAndRet(_files, cont_);
    }

    private static AnalyzedTestContext contextElDefault(int _m) {
        Options opt_ = newOptions();
        return InitializationLgNames.buildStdOneAna(_m, opt_);
    }

    protected static ContextEl contextElTypes(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElTypes("pkg.ExTwo","pkg.ExThree","pkg.ExFour","Biz");
        return validateAndRet(_files, cont_);
    }

    private static AnalyzedTestContext contextElTypes(String... _types) {
        Options opt_ = newOptions();
        addTypesInit(opt_, _types);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    private static void addTypesInit(Options _opt, String... _types) {
        _opt.getTypesInit().addAllElts(new StringList(_types));
    }

    protected static ContextEl ctxResOk(StringMap<String> _srcFiles, StringMap<String> _all) {
        AnalyzedTestContext cont_ = ctxAna();
        cont_.getAnalyzing().addResources(_all);
        return validateAndRet(_srcFiles, cont_);
    }

    protected static ContextEl ctxOk(StringMap<String> _files, String... _types) {
        AnalyzedTestContext cont_ = ctxAna(_types);
        return validateAndRet(_files, cont_);
    }

    protected static ContextEl ctxOkRead(StringMap<String> _files, String... _types) {
        AnalyzedTestContext cont_ = ctxReadOnlyAna(_types);
        return validateAndRet(_files, cont_);
    }

    protected static ContextEl ctxOkReadSeed(StringMap<String> _files, String _seed) {
        AnalyzedTestContext cont_ = ctxReadOnlyAnaSeed(_seed);
        return validateAndRet(_files, cont_);
    }

    private static ContextEl validateAndRet(StringMap<String> _files, AnalyzedTestContext _cont) {
        ContextEl ctx_ = validateAll(_files, _cont);
        assertTrue(isEmptyErrors(_cont));
        return ctx_;
    }

    protected static ContextEl ctxLgOk(String _lg,StringMap<String> _files, String... _types) {
        AnalyzedTestContext cont_ = ctxLgAna(_lg,_types);
        return validateAndRet(_files, cont_);
    }

    protected static ContextEl forwardAndClear(AnalyzedTestContext _cont) {
        ContextEl ctx_ = _cont.getForwards().generate(_cont.getOpt());
        Classes.forwardAndClear(ctx_);
        return ctx_;
    }

    protected static void validateWithoutInit(StringMap<String> _files, AnalyzedTestContext _cont) {
        Classes.validateWithoutInit(_files, _cont.getAnalyzing());
    }


    protected static void buildFilesBodies(AnalyzedTestContext _cont, StringMap<String> _files) {
        ClassesUtil.buildFilesBodies(_files,true, _cont.getAnalyzing());
    }

    protected static void parseFiles(AnalyzedTestContext _cont) {
        ClassesUtil.parseFiles(_cont.getAnalyzing());
    }

    protected static void validateInheritingClasses(AnalyzedTestContext _cont) {
        ClassesUtil.validateInheritingClasses(_cont.getAnalyzing());
    }

    protected static void validateIds(AnalyzedTestContext _cont) {
        ClassesUtil.validateIds(_cont.getAnalyzing());
    }

    protected static void validateOverridingInherit(AnalyzedTestContext _cont) {
        ClassesUtil.validateOverridingInherit(_cont.getAnalyzing());
    }

    protected static void postValidation(AnalyzedTestContext _ctx) {
        ClassesUtil.postValidation(_ctx.getAnalyzing());
        ForwardInfos.generalForward(_ctx.getAnalyzing(), _ctx.getForwards());
    }

    protected static void validateEl(AnalyzedTestContext _cont) {
        ClassesUtil.validateEl(_cont.getAnalyzing());
    }

    protected static void checkInterfaces(AnalyzedTestContext _cont) {
        AnaTypeUtil.checkInterfaces(_cont.getAnalyzing());
    }

    protected static AnalyzedTestContext ctxReadOnlyAna(String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_, _types);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    protected static AnalyzedTestContext ctxAna(String... _types) {
        Options opt_ = newOptions();
        addTypesInit(opt_, _types);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    protected static AnalyzedTestContext ctxReadOnlyAnaSeed(String _seed) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setSeedElts(_seed);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    protected static AnalyzedTestContext ctxAnaWarn(String... _types) {
        Options opt_ = newOptions();
        WarningShow warningShow_ = new WarningShow();
        warningShow_.setTernary(true);
        warningShow_.setUnusedParameterStaticMethod(true);
        opt_.setWarningShow(warningShow_);
        addTypesInit(opt_, _types);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    protected static AnalyzedTestContext ctxLgAna(String _lg, String... _types) {
        Options opt_ = newOptions();
        addTypesInit(opt_, _types);
        return InitializationLgNames.buildStdOneAna(_lg, opt_);
    }

    protected static AnalyzedTestContext getEnContextElComment() {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));


        return InitializationLgNames.buildStdOneAna("en", opt_);
    }


    protected boolean failValidateValue(StringMap<String> _files) {
        Options opt_ = newOptions();

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdOneAna(opt_);
        validateWithoutInit(_files, cont_);
        ReportedMessages headers_ = cont_.getAnalyzing().getMessages();
        headers_.displayErrors();
        return !isEmptyErrors(cont_);
    }

    protected static ContextEl ctxLgReadOnlyOk(String _lg,StringMap<String> _files, String... _types) {
        AnalyzedTestContext cont_ = ctxLgReadOnlyAna(_lg,_types);
        return validateAndRet(_files, cont_);
    }

    protected static boolean hasErrLgReadOnly(String _lg,StringMap<String> _files) {
        AnalyzedTestContext cont_ = ctxLgReadOnlyAna(_lg);
        return invalid(_files, cont_);
    }

    protected static ContextEl ctxReadOnlyOk(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ctxReadOnlyAna();
        return validateAndRet(_files, cont_);
    }

    private static AnalyzedTestContext ctxReadOnlyAna() {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);

        return InitializationLgNames.buildStdOneAna(opt_);
    }

    private static AnalyzedTestContext contextElSingleDotDefaultComment() {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));

        return InitializationLgNames.buildStdOneAna(opt_);
    }

    protected ContextEl validateStaticFields(StringMap<String> _files) {
        Options opt_ = newOptions();

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdOneAna(opt_);
        return validQuick(_files, cont_);
    }

    protected static Struct getStaticField(ContextEl _ctx, ClassField _id) {
        StringMap<StringMap<Struct>> staticFields_ = _ctx.getClasses().getStaticFields();
        return NumParsers.getStaticField(_id, staticFields_);
    }

    protected static ContextEl validQuick(StringMap<String> _files, AnalyzedTestContext _cont) {
        validateWithoutInit(_files, _cont);
        assertTrue( isEmptyErrors(_cont));
        ForwardInfos.generalForward(_cont.getAnalyzing(),_cont.getForwards());
        return forwardAndClear(_cont);
    }

    protected StringMap<StringMap<Struct>> validateStaticFieldsFail(StringMap<String> _files) {
        Options opt_ = newOptions();

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdOneAna(opt_);
        validateWithoutInit(_files, cont_);
        assertTrue(!isEmptyErrors(cont_));
        return cont_.getForwards().getStaticFields();
    }
    protected static AnalyzedTestContext unfullValidateInheriting(StringMap<String> _files) {
        Options opt_ = newOptions();

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdOneAna(opt_);
        parseCustomFiles(_files, cont_);
        assertTrue( isEmptyErrors(cont_));
        validateInheritingClasses(cont_);
        assertTrue( isEmptyErrors(cont_));
        return cont_;
    }

    protected boolean failValidateInheritingClassesValue(StringMap<String> _files) {
        Options opt_ = newOptions();
        AnalyzedTestContext cont_ = InitializationLgNames.buildStdOneAna(opt_);
        parseCustomFiles(_files, cont_);
        assertTrue( isEmptyErrors(cont_));
        validateInheritingClasses(cont_);
        return !isEmptyErrors(cont_);
    }

    protected boolean failValidateInheritingClassesSingleValue(StringMap<String> _files) {
        Options opt_ = newOptions();

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdOneAna(opt_);
        parseCustomFiles(_files, cont_);
        assertTrue( isEmptyErrors(cont_));
        validateInheritingClasses(cont_);
        return !isEmptyErrors(cont_);
    }

    protected static void parseCustomFiles(StringMap<String> _files, AnalyzedTestContext _cont) {
        ClassesUtil.tryBuildAllBracedClassesBodies(_files, _cont.getAnalyzing());
    }

    protected static void validateInheritingClassesId(AnalyzedTestContext _cont) {
        ClassesUtil.validateInheritingClassesId(_cont.getAnalyzing());
    }

    protected static AnalyzedTestContext simpleCtx() {
        Options opt_ = newOptions();
        AnalyzedTestContext cont_ = InitializationLgNames.buildStdOneAna(opt_);
        parsePredefFiles(cont_);
        return cont_;
    }

    protected static AnalyzedTestContext simpleCtxComment() {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));
        AnalyzedTestContext cont_ = InitializationLgNames.buildStdOneAna(opt_);
        parsePredefFiles(cont_);
        return cont_;
    }

    private static Options newOptions() {
        Options options_ = new Options();
        options_.setEncodeHeader(false);
        DefaultAccess defaultAccess_ = options_.getDefaultAccess();
        setup(defaultAccess_.getAccAnonymous(),AccessEnum.PACKAGE);
        setup(defaultAccess_.getAccClass(),AccessEnum.PACKAGE);
        setup(defaultAccess_.getAccEnum(),AccessEnum.PACKAGE);
        setup(defaultAccess_.getAccInnerEnum(),AccessEnum.PACKAGE);
        setup(defaultAccess_.getAccAnnotation(),AccessEnum.PUBLIC);
        setup(defaultAccess_.getAccInterface(),AccessEnum.PUBLIC);
        defaultAccess_.setAccOuter(AccessEnum.PACKAGE);
        return options_;
    }
    private static void setup(DefaultAccessType _def, AccessEnum _value) {
        _def.setAccLocalTypes(_value);
        _def.setAccMember(_value);
        _def.setAccInners(_value);
    }

    private static void parsePredefFiles(AnalyzedTestContext _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        for (EntryCust<String, String> e: page_.buildFiles().entryList()) {
            String name_ = e.getKey();
            String content_ = e.getValue();
            FileResolverTest.parseFile(_cont, name_, true, content_);
        }
    }

    protected static Struct getField(Struct _struct, ClassField _key) {
        return ClassFieldStruct.getPair(((FieldableStruct)_struct).getFields(),_key).getStruct();
    }

    protected static Struct getTrueException(StackCall _cont) {
        CallingState str_ = _cont.getCallingState();
        return ((CustomFoundExc) str_).getStruct();
    }
    protected static ContextEl cov(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageDefAna();
        return validateCovAndRet(_files, cont_);
    }

    protected static ContextEl covReadOnly(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageReadOnlyDef();
        return validateCovAndRet(_files, cont_);
    }

    protected static ContextEl covReadOnlyImpl(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageReadOnlyDefImpl();
        return validateCovAndRet(_files, cont_);
    }

    protected static ContextEl covDisplay(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageDisplayDef();
        return validateCovAndRet(_files, cont_);
    }

    protected static ContextEl covCom(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageDefaultComment();
        return validateCovAndRet(_files, cont_);
    }

    protected static ContextEl covEnCom(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageDefaultEnComment();
        return validateCovAndRet(_files, cont_);
    }

    protected static ContextEl covVal(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageDefAna();
        return validateCovAndRet(_files, cont_);
    }

    private static ContextEl validateCovAndRet(StringMap<String> _files, AnalyzedTestContext _cont) {
        ContextEl ctx_ = validate(_cont, _files);
        assertTrue(isEmptyErrors(_cont));
        return ctx_;
    }

    protected static ContextEl covVal2(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElCoverageDefAna();
        return validateAndRet(_files, cont_);
    }

    protected static ContextEl validate(AnalyzedTestContext _c, StringMap<String> _f) {
        return validate(_f,_c.getOpt(), _c.getAnalyzing(), _c.getForwards());
    }
    protected static String getString(Argument _arg) {
        return ((CharSequenceStruct)_arg.getStruct()).toStringInstance();
    }
    protected static long getNumber(Argument _arg) {
        return ((NumberStruct)_arg.getStruct()).longStruct();
    }
    protected static double getDouble(Argument _arg) {
        return ((NumberStruct)_arg.getStruct()).doubleStruct();
    }
    protected static char getChar(Argument _arg) {
        return ((CharStruct)_arg.getStruct()).getChar();
    }
    protected static CustList<ExecOverridableBlock> getDeepMethodBodiesById(ContextEl _context,String _genericClassName, MethodId _id) {
        String base_ = StringExpUtil.getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        ExecRootBlock r_ = classes_.getClassBody(base_);
        return ExecClassesUtil.getMethodBodiesById(r_,_id);
    }

    protected static ContextEl checkWarn(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ctxAnaWarn();
        ContextEl ctx_ = validateAll(_files, cont_);
        ReportedMessages methodHeaders_ = cont_.getAnalyzing().getMessages();
        assertTrue(isEmptyErrors(cont_));
        assertTrue(methodHeaders_.displayMessageErrors()+methodHeaders_.displayErrors()+methodHeaders_.displayStdErrors()+methodHeaders_.displayWarnings(),!methodHeaders_.isEmptyWarnings());
        return ctx_;
    }

    protected static boolean hasErrReadOnly(StringMap<String> _files) {
        AnalyzedTestContext ctx_ = ctxReadOnlyAna();
        return invalid(_files, ctx_);
    }

    protected static boolean hasErrDefCom(StringMap<String> _files) {
        AnalyzedTestContext cont_ = contextElSingleDotDefaultComment();
        return invalid(_files, cont_);
    }

    private static boolean invalid(StringMap<String> _files, AnalyzedTestContext _cont) {
        validateWithoutInit(_files, _cont);
        return !isEmptyErrors(_cont);
    }

    protected static boolean hasErr(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ctxAna();
        return invalid(_files, cont_);
    }

    protected static boolean hasErrLg(StringMap<String> _files, String _lg) {
        AnalyzedTestContext cont_ = ctxLgAna(_lg);
        return invalid(_files, cont_);
    }

    protected static boolean isSuccessfulInitialized(ContextEl _cont, String _s) {
        return _cont.getLocks().getState(_s) == InitClassState.SUCCESS;
    }

    protected static boolean isEmptyErrors(AnalyzedTestContext _cont) {
        return _cont.getAnalyzing().isEmptyErrors();
    }

    protected static boolean isInitialized(ContextEl _cont, String _cl) {
        return _cont.getLocks().getState(_cl) != InitClassState.NOT_YET;
    }

}
