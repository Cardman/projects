package code.mock;

import code.stream.AbsSoundRecord;
import code.util.Ints;

public final class MockSoundRecord implements AbsSoundRecord {
    private final Ints bytes = new Ints();
    private boolean active;
    @Override
    public boolean supported(long _sampleRate, int _sampleSize, int _channels, boolean _signed, boolean _bigEndian) {
        return _signed;
    }

    @Override
    public void update() {
        active = true;
    }

    @Override
    public void restore() {
        bytes.clear();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public byte[] recordSong() {
        return convert();
    }

    @Override
    public byte[] recordSongInFile(String _file) {
        return convert();
    }

    private byte[] convert() {
        int size_ = bytes.size();
        byte[] bs_ = new byte[size_];
        for (int i = 0; i < size_; i++){
            int j_ = bytes.get(i);
            bs_[i] = (byte) j_;
        }
        return bs_;
    }
    public void appendHeader() {
        bytes.add((int) 'R');
        bytes.add((int) 'I');
        bytes.add((int) 'F');
        bytes.add((int) 'F');
        bytes.add(0);
        bytes.add(0);
        bytes.add(0);
        bytes.add(0);
        bytes.add((int) 'W');
        bytes.add((int) 'A');
        bytes.add((int) 'V');
        bytes.add((int) 'E');
    }
    public void append(int[] _bytes) {
        for (int i: _bytes) {
            bytes.add(i);
        }
    }

    @Override
    public void stop() {
        active = false;
    }

}
