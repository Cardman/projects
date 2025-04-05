package code.expressionlanguage.guicompos;

import code.gui.*;
import code.gui.initialize.*;
import code.mock.MockPanel;
import code.stream.*;
import code.threads.*;
import code.util.core.StringUtil;
import org.junit.Test;

public final class WindowFullTest extends EquallableGuiInterpreterUtil {
    @Test
    public void p1(){
        CreateMainWindowFull cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        cr_.run();
        WindowFull w_ = cr_.getWindow();
        w_.getConf().setText("/_.zip\n"+StringUtil.EN+"\nmain=pkg.Sample.m\ncover=\nargs=");
        tryClick(w_.getLaunch());
        assertFalse(w_.getContext().getInterrupt().get());
//        pr_.getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        tryClick(w_.getCoverage());
        w_.quit();
        assertTrue(w_.getContext().getInterrupt().get());
    }
    @Test
    public void p2(){
        CreateMainWindowFull cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        cr_.run();
        WindowFull w_ = cr_.getWindow();
        w_.getConf().setText("");
        tryClick(w_.getLaunch());
        tryClick(w_.getStop());
//        pr_.getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        w_.quit();
        assertTrue(w_.getLaunch().isEnabled());
    }
    @Test
    public void p3(){
        CreateMainWindowFull cr_ = create("/conf.txt");
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\n"+StringUtil.EN+"\nmain=pkg.Sample.m\ncover=\nargs=", pr_.getStreams());
        cr_.run();
        WindowFull w_ = cr_.getWindow();
        assertFalse(w_.getContext().getInterrupt().get());
        w_.launchFileConf("",false);
        assertFalse(w_.getContext().getInterrupt().get());
//        pr_.getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        tryClick(w_.getCoverage());
        w_.quit();
        assertTrue(w_.getContext().getInterrupt().get());
    }
    @Test
    public void p4(){
        CreateMainWindowFull cr_ = create("/conf.txt");
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","", pr_.getStreams());
        cr_.run();
        WindowFull w_ = cr_.getWindow();
        tryClick(w_.getStop());
//        pr_.getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        w_.quit();
        assertTrue(w_.getLaunch().isEnabled());
    }
    @Test
    public void p5(){
        CreateMainWindowFull cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\n"+StringUtil.EN+"\nmain=pkg.Sample.m\ncover=\nargs=", pr_.getStreams());
        cr_.run();
        WindowFull w_ = cr_.getWindow();
        tryClick(w_.getOpen());
        w_.getFileOpenFrame().getFileDialogContent().getFileName().setText("/conf.txt");
        tryClick((AbsButton) ((MockPanel)w_.getFileOpenFrame().getFileDialogContent().getButtons()).getComponent(0));
        assertFalse(w_.getContext().getInterrupt().get());
//        pr_.getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        tryClick(w_.getCoverage());
        w_.changeLanguage("");
        w_.quit();
        assertTrue(w_.getContext().getInterrupt().get());
        GuiBaseUtil.tryToReopen(w_.getApplicationName(),w_.getFrames());
    }
}
