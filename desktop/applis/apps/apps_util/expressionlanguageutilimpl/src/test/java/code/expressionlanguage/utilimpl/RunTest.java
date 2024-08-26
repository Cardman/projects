package code.expressionlanguage.utilimpl;

import code.expressionlanguage.gui.unit.*;
import code.expressionlanguage.options.DefBuildLightResultContextNext;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.expressionlanguage.utilcompo.MemInputFiles;
import code.gui.AbsTableGui;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.stream.BytesInfo;
import code.stream.core.ContentTime;
import code.threads.ConcreteBoolean;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class RunTest extends EquallableElUtImplUtil {

    @Test
    public void retrieve1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(StringUtil.encode(""), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_));
        assertEq("__",RunningTest.newFromContent(new StringList(StringUtil.EN,StringUtil.FR),"",progTest_,infos_, new DefBuildLightResultContextNext(),new DefFileBuilderListGene()).retrieve());
    }
    @Test
    public void retrieve2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(StringUtil.encode(""), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_));
        assertEq("__",RunningTest.newFromFile(new StringList(StringUtil.EN,StringUtil.FR),"",progTest_,infos_, new DefBuildLightResultContextNext(),new DefFileBuilderListGene()).retrieve());
    }
    @Test
    public void launch1() {
        assertFalse(RunningTest.launchByConfContent(new StringList(),"",null,null, new DefBuildLightResultContextNext(),new DefFileBuilderListGene()));
    }
    @Test
    public void launch2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(StringUtil.encode(""), true), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        assertFalse(RunningTest.launchByConfContent(new StringList(),";\n;",null,infos_, new DefBuildLightResultContextNext(),new DefFileBuilderListGene()));
    }
    @Test
    public void launch3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(StringUtil.encode(""), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_));
        assertFalse(RunningTest.launchByConfContent(new StringList(),";\n;\nout=//",progTest_,infos_, new SampleAtIntLgNames(),new SampleExecFileBuilderGene()));
    }
    @Test
    public void launch4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(StringUtil.encode(""), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_));
        assertTrue(RunningTest.launchByConfContent(new StringList(),";\n;",progTest_,infos_, new SampleAtIntLgNames(),new SampleExecFileBuilderGene()));
    }
    @Test
    public void launch5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_));
        assertTrue(RunningTest.launchByConfContent(new StringList(StringUtil.EN),";\n"+StringUtil.EN+"\nerr=",progTest_,infos_, new SampleAtIntLgNames(),new SampleExecFileBuilderGene()));
        StringMap<ContentTime> reported_ = pr_.getZipFact().zippedBinaryFiles(progTest_.getExportedReport());
        assertEq("<html><head><meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Sample</a>{}</span></pre></body></html>",StringUtil.decode(reported_.getVal("errors/src/folder/file.txt.html").getContent()));
    }
    @Test
    public void launch6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode(";\n"+StringUtil.EN+"\ncover="), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        AbsTableGui t_ = compo_.newTableGui("0", "1", "2", "3");
        t_.setRowCount(2);
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), t_, compo_.newTextArea(), compo_.newAbsProgressBar());
        AbsTestableFrame fr_ = new LightTestableFrame(pr_, null, new MockInterceptor(), mem_, bar_);
//        fr_.ok("");
        fr_.getTxtConf();
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(fr_);
        RunningTest running_ = RunningTest.newFromContent(new StringList(StringUtil.EN,StringUtil.FR), "", progTest_, fr_.getInfos(), new SampleAtIntLgNames(),new SampleExecFileBuilderGene());
        running_.run();
        StringMap<ContentTime> reported_ = pr_.getZipFact().zippedBinaryFiles(running_.getProgressingTests().getExportedReport());
        assertEq("<html><head><meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Sample</a>{<span class=\"f2\">@Test</span> public void <a name=\"m42\">err</a>(){<span class=\"n\"><span class=\"n\">Assert</span>.<span class=\"n\">assert(<span class=\"n\">0</span>,<span class=\"n\">1</span>)</span></span>;}<span class=\"f2\">@Test</span> public void <a name=\"m86\">success</a>(){<span class=\"f\"><span class=\"f\">Assert</span>.<span class=\"f\">assert(<span class=\"f\">1</span>,<span class=\"f\">1</span>)</span></span>;}}</span></pre></body></html>",StringUtil.decode(reported_.getVal("coverage/src/folder/file.txt.html").getContent()));
