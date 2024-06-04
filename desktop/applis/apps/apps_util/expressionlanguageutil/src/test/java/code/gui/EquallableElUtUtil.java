package code.gui;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.AdvContextGenerator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.dbg.DbgStackStopper;
import code.expressionlanguage.exec.dbg.DefLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.guicompos.GuiFileBuilder;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractLightProgramInfos;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.Rate;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.sml.Node;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.stream.BytesInfo;
import code.stream.core.ContentTime;
import code.stream.core.OutputType;
import code.stream.core.StreamZipFile;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableElUtUtil {
    public static void assertNull(byte[] _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractImage _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(String _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(Struct _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(Runnable _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(StringMap<ContentTime> _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertEq(String _expected, Struct _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, ((CharSequenceStruct)_result).toStringInstance());
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(Rate _exp, Rate _result) {
        assertEq(_exp.toNumberString(),_result.toNumberString());
        assertTrue(_exp.eq(_result));
    }
    public static void assertSame(OutputType _expected, OutputType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(MockLayout _expected, MockLayout _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Struct _expected, Struct _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(double _expected, Struct _result) {
        Assert.assertEquals(Double.toString(_expected),Double.toString(((DoubleStruct)_result).doubleStruct()));
    }

    public static void assertNull(Node _value) {
        Assert.assertNull(_value);
    }

    public static void assertTrue(Struct _value) {
        Assert.assertTrue(BooleanStruct.isTrue(_value));
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(Struct _value) {
        Assert.assertTrue(BooleanStruct.isFalse(_value));
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static LgNamesGui newLgNamesGuiSampleFull(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(),_light.getLanguage(),"en");
//        stds_.getExecContent().getCustAliases().build(new StringMap<String>(),new StringMap<String>(),new StringMap<String>());
//        basicStandards(stds_);
        return stds_;
    }
    public static LgNamesGui newLgNamesGuiSample(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGuiLight(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(),_light.getLanguage(),"en");
//        stds_.getExecContent().getCustAliases().build(new StringMap<String>(),new StringMap<String>(),new StringMap<String>());
//        basicStandards(stds_);
        return stds_;
    }

    public static MockLgNames mock(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        MockLgNames stds_ = new MockLgNames();
        TranslationsFile en_ = new TranslationsFile();
        LgNamesContent.en(en_);
        StringViewReplaceAliases.en(en_);
//        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        stds_.getContent().build(TranslationsFile.extractMap(en_),new StringMap<String>(), TranslationsFile.extractKeys(en_));
        stds_.getStrAlias().build(TranslationsFile.extractMap(en_),new StringMap<String>(), TranslationsFile.extractKeys(en_));
//        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
//        stds_.getExecContent().updateTranslations(_light.getTranslations(),_light.getLanguage(),"en");
//        stds_.getExecContent().getCustAliases().build(new StringMap<String>(),new StringMap<String>(),new StringMap<String>());
//        basicStandards(stds_);
        return stds_;
    }
    public static LgNamesUtils newLgNamesUtSample(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesUtils stds_ = newLgNamesUt(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(),_light.getLanguage(),"en");
//        stds_.getExecContent().getCustAliases().build(new StringMap<String>(),new StringMap<String>(),new StringMap<String>());
//        basicStandards(stds_);
        return stds_;
    }

    public static LgNamesUtils newLgNamesUtSampleLight(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesUtils stds_ = newLgNamesUtLight(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(),_light.getLanguage(),"en");
//        stds_.getExecContent().getCustAliases().build(new StringMap<String>(),new StringMap<String>(),new StringMap<String>());
//        basicStandards(stds_);
        return stds_;
    }
    public static LgNamesGui newLgNamesGuiSampleCl(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGuiLight(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(),_light.getLanguage(),"");
        return stds_;
    }
    public static LgNamesGui newLgNamesGui(AbstractLightProgramInfos _light, AbstractIssuer _issuer, String _conf, String _src, StringMap<ContentTime> _files) {
        byte[] zipped_ = _light.getZipFact().zipBinFiles(_files);
        FileInfos infos_ = FileInfos.buildMemoryFromFile(_light, _light.getGenerator(), _light.getValidator(), _issuer, new MemInputFiles(StringUtil.encode(_conf), new BytesInfo(StringUtil.encode(_src),false), new BytesInfo(GuiConstants.nullToEmpty(zipped_),false)), _light.getZipFact(), _light.getThreadFactory());
        return new LgNamesGui(infos_, new MockInterceptor());
    }
    public static LgNamesGui newLgNamesGuiLight(AbstractLightProgramInfos _light, AbstractIssuer _issuer, String _conf, String _src, StringMap<ContentTime> _files) {
        byte[] zipped_ = _light.getZipFact().zipBinFiles(_files);
        FileInfos infos_ = FileInfos.buildMemoryFromFile(_light, _light.getGenerator(), _light.getValidator(), _issuer, new MemInputFiles(StringUtil.encode(_conf), new BytesInfo(StringUtil.encode(_src),false), new BytesInfo(GuiConstants.nullToEmpty(zipped_),false)), _light.getZipFact(), _light.getThreadFactory());
        return new LgNamesGui(infos_, new MockInterceptor());
    }
    public static LgNamesUtils newLgNamesUt(AbstractLightProgramInfos _light, AbstractIssuer _issuer, String _conf, String _src, StringMap<ContentTime> _files) {
        byte[] zipped_ = _light.getZipFact().zipBinFiles(_files);
        FileInfos infos_ = FileInfos.buildMemoryFromFile(_light, _light.getGenerator(), _light.getValidator(), _issuer, new MemInputFiles(StringUtil.encode(_conf), new BytesInfo(StringUtil.encode(_src),false), new BytesInfo(GuiConstants.nullToEmpty(zipped_),false)), _light.getZipFact(), _light.getThreadFactory());
        return new LgNamesUtils(infos_, new MockInterceptor());
    }

    public static LgNamesUtils newLgNamesUtLight(AbstractLightProgramInfos _light, AbstractIssuer _issuer, String _conf, String _src, StringMap<ContentTime> _files) {
        byte[] zipped_ = _light.getZipFact().zipBinFiles(_files);
        FileInfos infos_ = FileInfos.buildMemoryFromFile(_light, _light.getGenerator(), _light.getValidator(), _issuer, new MemInputFiles(StringUtil.encode(_conf), new BytesInfo(StringUtil.encode(_src),false), new BytesInfo(GuiConstants.nullToEmpty(zipped_),false)), _light.getZipFact(), _light.getThreadFactory());
        return new LgNamesUtils(infos_, new MockInterceptor());
    }
    public static ArgumentListCall one(Struct _arg) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_arg));
        return new ArgumentListCall(ls_);
    }
    public static ArgumentListCall two(Struct _first, Struct _second) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        return new ArgumentListCall(ls_);
    }

    public static ArgumentListCall three(Struct _first, Struct _second, Struct _third) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        ls_.add(new ArgumentWrapper(_third));
        return new ArgumentListCall(ls_);
    }

    public static ArgumentListCall four(Struct _first, Struct _second, Struct _third, Struct _fourth) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        ls_.add(new ArgumentWrapper(_third));
        ls_.add(new ArgumentWrapper(_fourth));
        return new ArgumentListCall(ls_);
    }

    public static ArgumentListCall five(Struct _first, Struct _second, Struct _third, Struct _fourth, Struct _fifth) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        ls_.add(new ArgumentWrapper(_third));
        ls_.add(new ArgumentWrapper(_fourth));
        ls_.add(new ArgumentWrapper(_fifth));
        return new ArgumentListCall(ls_);
    }

    public static ArgumentListCall six(Struct _first, Struct _second, Struct _third, Struct _fourth, Struct _fifth, Struct _sixth) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        ls_.add(new ArgumentWrapper(_third));
        ls_.add(new ArgumentWrapper(_fourth));
        ls_.add(new ArgumentWrapper(_fifth));
        ls_.add(new ArgumentWrapper(_sixth));
        return new ArgumentListCall(ls_);
    }

    public static ArgumentListCall seven(Struct _first, Struct _second, Struct _third, Struct _fourth, Struct _fifth, Struct _sixth, Struct _seventh) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        ls_.add(new ArgumentWrapper(_third));
        ls_.add(new ArgumentWrapper(_fourth));
        ls_.add(new ArgumentWrapper(_fifth));
        ls_.add(new ArgumentWrapper(_sixth));
        ls_.add(new ArgumentWrapper(_seventh));
        return new ArgumentListCall(ls_);
    }

    public static ArgumentListCall eight(Struct _first, Struct _second, Struct _third, Struct _fourth, Struct _fifth, Struct _sixth, Struct _seventh, Struct _eighth) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        ls_.add(new ArgumentWrapper(_third));
        ls_.add(new ArgumentWrapper(_fourth));
        ls_.add(new ArgumentWrapper(_fifth));
        ls_.add(new ArgumentWrapper(_sixth));
        ls_.add(new ArgumentWrapper(_seventh));
        ls_.add(new ArgumentWrapper(_eighth));
        return new ArgumentListCall(ls_);
    }

    public static ArgumentListCall nine(Struct _first, Struct _second, Struct _third, Struct _fourth, Struct _fifth, Struct _sixth, Struct _seventh, Struct _eighth, Struct _ninth) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        ls_.add(new ArgumentWrapper(_third));
        ls_.add(new ArgumentWrapper(_fourth));
        ls_.add(new ArgumentWrapper(_fifth));
        ls_.add(new ArgumentWrapper(_sixth));
        ls_.add(new ArgumentWrapper(_seventh));
        ls_.add(new ArgumentWrapper(_eighth));
        ls_.add(new ArgumentWrapper(_ninth));
        return new ArgumentListCall(ls_);
    }

    public static ArgumentListCall ten(Struct _first, Struct _second, Struct _third, Struct _fourth, Struct _fifth, Struct _sixth, Struct _seventh, Struct _eighth, Struct _ninth, Struct _tenth) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        ls_.add(new ArgumentWrapper(_third));
        ls_.add(new ArgumentWrapper(_fourth));
        ls_.add(new ArgumentWrapper(_fifth));
        ls_.add(new ArgumentWrapper(_sixth));
        ls_.add(new ArgumentWrapper(_seventh));
        ls_.add(new ArgumentWrapper(_eighth));
        ls_.add(new ArgumentWrapper(_ninth));
        ls_.add(new ArgumentWrapper(_tenth));
        return new ArgumentListCall(ls_);
    }
    public static long toLong(Struct _str) {
        return ((NumberStruct)_str).longStruct();
    }
    public static Struct call(StdCaller _caller, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return value(_caller.call(_exit, _cont, _instance, _firstArgs, _stackCall));
    }

    public static Struct call(DfInstancer _caller, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return value(_caller.call(_exit, _cont, _firstArgs, _stackCall));
    }

    public static Struct value(ArgumentWrapper _a) {
        return _a.getValue().getStruct();
    }
    public static AbstractThreadFactory newFileInfos(AbstractLightProgramInfos _light) {
        return _light.getThreadFactory();
    }
    public static StackCall stack(Struct _sensible, InitPhase _phase) {
        StackCall st_ = new StackCall(new DefStackStopper(),_phase,new CustomSeedGene());
        st_.getInitializingTypeInfos().getSensibleFields().add(_sensible);
        return st_;
    }
    public static StackCall stack(ContextEl _phase) {
        return StackCall.newInstance(InitPhase.NOTHING,_phase);
    }
    public static StackCall stackLogger(ContextEl _phase) {
        return ResultContextLambda.newInstance(new DefStackStopper(new DefLogDbg()),_phase,InitPhase.NOTHING);
    }
    public static StringMap<ContentTime> init() {
        return new StringMap<ContentTime>();
    }
    public static StringMap<ContentTime> with(AbstractLightProgramInfos _light, StringMap<ContentTime> _all, String _name, String _content) {
        _all.put(_name,new ContentTime(StringUtil.encode(_content),_light.getThreadFactory().millis()));
        return _all;
    }

    protected static Forwards getForwards(LgNamesGui _lgName, Options _opt) {
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(_lgName.getContent(), _lgName.getGuiAliases(), _lgName.getExecContent().getCustAliases());
        return new Forwards(_lgName,_lgName.getExecContent(), fileBuilder_, _opt);
    }


    public static void updateBuilders(LgNamesGui _stds, AnalyzedPageEl _page) {
        CustList<AbsAliasFileBuilder> builders_ = new CustList<AbsAliasFileBuilder>();
        builders_.add(new DefAliasFileBuilder());
        builders_.add(_stds.getExecContent().getCustAliases());
        builders_.add(_stds.getExecContent().getCustAliases().getStringViewReplaceAliases());
        builders_.add(_stds.getGuiAliases());
        CustList<AbsAliasFileBuilder> fbs_ = _page.getFileBuilders();
        fbs_.clear();
        fbs_.addAllElts(builders_);
    }

    protected static Forwards getForwards(LgNamesUtils _lgName, Options _opt) {
        CustFileBuilder fileBuilder_ = new CustFileBuilder(new CustAliasGroups(_lgName.getExecContent().getCustAliases(), _lgName.getContent()));
        return new Forwards(_lgName,_lgName.getExecContent(), fileBuilder_, _opt);
    }
    public static MockProgramInfos newMockProgramInfos(CustomSeedGene _s, MockFileSet _set) {
        return new MockProgramInfos("", "", _s, _set);
    }
    public static void update(MockProgramInfos _pr) {
        FileInfos.enTr(FileInfos.initComments(lg(_pr,FileInfos.EN)));
        FileInfos.frTr(FileInfos.initComments(lg(_pr,FileInfos.FR)));
        _pr.setLanguage("en");
    }
    public static TranslationsLg lg(MockProgramInfos _pr,String _key) {
        return _pr.lg(_key);
    }
    public static MockFileSet fileSet(long _initMillis, long[] _incrs, String... _roots) {
        return new MockFileSet(_initMillis,_incrs,_roots);
    }
    public static Struct str(Argument _arg) {
        return _arg.getStruct();
    }
    public static void memoryFileSystem(LgNamesGui _stds,AbstractProgramInfos _pr, MockNameFile... _files) {
        AbstractFileSystem m_ = _stds.getExecContent().getCustAliases().getInfos().getFileSystem();
        m_.build(_stds.getExecContent().getExecutingOptions(), StreamZipFile.getZippedBinFiles(new BytesInfo(_pr.getZipFact().zipBinFiles(MockZipFact.wrapText(_files)),false), _pr.getZipFact()));
    }
    public static double[] dbs(double... _args) {
        return _args;
    }
    public static long[] lgs(long... _args) {
        return _args;
    }
    public static byte[] wrapInts(int... _files) {
        return NumberUtil.wrapByteArray(MockZipFact.wrapInts(_files));
    }
    public static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }

    public static ResultContext build(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(_definedLgNames.getContent(), _definedLgNames.getGuiAliases(), _definedLgNames.getExecContent().getCustAliases());
        updateBuilders(_definedLgNames,page_);
        Forwards forwards_ = new Forwards(_definedLgNames, _definedLgNames.getExecContent(), fileBuilder_, _options);
        forwards_.getResources().addAllEntries(_files);
        page_.setLogErr(forwards_);
        AnalysisMessages.validateMessageContents(_mess.allMessages(), page_);
        ContextFactory.beforeBuild(forwards_,_mess,_definedKw,_definedLgNames.getExecContent().getCustAliases().defComments(),_options,_definedLgNames.getContent(),page_);
        ContextFactory.build(forwards_,_definedKw,_options,page_,new DefBuildLightResultContextNext());
//        ContextFactory.validateStds(forwards_,_mess, _definedKw, _definedLgNames.getExecContent().getCustAliases().defComments(), _options, _definedLgNames.getContent(), page_);
//        ContextFactory.validateStds(forwards_,_mess, _definedKw, _definedLgNames.getExecContent().getCustAliases().defComments(), _options, _definedLgNames.getContent(), page_);
        ResultContext r_ = new ResultContext(page_, forwards_);
        ResultContext res_ = ResultContext.def(r_, _files,  _exec.getSrcFolder());
        ResultContext.fwd(res_, new AdvContextGenerator(_definedLgNames.getExecContent().getInfos().getThreadFactory().newAtomicBoolean()));
        Classes.tryInit(res_);
        return res_;
    }

    public static void preBuild(LgNamesGui _definedLgNames, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw) {
        _definedLgNames.getExecContent().updateTranslations(_exec.getLightProgramInfos().getTranslations(), _exec.getLightProgramInfos().getLanguage(),"en");
        _definedLgNames.getExecContent().getCustAliases().messages(_mess, _exec.getMessages());
        _definedLgNames.getExecContent().getCustAliases().keyWord(_definedKw, _exec.getKeyWords());
        _definedKw.initSupplDigits();
        _definedLgNames.getExecContent().getCustAliases().otherAlias(_definedLgNames.getContent(), _exec.getAliases());
        StringMap<String> keys_ = LgNamesGui.extractAliasesKeys(_definedLgNames.getExecContent().getCustAliases());
        _definedLgNames.getGuiAliases().otherAliasGui(LgNamesGui.addon(_definedLgNames.getExecContent().getCustAliases()), _exec.getAliases(),keys_);
        _definedLgNames.getExecContent().setExecutingOptions(_exec);
        _definedLgNames.getGuiExecutingBlocks().initApplicationParts(new StringList(), _exec.getLightProgramInfos());
    }

    public static void preBuild(LgNamesUtils _definedLgNames, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw) {
        _definedLgNames.getExecContent().updateTranslations(_exec.getLightProgramInfos().getTranslations(), _exec.getLightProgramInfos().getLanguage(),"en");
        _definedLgNames.getExecContent().getCustAliases().messages(_mess, _exec.getMessages());
        _definedLgNames.getExecContent().getCustAliases().keyWord(_definedKw, _exec.getKeyWords());
        _definedKw.initSupplDigits();
        _definedLgNames.getExecContent().getCustAliases().otherAlias(_definedLgNames.getContent(), _exec.getAliases());
        _definedLgNames.getExecContent().setExecutingOptions(_exec);
    }

    public static ResultContext commonMock(ExecutingOptions _exec, LgNamesUtils _definedLgNames, StringMap<String> _files, AnalyzedPageEl _page, Forwards _forwards) {
        ResultContext r_ = new ResultContext(_page, _forwards);
        ResultContext res_ = ResultContext.def(r_, _files,  _exec.getSrcFolder());
        assertTrue(res_.getPageEl().getMessages().isAllEmptyErrors());
        ForwardInfos.generalForward(res_);
        ContextEl ctx_ = ((AbsLightContextGenerator) new AdvContextGenerator(_definedLgNames.getExecContent().getInfos().getThreadFactory().newAtomicBoolean())).gene(res_.getForwards());
        res_.setContext(ctx_);
        return res_;
    }

    public static ResultContext commonMockDbg(ExecutingOptions _exec, LgNamesUtils _definedLgNames, StringMap<String> _files, AnalyzedPageEl _page, Forwards _forwards) {
        ResultContext r_ = new ResultContext(_page, _forwards);
        ResultContext res_ = ResultContext.def(r_, _files,  _exec.getSrcFolder(),new DbgStackStopper(new DefLogDbg()));
        assertTrue(res_.getPageEl().getMessages().isAllEmptyErrors());
        ForwardInfos.generalForward(res_);
        ContextEl ctx_ = ((AbsLightContextGenerator) new AdvContextGenerator(_definedLgNames.getExecContent().getInfos().getThreadFactory().newAtomicBoolean())).gene(res_.getForwards());
        res_.setContext(ctx_);
        return res_;
    }

    public static ResultContext commonMockErr(ExecutingOptions _exec, LgNamesUtils _definedLgNames, StringMap<String> _files, AnalyzedPageEl _page, Forwards _forwards) {
        ResultContext r_ = new ResultContext(_page, _forwards);
        return ResultContext.def(r_, _files,  _exec.getSrcFolder());
    }
    public static Forwards nextBuild(Options _options, KeyWords _definedKw, LgNamesUtils _definedLgNames, StringMap<String> _files, StringMap<String> _predef, AnalyzedPageEl _page) {
        MockFileBuilder fileBuilder_ = new MockFileBuilder(new DefaultAliasGroups(_definedLgNames.getContent()));
        Forwards forwards_ = new Forwards(_definedLgNames, _definedLgNames.getExecContent(), fileBuilder_, _options);
        forwards_.getResources().addAllEntries(_files);
        _page.setLogErr(forwards_);
        updateMockBuilders(_definedLgNames,_page,_predef);
        ContextFactory.beforeBuild(forwards_, new AnalysisMessages(), _definedKw, _definedLgNames.getExecContent().getCustAliases().defComments(), _options, _definedLgNames.getContent(),_page);
        return forwards_;
    }

    public static void updateMockBuilders(LgNamesUtils _stds, AnalyzedPageEl _page, StringMap<String> _predef) {
        CustList<AbsAliasFileBuilder> builders_ = new CustList<AbsAliasFileBuilder>();
        builders_.add(new DefAliasFileBuilder());
        builders_.add(_stds.getExecContent().getCustAliases().getStringViewReplaceAliases());
        builders_.add(new MockAliasFileBuilder(_predef));
        CustList<AbsAliasFileBuilder> fbs_ = _page.getFileBuilders();
        fbs_.clear();
        fbs_.addAllElts(builders_);
    }

    public static AnalyzedPageEl beginBuild(LgNamesUtils _definedLgNames) {
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAbstractSymbolFactory(new AdvSymbolFactory(_definedLgNames.getExecContent().getCustAliases().getMathAdvAliases()));
        StringMap<String> m_ = _definedLgNames.getExecContent().getCustAliases().extractAliasesKeys();
        m_.addAllEntries(LgNamesGui.extractAliasesKeys(_definedLgNames.getExecContent().getCustAliases()));
        page_.setMappingAliases(m_);
        return page_;
    }

    public static ResultContext build(Options _options, ExecutingOptions _exec, StringMap<String> _files) {
        TranslationsFile en_ = new TranslationsFile();
        LgNamesContent.en(en_);
        MathAdvAliases.en(en_);
        MathAdvAliases a_ = new MathAdvAliases();
        StringMap<String> mapp_ = TranslationsFile.extractMap(en_);
        StringMap<String> keys_ = TranslationsFile.extractKeys(en_);
        a_.build(mapp_,new StringMap<String>(), keys_);
        MockLightLgNames stds_ = new MockLightLgNames();
        stds_.setAliasLgInt(a_.getAliasLgInt());
        stds_.setAliasRate(a_.getAliasRate());
        stds_.getContent().build(mapp_,new StringMap<String>(), keys_);
        stds_.getContent().getCoreNames().setObjType(new StandardClass(stds_.getContent().getCoreNames().getAliasObject(),new CustList<CstFieldInfo>(),new CustList<StandardConstructor>(),new CustList<StandardMethod>(),"", MethodModifier.NORMAL));
        a_.buildOther(stds_.getContent());
        _files.addEntry("src/runnable.txt","public interface pkg.Runnable{public void run();}");
        return MockLightLgNames.resultContext(_options,stds_,DefaultFileBuilder.newInstance(stds_.getContent()),en_,_files,_exec.getSrcFolder(), new AdvSymbolFactory(a_));
    }
    public static ContextEl gene(LgNamesUtils _definedLgNames, Options _opt) {
        return new AdvContextGenerator(_definedLgNames.getExecContent().getInfos().getThreadFactory().newAtomicBoolean()).gene(getForwards(_definedLgNames, _opt));
    }
    public static ContextEl gene(LgNamesGui _definedLgNames, Options _opt) {
        return new AdvContextGenerator(_definedLgNames.getExecContent().getInfos().getThreadFactory().newAtomicBoolean()).gene(getForwards(_definedLgNames, _opt));
    }
}
