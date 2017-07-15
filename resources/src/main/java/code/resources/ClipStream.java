package code.resources;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public final class ClipStream {

    private Clip clip;

    private AudioInputStream stream;

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip _clip) {
        clip = _clip;
    }

    public AudioInputStream getStream() {
        return stream;
    }

    public void setStream(AudioInputStream _stream) {
        stream = _stream;
    }
}
