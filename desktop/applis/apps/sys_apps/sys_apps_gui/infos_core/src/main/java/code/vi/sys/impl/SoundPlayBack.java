package code.vi.sys.impl;

import code.stream.AbsPlayBack;
import code.threads.AbstractAtomicBoolean;
import code.vi.prot.impl.DefAtomicBoolean;

import javax.sound.sampled.*;

public final class SoundPlayBack implements AbsPlayBack {
    private final AudioInputStream audioInputStream;
    private final AudioFormat currentFormat;
    private AudioInputStream playbackInputStream;
    private final AbstractAtomicBoolean state = new DefAtomicBoolean(false);
    private final AbstractAtomicBoolean ok = new DefAtomicBoolean(true);
    private SourceDataLine line;
    private int numBytesRead;
    private int numBytesRemaining;
    private byte[] data = new byte[0];

    public SoundPlayBack(AudioInputStream _a, AudioFormat _f) {
        this.audioInputStream = _a;
        this.currentFormat = _f;
    }

    @Override
    public boolean prepare() {
        try {
            playbackInputStream = AudioSystem.getAudioInputStream(currentFormat,
                    audioInputStream);
            line = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, currentFormat));
            line.open(currentFormat, 16384);
            data = new byte[(line.getBufferSize() / 8) * currentFormat.getFrameSize()];
            // start the source data line
            line.start();
            state.set(true);
            return true;
        } catch (Exception e) {
            ok.set(false);
            return false;
        }
    }

    @Override
    public int readBytes() {
        try {
            int r_ = playbackInputStream.read(data);
            numBytesRead = r_;
            return r_;
        } catch (Exception e) {
            ok.set(false);
            return -1;
        }
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
    public void writeBytes() {
        numBytesRemaining -= line.write(data, 0, numBytesRemaining);
    }

    @Override
    public AbstractAtomicBoolean getState() {
        return state;
    }

    @Override
    public AbstractAtomicBoolean getOk() {
        return ok;
    }

    @Override
    public boolean drain() {
        line.drain();
        return true;
    }

    @Override
    public boolean finish() {
        line.stop();
        line.close();
        return true;
    }
}