//        assertFalse(bar_.getMessages().isEmpty());
        messages(pr_);
        assertEq(2, bar_.getResults().size());
        assertEq(ProgTestBar.FLAG_FAIL, bar_.getResults().get(0).getResultSuccessLong());
        assertEq(ProgTestBar.FLAG_SUCCESS, bar_.getResults().get(1).getResultSuccessLong());
        assertEq(ProgTestBar.FLAG_FAIL, bar_.getResults().get(0).getResultSuccess());
        assertEq(ProgTestBar.FLAG_SUCCESS, bar_.getResults().get(1).getResultSuccess());
        assertFalse(bar_.getResults().get(0).isSuccess());
        assertTrue(bar_.getResults().get(1).isSuccess());
        assertFalse(progTest_.getStop().get());
    }
    @Test
    public void launch7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        MemoryProgressingTests progTest_ = new MemoryProgressingTests(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_));
        assertTrue(RunningTest.launchByConfContent(new StringList(StringUtil.EN),";\n"+StringUtil.EN+"\nerr=",progTest_,infos_,new SampleAtIntLgNames(),new SampleExecFileBuilderGene()));
    }
    @Test
    public void splitMemo1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        ResultContext res_ = together(baseValidateMemo(StringUtil.EN, new StringList("keyWords=If=;"), new MockInterceptor(), pr_.light()), null);
        assertTrue(res_.getPageEl().notAllEmptyErrors());
    }
    @Test
    public void splitMemo2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        StringList lines_ = new StringList("",StringUtil.EN,"out=//");
        ResultContext res_ = together(baseValidateMemo(StringUtil.EN, lines_, new MockInterceptor(), pr_.light()), new MemInputFiles(new byte[0],new BytesInfo(new byte[0],false),new BytesInfo(new byte[0],false)));
        assertFalse(res_.getPageEl().isCustomAna());
    }
    @Test
    public void splitMemo3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        StringList lines_ = new StringList("",StringUtil.EN);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        ResultContext res_ = together(baseValidateMemo(StringUtil.EN, lines_, new MockInterceptor(), pr_.light()), new MemInputFiles(new byte[0],new BytesInfo(zipped_,false),new BytesInfo(new byte[0],false)));
        assertTrue(res_.getPageEl().isCustomAna());
        assertTrue(res_.getPageEl().notAllEmptyErrors());
    }
    @Test
    public void splitMemo4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        StringList lines_ = new StringList("",StringUtil.EN);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        MemInputFiles in_ = new MemInputFiles(new byte[0],new BytesInfo(zipped_,false),new BytesInfo(new byte[0],false));
        ResultContext pred_ = baseValidateMemo(StringUtil.EN, lines_, new MockInterceptor(), pr_.light());
        MemoResultContextNext memo_ = new MemoResultContextNext(pred_, in_, null);
        ResultContext res_ = RunningTest.nextValidate(pred_, (LgNamesWithNewAliases) pred_.getForwards().getGenerator(), memo_,new MockResultContextNextIm());
        assertTrue(res_.getPageEl().isCustomAna());
        assertFalse(res_.getPageEl().notAllEmptyErrors());
    }
    @Test
    public void memoDef() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        MockInterceptor inter_ = new MockInterceptor();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "\n"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        MemInputFiles in_ = new MemInputFiles(StringUtil.encode("\n"), new BytesInfo(zipped_, false), new BytesInfo(new byte[0], false));
        ResultContext pred_ = RunningTest.baseValidateMemoDef(StringUtil.EN, inter_, pr_.light(), new SampleFileBuilderListGene(), new SampleAtIntLgNames());
        MemoResultContextNext memo_ = new MemoResultContextNext(pred_, in_, null);
        ResultContext res_ = RunningTest.nextValidate(pred_, (LgNamesWithNewAliases) pred_.getForwards().getGenerator(), memo_,new MockResultContextNextIm());
        assertTrue(res_.getPageEl().isCustomAna());
        assertFalse(res_.getPageEl().notAllEmptyErrors());
        memo_.generate(new ConcreteBoolean()).gene(res_.getForwards());
    }

    @Test
    public void splitMemoQuick1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        ResultContext res_ = RunningTest.nextValidateMemoQuick(baseValidateMemo(StringUtil.EN, new StringList("keyWords=If=;"), new MockInterceptor(), pr_.light()),null,null);
        assertTrue(res_.getPageEl().notAllEmptyErrors());
    }
    @Test
    public void splitMemoQuick2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        StringList lines_ = new StringList("",StringUtil.EN,"out=//");
        ResultContext res_ = RunningTest.nextValidateMemoQuick(baseValidateMemo(StringUtil.EN, lines_, new MockInterceptor(), pr_.light()),new MemInputFiles(new byte[0],new BytesInfo(new byte[0],false),new BytesInfo(new byte[0],false)),null);
        assertFalse(res_.getPageEl().isCustomAna());
    }
    @Test
    public void splitMemoQuick3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        StringList lines_ = new StringList("",StringUtil.EN);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        ResultContext res_ = RunningTest.nextValidateMemoQuick(baseValidateMemo(StringUtil.EN, lines_, new MockInterceptor(), pr_.light()),new MemInputFiles(new byte[0],new BytesInfo(zipped_,false),new BytesInfo(new byte[0],false)),null);
        assertTrue(res_.getPageEl().isCustomAna());
        assertTrue(res_.getPageEl().notAllEmptyErrors());
    }
    @Test
    public void splitMemoQuick4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        StringList lines_ = new StringList("",StringUtil.EN);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        ResultContext res_ = RunningTest.nextValidateMemoQuick(baseValidateMemo(StringUtil.EN, lines_, new MockInterceptor(), pr_.light()),new MemInputFiles(new byte[0],new BytesInfo(zipped_,false),new BytesInfo(new byte[0],false)),null);
        assertTrue(res_.getPageEl().isCustomAna());
        assertFalse(res_.getPageEl().notAllEmptyErrors());
    }
    @Test
    public void splitMemoQuick5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        StringList lines_ = new StringList("",StringUtil.EN);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        StringMap<String> added_ = new StringMap<String>();
        added_.addEntry("src/folder/file.txt", "public class pkg.Sample{}");
        ResultContext res_ = RunningTest.nextValidateMemoQuick(baseValidateMemo(StringUtil.EN, lines_, new MockInterceptor(), pr_.light()),new MemInputFiles(new byte[0],new BytesInfo(zipped_,false),new BytesInfo(new byte[0],false)),null, added_);
        assertTrue(res_.getPageEl().isCustomAna());
        assertFalse(res_.getPageEl().notAllEmptyErrors());
    }

    private ResultContext together(ResultContext _base, MemInputFiles _in) {
        return RunningTest.nextValidateMemo(_base, _in, null);
    }

    private ResultContext baseValidateMemo(String _lg, StringList _otherLines, AbstractInterceptor _interceptor, AbstractLightProgramInfos _factories) {
        return RunningTest.baseValidateMemo(_lg, _otherLines, _interceptor, _factories, null,new SampleAtIntLgNames(),new SampleFileBuilderListGene());
    }

}
