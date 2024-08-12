package code.expressionlanguage.utilimpl;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.gui.unit.ProgTestBar;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractLightProgramInfos;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.Rate;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.sml.Node;
import code.sml.util.TranslationsLg;
import code.stream.BytesInfo;
import code.stream.core.ContentTime;
import code.stream.core.OutputType;
import code.stream.core.StreamZipFile;
import code.util.CustList;
import code.util.*;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableElUtImplUtil {
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

    public static LgNamesGui newLgNamesGuiSampleCl(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(exOpt(_light));
        return stds_;
    }
    public static LgNamesGui newLgNamesGui(AbstractLightProgramInfos _light, AbstractIssuer _issuer, String _conf, String _src, StringMap<ContentTime> _files) {
        byte[] zipped_ = _light.getZipFact().zipBinFiles(_files);
        FileInfos infos_ = FileInfos.buildMemoryFromFile(_light, _light.getGenerator(), _light.getValidator(), _issuer, new MemInputFiles(StringUtil.encode(_conf), new BytesInfo(StringUtil.encode(_src),false), new BytesInfo(GuiConstants.nullToEmpty(zipped_),false)), _light.getZipFact(), _light.getThreadFactory());
        return new LgNamesGui(infos_, new MockInterceptor());
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
    public static FileInfos newFileInfos(AbstractLightProgramInfos _light) {
        return FileInfos.buildMemoryFromFile(_light, _light.getGenerator(), _light.getValidator(), null, new MemInputFiles(new byte[0],new BytesInfo(new byte[0],false),new BytesInfo(new byte[0],false)), _light.getZipFact(), _light.getThreadFactory());
    }
    public static StackCall stack(ContextEl _phase) {
        return StackCall.newInstance(InitPhase.NOTHING,_phase);
    }
    public static StringMap<ContentTime> init() {
        return new StringMap<ContentTime>();
    }
    public static StringMap<ContentTime> with(AbstractLightProgramInfos _light, StringMap<ContentTime> _all, String _name, String _content) {
        _all.put(_name,new ContentTime(StringUtil.encode(_content),_light.getThreadFactory().millis()));
        return _all;
    }
    public static StringMap<ContentTime> with(AbstractLightProgramInfos _light, StringMap<ContentTime> _all, String _name) {
        _all.put(_name,new ContentTime(null,_light.getThreadFactory().millis()));
        return _all;
    }

    public static MockProgramInfos newMockProgramInfos(CustomSeedGene _s, MockFileSet _set) {
        MockProgramInfos pr_ = new MockProgramInfos("", "", _s, _set);
        pr_.setLanguages(new StringList("en","fr"));
        pr_.setLanguage(FileInfos.EN);
        update(pr_);
        return pr_;
    }
    public static void update(MockProgramInfos _pr) {
        FileInfos.enTr(FileInfos.initComments(lg(_pr,FileInfos.EN)));
        FileInfos.frTr(FileInfos.initComments(lg(_pr,FileInfos.FR)));
    }
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        return _pr.lg(_key);
    }
    public static MockFileSet fileSet(long _initMillis, long[] _incrs, String... _roots) {
        return new MockFileSet(_initMillis,_incrs,_roots);
    }
    public static Struct str(Argument _arg) {
        return _arg.getStruct();
    }
    public static void memoryFileSystem(LgNamesGui _stds, AbstractProgramInfos _pr, MockNameFile... _files) {
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
        return SortConstants.wrapByteArray(MockZipFact.wrapInts(_files));
    }
    public static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }

    public static StringMap<String> messages() {
        StringMap<String> ms_ = new StringMap<String>();
        ms_.addEntry(ProgTestBar.KEY_FAIL,"0");
        ms_.addEntry(ProgTestBar.KEY_SUCCESS,"1");
        return ms_;
    }
    public static ExecutingOptions exOpt(AbstractLightProgramInfos _p) {
        return new ExecutingOptions(_p);
    }
}
