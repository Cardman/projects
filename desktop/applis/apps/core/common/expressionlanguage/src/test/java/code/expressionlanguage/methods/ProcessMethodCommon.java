package code.expressionlanguage.methods;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.DefaultAccess;
import code.expressionlanguage.analyze.files.DefaultAccessType;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.options.*;
import code.expressionlanguage.sample.CustLgNames;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ListLoggableLgNames;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
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

    protected static ResultContext validate(Options _opt, CustLgNames _lgName, KeyWords _kw, StringMap<String> _files) {
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = getForwards(_opt,_lgName,_kw,page_, _files);
        ResultContext r_ = new ResultContext(page_, forwards_);
        ResultContext res_ = ResultContext.def(r_, _files, "src");
        ResultContext.fwd(res_, new DefContextGenerator());
        Classes.tryInit(res_);
        return res_;
    }

    protected static Argument calculateError(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        ExecRootBlock classBody_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        assertTrue(_args.isEmpty());
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_,_class), new ExecTypeFunction(classBody_, method_), p_), _cont, stackCall_);
        CustomFoundExc excState_ = (CustomFoundExc) stackCall_.getCallingState();
        Struct exc_ = excState_.getStruct();
        assertNotNull(exc_);
        return new Argument(exc_);
    }

    protected static String calculateErrorMess(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        ExecRootBlock classBody_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        assertTrue(_args.isEmpty());
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_,_class), new ExecTypeFunction(classBody_, method_), p_), _cont, stackCall_);
        return _cont.errorMessage(stackCall_);
    }

    protected static Argument calculateNormal(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        ExecRootBlock classBody_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        assertTrue(_args.isEmpty());
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        Argument arg_ = ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_), _cont, stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        return arg_;
    }

    protected static Argument calculateNormalParam(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        ExecRootBlock classBody_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        LocalVariable lv_ = LocalVariable.newLocalVariable(_args.first().getStruct(), _cont);
        p_.getRefParameters().addEntry(method_.getParametersName(0), new VariableWrapper(lv_));
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        Argument arg_ = ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_), _cont, stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        return arg_;
    }
    protected static String convertStr(Struct _str, ContextEl _cont) {
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        return ProcessMethod.convertStr(_str,_cont,stackCall_);
    }

    protected static MethodId getMethodId(String _name, String..._classNames) {
        return getMethodId(MethodAccessKind.STATIC, _name, false, _classNames);
    }

    protected static MethodId getMethodId(MethodAccessKind _k,String _name, boolean _vararg, String..._classNames) {
        StringList cl_ = new StringList(_classNames);
        return new MethodId(_k, _name, cl_, _vararg);
    }

    protected static Argument instanceError(String _class, Argument _global, ConstructorId _id, ContextEl _cont) {
        assertEq(0, _id.getParametersTypesLength());
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        ProcessMethod.calculate(new CustomFoundConstructor(_cont,new ExecFormattedRootBlock(type_,_class), _global), _cont, stackCall_);
        CustomFoundExc excState_ = (CustomFoundExc) stackCall_.getCallingState();
        Struct exc_ = excState_.getStruct();
        assertNotNull(exc_);
        return new Argument(exc_);
    }
    protected static Argument instanceNormal(String _class, Argument _global, ConstructorId _id, ContextEl _cont) {
        assertEq(0, _id.getParametersTypesLength());
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        Argument arg_ = ProcessMethod.calculate(new CustomFoundConstructor(_cont,new ExecFormattedRootBlock(type_, _class), _global), _cont, stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        return arg_;
    }
    protected static Argument instanceNormalCtor(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        assertEq(0, _id.getParametersTypesLength());
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock ctor_ = (ExecNamedFunctionBlock) type_.getAllFct().first();
        Parameters p_ = new Parameters();
        assertTrue(_args.isEmpty());
//        feedParams(_args, _cont, ctor_, p_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        Argument arg_ = ProcessMethod.calculate(new CustomFoundConstructor(_cont,new ExecFormattedRootBlock(type_, _class), new ExecTypeFunction(type_, ctor_), "",-1, _global, p_), _cont, stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        return arg_;
    }
    protected static Argument instanceNormalCtorParam(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        assertEq(1, _id.getParametersTypesLength());
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock ctor_ = (ExecNamedFunctionBlock) type_.getAllFct().first();
        Parameters p_ = new Parameters();
        LocalVariable lv_ = LocalVariable.newLocalVariable(_args.first().getStruct(), _cont);
        p_.getRefParameters().addEntry(ctor_.getParametersName(0), new VariableWrapper(lv_));
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        Argument arg_ = ProcessMethod.calculate(new CustomFoundConstructor(_cont,new ExecFormattedRootBlock(type_, _class), new ExecTypeFunction(type_, ctor_), "",-1, _global, p_), _cont, stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        return arg_;
    }
    protected static Argument instanceNormalCtor(String _class, Argument _global, int _id, CustList<Argument> _args, ContextEl _cont) {
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock ctor_ = (ExecNamedFunctionBlock) type_.getAllFct().get(_id);
        Parameters p_ = new Parameters();
        assertTrue(_args.isEmpty());
//        feedParams(_args, _cont, ctor_, p_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        Argument arg_ = ProcessMethod.calculate(new CustomFoundConstructor(_cont,new ExecFormattedRootBlock(type_, _class), new ExecTypeFunction(type_, ctor_), "",-1, _global, p_), _cont, stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        return arg_;
    }

    protected static ConstructorId getConstructorId(String _name, String..._classNames) {
        StringList cl_ = new StringList(_classNames);
        return new ConstructorId(_name, cl_, false);
    }

    protected static ConstructorId getConstructorId(boolean _vararg, String..._classNames) {
        StringList cl_ = new StringList(_classNames);
        return new ConstructorId("", cl_, _vararg);
    }

    private static StringMap<String> getErrors(ResultContext _report) {
        return _report.getReportedMessages().getErrors();
    }

    private static StringMap<String> validateAndCheckReportErrors(Options _opt, CustLgNames _lgName, KeyWords _kw, StringMap<String> _files) {
        ResultContext validate_ = validate(_opt,_lgName,_kw,_files);
        assertFalse(isEmptyErrors(validate_.getPageEl()));
        return getErrors(validate_);
    }

    protected static void existErrors(AnalyzedPageEl _page) {
        assertTrue(notAllEmptyErrors(_page));
        assertFalse(isEmptyErrors(_page));
    }

    protected static void inexistErrors(AnalyzedPageEl _page) {
        assertFalse(notAllEmptyErrors(_page));
        assertTrue(isEmptyErrors(_page));
    }

    private static StringMap<String> validateAndCheckReportWarnings(Options _opt, CustLgNames _lgName, KeyWords _kw, StringMap<String> _files) {
        ResultContext validate_ = validate(_opt,_lgName,_kw,_files);
        return getErrors(validate_);
    }

    private static StringMap<String> validateAndCheckNoReportErrors(Options _opt, CustLgNames _lgName, KeyWords _kw, StringMap<String> _files) {
        ResultContext validate_ = validate(_opt,_lgName,_kw,_files);
        assertTrue(isEmptyErrors(validate_.getPageEl()));
        return getErrors(validate_);
    }

    protected static boolean covErr(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);

        ResultContext r_ = validate(opt_,lgName_,kw_, _files);
        r_.getPageEl().getMessages().displayErrors();
        return notAllEmptyErrors(r_.getPageEl());
    }

    protected static StringMap<String> ctxNotErrReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);
        
        return validateAndCheckNoReportErrors(opt_,lgName_,kw_,_files);
    }
    protected static StringMap<String> ctxErrReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return repErrs(_files, opt_, lgName_, kw_);
    }
    protected static StringMap<String> ctxErrReadOnlyImpl(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        opt_.getOptionsReport().setDisplayImplicit(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return repErrs(_files, opt_, lgName_, kw_);
    }

    protected static StringMap<String> ctxErrStdReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        return repErrs(_files, opt_, lgName_, kwl_);
    }

    protected static StringMap<String> ctxWarnStdReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        WarningShow warningShow_ = new WarningShow();
        warningShow_.setTernary(true);
        warningShow_.setUnusedParameterStaticMethod(true);
        opt_.setWarningShow(warningShow_);
        opt_.setGettingErrors(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);
        
        return validateAndCheckReportWarnings(opt_,lgName_,kwl_,_files);
    }

    protected static StringMap<String> ctxErrStdReadOnlyImpl(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        opt_.getOptionsReport().setDisplayImplicit(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        return repErrs(_files, opt_, lgName_, kwl_);
    }
    protected static StringMap<String> ctxErrStdReadOnlyImpl2(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        opt_.getOptionsReport().setDisplayImplicit(true);
        opt_.getOptionsReport().setEncodeHeader(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        return repErrs(_files, opt_, lgName_, kwl_);
    }

    protected static StringMap<String> export(ContextEl _cont) {
        return ExecFileBlock.export(_cont);
    }
    protected static ContextEl covEnReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        return cov(_files, opt_, lgName_, kwl_);
    }

    protected static ContextEl covEnReadOnlyImpl(StringMap<String> _files) {
        return covEnReadOnlyImpl(_files, false);
    }

    protected static ContextEl covEnReadOnlyImpl(StringMap<String> _files, boolean _disLabel) {
        Options opt_ = newOptions();
        opt_.getOptionsReport().setDisplayImplicitLabel(_disLabel);
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        opt_.getOptionsReport().setDisplayImplicit(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        return cov(_files, opt_, lgName_, kwl_);
    }

    protected static ContextEl covEnReadOnlyImpl2(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.getOptionsReport().setEncodeHeader(true);
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        opt_.getOptionsReport().setDisplayImplicit(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        return cov(_files, opt_, lgName_, kwl_);
    }

    protected static ContextEl covEn(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        return cov(_files, opt_, lgName_, kwl_);
    }

    protected static ContextEl covValEn(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        return getContextEl(_files, opt_, lgName_, kwl_);
    }

    protected static ContextEl contextElEnum(StringMap<String> _files) {
        Options opt_ = newOptions();
        CustLgNames lgName_ = getLgNames();
        lgName_.getContent().getPredefTypes().setAliasEnumName("name");
        lgName_.getContent().getPredefTypes().setAliasEnumOrdinal("ordinal");
        
        KeyWords kw_ = new KeyWords();

        return getContextEl(_files, opt_, lgName_, kw_);
    }

    protected static boolean ctxMustInitFail(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return inval(_files,opt_,lgName_,kw_);
    }

    protected static ContextEl ctxMustInit(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        return ctx(_files, opt_);
    }

    protected static ContextEl contextElToString(StringMap<String> _files) {
        Options opt_ = newOptions();
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();
        kw_.setKeyWordToString("toSpecString");

        return getContextEl(_files, opt_, lgName_, kw_);
    }

    protected static boolean ok(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);
        return isEmptyErrors(validateAndRetWithoutInitCheck(opt_,lgName_,kw_,_files,new StringMap<String>()).getPageEl());
    }

    protected static ContextEl ctxNoErrExp(StringMap<String> _files) {
        Options opt_ = newOptions();
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();
        kw_.setKeyWordNbExpBin("power");
        kw_.setKeyWordNbExpDec("exp");

        return getContextEl(_files, opt_, lgName_, kw_);
    }

    protected static ContextEl contextElDefault(StringMap<String> _files, int _i) {
        Options opt_ = newOptions();
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();
        
        return getResContextEl(_files,opt_,lgName_,kw_,_i).getContext();
    }

    protected static ContextEl contextElTypes(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_, "pkg.ExTwo", "pkg.ExThree", "pkg.ExFour", "Biz");
        return ctx(_files, opt_);
    }

    protected static void addTypesInit(Options _opt, String... _types) {
        _opt.getTypesInit().addAllElts(new StringList(_types));
    }

    protected static ContextEl ctxResOk(StringMap<String> _srcFiles, StringMap<String> _all) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);
        return validateAndRetAfterInit(opt_,lgName_,kw_,_srcFiles, _all).getContext();
    }

    protected static ContextEl ctxOk(StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        addTypesInit(opt_, _types);
        return ctx(_files, opt_);
    }

    protected static ContextEl ctxOkRead(StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_, _types);
        return ctx(_files, opt_);
    }

    protected static ContextEl ctxOkReadSeed(StringMap<String> _files, String _seed) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setSeedElts(_seed);
        return ctx(_files, opt_);
    }

    protected static ResultContext validateAndRet(Options _opt, CustLgNames _lgNames, KeyWords _kw, StringMap<String> _files, StringMap<String> _all) {
        ResultContext res_ = validateAndRetWithoutInit(_opt, _lgNames, _kw, _files, _all);
        Classes.tryInit(res_);
        res_.getContext().setExiting(new NoExiting());
        return res_;
    }

    protected static ResultContext validateAndRetAfterInit(Options _opt, CustLgNames _lgNames, KeyWords _kw, StringMap<String> _files, StringMap<String> _all) {
        ResultContext res_ = validateAndRetWithoutInit(_opt, _lgNames, _kw, _files, _all);
        Classes.tryInit(res_);
        res_.getContext().setExiting(new AfterInitExiting(res_.getContext()));
        return res_;
    }

    protected static ResultContext validateAndRetWithoutInit(Options _opt, CustLgNames _lgNames, KeyWords _kw, StringMap<String> _files, StringMap<String> _all) {
        return validateAndRetWithoutInit(_opt, _lgNames, _kw, _files, _all, new DefStackStopper());
    }
    protected static ResultContext validateAndRetWithoutInit(Options _opt, CustLgNames _lgNames, KeyWords _kw, StringMap<String> _files, StringMap<String> _all, AbsStackStopper _s) {
        ResultContext res_ = validateAndRetWithoutInitCheck(_opt, _lgNames, _kw, _files, _all,_s);
        assertTrue(isEmptyErrors(res_.getPageEl()));
        ResultContext.fwd(res_, new DefContextGenerator());
        return res_;
    }

    protected static ResultContext validateAndRetWithoutInitCheck(Options _opt, CustLgNames _lgNames, KeyWords _kw, StringMap<String> _files, StringMap<String> _all) {
        return validateAndRetWithoutInitCheck(_opt, _lgNames, _kw, _files, _all, new DefStackStopper());
    }
    protected static ResultContext validateAndRetWithoutInitCheck(Options _opt, CustLgNames _lgNames, KeyWords _kw, StringMap<String> _files, StringMap<String> _all, AbsStackStopper _s) {
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = getForwards(_opt, _lgNames, _kw,page_, _all,_s);
        ResultContext r_ = new ResultContext(page_, forwards_);
        return ResultContext.afterDef(r_, _all, ResultContext.defFilter(page_, _all, _files),_s);
    }

    protected static ContextEl ctxLgOk(String _lg,StringMap<String> _files, String... _types) {
        assertEq("en",_lg);
        Options opt_ = newOptions();
        addTypesInit(opt_, _types);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        return getContextEl(_files, opt_, lgName_, kwl_);
    }


    protected static void buildFilesBodies(AnalyzedPageEl _cont, StringMap<String> _files) {
        ClassesUtil.buildFilesBodies(_files,true, _cont);
    }

    protected static void parseFiles(AnalyzedPageEl _cont) {
        ClassesUtil.parseFiles(_cont);
    }

    protected static void validateInheritingClasses(AnalyzedPageEl _cont) {
        ClassesUtil.validateInheritingClasses(_cont);
    }

    protected static void validateIds(AnalyzedPageEl _cont) {
        ClassesUtil.validateIds(_cont);
    }

    protected static void validateOverridingInherit(AnalyzedPageEl _cont) {
        ClassesUtil.validateOverridingInherit(_cont);
    }


    protected static void validateEl(AnalyzedPageEl _cont) {
        ClassesUtil.validateEl(_cont);
    }

    protected static void checkInterfaces(AnalyzedPageEl _cont) {
        AnaTypeUtil.checkInterfaces(_cont);
    }


    protected boolean failValidateValue(StringMap<String> _files) {
        Options opt_ = newOptions();

        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return inval(_files,opt_,lgName_,kw_);
    }

    protected static ContextEl ctxLgReadOnlyOk(String _lg,StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_, _types);
        CustLgNames lgName_ = getLgNames();
        assertEq("en",_lg);
        KeyWords kwl_ = en(lgName_);

        return getContextEl(_files, opt_, lgName_, kwl_);
    }

    protected static boolean hasErrLgReadOnly(String _lg,StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();
        assertEq("en",_lg);
        KeyWords kwl_ = en(lgName_);

        return inval(_files,opt_,lgName_,kwl_);
    }

    protected static boolean hasErrLgReadOnlyFr(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();
        KeyWords kwl_ = fr(lgName_);

        return inval(_files,opt_,lgName_,kwl_);
    }

    protected static ContextEl ctxReadOnlyOk(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);

        return ctx(_files, opt_);
    }

    private static ContextEl ctx(StringMap<String> _files, Options _opt) {
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return getContextEl(_files, _opt, lgName_, kw_);
    }

    protected ContextEl validateStaticFields(StringMap<String> _files) {
        Options opt_ = newOptions();

        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);
