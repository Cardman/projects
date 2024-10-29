package code.expressionlanguage.gui.unit;

import code.expressionlanguage.options.DefBuildLightResultContextNext;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilfiles.*;
import code.expressionlanguage.utilimpl.DefFileBuilderListGene;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.AbsTableGui;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockInterceptor;
import code.mock.MockProgramInfos;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.stream.BytesInfo;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import org.junit.Test;

public final class ProgressingTestsImplTest extends EquallableElUtFilesUtil {
    @Test
    public void launch3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(StringUtil.encode(""), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        ProgressingTestsImpl progTest_ = new ProgressingTestsImpl(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_),pr_.getStreams(),pr_.getFileCoreStream());
        assertFalse(RunningTest.launchByConfContent(new StringList(),";\n;\nout=//",progTest_,infos_, new DefBuildLightResultContextNext(),new DefFileBuilderListGene()));
    }
    @Test
    public void launch4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_, init(), "conf.txt", "content"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(StringUtil.encode(""), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        ProgressingTestsImpl progTest_ = new ProgressingTestsImpl(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_),pr_.getStreams(),pr_.getFileCoreStream());
        assertTrue(RunningTest.launchByConfContent(new StringList(),";\n;",progTest_,infos_, new DefBuildLightResultContextNext(),new DefFileBuilderListGene()));
    }
    @Test
    public void launch5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        ProgressingTestsImpl progTest_ = new ProgressingTestsImpl(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_),pr_.getStreams(),pr_.getFileCoreStream());
        assertTrue(RunningTest.launchByConfContent(indexes(),";\n"+StringUtil.EN+"\nerr=",progTest_,infos_, new DefBuildLightResultContextNext(),new DefFileBuilderListGene()));
        assertTrue(progTest_.getExportedReport().isNul());
//        StringMap<ContentTime> reported_ = pr_.getZipFact().zippedBinaryFiles(progTest_.getExportedReport());
//        assertEq("<html><head><meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Sample</a>{}</span></pre></body></html>",StringUtil.decode(reported_.getVal("errors/src/folder/file.txt.html").getContent()));
    }
    @Test
    public void launch6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode(";\n"+StringUtil.EN+"\ncover="), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        AbsTableGui t_ = compo_.newTableGui("0", "1", "2", "3");
        t_.setRowCount(2);
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), t_, compo_.newTextArea(), compo_.newAbsProgressBar());
        AbsTestableFrame fr_ = new LightTestableFrame(pr_, null, new MockInterceptor(), mem_, bar_);
//        fr_.ok("");
        fr_.getTxtConf();
        ProgressingTestsImpl progTest_ = new ProgressingTestsImpl(fr_,pr_.getStreams(),pr_.getFileCoreStream());
        RunningTest running_ = RunningTest.newFromContent(indexesAll(), "", progTest_, fr_.getInfos(), new DefBuildLightResultContextNext(),new DefFileBuilderListGene());
        running_.run();
        assertTrue(progTest_.getExportedReport().isNul());
//        assertEq("<html><head><meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Sample</a>{<span class=\"f2\">@Test</span> public void <a name=\"m42\">err</a>(){<span class=\"n\"><span class=\"n\">Assert</span>.<span class=\"n\">assert(<span class=\"n\">0</span>,<span class=\"n\">1</span>)</span></span>;}<span class=\"f2\">@Test</span> public void <a name=\"m86\">success</a>(){<span class=\"f\"><span class=\"f\">Assert</span>.<span class=\"f\">assert(<span class=\"f\">1</span>,<span class=\"f\">1</span>)</span></span>;}}</span></pre></body></html>",StringUtil.decode(reported_.getVal("coverage/src/folder/file.txt.html").getContent()));
//        assertEq(2, bar_.getMessages().size());
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
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        FileInfos infos_ = FileInfos.buildMemoryFromFile(pr_, pr_.getGenerator(), pr_.getValidator(), null, mem_, pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        ProgressingTestsImpl progTest_ = new ProgressingTestsImpl(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_),pr_.getStreams(),pr_.getFileCoreStream());
        assertTrue(RunningTest.launchByConfContent(indexes(),";\n"+StringUtil.EN+"\nerr=",progTest_,infos_,new DefBuildLightResultContextNext(),new DefFileBuilderListGene()));
    }
    @Test
    public void launch8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode(";\n"+StringUtil.EN), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        AbsTableGui t_ = compo_.newTableGui("0", "1", "2", "3");
        t_.setRowCount(2);
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), t_, compo_.newTextArea(), compo_.newAbsProgressBar());
        AbsTestableFrame fr_ = new LightTestableFrame(pr_, null, new MockInterceptor(), mem_, bar_);
