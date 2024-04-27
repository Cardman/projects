package code.gui.files;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.mock.*;
import code.stream.StreamLanguageUtil;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FileDialogTest extends EquallableGuiCommonUtil {
    @Test
    public void coords1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        FileDialog.saveCoords("/tmp","_",1,2,pr_.getStreams());
        TopLeftFrame tl_ = FileDialog.loadCoords("/tmp", "_", pr_.getFileCoreStream(), pr_.getStreams());
        assertEq(1,tl_.getWidth());
        assertEq(2,tl_.getHeight());
    }
    @Test
    public void coords2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        TopLeftFrame tl_ = FileDialog.loadCoords("/tmp", "_", pr_.getFileCoreStream(), pr_.getStreams());
        assertEq(0,tl_.getWidth());
        assertEq(0,tl_.getHeight());
    }
    @Test
    public void coords3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        TopLeftFrame tl_ = new TopLeftFrame();
        tl_.setWidth(-1000000);
        tl_.setHeight(-1000000);
        FileDialog.setLocation(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), tl_);
        assertEq(-1000000,tl_.getHeight());
        assertEq(-1000000,tl_.getWidth());
    }
    @Test
    public void coords4() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        TopLeftFrame tl_ = new TopLeftFrame();
        tl_.setWidth(1000000);
        tl_.setHeight(1000000);
        FileDialog.setLocation(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), tl_);
        assertEq(1000000,tl_.getHeight());
        assertEq(1000000,tl_.getWidth());
    }
    @Test
    public void coords5() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        TopLeftFrame tl_ = new TopLeftFrame();
        tl_.setWidth(1);
        tl_.setHeight(1);
        FileDialog.setLocation(pr_.getFrameFactory().newCommonFrame("en",pr_,pr_.getImageFactory().newImageArgb(1,1)), tl_);
        assertEq(1,tl_.getHeight());
        assertEq(1,tl_.getWidth());
    }
    @Test
    public void lg1() {
        assertEq("_",FileDialog.checkLgs(new StringList("_"),"_"));
    }
    @Test
    public void lg2() {
        assertEq("",FileDialog.checkLgs(new StringList("_"),"__"));
    }
    @Test
    public void lg3() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        StreamLanguageUtil.saveLanguage("/tmp","_",pr_.getStreams());
        assertEq("_",FileDialog.loadLanguage("/tmp", pr_.getFileCoreStream(), pr_.getStreams(),new StringList("_")));
    }
    @Test
    public void lg4() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        pr_.setCurrentPath("/tmp");
        StreamLanguageUtil.saveLanguage("/tmp","__",pr_.getStreams());
        assertEq("",FileDialog.loadLanguage("/tmp", pr_.getFileCoreStream(), pr_.getStreams(),new StringList("_")));
    }
    @Test
    public void getImage() {
        AbstractImage img_ = FileDialog.getImage("AAABAAAA", new MockImageFactory());
        assertEq(1,img_.getWidth());
        assertEq(1,img_.getHeight());
    }
    @Test
    public void defs() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        update(pr_);
        pr_.getFileCoreStream().newFile("tmp").mkdirs();
        FileSaveDialog f_ = new FileSaveDialog(pr_);
//        f_.getImageIconFrame();
        f_.setTitle("_");
