package code.vi.sys.impl;

import code.gui.GuiBaseUtil;
import code.maths.litteralcom.MathExpUtil;
import code.stream.AbsPlayBack;
import code.stream.AbsSoundRecord;
import code.threads.AbstractAtomicBoolean;
import code.vi.prot.impl.DefAtomicBoolean;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public final class SoundRecord implements AbsSoundRecord {
    private AudioInputStream audioInputStream;
    private AudioFormat candidateFormat;
    private AudioFormat currentFormat;
    private TargetDataLine currentLine;
    private DataLine.Info candidateInfo;
    private DataLine.Info currentInfo;
    private final AbstractAtomicBoolean state = new DefAtomicBoolean(false);
    private ByteArrayOutputStream out =new ByteArrayOutputStream();
    private int frameSizeInBytes;
    private int bufferLengthInBytes;
    private byte[] data = new byte[0];
    private int numBytesRead;
    private long duration;

    @Override
    public boolean supported(long _sampleRate, int _sampleSize, int _channels, boolean _signed, boolean _bigEndian) {
        candidateFormat = new AudioFormat((float) MathExpUtil.toDouble(_sampleRate), _sampleSize, _channels, _signed, _bigEndian);
        candidateInfo = new DataLine.Info(TargetDataLine.class, candidateFormat);
        return AudioSystem.isLineSupported(candidateInfo);
    }

    @Override
    public void update() {
        currentFormat = candidateFormat;
        currentInfo = candidateInfo;
    }

    @Override
    public void restore() {
        candidateFormat = null;
        candidateInfo = null;
    }

    @Override
    public boolean isActive() {
        try {
            return currentLine.isActive();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public byte[] recordSong() {
        try {
            currentLine = line();
            getState().set(true);
            GuiBaseUtil.recordSong(this);
            currentLine.stop();
            currentLine.close();
            currentLine = null;
            out.flush();
            out.close();
            byte[] bs_ = out.toByteArray();
            audioInputStream = new AudioInputStream(new ByteArrayInputStream(bs_), currentFormat, bs_.length / frameSizeInBytes);
            duration = (long) ((audioInputStream.getFrameLength() * 1000) / currentFormat.getFrameRate());
            audioInputStream.reset();
            ByteArrayOutputStream out_ = new ByteArrayOutputStream();
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out_);
            byte[] bytes_ = out_.toByteArray();
            audioInputStream.close();
            return bytes_;
        } catch (Exception e) {
            currentLine = null;
            return new byte[0];
        }
    }
//    @Override
//    public byte[] recordSongInFile(String _file) {
//        try {
//            currentLine = line();
//            return rec(_file);
//        } catch (Exception e) {
//            currentLine = null;
//            return new byte[0];
//        }
//    }
    private TargetDataLine line() {
        try {
            currentLine = (TargetDataLine)AudioSystem.getLine(currentInfo);
            currentLine.open(currentFormat, currentLine.getBufferSize());
            out =new ByteArrayOutputStream();
            frameSizeInBytes = currentFormat.getFrameSize();
            int bufferLengthInFrames_ = currentLine.getBufferSize() / 8;
            bufferLengthInBytes = bufferLengthInFrames_ * frameSizeInBytes;
            data = new byte[bufferLengthInBytes];
            currentLine.start();
            return currentLine;
        } catch (Exception e) {
            return null;
        }
    }
//    private byte[] rec(String _file) {
//        byte[] w_ = new byte[0];
//        try {
//            // start capturing
//            AudioInputStream aisFile_ = new AudioInputStream(currentLine);
//            // start recording
//            File out_ = DefaultFile.newFile(_file);
//            DefaultFile def_ = new DefaultFile(_file);
//            AudioSystem.write(aisFile_, AudioFileFormat.Type.WAVE, out_);
//            aisFile_.close();
//            w_ =  StreamBinaryFile.loadFile(_file,str).getBytes();
//            def_.delete();
//            return w_;
//        } catch (Exception e) {
//            return w_;
//        }
//    }

    @Override
    public void stop() {
        currentLine.stop();
        currentLine.close();
        currentLine = null;
    }

    @Override
    public AbstractAtomicBoolean getState() {
        return state;
    }

    @Override
    public int readBytes() {
        int r_ = currentLine.read(data, 0, bufferLengthInBytes);
        numBytesRead = r_;
        return r_;
    }

    @Override
    public void writeBytes() {
        out.write(data, 0, numBytesRead);
    }

    @Override
    public long millis() {
        return duration;
    }

    @Override
    public AbsPlayBack build() {
        try {
            SoundPlayBack s_ = new SoundPlayBack(audioInputStream, currentFormat);
            audioInputStream.reset();
            return s_;
        } catch (Exception e) {
            return null;
        }
    }
}
