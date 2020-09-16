package code.expressionlanguage.methods;

import code.expressionlanguage.AnalyzedTestContext;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.files.CommentDelimiters;
import code.expressionlanguage.files.FileResolver;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

    protected static ReportedMessages validate(StringMap<String> _files, ContextEl _contextEl, AnalyzedPageEl _page) {
        return ContextFactory.addResourcesAndValidate(_files,_contextEl,"src", _page);
    }

    protected static Argument calculateError(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        MethodId fct_ = new MethodId(_method.getKind(), _method.getName(),_method.getParametersTypes());
        ExecRootBlock classBody_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecBlock.getMethodBodiesById(classBody_, fct_).first();
        ExecBlock firstChild_ = method_.getFirstChild();
        if (firstChild_ == null) {
            return new Argument();
        }
        Argument argGlLoc_ = new Argument();
        int i_ = CustList.FIRST_INDEX;
        Parameters p_ = new Parameters();
        for (Argument a: _args) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(a.getStruct(),_cont);
            p_.getParameters().addEntry(method_.getParametersNames().get(i_),lv_);
            i_++;
        }
        ProcessMethod.calculateArgument(argGlLoc_, _class, classBody_, method_, p_, _cont);
        Struct exc_ = getException(_cont);
        assertNotNull(exc_);
        return new Argument(exc_);
    }
    protected static Argument calculateNormal(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        MethodId fct_ = new MethodId(_method.getKind(), _method.getName(),_method.getParametersTypes());
        ExecRootBlock classBody_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecBlock.getMethodBodiesById(classBody_, fct_).first();
        ExecBlock firstChild_ = method_.getFirstChild();
        if (firstChild_ == null) {
            return new Argument();
        }
        Argument argGlLoc_ = new Argument();
        int i_ = CustList.FIRST_INDEX;
        Parameters p_ = new Parameters();
        for (Argument a: _args) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(a.getStruct(),_cont);
            p_.getParameters().addEntry(method_.getParametersNames().get(i_),lv_);
            i_++;
        }
        Argument arg_ = ProcessMethod.calculateArgument(argGlLoc_, _class, classBody_, method_, p_, _cont);
        assertNull(getException(_cont));
        return arg_;
    }

    protected static MethodId getMethodId(String _name, String..._classNames) {
        StringList cl_ = new StringList();
        for (String c: _classNames) {
            cl_.add(c);
        }
        return new MethodId(MethodAccessKind.STATIC, _name, cl_);
    }

    protected static Argument instanceError(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        int len_ = _id.getParametersTypes().size();
        StringList constraints_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = _id.getParametersTypes().get(i);
            constraints_.add(n_);
        }
        ConstructorId id_ = new ConstructorId(_id.getName(),constraints_, false);
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecConstructorBlock ctor_ = tryGet(_class, _cont, id_);
        int i_ = CustList.FIRST_INDEX;
        Parameters p_ = new Parameters();
        if (ctor_ != null) {
            for (Argument a : _args) {
                LocalVariable lv_ = LocalVariable.newLocalVariable(a.getStruct(), _cont);
                p_.getParameters().addEntry(ctor_.getParametersNames().get(i_), lv_);
                i_++;
            }
        }
        Argument arg_ = ProcessMethod.instanceArgument(_class, type_, _global, ctor_, p_, _cont);
        assertNotNull(getException(_cont));
        return arg_;
    }
    protected static Argument instanceNormal(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        int len_ = _id.getParametersTypes().size();
        StringList constraints_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = _id.getParametersTypes().get(i);
            constraints_.add(n_);
        }
        ConstructorId id_ = new ConstructorId(_id.getName(),constraints_, false);
        ExecRootBlock type_ = _cont.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecConstructorBlock ctor_ = tryGet(_class, _cont, id_);
        int i_ = CustList.FIRST_INDEX;
        Parameters p_ = new Parameters();
        if (ctor_ != null) {
            for (Argument a : _args) {
                LocalVariable lv_ = LocalVariable.newLocalVariable(a.getStruct(), _cont);
                p_.getParameters().addEntry(ctor_.getParametersNames().get(i_), lv_);
                i_++;
            }
        }
        Argument arg_ = ProcessMethod.instanceArgument(_class, type_, _global, ctor_, p_, _cont);
        assertNull(getException(_cont));
        return arg_;
    }

    private static ExecConstructorBlock tryGet(String _class, ContextEl _cont, ConstructorId id_) {
        CustList<ExecConstructorBlock> list_ = getConstructorBodiesById(_cont, _class, id_);
        if (list_.isEmpty()) {
            return null;
        }
        return list_.first();
    }

    protected static ConstructorId getConstructorId(String _name, String..._classNames) {
        StringList cl_ = new StringList();
        for (String c: _classNames) {
            cl_.add(c);
        }
        return new ConstructorId(_name, cl_, false);
    }

    protected static AnalyzedTestContext contextElCoverageDefaultEnComment() {
        Options opt_ = new Options();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }
    protected static AnalyzedTestContext contextElCoverageDefaultComment() {
        Options opt_ = new Options();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }
    protected static AnalyzedTestContext contextElErrorReadOnlyDef() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    private static StringMap<String> getErrors(ReportedMessages _report) {
        return _report.getErrors();
    }

    public static StringMap<String> validateAndCheckReportErrors(StringMap<String> _files, AnalyzedTestContext _cont) {
        ReportedMessages report_ = validate(_cont, _files);
        assertTrue(!isEmptyErrors(_cont));
        return getErrors(report_);
    }

    public static StringMap<String> validateAndCheckNoReportErrors(StringMap<String> _files, AnalyzedTestContext _cont) {
        ReportedMessages report_ = validate(_cont, _files);
        assertTrue(isEmptyErrors(_cont));
        return getErrors(report_);
    }

    protected static boolean covErr(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElCoverageDefAna();
        validate(cont_,files_);
        return !isEmptyErrors(cont_);
    }

    protected static StringMap<String> ctxNotErrReadOnly(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElErrorReadOnlyDef();
        return validateAndCheckNoReportErrors(files_, cont_);
    }
    protected static StringMap<String> ctxErrReadOnly(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElErrorReadOnlyDef();
        return validateAndCheckReportErrors(files_, cont_);
    }

    protected static StringMap<String> ctxErrStdReadOnly(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElErrorStdReadOnlyDef();
        return validateAndCheckReportErrors(files_, cont_);
    }
    protected static AnalyzedTestContext contextElErrorStdReadOnlyDef() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        opt_.setGettingErrors(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }
    protected static AnalyzedTestContext contextElCoverageReadOnlyDef() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    protected static AnalyzedTestContext contextElCoverageDisplayDef() {
        Options opt_ = new Options();
        opt_.setCovering(true);
        AnalyzedTestContext ct_ = InitializationLgNames.buildStdOneAna(opt_);
        ct_.getStandards().getDisplayedStrings().setTrueString("\"");
        ct_.getStandards().getDisplayedStrings().setFalseString("&");
        return ct_;
    }

    protected static ContextEl contextElCoverageDef() {
        Options opt_ = new Options();
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOne(opt_);
    }
    protected static AnalyzedTestContext contextElCoverageDefAna() {
        Options opt_ = new Options();
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna(opt_);
    }

    protected static ContextEl covEnReadOnly(StringMap<String> files_) {
        AnalyzedTestContext cont_ = ontextElCoverageReadOnlyEn();
        validateAndCheckValid(files_, cont_);
        return cont_.getContext();
    }

    protected static AnalyzedTestContext ontextElCoverageReadOnlyEn() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }

    protected static ContextEl covEn(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElCoverageEnAna();
        validateAndCheckValid(files_, cont_);
        return cont_.getContext();
    }
    protected static ContextEl contextElCoverageEn() {
        Options opt_ = new Options();
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOne("en",opt_);
    }
    protected static AnalyzedTestContext contextElCoverageEnAna() {
        Options opt_ = new Options();
        opt_.setCovering(true);
        return InitializationLgNames.buildStdOneAna("en",opt_);
    }

    protected static ContextEl contextElEnum() {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdEnums(opt_);
    }

    protected static ContextEl contextElReadOnlyMustInit() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        opt_.setFailIfNotAllInit(true);
        return InitializationLgNames.buildStdOne(opt_);
    }
    protected static ContextEl contextElToString() {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdToString(opt_);
    }
    protected static ContextEl contextElExp() {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdExp(opt_);
    }

    protected static ContextEl ctxLgReadOnly(String fr) {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        return InitializationLgNames.buildStdOne(fr, opt_);
    }

    protected static ContextEl contextElDefault(int _m) {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdOne(_m, opt_);
    }

    protected static ContextEl contextElTypes(String... _types) {
        Options opt_ = new Options();
        for (String t: _types) {
            opt_.getTypesInit().add(t);
        }
        return InitializationLgNames.buildStdOne(opt_);
    }

    protected static ContextEl ctx() {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdOne(opt_);
    }

    protected static ContextEl ctxLg(String _lg) {
        Options opt_ = new Options();
        return InitializationLgNames.buildStdOne(_lg, opt_);
    }

    protected static ContextEl getEnContextElComment() {
        Options opt_ = new Options();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));


        return InitializationLgNames.buildStdOne("en", opt_);
    }

    private static CustList<ExecConstructorBlock> getConstructorBodiesById(ContextEl _context, String _genericClassName, ConstructorId _id) {
        CustList<ExecConstructorBlock> methods_ = new CustList<ExecConstructorBlock>();
        String base_ = StringExpUtil.getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        for (EntryCust<String, ExecRootBlock> c: classes_.getClassesBodies().entryList()) {
            if (!StringList.quickEq(c.getKey(), base_)) {
                continue;
            }
            CustList<ExecBlock> bl_ = ExecBlock.getDirectChildren(c.getValue());
            for (ExecBlock b: bl_) {
                if (!(b instanceof ExecConstructorBlock)) {
                    continue;
                }
                ExecConstructorBlock method_ = (ExecConstructorBlock) b;
                if (!method_.getId().eq(_id)) {
                    continue;
                }
                methods_.add(method_);
            }
        }
        return methods_;
    }

    private static CustList<ExecOverridableBlock> getDeepMethodExecBlocks(ExecRootBlock _element) {
        CustList<ExecOverridableBlock> methods_ = new CustList<ExecOverridableBlock>();
        for (ExecBlock b: ExecBlock.getDirectChildren(_element)) {
            if (b instanceof ExecOverridableBlock) {
                methods_.add((ExecOverridableBlock) b);
            }
        }
        return methods_;
    }

    protected boolean failValidateValue(StringMap<String> _files) {
        Options opt_ = new Options();

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateWithoutInit(_files, cont_);
        ReportedMessages headers_ = cont_.getAnalyzing().getMessages();
        headers_.displayErrors();
        return !isEmptyErrors(cont_);
    }

    protected static ContextEl ctxReadOnly() {
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        return InitializationLgNames.buildStdOne(opt_);
    }

    protected static ContextEl contextElSingleDotDefaultComment() {
        Options opt_ = new Options();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));

        return InitializationLgNames.buildStdOne(opt_);
    }

    protected ContextEl validateStaticFields(StringMap<String> _files) {
        Options opt_ = new Options();

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateWithoutInit(_files,cont_);
        assertTrue( isEmptyErrors(cont_));
        Classes.forwardAndClear(cont_,cont_.getAnalyzing());
        return cont_;
    }

    protected ContextEl validateStaticFieldsFail(StringMap<String> _files) {
        Options opt_ = new Options();

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateWithoutInit(_files,cont_);
        assertTrue(! isEmptyErrors(cont_));
        return cont_;
    }
    protected static AnalyzedTestContext unfullValidateInheriting(StringMap<String> _files) {
        Options opt_ = new Options();

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        parseCustomFiles(_files, cont_);
        assertTrue( isEmptyErrors(cont_));
        ClassesUtil.validateInheritingClasses(cont_);
        assertTrue( isEmptyErrors(cont_));
        return new AnalyzedTestContext(cont_,cont_.getAnalyzing());
    }

    protected boolean failValidateInheritingClassesValue(StringMap<String> _files) {
        Options opt_ = new Options();
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        parseCustomFiles(_files, cont_);
        assertTrue( isEmptyErrors(cont_));
        ClassesUtil.validateInheritingClasses(cont_);
        return !isEmptyErrors(cont_);
    }

    protected boolean failValidateInheritingClassesSingleValue(StringMap<String> _files) {
        Options opt_ = new Options();

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        parseCustomFiles(_files, cont_);
        assertTrue( isEmptyErrors(cont_));
        ClassesUtil.validateInheritingClasses(cont_);
        return !isEmptyErrors(cont_);
    }

    protected static void parseCustomFiles(StringMap<String> _files, ContextEl _cont) {
        ClassesUtil.tryBuildAllBracedClassesBodies(_files,_cont, new StringMap<ExecFileBlock>());
    }

    protected static ContextEl simpleCtx() {
        Options opt_ = new Options();
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        AnalyzedPageEl page_ = cont_.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        for (EntryCust<String, String> e: stds_.buildFiles(page_).entryList()) {
            String name_ = e.getKey();
            String content_ = e.getValue();
            parseFile(cont_, name_, true, content_);
        }
        return cont_;
    }

    protected static void parseFile(StringBuilder file_, ContextEl context_, String _myFile, boolean _predefined) {
        String content_ = file_.toString();
        parseFile(context_, _myFile, _predefined, content_);
    }

    protected static void parseFile(ContextEl context_, String _fileName, boolean _predefined, String _file) {
        FileBlock fileBlock_ = new FileBlock(new OffsetsBlock(),_predefined);
        fileBlock_.setFileName(_fileName);
        context_.getAnalyzing().putFileBlock(_fileName, fileBlock_);
        context_.getCoverage().putFile(fileBlock_);
        context_.getAnalyzing().getErrors().putFile(context_,fileBlock_);
        fileBlock_.processLinesTabsWithError(context_,_file);
        if (fileBlock_.getBinChars().isEmpty()) {
            FileResolver.parseFile(fileBlock_, _fileName, _file, context_);
        }
        StringList basePkgFound_ = context_.getAnalyzing().getBasePackagesFound();
        basePkgFound_.addAllElts(fileBlock_.getAllBasePackages());
        StringList pkgFound_ = context_.getAnalyzing().getPackagesFound();
        pkgFound_.addAllElts(fileBlock_.getAllPackages());
        ExecFileBlock exFile_ = new ExecFileBlock(fileBlock_);
        ClassesUtil.fetchByFile(context_,basePkgFound_,pkgFound_,fileBlock_,exFile_);
    }

    protected static ContextEl simpleCtxComment() {
        Options opt_ = new Options();
        opt_.getComments().add(new CommentDelimiters("\\\\",new StringList("\r\n","\r","\n")));
        opt_.getComments().add(new CommentDelimiters("\\*",new StringList("*\\")));
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        AnalyzedPageEl page_ = cont_.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        for (EntryCust<String, String> e: stds_.buildFiles(page_).entryList()) {
            String name_ = e.getKey();
            String content_ = e.getValue();
            parseFile(cont_, name_, true, content_);
        }
        return cont_;
    }

    protected static Struct getField(Struct _struct, ClassField _key) {
        return ClassFieldStruct.getPair(((FieldableStruct)_struct).getFields(),_key).getStruct();
    }

    protected static Struct getException(ContextEl _cont) {
        CallingState str_ = _cont.getCallingState();
        if (str_ instanceof Struct) {
            return (Struct) str_;
        }
        return null;
    }

    protected static ContextEl cov(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElCoverageDefAna();
        validateAndCheckValid(files_, cont_);
        return cont_.getContext();
    }

    protected static ContextEl covReadOnly(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElCoverageReadOnlyDef();
        validateAndCheckValid(files_, cont_);
        return cont_.getContext();
    }

    protected static ContextEl covDisplay(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElCoverageDisplayDef();
        validateAndCheckValid(files_, cont_);
        return cont_.getContext();
    }

    protected static ContextEl covCom(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElCoverageDefaultComment();
        validateAndCheckValid(files_, cont_);
        return cont_.getContext();
    }

    protected static ContextEl covEnCom(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElCoverageDefaultEnComment();
        validateAndCheckValid(files_, cont_);
        return cont_.getContext();
    }

    protected static ContextEl covVal(StringMap<String> files_) {
        AnalyzedTestContext cont_ = contextElCoverageDefAna();
        validate(cont_,files_);
        assertTrue(isEmptyErrors(cont_));
        return cont_.getContext();
    }

    protected static void validateAndCheckValid(StringMap<String> _files, AnalyzedTestContext _cont) {
        validate(_cont, _files);
        assertTrue(isEmptyErrors(_cont));
    }

    protected static ReportedMessages validate(AnalyzedTestContext _c, StringMap<String> _f) {
        return validate(_f,_c.getContext(), _c.getAnalyzing());
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
        return filterDeep(getDeepMethodBodies(_context,_genericClassName),_id);
    }
    private static CustList<ExecOverridableBlock> getDeepMethodBodies(ContextEl _context,String _genericClassName) {
        CustList<ExecOverridableBlock> methods_ = new CustList<ExecOverridableBlock>();
        String base_ = StringExpUtil.getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        ExecRootBlock r_ = classes_.getClassBody(base_);
        for (ExecOverridableBlock m: getDeepMethodExecBlocks(r_)) {
            methods_.add(m);
        }
        return methods_;
    }
    private static CustList<ExecOverridableBlock> filterDeep(CustList<ExecOverridableBlock> _methods,MethodId _id) {
        CustList<ExecOverridableBlock> methods_ = new CustList<ExecOverridableBlock>();
        for (ExecOverridableBlock m: _methods) {
            if (((GeneMethod)m).getId().eq(_id)) {
                methods_.add(m);
            }
        }
        return methods_;
    }

    protected static boolean isEmptyErrors(ContextEl cont_) {
        return cont_.getAnalyzing() == null || (cont_.getAnalyzing().isEmptyErrors());
    }

    protected static boolean isEmptyErrors(AnalyzedTestContext cont_) {
        return cont_.getAnalyzing() == null || (cont_.getAnalyzing().isEmptyErrors());
    }
}