//        f_.getLocationOnScreen();
        assertFalse(f_.getTitle().isEmpty());
        f_.setCurrentTitle("_");
        assertFalse(f_.getCurrentTitle().isEmpty());
        MessageDialogAbs d_ = new DefMessageDialogAbs(new ConfirmDialog(pr_));
        d_.input(pr_.getFrameFactory().newDialog(),"","", 0);
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame("", pr_, pr_.getImageFactory().newImageArgb(1, 1));
        d_.input(fr_,"","", 0);
        d_.input(fr_,pr_.getCompoFactory().newPlainLabel(""),"", 0);
        ConfirmDialogAnsAbs a_ = new DefConfirmDialogAnsAbs(new ConfirmDialog(pr_));
        a_.input(pr_.getFrameFactory().newDialog(),fr_,"","", 0);
        a_.input(fr_,"","", 0);
        ConfirmDialogTextAbs t_ = new DefConfirmDialogTextAbs(new ConfirmDialog(pr_));
        t_.input(fr_,"","","");
        FolderOpenDialogAbs fo_ = new DefFolderOpenDialogAbs(pr_);
        fo_.input(fr_, false);
        fo_.input(fr_, true);
        FileOpenDialogAbs ff_ = new DefFileOpenDialogAbs(pr_);
        ff_.input(fr_, false,"","");
        ff_.input(fr_, true,"","");
        FileSaveDialogAbs s_ = new DefFileSaveDialogAbs(pr_);
        s_.input(fr_, false,"","");
        s_.input(fr_, true,"","");
        s_.input(fr_,pr_.getFrameFactory().newDialog(), false,"","");
        s_.input(fr_,pr_.getFrameFactory().newDialog(), true,"","");
    }
    @Test
    public void lgCh() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.setLanguages(new StringList("_","__"));
        StringMap<String> ds_ = new StringMap<String>();
        ds_.addEntry("_","_");
        ds_.addEntry("__","__");
        pr_.setDisplayLanguages(ds_);
        LanguageDialog l_ = new LanguageDialog(pr_);
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame("", pr_, pr_.getImageFactory().newImageArgb(1, 1));
        l_.init(fr_,pr_,"");
        l_.getContent().getPanel().getComponent(1).getMouseListenersRel().get(0).mouseReleased(null,null,null);
        assertEq("__",l_.getLanguage());
    }
    @Test
    public void lgChButtons1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.setLanguages(new StringList("_","__"));
        StringMap<String> ds_ = new StringMap<String>();
        ds_.addEntry("_","_");
        ds_.addEntry("__","__");
        pr_.setDisplayLanguages(ds_);
        LanguageDialogButtons l_ = new LanguageDialogButtons(pr_,null);
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame("", pr_, pr_.getImageFactory().newImageArgb(1, 1));
        l_.init(fr_,pr_,"",new MockSampleFrame(pr_));
        l_.translate("");
        l_.commonParametersMenu(pr_.getCompoFactory().newMenuItem(),new MockAbstractAction(null),0,0);
        l_.getContent().getGroupe().get(1).getActionListeners().get(0).action();
        assertEq("__",l_.getLanguage());
        l_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(l_.getCommonFrame().isVisible());
    }
    @Test
    public void lgChButtons2() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        pr_.setLanguages(new StringList("_","__"));
        StringMap<String> ds_ = new StringMap<String>();
        ds_.addEntry("_","_");
        ds_.addEntry("__","__");
        pr_.setDisplayLanguages(ds_);
        LanguageDialogButtons l_ = new LanguageDialogButtons(pr_,pr_.getCompoFactory().newMenuItem());
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame("", pr_, pr_.getImageFactory().newImageArgb(1, 1));
        l_.init(fr_,pr_,"",new MockSampleFrame(pr_));
        l_.translate("");
        l_.commonParametersMenu(pr_.getCompoFactory().newMenuItem(),new MockAbstractAction(null),0,0);
        l_.getContent().getGroupe().get(1).getActionListeners().get(0).action();
        assertEq("__",l_.getLanguage());
        l_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(l_.getCommonFrame().isVisible());
    }
    @Test
    public void trs1() {
        MockProgramInfos pr_ = new MockProgramInfos("", "", new MockEventListIncr(dbs(0.75),new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(0, new long[0], StringUtil.wrapStringArray("/")));
        AbsPlainLabel lab_ = pr_.getCompoFactory().newPlainLabel("");
        StringMap<String> trs_ = new StringMap<String>();
        trs_.addEntry("_","__");
        assertTrue(LanguageDialogButtons.translate(lab_, trs_,"_"));
        assertEq("__", lab_.getText());
    }
    @Test
    public void trs2() {
        StringMap<String> trs_ = new StringMap<String>();
        trs_.addEntry("_","__");
        assertFalse(LanguageDialogButtons.translate(null, trs_,"_"));
    }
}
