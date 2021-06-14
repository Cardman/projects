package code.stream;
import javax.sound.sampled.Clip;
import java.io.Closeable;

public final class ClipStream {

    private Clip clip;

    private Closeable stream;

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip _clip) {
        clip = _clip;
    }

    public Closeable getStream() {
        return stream;
    }

    public void setStream(Closeable _stream) {
        stream = _stream;
    }
}
