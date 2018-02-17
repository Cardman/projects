package code.stream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import code.resources.ClipStream;
import code.resources.ResourceFiles;
import code.util.opers.BaseSixtyFourUtil;

public final class StreamSoundFile {

    private StreamSoundFile() {
    }

    public static ClipStream createClip(AudioInputStream _a) {
        return createClip(null, _a);
    }

    public static ClipStream createClip(LineListener _l, AudioInputStream _a) {
        try {
            Clip clip_ = AudioSystem.getClip();
            AudioInputStream ais_ = AudioSystem.getAudioInputStream(_a);
            if (_l != null) {
                clip_.addLineListener(_l);
            }
            clip_.open(ais_);
            ClipStream c_ = new ClipStream();
            c_.setClip(clip_);
            c_.setStream(ais_);
            return c_;
        } catch (RuntimeException _0) {
            return null;
        } catch (LineUnavailableException _0) {
            return null;
        } catch (UnsupportedAudioFileException _0) {
            return null;
        } catch (IOException _0) {
            return null;
        }
    }

    public static ClipStream resourceClipSixtyFour(String _file) {
        String content_ = ResourceFiles.ressourceFichier(_file);
        return openClip(content_, null);
    }

    public static ClipStream resourceClipSixtyFour(String _file, LineListener _l) {
        String content_ = ResourceFiles.ressourceFichier(_file);
        return openClip(content_, _l);
    }

    public static ClipStream openClip(String _imageString) {
        return openClip(BaseSixtyFourUtil.parseBaseSixtyFourBinary(_imageString), null);
    }

    public static ClipStream openClip(String _imageString, LineListener _l) {
        return openClip(BaseSixtyFourUtil.parseBaseSixtyFourBinary(_imageString), _l);
    }

    public static ClipStream openClip(byte[] _file) {
        return openClip(_file, null);
    }

    public static ClipStream openClip(byte[] _file, LineListener _l) {
        try {
            ByteArrayInputStream bis_ = new ByteArrayInputStream(_file);
            AudioInputStream audioIn_ = AudioSystem.getAudioInputStream(bis_);
            Clip clip_ = AudioSystem.getClip();
            if (_l != null) {
                clip_.addLineListener(_l);
            }
            clip_.open(audioIn_);
            bis_.close();
            ClipStream c_ = new ClipStream();
            c_.setClip(clip_);
            c_.setStream(audioIn_);
            return c_;
        } catch (RuntimeException _0) {
            return null;
        } catch (LineUnavailableException _0) {
            return null;
        } catch (UnsupportedAudioFileException _0) {
            return null;
        } catch (IOException _0) {
            return null;
        }
    }

//    public static void openClip(Clip _clip, byte[] _file, AudioFormat _format) throws LineUnavailableException {
//        _clip.open(_format, _file, List.FIRST_INDEX, _file.length);
//    }
}
