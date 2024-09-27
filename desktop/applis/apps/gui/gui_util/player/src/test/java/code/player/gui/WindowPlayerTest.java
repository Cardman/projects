package code.player.gui;

import code.gui.GuiBaseUtil;
import code.gui.LanguagesButtonsPair;
import code.mock.MockClipStream;
import code.mock.MockProgramInfos;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.FullDocument;
import code.sml.core.*;
import code.stream.*;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class WindowPlayerTest extends EquallableSoundPlayerUtil {
    @Test
    public void noLaunch() {
        WindowPlayer w_ = windowPlayer();
        tryClick(w_.getPlay());
        assertNull(w_.getClipStream());
    }
    @Test
    public void noLaunchArg() {
        MockProgramInfos pr_ = build();
        CreateMainWindowPlayer cr_ = new CreateMainWindowPlayer(EN, new StringList(), pr_, new LanguagesButtonsPair(null,null,null), null);
        cr_.run();
        WindowPlayer w_ = cr_.getWindow();
        assertNull(w_.getClipStream());
        w_.changeLanguage(FR);
//        w_.getFrames().getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        w_.quit();
        GuiBaseUtil.tryToReopen(w_.getApplicationName(),w_.getFrames());
    }
    @Test
    public void wave1() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'e', 1),w_.getStreams());
        w_.getSongs().append("file");
        tryClick(w_.getPlay());
        assertNull(w_.getClipStream());
        w_.possibleLaunch();
        new UpdateSongTimeEvent(w_).run();
        assertNull(w_.getClipStream());
    }
    @Test
    public void wave2() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1),w_.getStreams());
        w_.getSongs().append("file");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
        w_.setElapsedTime();
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        tryClick(w_.getPlay());
        assertTrue(w_.isPausing());
        assertNotNull(w_.getClipStream());
        w_.setElapsedTime();
        tryClick(w_.getPlay());
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        tryClick(w_.getStop());
        assertFalse(w_.isPausing());
        assertNull(w_.getClipStream());
    }
    @Test
    public void wave3() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1),w_.getStreams());
        w_.getSongs().append("file");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        ((MockClipStream)w_.getClipStream()).setPosition(w_.getClipStream().getMicrosecondLength()+1L);
        assertFalse(w_.isPausing());
        assertNull(w_.getClipStream());
    }
    @Test
    public void wave4() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'e', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1),w_.getStreams());
        w_.getSongs().append("file1\nfile2");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
    }
    @Test
    public void wave5() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1),w_.getStreams());
        w_.getSongs().append("file");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        w_.update("",0);
        assertFalse(w_.isPausing());
        assertTrue(w_.getClipStream().isRunning());
    }
    @Test
    public void wave6() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1),w_.getStreams());
        w_.getSongs().append("file");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        ((MockClipStream)w_.getClipStream()).setPosition(w_.getClipStream().getMicrosecondLength()+1L);
        tryClick(w_.getPlay());
        assertFalse(w_.isPausing());
        assertTrue(w_.getClipStream().isRunning());
        assertNotNull(w_.getClipStream());
    }
    @Test
    public void wave7() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),w_.getStreams());
        w_.getSongs().append("file1\nfile2");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        ((MockClipStream)w_.getClipStream()).setPosition(w_.getClipStream().getMicrosecondLength()+1L);
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        assertEq(1,w_.getNoSong());
    }
    @Test
    public void wave8() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),w_.getStreams());
        w_.getSongs().append("file1\nfile2");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        tryClick(w_.getPlayNext());
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        assertEq(1,w_.getNoSong());
    }
    @Test
    public void wave9() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),w_.getStreams());
        w_.getSongs().append("file1\nfile2");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        tryClick(w_.getPlayNext());
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        assertEq(1,w_.getNoSong());
        tryClick(w_.getPlayPrevious());
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        assertEq(0,w_.getNoSong());
    }
    @Test
    public void mp1() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file",wrapInts('I', 'd', '3', 1),w_.getStreams());
        w_.getSongs().append("file");
        tryClick(w_.getPlay());
        assertNull(w_.getClipStream());
        w_.possibleLaunch();
        new UpdateSongTimeEvent(w_).run();
        assertNull(w_.getClipStream());
    }
    @Test
    public void mp2() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file",wrapInts('I', 'D', '3', 1),w_.getStreams());
        w_.getSongs().append("file");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
        w_.setElapsedTime();
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        tryClick(w_.getPlay());
        assertTrue(w_.isPausing());
        assertNotNull(w_.getClipStream());
        w_.setElapsedTime();
        tryClick(w_.getPlay());
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        tryClick(w_.getStop());
        assertFalse(w_.isPausing());
        assertNull(w_.getClipStream());
    }
    @Test
    public void mp3() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file",wrapInts('I', 'D', '3', 1),w_.getStreams());
        w_.getSongs().append("file");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        ((MockClipStream)w_.getClipStream()).setPosition(w_.getClipStream().getMicrosecondLength()+1L);
        assertFalse(w_.isPausing());
        assertNull(w_.getClipStream());
    }
    @Test
    public void mp4() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file1",wrapInts('I', 'd', '3', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('I', 'D', '3', 1),w_.getStreams());
        w_.getSongs().append("file1\nfile2");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
    }
    @Test
    public void mp5() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file",wrapInts('I', 'D', '3', 1),w_.getStreams());
        w_.getSongs().append("file");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        w_.updateMp3("",0);
        assertFalse(w_.isPausing());
        assertTrue(w_.getClipStream().isRunning());
    }
    @Test
    public void mp6() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file",wrapInts('I', 'D', '3', 1),w_.getStreams());
        w_.getSongs().append("file");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        ((MockClipStream)w_.getClipStream()).setPosition(w_.getClipStream().getMicrosecondLength()+1L);
        tryClick(w_.getPlay());
        assertFalse(w_.isPausing());
        assertTrue(w_.getClipStream().isRunning());
        assertNotNull(w_.getClipStream());
    }
    @Test
    public void mp7() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file1",wrapInts('I', 'D', '3', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('I', 'D', '3', 2),w_.getStreams());
        w_.getSongs().append("file1\nfile2");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        ((MockClipStream)w_.getClipStream()).setPosition(w_.getClipStream().getMicrosecondLength()+1L);
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        assertEq(1,w_.getNoSong());
    }
    @Test
    public void mp8() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file1",wrapInts('I', 'D', '3', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('I', 'D', '3', 2),w_.getStreams());
        w_.getSongs().append("file1\nfile2");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        tryClick(w_.getPlayNext());
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        assertEq(1,w_.getNoSong());
    }
    @Test
    public void mp9() {
        WindowPlayer w_ = windowPlayer();
        StreamBinaryFile.writeFile("file1",wrapInts('I', 'D', '3', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('I', 'D', '3', 2),w_.getStreams());
        w_.getSongs().append("file1\nfile2");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
//        w_.getClipStream().resume();
        assertTrue(w_.getClipStream().isRunning());
        tryClick(w_.getPlayNext());
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        assertEq(1,w_.getNoSong());
        tryClick(w_.getPlayPrevious());
        assertFalse(w_.isPausing());
        assertNotNull(w_.getClipStream());
        assertEq(0,w_.getNoSong());
    }
    @Test
    public void badList() {
        MockProgramInfos pr_ = build();
        StreamBinaryFile.writeFile("file",wrapInts('<'),pr_.getStreams());
        CreateMainWindowPlayer cr_ = new CreateMainWindowPlayer(EN, new StringList("file"), pr_, new LanguagesButtonsPair(null,null,null), null);
        cr_.run();
        WindowPlayer w_ = cr_.getWindow();
        assertNull(w_.getClipStream());
    }
    @Test
    public void badListNoFile() {
        MockProgramInfos pr_ = build();
        StreamBinaryFile.writeFile("file",wrapInts(),pr_.getStreams());
        CreateMainWindowPlayer cr_ = new CreateMainWindowPlayer(EN, new StringList("file"), pr_, new LanguagesButtonsPair(null,null,null), null);
        cr_.run();
        WindowPlayer w_ = cr_.getWindow();
        assertNull(w_.getClipStream());
    }
    @Test
    public void noStop() {
        WindowPlayer w_ = windowPlayer();
        tryClick(w_.getStop());
        assertNull(w_.getClipStream());
    }
    @Test
    public void noNext() {
        WindowPlayer w_ = windowPlayer();
        tryClick(w_.getPlayNext());
        assertNull(w_.getClipStream());
    }
    @Test
    public void noPrevious() {
        WindowPlayer w_ = windowPlayer();
        tryClick(w_.getPlayPrevious());
        assertNull(w_.getClipStream());
    }
    @Test
    public void waveList1() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'e', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'e', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNull(w_.getClipStream());
    }
    @Test
    public void waveList2() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'e', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
        assertTrue(w_.getClipStream().isRunning());
    }
    @Test
    public void waveList3() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        r_.appendChild(d_.createElement(WindowPlayer.CST_KEY_RANDOM));
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'e', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
        assertTrue(w_.getClipStream().isRunning());
        assertTrue(w_.getRandom().isSelected());
    }
    @Test
    public void waveList4() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element pause_ = d_.createElement(WindowPlayer.CST_KEY_PAUSE);
        pause_.setAttribute(DocumentWriterCoreUtil.VALUE,"1");
        r_.appendChild(pause_);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
        assertTrue(w_.getClipStream().isRunning());
        assertEq(1,w_.getNoSong());
    }
    @Test
    public void waveList5() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element pause_ = d_.createElement(WindowPlayer.CST_KEY_PAUSE);
        pause_.setAttribute(DocumentWriterCoreUtil.VALUE,"2");
        r_.appendChild(pause_);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
        assertTrue(w_.getClipStream().isRunning());
    }
    @Test
    public void waveList6() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
    }
    @Test
    public void mpList1() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('I', 'd', '3', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('I', 'd', '3', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNull(w_.getClipStream());
    }
    @Test
    public void mpList2() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('I', 'd', '3', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('I', 'D', '3', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
        assertTrue(w_.getClipStream().isRunning());
    }
    @Test
    public void mpList3() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        r_.appendChild(d_.createElement(WindowPlayer.CST_KEY_RANDOM));
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('I', 'd', '3', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('I', 'D', '3', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
        assertTrue(w_.getClipStream().isRunning());
        assertTrue(w_.getRandom().isSelected());
    }
    @Test
    public void mpList4() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element pause_ = d_.createElement(WindowPlayer.CST_KEY_PAUSE);
        pause_.setAttribute(DocumentWriterCoreUtil.VALUE,"1");
        r_.appendChild(pause_);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('I', 'D', '3', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('I', 'D', '3', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
        assertTrue(w_.getClipStream().isRunning());
        assertEq(1,w_.getNoSong());
    }
    @Test
    public void mpList5() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element pause_ = d_.createElement(WindowPlayer.CST_KEY_PAUSE);
        pause_.setAttribute(DocumentWriterCoreUtil.VALUE,"2");
        r_.appendChild(pause_);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('I', 'D', '3', 1),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('I', 'D', '3', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
        assertTrue(w_.getClipStream().isRunning());
    }
    @Test
    public void mpList6() {
        WindowPlayer w_ = windowPlayer();
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(DocumentWriterCoreUtil.VALUE,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(DocumentWriterCoreUtil.VALUE,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile("ls", StringUtil.encode(d_.export()),w_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('I', 'D', '3', 2),w_.getStreams());
        w_.getSongs().append("ls");
        tryClick(w_.getPlay());
        assertNotNull(w_.getClipStream());
    }
}
