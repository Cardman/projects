package code.player.gui;

import code.gui.GuiBaseUtil;
import code.mock.MockSoundRecord;
import code.mock.MockThreadFactory;
import code.threads.ConcreteInteger;
import org.junit.Test;

public final class WindowRecorderTest extends EquallableSoundPlayerUtil {
    @Test
    public void playNoRecord() {
        WindowRecorder w_ = windowRecorder();
        int rate_ = w_.getRate().getValue();
        w_.getRate().setValue(44200);
        w_.getRate().setValue(rate_);
        int size_ = w_.getSize().getValue();
        w_.getSize().setValue(17);
        w_.getSize().setValue(size_);
        int channel_ = w_.getChannel().getValue();
        w_.getChannel().setValue(3);
        w_.getChannel().setValue(channel_);
        tryToggle(w_.getBigEndian());
        tryToggle(w_.getBigEndian());
        tryToggle(w_.getSigned());
        tryToggle(w_.getSigned());
        assertFalse(w_.getRecordSong().isEnabled());
        w_.getSoundRecord().stop();
        assertFalse(w_.okStop());
    }
    @Test
    public void playWithRecord1() {
        CreateMainWindowRecorder cr_ = new CreateMainWindowRecorder(build(),"", null);
        cr_.run();
        WindowRecorder w_ = cr_.getWindowRecorder();
        assertTrue(w_.getRecordSong().isEnabled());
        tryClick(w_.getRecordSong());
        w_.getFileSave().setText("file");
        tryClick(w_.getRecordSong());
        tryAn((MockThreadFactory) w_.getFrames().getThreadFactory());
        assertFalse(w_.getFrames().getFileCoreStream().newFile("file").exists());
        w_.changeLanguage("");
//        w_.getFrames().getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        w_.quit();
        GuiBaseUtil.tryToReopen(w_.getApplicationName(),w_.getFrames());
    }
    @Test
    public void playWithRecord2() {
        WindowRecorder w_ = windowRecorder();
        assertTrue(w_.getRecordSong().isEnabled());
        tryClick(w_.getRecordSong());
        w_.getFileSave().setText("file");
        tryClick(w_.getRecordSong());
        ((MockSoundRecord)w_.getSoundRecord()).appendHeader();
        ((MockSoundRecord)w_.getSoundRecord()).append(new int[]{1});
        tryAn((MockThreadFactory) w_.getFrames().getThreadFactory());
        assertTrue(w_.getFrames().getFileCoreStream().newFile("file").exists());
        tryClick(w_.getStopSong());
        tryClick(w_.getPlaySong());
        tryAn((MockThreadFactory) w_.getFrames().getThreadFactory());
        tryClick(w_.getStopSong());
    }
    @Test
    public void playWithRecord3() {
        WindowRecorder w_ = windowRecorder();
        assertTrue(w_.getRecordSong().isEnabled());
        tryClick(w_.getRecordSong());
        w_.getFileSave().setText("file");
        tryClick(w_.getRecordSong());
        ((MockSoundRecord)w_.getSoundRecord()).appendHeader();
        ((MockSoundRecord)w_.getSoundRecord()).append(new int[]{1});
        tryAn((MockThreadFactory) w_.getFrames().getThreadFactory());
        assertTrue(w_.getFrames().getFileCoreStream().newFile("file").exists());
        tryClick(w_.getStopSong());
        w_.getSoundRecord().writeBytes();
        w_.playBack();
        tryClick(w_.getStopSong());
    }
}
