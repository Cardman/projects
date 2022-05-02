package code.stream;

public interface AbsClipStream {

    long getMicrosecondLength();

    boolean isRunning();

    void addLineListener(LineShortListenable _line);
    void resume();

    void stop(long _lastPosition);

    boolean closeClipStream();

}
