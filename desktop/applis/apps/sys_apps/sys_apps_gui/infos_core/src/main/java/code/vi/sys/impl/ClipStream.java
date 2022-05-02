package code.vi.sys.impl;
import code.stream.AbsClipStream;
import code.stream.LineShortListenable;
import code.vi.prot.impl.StreamCoreUtil;

import javax.sound.sampled.Clip;
import java.io.Closeable;

public final class ClipStream implements AbsClipStream {

    private final Clip clip;

    private final Closeable stream;

    public ClipStream(Clip _clip,Closeable _stream) {
        clip = _clip;
        stream = _stream;
    }

    public long getMicrosecondLength() {
        return clip.getMicrosecondLength();
    }

    public long getFramePosition() {
        return clip.getFramePosition();
    }

    public boolean isRunning() {
        return clip.isRunning();
    }

    public void setFramePosition(int _line) {
        clip.setFramePosition(_line);
    }

    public void addLineListener(LineShortListenable _line) {
        start();
        clip.addLineListener(new SpeakingEvent(_line));
    }
    public void start() {
        clip.start();
    }

    @Override
    public void start(int _framePosition) {
        setFramePosition(_framePosition);
        clip.start();
    }

    @Override
    public void stop(long _lastPosition) {
        clip.stop();
    }

    @Override
    public boolean closeClipStream() {
        clip.close();
        return StreamCoreUtil.close(stream);
    }

}
