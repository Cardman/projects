package code.expressionlanguage.gui.unit;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilimpl.*;
import code.gui.*;
import code.gui.initialize.*;
import code.mock.*;
import code.stream.*;
import code.threads.*;
import org.junit.Test;

public final class WindowUnitTest extends EquallableUnitInterpreterUtil {
    @Test
    public void p1() {
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        w_.getConf().setText("/_.zip\nen");
        tryClick(w_.getLaunch());
        ((MockBaseExecutorService)w_.getExec()).getTasks().lastValue().attendre();
        assertEq(0,w_.getResultsTable().getRowCount());
        tryClick(w_.getStop());
        assertEq(0,w_.getResultsTable().getRowCount());
        ProgressingTests progressingTests_ = w_.getRunning().getProgressingTests();
        ContextEl rCont_ =  progressingTests_.ctx().copy(w_.getThreadFactory().newAtomicBoolean(),null);
        LgNamesWithNewAliases stds_ = (LgNamesWithNewAliases)rCont_.getStandards();
        String infoTest_ = stds_.getExecContent().getCustAliases().getAliasInfoTest();
        ExecFormattedRootBlock className_ = ExecFormattedRootBlock.build(infoTest_, rCont_.getClasses());
        Struct infoStruct_ = ArgumentListCall.toStr(ProcessMethod.calculate(new CustomFoundConstructor(rCont_,className_, new Argument()),rCont_, StackCall.newInstance(InitPhase.NOTHING,rCont_)).getValue());
        new ShowUpdates(infoStruct_,rCont_,progressingTests_,stds_).run();
    }
    @Test
    public void p2() {
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        w_.getConf().setText("/_.zip\nen");
        w_.getMemory().setSelected(true);
        tryClick(w_.getLaunch());
        ((MockBaseExecutorService)w_.getExec()).getTasks().lastValue().attendre();
        assertEq(0,w_.getResultsTable().getRowCount());
    }
    @Test
    public void p3(){
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nen", pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        tryClick(w_.getStop());
        tryClick(w_.getOpen());
        w_.getFileOpenFrame().getFileDialogContent().getFileName().setText("/conf.txt");
        tryClick((AbsButton) w_.getFileOpenFrame().getFileDialogContent().getButtons().getComponent(0));
        assertEq(0,w_.getResultsTable().getRowCount());
        pr_.getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        w_.changeLanguage("");
        w_.quit();
        assertEq(0,w_.getResultsTable().getRowCount());
    }
    @Test
    public void p4() {
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        w_.getConf().setText("/_.zip\nen");
        tryClick(w_.getLaunch());
        ((MockBaseExecutorService)w_.getExec()).getTasks().lastValue().attendre();
        assertEq(0,w_.getResultsTable().getRowCount());
        pr_.getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        w_.quit();
        assertEq(0,w_.getResultsTable().getRowCount());
    }
    @Test
    public void p5() {
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("",zipped_,pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        w_.getConf().setText("/_.zip\nen");
        tryClick(w_.getLaunch());
        ((MockBaseExecutorService)w_.getExec()).getTasks().lastValue().attendre();
        assertEq(0,w_.getResultsTable().getRowCount());
        tryClick(w_.getStop());
        assertEq(0,w_.getResultsTable().getRowCount());
    }
    @Test
    public void p6() {
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        w_.getConf().setText("/_.zip\nen");
        tryClick(w_.getLogErr());
        tryClick(w_.getLogErr());
        pr_.getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        w_.quit();
        assertEq(0,w_.getResultsTable().getRowCount());
    }
    @Test
    public void p7() {
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nen", pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        tryClick(w_.getSimpleFrame());
        SimpleFilesFrame s_ = w_.getFilesFrame();
        s_.setFilePath("");
        tryClick(s_.getOpen());
        w_.getFileOpenFrame().getFileDialogContent().getFileName().setText("/conf.txt");
        tryClick((AbsButton) w_.getFileOpenFrame().getFileDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        s_.getSrcField().setText("");
        tryClick(s_.getSrcButton());
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        s_.getSrcField().setText("/_.zip");
        tryClick(s_.getSrcButton());
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        tryClick(s_.getLaunchByFile());
        ((MockBaseExecutorService)w_.getExec()).getTasks().lastValue().attendre();
        assertEq(0,w_.getResultsTable().getRowCount());
        ProgressingTests progressingTests_ = w_.getRunning().getProgressingTests();
        ContextEl rCont_ =  progressingTests_.ctx().copy(w_.getThreadFactory().newAtomicBoolean(),null);
        LgNamesWithNewAliases stds_ = (LgNamesWithNewAliases)rCont_.getStandards();
        String infoTest_ = stds_.getExecContent().getCustAliases().getAliasInfoTest();
        ExecFormattedRootBlock className_ = ExecFormattedRootBlock.build(infoTest_, rCont_.getClasses());
        Struct infoStruct_ = ArgumentListCall.toStr(ProcessMethod.calculate(new CustomFoundConstructor(rCont_,className_, new Argument()),rCont_, StackCall.newInstance(InitPhase.NOTHING,rCont_)).getValue());
        new ShowUpdates(infoStruct_,rCont_,progressingTests_,stds_).run();
    }
    @Test
    public void p8() {
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt",""));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nen", pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        tryClick(w_.getSimpleFrame());
        SimpleFilesFrame s_ = w_.getFilesFrame();
        s_.setFilePath("");
        tryClick(s_.getOpen());
        w_.getFileOpenFrame().getFileDialogContent().getFileName().setText("/conf.txt");
        tryClick((AbsButton) w_.getFileOpenFrame().getFileDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        s_.getSrcField().setText("/_.zip");
        tryClick(s_.getSrcButton());
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        tryClick(s_.getLaunchByFile());
        ((MockBaseExecutorService)w_.getExec()).getTasks().lastValue().attendre();
        assertEq(0,w_.getResultsTable().getRowCount());
    }
    @Test
    public void p9() {
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        byte[] folder_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("/__.zip",folder_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nen", pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        tryClick(w_.getSimpleFrame());
        SimpleFilesFrame s_ = w_.getFilesFrame();
        s_.setFilePath("");
        tryClick(s_.getOpen());
        w_.getFileOpenFrame().getFileDialogContent().getFileName().setText("/conf.txt");
        tryClick((AbsButton) w_.getFileOpenFrame().getFileDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        s_.getSrcField().setText("/_.zip");
        tryClick(s_.getSrcButton());
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        s_.getFilesField().setText("");
        tryClick(s_.getFilesButton());
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        s_.getFilesField().setText("/__.zip");
        tryClick(s_.getFilesButton());
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        tryClick(s_.getLaunchByFile());
        ((MockBaseExecutorService)w_.getExec()).getTasks().lastValue().attendre();
        assertEq(0,w_.getResultsTable().getRowCount());
    }
    @Test
    public void p10() {
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        byte[] folder_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("/__.zip",folder_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nen", pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        tryClick(w_.getSimpleFrame());
        SimpleFilesFrame s_ = w_.getFilesFrame();
        s_.setFilePath("");
        tryClick(s_.getOpen());
        w_.getFileOpenFrame().getFileDialogContent().getFileName().setText("/conf.txt");
        tryClick((AbsButton) w_.getFileOpenFrame().getFileDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        s_.getFolderField().setText("_");
        s_.getSrcField().setText("_.zip");
        tryClick(s_.getSrcButton());
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        s_.getFilesField().setText("__.zip");
        tryClick(s_.getFilesButton());
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        s_.getFolderField().setText("/");
        s_.getSrcField().setText("_.zip");
        tryClick(s_.getSrcButton());
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        s_.getFilesField().setText("__.zip");
        tryClick(s_.getFilesButton());
        tryAn(((MockThreadFactory)pr_.getThreadFactory()));
        tryClick(s_.getLaunchByFile());
        ((MockBaseExecutorService)w_.getExec()).getTasks().lastValue().attendre();
        assertEq(0,w_.getResultsTable().getRowCount());
    }
    @Test
    public void p11() {
        CreateMainWindowUnit cr_ = create();
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        tryClick(w_.getSimpleFrame());
        SimpleFilesFrame s_ = w_.getFilesFrame();
        s_.getConf().setText("/_.zip\nen");
        tryClick(s_.getLaunch());
        ((MockBaseExecutorService)w_.getExec()).getTasks().lastValue().attendre();
        assertEq(0,w_.getResultsTable().getRowCount());
        tryClick(s_.getStop());
        assertEq(0,w_.getResultsTable().getRowCount());
        s_.closeWindow();
    }
    @Test
    public void p12(){
        CreateMainWindowUnit cr_ = create("/conf.txt");
        AbstractProgramInfos pr_ = cr_.getProgramInfos();
        byte[] zipped_ = pr_.getZipFact().zipBinFiles(with(pr_,  with(pr_,with(pr_,with(pr_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,pr_.getStreams());
        StreamTextFile.saveTextFile("/conf.txt","/_.zip\nen", pr_.getStreams());
        cr_.run();
        WindowUnit w_ = cr_.getWindow();
        ((MockBaseExecutorService)w_.getExec()).getTasks().lastValue().attendre();
        assertEq(0,w_.getResultsTable().getRowCount());
    }
}
