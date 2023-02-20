package code.mock;

import code.stream.AbsPlayBack;
import code.threads.AbstractAtomicBoolean;
import code.util.Ints;

public final class MockPlayBack implements AbsPlayBack {
    private final AbstractAtomicBoolean state = new MockAtomicBoolean(false);
    private int numBytesRead;
    private int numBytesRemaining;
    private int indexRead = -1;
    private final Ints bytes = new Ints();
    public MockPlayBack(MockSoundRecord _sound) {
        bytes.addAllElts(_sound.getBytes());
    }
    @Override
    public boolean prepare() {
        getState().set(bytes.size() > 8);
        return bytes.size() > 4;
    }

    @Override
    public int readBytes() {
        int next_ = indexRead + 1;
        if (bytes.isValidIndex(next_)) {
            indexRead++;
            numBytesRead = 1;
            return 1;
        }
        numBytesRead = -1;
        indexRead = -1;
        return -1;
    }

    @Override
    public void remainPrep() {
        numBytesRemaining = numBytesRead;
    }

    @Override
    public int remain() {
        return numBytesRemaining;
    }

    @Override
    public AbstractAtomicBoolean getState() {
        return state;
    }

    @Override
    public void writeBytes() {
        numBytesRemaining-=2;
    }

    @Override
    public boolean drain() {
        return false;
    }

    @Override
    public boolean finish() {
        return false;
    }
}
