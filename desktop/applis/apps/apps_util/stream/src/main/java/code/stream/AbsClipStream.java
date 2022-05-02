package code.stream;

public interface AbsClipStream {

    long getMicrosecondLength();

    long getFramePosition();

    boolean isRunning();

    void addLineListener(LineShortListenable _line);
    void start(int _framePosition);

    void stop(long _lastPosition);

    boolean closeClipStream();

}
