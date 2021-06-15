package code.stream;

public interface AbsClipStream {

    long getMicrosecondLength();

    long getMicrosecondPosition();

    int getFramePosition();

    int getFrameLength();

    boolean isRunning();

    void setFramePosition(int _line);

    void addLineListener(LineShortListenable _line);
    void start();

    void stop();

    void closeClip();
    void closeStream();

}
