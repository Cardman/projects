package code.stream;
import java.io.ByteArrayInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import code.resources.ClipStream;

public final class StreamSoundFile {

    private StreamSoundFile() {
    }

    public static ClipStream openClip(byte[] _file) {
        try {
            ByteArrayInputStream bis_ = new ByteArrayInputStream(_file);
            AudioInputStream audioIn_ = AudioSystem.getAudioInputStream(bis_);
            Clip clip_ = AudioSystem.getClip();
            clip_.open(audioIn_);
            bis_.close();
            ClipStream c_ = new ClipStream();
            c_.setClip(clip_);
            c_.setStream(audioIn_);
            return c_;
        } catch (Exception _0) {
            return null;
        }
    }
}