//        fr_.ok("");
        fr_.getTxtConf();
        ProgressingTestsImpl progTest_ = new ProgressingTestsImpl(fr_,pr_.getStreams(),pr_.getFileCoreStream());
        RunningTest running_ = RunningTest.newFromContent(indexesAll(), "", progTest_, fr_.getInfos(), new DefBuildLightResultContextNext(),new DefFileBuilderListGene());
        running_.run();
        assertTrue(progTest_.getExportedReport().isNul());
//        assertEq("<html><head><meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Sample</a>{<span class=\"f2\">@Test</span> public void <a name=\"m42\">err</a>(){<span class=\"n\"><span class=\"n\">Assert</span>.<span class=\"n\">assert(<span class=\"n\">0</span>,<span class=\"n\">1</span>)</span></span>;}<span class=\"f2\">@Test</span> public void <a name=\"m86\">success</a>(){<span class=\"f\"><span class=\"f\">Assert</span>.<span class=\"f\">assert(<span class=\"f\">1</span>,<span class=\"f\">1</span>)</span></span>;}}</span></pre></body></html>",StringUtil.decode(reported_.getVal("coverage/src/folder/file.txt.html").getContent()));
//        assertEq(2, bar_.getMessages().size());
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
    public void launch9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        MemInputFiles mem_ = new MemInputFiles(StringUtil.encode("__"), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false), new BytesInfo(GuiConstants.nullToEmpty(zipped_), false));
        DefaultUniformingString uniformingString_ = new DefaultUniformingString();
        FileInfos infos_ = new FileInfos(new DefaultLogger(null, pr_.getFileCoreStream(),pr_.getStreams()), new MemoryFileSystem(pr_.getValidator(), pr_.getThreadFactory()), new MemoryReporter(pr_, mem_.getConf(), mem_.getSrc(), mem_.getFiles(), pr_.getValidator(), uniformingString_), pr_.getGenerator(), pr_.getZipFact(), pr_.getThreadFactory());
        AbsCompoFactory compo_ = pr_.getCompoFactory();
        ProgTestBar bar_ = new ProgTestBar(compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newPlainLabel(""), compo_.newTableGui(), compo_.newTextArea(), compo_.newAbsProgressBar());
        ProgressingTestsImpl progTest_ = new ProgressingTestsImpl(new LightTestableFrame(pr_, null,new MockInterceptor(), mem_, bar_),pr_.getStreams(),pr_.getFileCoreStream());
        assertTrue(RunningTest.launchByConfContent(indexes(),";\n"+StringUtil.EN,progTest_,infos_,new DefBuildLightResultContextNext(),new DefFileBuilderListGene()));
    }
    public static void update(MockProgramInfos _pr) {
        _pr.setLanguages(new StringList(StringUtil.EN,StringUtil.FR));
        _pr.setLanguage(StringUtil.EN);
        FileInfos.enTr(updateMes(FileInfos.initComments(lg(_pr,StringUtil.EN))));
        FileInfos.frTr(updateMes(FileInfos.initComments(lg(_pr,StringUtil.FR))));
    }
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        return _pr.lg(_key);
    }
    public static TranslationsAppli updateMes(TranslationsAppli _a) {
//        TranslationsFile t_ = new TranslationsFile();
//        t_.add(ProgTestBar.EXEC_OPTIONS_TABLE_FAIL,"0");
//        t_.add(ProgTestBar.EXEC_OPTIONS_TABLE_SUCCESS,"1");
//        _a.getMapping().addEntry(ProgTestBar.EXEC_OPTIONS_TABLE, t_);
        return _a;
    }

    public static StringList indexes() {
        return new StringList(StringUtil.EN);
    }

    public static StringList indexesAll() {
        return new StringList(StringUtil.EN,StringUtil.FR);
    }
}