//        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        StringMap<String> added_ = new StringMap<String>();
        return validateAndRetWithoutInit(opt_,lgName_,kw_,_files,added_).getContext();
//        Forwards forwards_ = getForwards(opt_,lgName_,kw_,page_,added_);
//
//        ClassesUtil.buildCoreBracesBodies(page_);
//        ResultContext r_ = new ResultContext(page_, forwards_, page_.getMessages());
//        ResultContext res_ = ResultContext.afterDef(r_, added_, ResultContext.defFilter(page_, added_, _files));
//
//        assertTrue( isEmptyErrors(res_.getPageEl()));
//        ResultContext.fwd(res_, new DefContextGenerator());
//        return res_.getContext();
    }

    protected static Struct getStaticField(ContextEl _ctx, ClassField _id) {
        StringMap<StringMap<Struct>> staticFields_ = _ctx.getClasses().getStaticFields();
        return NumParsers.getStaticField(_id, staticFields_);
    }


    protected StringMap<StringMap<Struct>> validateStaticFieldsFail(StringMap<String> _files) {
        Options opt_ = newOptions();

        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        StringMap<String> all_ = new StringMap<String>();
        Forwards forwards_ = getForwards(opt_, lgName_, kw_, page_,all_);

        ResultContext r_ = new ResultContext(page_, forwards_);
        ResultContext res_ = ResultContext.afterDef(r_, all_, ResultContext.defFilter(page_, all_, _files));

        assertTrue(notAllEmptyErrors(res_.getPageEl()));
        return forwards_.getStaticFields();
    }

    protected boolean failValidateInheritingClassesValue(StringMap<String> _files) {
        Options opt_ = newOptions();
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        getForwards(opt_,lgName_,kw_,page_);
        
        parseCustomFiles(_files, page_);
        assertTrue( isEmptyErrors(page_));
        validateInheritingClasses(page_);
        return notAllEmptyErrors(page_);
    }

    protected static void parseCustomFiles(StringMap<String> _files, AnalyzedPageEl _cont) {
        ClassesUtil.tryBuildAllBracedClassesBodies(_files, _cont);
    }

    protected static void validateInheritingClassesId(AnalyzedPageEl _cont) {
        ClassesUtil.validateInheritingClassesId(_cont);
    }

    protected static Options newOptions() {
        Options options_ = new Options();
        options_.getOptionsReport().setEncodeHeader(false);
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

    protected static Struct getField(Struct _struct, ClassField _key) {
        return ClassFieldStruct.getPair(((FieldableStruct)_struct).getFields(),_key).getStruct();
    }

    protected static Struct getTrueException(StackCall _cont) {
        CallingState str_ = _cont.getCallingState();
        return ((CustomFoundExc) str_).getStruct();
    }
    protected static ContextEl cov(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return cov(_files, opt_, lgName_, kw_);
    }

    protected static ContextEl covReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return cov(_files, opt_, lgName_, kw_);
    }

    protected static ContextEl covReadOnlyImpl(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        opt_.getOptionsReport().setDisplayImplicit(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return cov(_files, opt_, lgName_, kw_);
    }

    protected static ContextEl covDisplay(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        CustLgNames lgName_ = getLgNames();
        lgName_.getDisplayedStrings().setTrueString("\"");
        lgName_.getDisplayedStrings().setFalseString("&");
        KeyWords kw_ = new KeyWords();
        return cov(_files, opt_,lgName_,kw_);
    }

    protected static ContextEl covCom(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));
        opt_.setCovering(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return cov(_files, opt_, lgName_, kw_);
    }

    protected static ContextEl covEnCom(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));
        opt_.setCovering(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kwl_ = en(lgName_);

        return cov(_files, opt_, lgName_, kwl_);
    }

    protected static ContextEl covVal(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return cov(_files, opt_, lgName_, kw_);
    }


    protected static ContextEl covVal2(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        return ctx(_files, opt_);
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
        Options opt_ = newOptions();
        WarningShow warningShow_ = new WarningShow();
        warningShow_.setTernary(true);
        warningShow_.setUnusedParameterStaticMethod(true);
        opt_.setWarningShow(warningShow_);
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        ResultContext ctx_ = getResContextEl(_files, opt_,lgName_, kw_);
        ReportedMessages methodHeaders_ = ctx_.getReportedMessages();
        assertFalse(methodHeaders_.displayMessageErrors()+methodHeaders_.displayErrors()+methodHeaders_.displayStdErrors()+methodHeaders_.displayWarnings(),methodHeaders_.isEmptyWarnings());
        return ctx_.getContext();
    }

    protected static boolean hasErrReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);

        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return inval(_files,opt_,lgName_,kw_);
    }

    protected static boolean hasErrDefCom(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));

        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();
        
        return inval(_files,opt_,lgName_,kw_);
    }

    protected static boolean hasErr(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();

        KeyWords kw_ = new KeyWords();

        return inval(_files, opt_, lgName_, kw_);
    }

    protected static boolean hasErrLg(StringMap<String> _files, String _lg) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();
        assertEq("en",_lg);
        KeyWords kwl_ = en(lgName_);

        return inval(_files,opt_,lgName_,kwl_);
    }

    protected static boolean hasErrLgFr(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();
        KeyWords kwl_ = fr(lgName_);

        return inval(_files,opt_,lgName_,kwl_);
    }

    public static Forwards fwd(CustLgNames _lgName, DefaultFileBuilder _fileBuilder, Options _opt) {
        return fwd(_lgName, _fileBuilder, _opt, new StringMap<String>());
    }

    public static Forwards fwd(CustLgNames _lgName, DefaultFileBuilder _fileBuilder, Options _opt, StringMap<String> _res) {
        return fwd(_lgName, _fileBuilder, _opt, _res, new DefStackStopper());
    }
    public static Forwards fwd(CustLgNames _lgName, DefaultFileBuilder _fileBuilder, Options _opt, StringMap<String> _res, AbsStackStopper _s) {
        Forwards f_ = new Forwards(_lgName, new ListLoggableLgNames(), _fileBuilder, _opt, _s);
        f_.getResources().addAllEntries(_res);
        return f_;
    }

    protected static CustLgNames getLgNames() {
        CustLgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        return lgName_;
    }

    protected static boolean stateMismatchSuccessful(ContextEl _cont, String _s) {
        return ProcessMethod.stateMismatch(asExecRootBlock(_cont, _s), _cont, InitClassState.SUCCESS);
    }

    protected static boolean notAllEmptyErrors(AnalyzedPageEl _cont) {
        return _cont.notAllEmptyErrors();
    }

    protected static boolean isEmptyErrors(AnalyzedPageEl _cont) {
        return _cont.getMessages().isAllEmptyErrors();
    }

    protected static boolean isInitialized(ContextEl _cont, String _cl) {
        return ProcessMethod.stateMatch(asExecRootBlock(_cont, _cl), _cont, InitClassState.SUCCESS);
    }

    private static ExecRootBlock asExecRootBlock(ContextEl _cont, String _cl) {
        return (ExecRootBlock) _cont.getClassBody(_cl);
    }

    protected static Forwards getForwards(Options _opt, CustLgNames _lgName, KeyWords _kw, AnalyzedPageEl _page) {
        return getForwards(_opt, _lgName, _kw, _page, new StringMap<String>());
    }
    protected static Forwards getForwards(Options _opt, CustLgNames _lgName, KeyWords _kw, AnalyzedPageEl _page, StringMap<String> _res) {
        return getForwards(_opt, _lgName, _kw, _page, _res,new DefStackStopper());
    }
    protected static Forwards getForwards(Options _opt, CustLgNames _lgName, KeyWords _kw, AnalyzedPageEl _page, StringMap<String> _res, AbsStackStopper _s) {
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(_lgName.getContent());
        updateBuilders(_page);
        Forwards forwards_ = fwd(_lgName, fileBuilder_, _opt, _res, _s);

        validatedStds(_page, forwards_, _kw, _opt, _lgName);
        return forwards_;
    }

    public static void updateBuilders(AnalyzedPageEl _page) {
        CustList<AbsAliasFileBuilder> builders_ = new CustList<AbsAliasFileBuilder>();
        builders_.add(new DefAliasFileBuilder());
        CustList<AbsAliasFileBuilder> fbs_ = _page.getFileBuilders();
        fbs_.clear();
        fbs_.addAllElts(builders_);
    }
    protected static void validatedStds(AnalyzedPageEl _page, Forwards _forwards, KeyWords _kw, Options _opt, LgNames _lgName) {
        validatedStds(new AnalysisMessages(),_page,_forwards,_kw,_opt,_lgName);
        assertTrue(_page.isEmptyStdError());
    }
    protected static void validatedStds(AnalysisMessages _a, AnalyzedPageEl _page, Forwards _forwards, KeyWords _kw, Options _opt, LgNames _lgName) {
        _page.setLogErr(_forwards);
        _kw.initSupplDigits();
//        ContextFactory.validateStds(_forwards, _a, _kw, new CustList<CommentDelimiters>(), _opt, _lgName.getContent(), _page);
        ContextFactory.beforeBuild(_forwards,_a,_kw,new CustList<CommentDelimiters>(), _opt,_lgName.getContent(),_page);
        ContextFactory.build(_forwards,_kw,_opt,_page,new DefBuildLightResultContextNext());
   }

    protected static ContextEl getContextEl(StringMap<String> _files, Options _opt, CustLgNames _lgName, KeyWords _kw) {
        return getResContextEl(_files, _opt, _lgName, _kw).getContext();
    }

    private static ContextEl cov(StringMap<String> _files, Options _opt, CustLgNames _lgName, KeyWords _kw) {
        setOpts(_opt, IndexConstants.INDEX_NOT_FOUND_ELT);

        ResultContext ctx_ = validate(_opt,_lgName,_kw,_files);
        assertTrue(isEmptyErrors(ctx_.getPageEl()));
        return ctx_.getContext();
    }

    private static ResultContext getResContextEl(StringMap<String> _files, Options _opt, CustLgNames _lgName, KeyWords _kw) {
        return getResContextEl(_files, _opt, _lgName, _kw, IndexConstants.INDEX_NOT_FOUND_ELT);
    }

    private static ResultContext getResContextEl(StringMap<String> _files, Options _opt, CustLgNames _lgName, KeyWords _kw, int _stack) {
        setOpts(_opt, _stack);

        return validateAndRet(_opt,_lgName,_kw,_files, new StringMap<String>());
    }

    private static boolean inval(StringMap<String> _files, Options _opt, CustLgNames _lgName, KeyWords _kw) {
        setOpts(_opt, IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        StringMap<String> all_ = new StringMap<String>();
        Forwards fwd_ = getForwards(_opt, _lgName, _kw, page_,all_);

        ResultContext r_ = new ResultContext(page_, fwd_);
        ResultContext res_ = ResultContext.afterDef(r_, all_, ResultContext.defFilter(page_, all_, _files));
        return notAllEmptyErrors(res_.getPageEl());
    }

    private static StringMap<String> repErrs(StringMap<String> _files, Options _opt, CustLgNames _lgName, KeyWords _kwl) {
        setOpts(_opt, IndexConstants.INDEX_NOT_FOUND_ELT);

        return validateAndCheckReportErrors(_opt,_lgName,_kwl,_files);
    }

    protected static void setOpts(Options _opt, int _i) {
        _opt.setTabWidth(4);
        _opt.setStack(_i);
    }

    protected static KeyWords fr(LgNames _lgName) {
        KeyWordsMap.initFrStds(_lgName);
        return KeyWordsMap.fr();
    }

    protected static KeyWords en(LgNames _lgName) {
        KeyWordsMap.initEnStds(_lgName);
        return KeyWordsMap.en();
    }

}
