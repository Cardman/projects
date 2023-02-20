package code.mock;

import code.stream.AbsPlayBack;
import code.stream.AbsSoundRecord;
import code.util.Ints;

public final class MockSoundRecord implements AbsSoundRecord {
    private final Ints bytes = new Ints();
    private final Ints out = new Ints();
    private int indexRead = -1;
    private final MockAtomicBoolean state = new MockAtomicBoolean(false);
    private boolean active;
    private int readValue;

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

    @Override
    public MockAtomicBoolean getState() {
        return state;
    }

    @Override
    public int readBytes() {
        int next_ = indexRead + 1;
        if (bytes.isValidIndex(next_)) {
            readValue = bytes.get(next_);
            indexRead++;
            return 1;
        }
        indexRead = -1;
        return -1;
    }

    @Override
    public void writeBytes() {
        out.add(readValue);
    }

    @Override
    public long millis() {
        return out.size();
    }

    public Ints getBytes() {
        return bytes;
    }

    @Override
    public AbsPlayBack build() {
        if (out.isEmpty()) {
            return null;
        }
        return new MockPlayBack(this);
    }
}
