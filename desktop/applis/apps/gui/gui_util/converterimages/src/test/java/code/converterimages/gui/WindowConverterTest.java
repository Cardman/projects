package code.converterimages.gui;

import code.gui.AbsButton;
import code.gui.files.*;
import code.gui.images.*;
import code.images.*;
import code.mock.*;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.stream.*;
import code.threads.ConcreteInteger;
import code.util.StringMap;
import code.util.core.*;
import org.junit.Test;

public final class WindowConverterTest extends EquallableConverterGuiUtil {
    @Test
    public void r1() {
        WindowConverter w_ = window();
        w_.getFrames().getFileCoreStream().newFile("/from/one").mkdirs();
        w_.getFrames().getFileCoreStream().newFile("/from/one/sub1").mkdirs();
        w_.getFrames().getFileCoreStream().newFile("/from/one/sub2").mkdirs();
        w_.getFrames().getFileCoreStream().newFile("/from/two").mkdirs();
        w_.getFrames().getFileCoreStream().newFile("/from/two/sub1").mkdirs();
        w_.getFrames().getFileCoreStream().newFile("/from/two/sub2").mkdirs();
        StreamBinaryFile.writeFile("/from/one/sub1/1",new byte[0],w_.getStreams());
        StreamBinaryFile.writeFile("/from/one/sub1/2",toBinary(new int[][]{new int[]{1,2},new int[]{3,4}}),w_.getStreams());
        StreamBinaryFile.writeFile("/from/one/sub2/1",new byte[0],w_.getStreams());
        StreamBinaryFile.writeFile("/from/one/sub2/2",toBinary(new int[][]{new int[]{1,2},new int[]{3,4},new int[]{5,6}}),w_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub1/1",new byte[0],w_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub1/2",toBinary(new int[][]{new int[]{1,2,3},new int[]{4,5,6}}),w_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub2/1",new byte[0],w_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub2/2",toBinary(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,9}}),w_.getStreams());
        w_.getPathExport().setText("/from");
        w_.getPath().setText("/to");
        tryClick(w_.getOkButton());
        int[][] i1_ = BaseSixtyFourUtil.getImageByString(StreamTextFile.contentsOfFile("/to/one/sub1/2", w_.getFileCoreStream(), w_.getStreams()));
        assertEq(2,i1_.length);
        assertEq(2,i1_[0].length);
        assertEq(1,i1_[0][0]);
        assertEq(2,i1_[0][1]);
        assertEq(2,i1_[1].length);
        assertEq(3,i1_[1][0]);
        assertEq(4,i1_[1][1]);
        int[][] i2_ = BaseSixtyFourUtil.getImageByString(StreamTextFile.contentsOfFile("/to/one/sub2/2", w_.getFileCoreStream(), w_.getStreams()));
        assertEq(3,i2_.length);
        assertEq(2,i2_[0].length);
        assertEq(1,i2_[0][0]);
        assertEq(2,i2_[0][1]);
        assertEq(2,i2_[1].length);
        assertEq(3,i2_[1][0]);
        assertEq(4,i2_[1][1]);
        assertEq(2,i2_[2].length);
        assertEq(5,i2_[2][0]);
        assertEq(6,i2_[2][1]);
        int[][] i3_ = BaseSixtyFourUtil.getImageByString(StreamTextFile.contentsOfFile("/to/two/sub1/2", w_.getFileCoreStream(), w_.getStreams()));
        assertEq(2,i3_.length);
        assertEq(3,i3_[0].length);
        assertEq(1,i3_[0][0]);
        assertEq(2,i3_[0][1]);
        assertEq(3,i3_[0][2]);
        assertEq(3,i3_[1].length);
        assertEq(4,i3_[1][0]);
        assertEq(5,i3_[1][1]);
        assertEq(6,i3_[1][2]);
        int[][] i4_ = BaseSixtyFourUtil.getImageByString(StreamTextFile.contentsOfFile("/to/two/sub2/2", w_.getFileCoreStream(), w_.getStreams()));
        assertEq(3,i4_.length);
        assertEq(3,i4_[0].length);
        assertEq(1,i4_[0][0]);
        assertEq(2,i4_[0][1]);
        assertEq(3,i4_[0][2]);
        assertEq(3,i4_[1].length);
        assertEq(4,i4_[1][0]);
        assertEq(5,i4_[1][1]);
        assertEq(6,i4_[1][2]);
        assertEq(3,i4_[2].length);
        assertEq(7,i4_[2][0]);
        assertEq(8,i4_[2][1]);
        assertEq(9,i4_[2][2]);
    }
    @Test
    public void r2() {
        WindowConverter w_ = window();
        w_.getFrames().getFileCoreStream().newFile("/from/one").mkdirs();
        w_.getFrames().getFileCoreStream().newFile("/from/one/sub1").mkdirs();
        w_.getFrames().getFileCoreStream().newFile("/from/one/sub2").mkdirs();
        w_.getFrames().getFileCoreStream().newFile("/from/two").mkdirs();
        w_.getFrames().getFileCoreStream().newFile("/from/two/sub1").mkdirs();
        w_.getFrames().getFileCoreStream().newFile("/from/two/sub2").mkdirs();
        StreamBinaryFile.writeFile("/from/one/sub1/1",new byte[0],w_.getStreams());
        StreamTextFile.saveTextFile("/from/one/sub1/2",toText(new int[][]{new int[]{1,2},new int[]{3,4}}),w_.getStreams());
        StreamBinaryFile.writeFile("/from/one/sub2/1",new byte[0],w_.getStreams());
        StreamTextFile.saveTextFile("/from/one/sub2/2",toText(new int[][]{new int[]{1,2},new int[]{3,4},new int[]{5,6}}),w_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub1/1",new byte[0],w_.getStreams());
        StreamTextFile.saveTextFile("/from/two/sub1/2",toText(new int[][]{new int[]{1,2,3},new int[]{4,5,6}}),w_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub2/1",new byte[0],w_.getStreams());
        StreamTextFile.saveTextFile("/from/two/sub2/2",toText(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,9}}),w_.getStreams());
        w_.getPathExport().setText("/from");
        w_.getPath().setText("/to");
        tryToggle(w_.getReadImages());
        tryClick(w_.getOkButton());
        int[][] i1_ = BaseSixtyFourUtil.getImageByString(StringUtil.decode(StreamBinaryFile.loadFile("/to/one/sub1/2",w_.getStreams()).getBytes()));
        assertEq(2,i1_.length);
        assertEq(2,i1_[0].length);
        assertEq(1,i1_[0][0]);
        assertEq(2,i1_[0][1]);
        assertEq(2,i1_[1].length);
        assertEq(3,i1_[1][0]);
        assertEq(4,i1_[1][1]);
        int[][] i2_ = BaseSixtyFourUtil.getImageByString(StringUtil.decode(StreamBinaryFile.loadFile("/to/one/sub2/2",w_.getStreams()).getBytes()));
        assertEq(3,i2_.length);
        assertEq(2,i2_[0].length);
        assertEq(1,i2_[0][0]);
        assertEq(2,i2_[0][1]);
        assertEq(2,i2_[1].length);
        assertEq(3,i2_[1][0]);
        assertEq(4,i2_[1][1]);
        assertEq(2,i2_[2].length);
        assertEq(5,i2_[2][0]);
        assertEq(6,i2_[2][1]);
        int[][] i3_ = BaseSixtyFourUtil.getImageByString(StringUtil.decode(StreamBinaryFile.loadFile("/to/two/sub1/2",w_.getStreams()).getBytes()));
        assertEq(2,i3_.length);
        assertEq(3,i3_[0].length);
        assertEq(1,i3_[0][0]);
        assertEq(2,i3_[0][1]);
        assertEq(3,i3_[0][2]);
        assertEq(3,i3_[1].length);
        assertEq(4,i3_[1][0]);
        assertEq(5,i3_[1][1]);
        assertEq(6,i3_[1][2]);
        int[][] i4_ = BaseSixtyFourUtil.getImageByString(StringUtil.decode(StreamBinaryFile.loadFile("/to/two/sub2/2",w_.getStreams()).getBytes()));
        assertEq(3,i4_.length);
        assertEq(3,i4_[0].length);
        assertEq(1,i4_[0][0]);
        assertEq(2,i4_[0][1]);
        assertEq(3,i4_[0][2]);
        assertEq(3,i4_[1].length);
        assertEq(4,i4_[1][0]);
        assertEq(5,i4_[1][1]);
        assertEq(6,i4_[1][2]);
        assertEq(3,i4_[2].length);
        assertEq(7,i4_[2][0]);
        assertEq(8,i4_[2][1]);
        assertEq(9,i4_[2][2]);
    }
    @Test
    public void r3() {
        MockProgramInfos pr_ = build();
        pr_.lg("");
        pr_.setLanguage("");
        updateBase(pr_.currentLg());
        pr_.getFileCoreStream().newFile("/from").mkdirs();
        StreamBinaryFile.writeFile("/from/_",new byte[0],pr_.getStreams());
        StringMap<BoolVal> map_ = new StringMap<BoolVal>();
        map_.addEntry("/from/_",BoolVal.TRUE);
        CreateMainWindowConverter cr_ = new CreateMainWindowConverter("", pr_);
        cr_.run();
        tryClick(cr_.getWindow().getReadButton());
        assertTrue(cr_.getWindow().getFolderOpenFrame().getFrame().isVisible());
        cr_.getWindow().getFolderOpenFrame().getFolderOpenDialogContent().getFileName().setText("_");
        cr_.getWindow().getFolderOpenFrame().getFolderOpenDialogContent().setSelectedPath("_");
        tryClick((AbsButton) cr_.getWindow().getFolderOpenFrame().getFolderOpenDialogContent().getButtons().getComponent(0));
        assertEq("_",cr_.getWindow().getPathExport().getText());
        cr_.getWindow().changeLanguage("");
        pr_.getCounts().addEntry(cr_.getWindow().getApplicationName(),new ConcreteInteger());
        cr_.getWindow().quit();
    }

    public static void updateBase(TranslationsLg _en) {
        StringMap<TranslationsFile> en_ = FileFrame.initAppliTr(_en).getMapping();
        en_.addEntry(FileFrame.FILE_DIAL, MessagesFileDialog.en());
        en_.addEntry(FileFrame.CONFIRM, MessagesConfirmDialog.en());
        en_.addEntry(FolderOpenFrame.FOLDER_OPEN_DIAL, MessagesFolderOpenDialog.en());
        en_.addEntry(FileOpenFrame.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        en_.addEntry(FileSaveFrame.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        en_.addEntry(FileTable.FILE_TAB,MessagesFileTable.en());
    }

    private static byte[] toBinary(int[][] _img) {
        AbstractImage img_ =  new MockImage(_img);
        return img_.writeImg("");
    }
    private static String toText(int[][] _img) {
        return BaseSixtyFourUtil.getStringByImage(_img);
    }
}
