package code.stream;

import code.threads.AbstractAtomicBoolean;

public interface AbsSoundRecord {
    boolean supported(long _sampleRate, int _sampleSize, int _channels, boolean _signed, boolean _bigEndian);
    void update();
    void restore();
    boolean isActive();
    byte[] recordSong();
    byte[] recordSongInFile(String _file);
    void stop();
    AbstractAtomicBoolean getState();

    int readBytes();

    void writeBytes();

    long millis();
}
