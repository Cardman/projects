package code.expressionlanguage.guicompos;

import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilfiles.EquallableElUtFilesUtil;
import code.gui.CdmFactory;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockInterceptor;
import code.mock.MockProgramInfos;
import code.sml.util.TranslationsLg;
import code.stream.StreamBinaryFile;
import code.util.StringList;
import org.junit.Test;

public final class GuiProcessTest extends EquallableElUtFilesUtil {
    @Test
    public void run1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        assertNull(GuiProcess.build("","",f_,pr_));
    }
    @Test
    public void run2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        GuiProcess b_ = GuiProcess.build("/_.zip", "/_.zip\nen\nmain=pkg.Sample.m\ncover=", f_, pr_);
        b_.run();
        assertFalse(b_.getContext().getInterrupt().get());
        new CoveringCodeTask(b_.getContext(),b_.getExecutingOptions()).run();
        assertTrue(pr_.getFileCoreStream().newFile("/coverage/src/folder/file.txt.html").exists());
    }
    @Test
    public void run3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){throw 1/0;}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        GuiProcess b_ = GuiProcess.build("/_.zip", "/_.zip\nen\nmain=pkg.Sample.m\ncover=", f_, pr_);
        b_.run();
        assertTrue(b_.getContext().getInterrupt().get());
        new CoveringCodeTask(b_.getContext(),b_.getExecutingOptions()).run();
        assertTrue(pr_.getFileCoreStream().newFile("/coverage/src/folder/file.txt.html").exists());
    }
    @Test
    public void run4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}static{throw 1/0;}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        GuiProcess b_ = GuiProcess.build("/_.zip", "/_.zip\nen\nmain=pkg.Sample.m", f_, pr_);
        b_.run();
        assertTrue(b_.getContext().getInterrupt().get());
        new CoveringCodeTask(b_.getContext(),b_.getExecutingOptions()).run();
        assertFalse(pr_.getFileCoreStream().newFile("/coverage/src/folder/file.txt.html").exists());
    }
    @Test
    public void run5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}static{throw 1/0;}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        GuiProcess b_ = GuiProcess.build("/_.zip", "/_.zip\nen\nmain=pkg.Sample.", f_, pr_);
        b_.run();
        assertTrue(b_.getContext().getInterrupt().get());
    }
    @Test
    public void run6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}static{throw 1/0;}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        GuiProcess b_ = GuiProcess.build("/_.zip", "/_.zip\nen\nmain=", f_, pr_);
        b_.run();
        assertTrue(b_.getContext().getInterrupt().get());
    }
    @Test
    public void run7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        GuiProcess b_ = GuiProcess.build("/_.zip", "/_.zip\nen\nmain=pkg.Sample.m\ncover=\nargs=", f_, pr_);
        b_.run();
        assertFalse(b_.getContext().getInterrupt().get());
        new CoveringCodeTask(b_.getContext(),b_.getExecutingOptions()).run();
        assertTrue(pr_.getFileCoreStream().newFile("/coverage/src/folder/file.txt.html").exists());
    }
    @Test
    public void run8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        assertNull(GuiProcess.build("/_.zip","/_.zip\nen\n_\nlog=a:b>c:d#",f_,pr_));
    }
    @Test
    public void run9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        assertNull(GuiProcess.build("/_.zip","/_.zip\nen\n_\nerr=",f_,pr_));
    }
    @Test
    public void run10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        assertNull(GuiProcess.build("/_.zip","/_.zip\nen\n_",f_,pr_));
    }
    @Test
    public void run11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        assertNull(GuiProcess.build("/_.zip","/_.zip\n_\n_",f_,pr_));
    }
    @Test
    public void run12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        update(pr_);
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        CdmFactory f_ = new CdmFactory(pr_, new MockInterceptor());
        assertNull(GuiProcess.build("/_.zip","/__.zip\n_\n_",f_,pr_));
    }
    public static void update(MockProgramInfos _pr) {
        _pr.setLanguages(new StringList("en","fr"));
        _pr.setLanguage(FileInfos.EN);
        FileInfos.enTr(FileInfos.initComments(lg(_pr,FileInfos.EN)));
        FileInfos.frTr(FileInfos.initComments(lg(_pr,FileInfos.FR)));
    }
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        return _pr.lg(_key);
    }
}
