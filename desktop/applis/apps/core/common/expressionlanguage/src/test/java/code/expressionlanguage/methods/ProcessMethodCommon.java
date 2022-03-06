package code.expressionlanguage.methods;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.instr.ParsedArgument;
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
import code.expressionlanguage.options.*;
import code.expressionlanguage.sample.CustLgNames;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

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

    protected static ResultContext validate(StringMap<String> _files, AnalyzedPageEl _page, Forwards _forwards) {
        ContextEl ctx_ = ContextFactory.addResourcesAndValidate(_files, "src", _page, _forwards);
        return new ResultContext(ctx_, _page.getMessages());
    }

    protected static Argument calculateError(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        ExecRootBlock classBody_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        feedParams(_args, _cont, method_, p_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_,_class), new ExecTypeFunction(classBody_, method_), p_), _cont, stackCall_);
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
        Argument arg_ = ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_), _cont, stackCall_).getValue();
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
        ProcessMethod.calculate(new CustomFoundConstructor(new ExecFormattedRootBlock(type_,_class), new ExecTypeFunction(type_,ctor_), _global, p_), _cont, stackCall_);
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
        Argument arg_ = ProcessMethod.calculate(new CustomFoundConstructor(new ExecFormattedRootBlock(type_, _class), new ExecTypeFunction(type_, ctor_), _global, p_), _cont, stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        return arg_;
    }
    protected static Argument instanceNormalCtor(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecConstructorBlock ctor_ = get(type_, _id);
        Parameters p_ = new Parameters();
        feedParams(_args, _cont, ctor_, p_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont);
        Argument arg_ = ProcessMethod.calculate(new CustomFoundConstructor(new ExecFormattedRootBlock(type_, _class), new ExecTypeFunction(type_, ctor_), _global, p_), _cont, stackCall_).getValue();
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

    private static StringMap<String> getErrors(ResultContext _report) {
        return _report.getReportedMessages().getErrors();
    }

    private static StringMap<String> validateAndCheckReportErrors(StringMap<String> _files, AnalyzedPageEl _page, Forwards _fwd) {
        ResultContext validate_ = validate(_files,_page,_fwd);
        assertTrue(!isEmptyErrors(_page));
        return getErrors(validate_);
    }

    private static StringMap<String> validateAndCheckReportWarnings(StringMap<String> _files, AnalyzedPageEl _page, Forwards _fwd) {
        ResultContext validate_ = validate(_files,_page,_fwd);
        return getErrors(validate_);
    }

    private static StringMap<String> validateAndCheckNoReportErrors(StringMap<String> _files, AnalyzedPageEl _page, Forwards _fwd) {
        ResultContext validate_ = validate(_files,_page,_fwd);
        assertTrue(isEmptyErrors(_page));
        return getErrors(validate_);
    }

    protected static boolean covErr(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        validate(_files,page_,forwards_);
        return !isEmptyErrors(page_);
    }

    protected static StringMap<String> ctxNotErrReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndCheckNoReportErrors(_files, page_,forwards_);
    }
    protected static StringMap<String> ctxErrReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndCheckReportErrors(_files, page_,forwards_);
    }
    protected static StringMap<String> ctxErrReadOnlyImpl(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        opt_.setDisplayImplicit(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndCheckReportErrors(_files, page_,forwards_);
    }

    protected static StringMap<String> ctxErrStdReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords("en");
        km_.initEnStds(lgName_);
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndCheckReportErrors(_files, page_,forwards_);
    }

    protected static StringMap<String> ctxWarnStdReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        WarningShow warningShow_ = new WarningShow();
        warningShow_.setTernary(true);
        warningShow_.setUnusedParameterStaticMethod(true);
        opt_.setWarningShow(warningShow_);
        opt_.setGettingErrors(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords("en");
        km_.initEnStds(lgName_);
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndCheckReportWarnings(_files, page_,forwards_);
    }

    protected static StringMap<String> ctxErrStdReadOnlyImpl(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        opt_.setDisplayImplicit(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords("en");
        km_.initEnStds(lgName_);
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndCheckReportErrors(_files, page_,forwards_);
    }
    protected static StringMap<String> ctxErrStdReadOnlyImpl2(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        opt_.setDisplayImplicit(true);
        opt_.setEncodeHeader(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords("en");
        km_.initEnStds(lgName_);
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndCheckReportErrors(_files, page_,forwards_);
    }

    protected static StringMap<String> export(ContextEl _cont) {
        return ExecFileBlock.export(_cont);
    }
    protected static ContextEl covEnReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords("en");
        km_.initEnStds(lgName_);
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateCovAndRet(_files, page_,forwards_);
    }

    protected static ContextEl covEnReadOnlyImpl(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        opt_.setDisplayImplicit(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords("en");
        km_.initEnStds(lgName_);
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateCovAndRet(_files, page_,forwards_);
    }

    protected static ContextEl covEnReadOnlyImpl2(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setEncodeHeader(true);
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        opt_.setDisplayImplicit(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords("en");
        km_.initEnStds(lgName_);
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateCovAndRet(_files, page_,forwards_);
    }

    protected static ContextEl covEn(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords("en");
        km_.initEnStds(lgName_);
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateCovAndRet(_files, page_,forwards_);
    }

    protected static ContextEl covValEn(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords("en");
        km_.initEnStds(lgName_);
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static ContextEl contextElEnum(StringMap<String> _files) {
        Options opt_ = newOptions();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        lgName_.getContent().getPredefTypes().setAliasEnumName("name");
        lgName_.getContent().getPredefTypes().setAliasEnumOrdinal("ordinal");
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static boolean ctxMustInitFail(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        validateAll(_files, page_,forwards_);
        ReportedMessages reportedMessages_ = page_.getMessages();
        reportedMessages_.displayErrors();
        return !reportedMessages_.isAllEmptyErrors();
    }

    protected static ContextEl validateAll(StringMap<String> _files, AnalyzedPageEl _cont, Forwards _fwd) {
        return Classes.validateAll(_files, _cont, _fwd);
    }

    protected static ContextEl ctxMustInit(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static ContextEl contextElToString(StringMap<String> _files) {
        Options opt_ = newOptions();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        kw_.setKeyWordToString("toSpecString");
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static boolean ok(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        validateAll(_files, page_,forwards_);
        return isEmptyErrors(page_);
    }

    protected static ContextEl ctxNoErrExp(StringMap<String> _files) {
        Options opt_ = newOptions();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        kw_.setKeyWordNbExpBin("power");
        kw_.setKeyWordNbExpDec("exp");
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static ContextEl contextElDefault(StringMap<String> _files, int _i) {
        Options opt_ = newOptions();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(_i);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static ContextEl contextElTypes(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_, "pkg.ExTwo", "pkg.ExThree", "pkg.ExFour", "Biz");
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static void addTypesInit(Options _opt, String... _types) {
        _opt.getTypesInit().addAllElts(new StringList(_types));
    }

    protected static ContextEl ctxResOk(StringMap<String> _srcFiles, StringMap<String> _all) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        page_.addResources(_all);
        return validateAndRet(_srcFiles, page_,forwards_);
    }

    protected static ContextEl ctxOk(StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        addTypesInit(opt_, _types);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static ContextEl ctxOkRead(StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_, _types);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static ContextEl ctxOkReadSeed(StringMap<String> _files, String _seed) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setSeedElts(_seed);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    private static ContextEl validateAndRet(StringMap<String> _files, AnalyzedPageEl _cont, Forwards _fwd) {
        ContextEl ctx_ = validateAll(_files, _cont, _fwd);
        assertTrue(isEmptyErrors(_cont));
        return ctx_;
    }

    protected static ContextEl ctxLgOk(String _lg,StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        addTypesInit(opt_, _types);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords(_lg);
        if (StringUtil.quickEq(_lg, "en")) {
            km_.initEnStds(lgName_);
        } else {
            km_.initFrStds(lgName_);
        }
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static ContextEl forwardAndClear(Forwards _cont) {
        ContextEl ctx_ = _cont.generate();
        Classes.forwardAndClear(ctx_);
        return ctx_;
    }

    protected static void validateWithoutInit(StringMap<String> _files, AnalyzedPageEl _cont) {
        Classes.validateWithoutInit(_files, _cont);
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

    protected static void postValidation(AnalyzedPageEl _ctx,Forwards _fwd) {
        ClassesUtil.postValidation(_ctx);
        generalForward(_ctx,_fwd);
    }

    protected static void validateEl(AnalyzedPageEl _cont) {
        ClassesUtil.validateEl(_cont);
    }

    protected static void checkInterfaces(AnalyzedPageEl _cont) {
        AnaTypeUtil.checkInterfaces(_cont);
    }


    protected boolean failValidateValue(StringMap<String> _files) {
        Options opt_ = newOptions();

        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        validateWithoutInit(_files, page_);
        ReportedMessages headers_ = page_.getMessages();
        headers_.displayErrors();
        return !isEmptyErrors(page_);
    }

    protected static ContextEl ctxLgReadOnlyOk(String _lg,StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_, _types);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords(_lg);
        if (StringUtil.quickEq(_lg, "en")) {
            km_.initEnStds(lgName_);
        } else {
            km_.initFrStds(lgName_);
        }
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected static boolean hasErrLgReadOnly(String _lg,StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords(_lg);
        if (StringUtil.quickEq(_lg, "en")) {
            km_.initEnStds(lgName_);
        } else {
            km_.initFrStds(lgName_);
        }
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return invalid(_files, page_);
    }

    protected static ContextEl ctxReadOnlyOk(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);

        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
    }

    protected ContextEl validateStaticFields(StringMap<String> _files) {
        Options opt_ = newOptions();

        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validQuick(_files, page_,forwards_);
    }

    protected static Struct getStaticField(ContextEl _ctx, ClassField _id) {
        StringMap<StringMap<Struct>> staticFields_ = _ctx.getClasses().getStaticFields();
        return NumParsers.getStaticField(_id, staticFields_);
    }

    protected static ContextEl validQuick(StringMap<String> _files, AnalyzedPageEl _cont, Forwards _fwd) {
        validateWithoutInit(_files, _cont);
        assertTrue( isEmptyErrors(_cont));
        generalForward(_cont,_fwd);
        return forwardAndClear(_fwd);
    }

    protected static void generalForward(AnalyzedPageEl _cont, Forwards _fwd) {
        ForwardInfos.generalForward(_cont, _fwd);
    }

    protected StringMap<StringMap<Struct>> validateStaticFieldsFail(StringMap<String> _files) {
        Options opt_ = newOptions();

        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        validateWithoutInit(_files, page_);
        assertTrue(!isEmptyErrors(page_));
        return forwards_.getStaticFields();
    }

    protected boolean failValidateInheritingClassesValue(StringMap<String> _files) {
        Options opt_ = newOptions();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        parseCustomFiles(_files, page_);
        assertTrue( isEmptyErrors(page_));
        validateInheritingClasses(page_);
        return !isEmptyErrors(page_);
    }

    protected boolean failValidateInheritingClassesSingleValue(StringMap<String> _files) {
        Options opt_ = newOptions();

        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        parseCustomFiles(_files, page_);
        assertTrue( isEmptyErrors(page_));
        validateInheritingClasses(page_);
        return !isEmptyErrors(page_);
    }

    protected static void parseCustomFiles(StringMap<String> _files, AnalyzedPageEl _cont) {
        ClassesUtil.tryBuildAllBracedClassesBodies(_files, _cont);
    }

    protected static void validateInheritingClassesId(AnalyzedPageEl _cont) {
        ClassesUtil.validateInheritingClassesId(_cont);
    }

    protected static Options newOptions() {
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
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateCovAndRet(_files, page_,forwards_);
    }

    protected static ContextEl covReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateCovAndRet(_files, page_,forwards_);
    }

    protected static ContextEl covReadOnlyImpl(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        opt_.setDisplayImplicit(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateCovAndRet(_files, page_, forwards_);
    }

    protected static ContextEl covDisplay(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        page_.getDisplayedStrings().setTrueString("\"");
        page_.getDisplayedStrings().setFalseString("&");
        return validateCovAndRet(_files, page_,forwards_);
    }

    protected static ContextEl covCom(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));
        opt_.setCovering(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateCovAndRet(_files, page_,forwards_);
    }

    protected static ContextEl covEnCom(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));
        opt_.setCovering(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords("en");
        km_.initEnStds(lgName_);
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateCovAndRet(_files, page_,forwards_);
    }

    protected static ContextEl covVal(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateCovAndRet(_files, page_,forwards_);
    }


    private static ContextEl validateCovAndRet(StringMap<String> _files, AnalyzedPageEl _page, Forwards _fwd) {
        ResultContext ctx_ = validate(_files, _page, _fwd);
        assertTrue(isEmptyErrors(_page));
        return ctx_.getContext();
    }

    protected static ContextEl covVal2(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setCovering(true);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return validateAndRet(_files, page_,forwards_);
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
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        ContextEl ctx_ = validateAll(_files, page_,forwards_);
        ReportedMessages methodHeaders_ = page_.getMessages();
        assertTrue(isEmptyErrors(page_));
        assertTrue(methodHeaders_.displayMessageErrors()+methodHeaders_.displayErrors()+methodHeaders_.displayStdErrors()+methodHeaders_.displayWarnings(),!methodHeaders_.isEmptyWarnings());
        return ctx_;
    }

    protected static boolean hasErrReadOnly(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);

        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return invalid(_files, page_);
    }

    protected static boolean hasErrDefCom(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));

        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return invalid(_files, page_);
    }

    private static boolean invalid(StringMap<String> _files, AnalyzedPageEl _cont) {
        validateWithoutInit(_files, _cont);
        return !isEmptyErrors(_cont);
    }

    protected static boolean hasErr(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kw_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return invalid(_files, page_);
    }

    protected static boolean hasErrLg(StringMap<String> _files, String _lg) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords(_lg);
        if (StringUtil.quickEq(_lg, "en")) {
            km_.initEnStds(lgName_);
        } else {
            km_.initFrStds(lgName_);
        }
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(4);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        AnalysisMessages.validateMessageContents(a_.allMessages(), page_);
        ContextFactory.validatedStds(forwards_, a_, kwl_, new CustList<CommentDelimiters>(), opt_, lgName_.getContent(), page_);
        ParsedArgument.buildCustom(opt_, kwl_);
        lgName_.build();
        ValidatorStandard.setupOverrides(page_);
        assertTrue(page_.isEmptyStdError());
        return invalid(_files, page_);
    }

    protected static boolean isSuccessfulInitialized(ContextEl _cont, String _s) {
        return _cont.getLocks().getState(_s) == InitClassState.SUCCESS;
    }

    protected static boolean isEmptyErrors(AnalyzedPageEl _cont) {
        return _cont.isEmptyErrors();
    }

    protected static boolean isInitialized(ContextEl _cont, String _cl) {
        return _cont.getLocks().getState(_cl) != InitClassState.NOT_YET;
    }

}
