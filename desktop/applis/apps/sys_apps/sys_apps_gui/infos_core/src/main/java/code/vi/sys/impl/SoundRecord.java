package code.vi.sys.impl;

import code.maths.litteralcom.MathExpUtil;
import code.stream.AbsSoundRecord;
import code.vi.prot.impl.DefaultFile;
import code.vi.prot.impl.StreamCoreUtil;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public final class SoundRecord implements AbsSoundRecord {
    private AudioFormat candidateFormat;
    private AudioFormat currentFormat;
    private TargetDataLine currentLine;
    private DataLine.Info candidateInfo;
    private DataLine.Info currentInfo;

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
        byte[] bytes_ = new byte[0];
        try {
            currentLine = line();
            return recBytes();
        } catch (Exception e) {
            return bytes_;
        }
    }
    private byte[] recBytes(){
        byte[] bytes_ = new byte[0];
        try {
            // start capturing
            AudioInputStream ais_ = new AudioInputStream(currentLine);
            // start recording
            ByteArrayOutputStream out_ = new ByteArrayOutputStream();
            AudioSystem.write(ais_, AudioFileFormat.Type.WAVE, out_);
            bytes_ = out_.toByteArray();
            ais_.close();
            return bytes_;
        } catch (Exception e) {
            currentLine = null;
            return bytes_;
        }
    }
    @Override
    public byte[] recordSongInFile(String _file) {
        try {
            currentLine = line();
            return rec(_file);
        } catch (Exception e) {
            currentLine = null;
            return new byte[0];
        }
    }
    private TargetDataLine line() {
        try {
            currentLine = (TargetDataLine)AudioSystem.getLine(currentInfo);
            currentLine.open(currentFormat);
            currentLine.start();
            return currentLine;
        } catch (Exception e) {
            return null;
        }
    }
    private byte[] rec(String _file) {
        byte[] w_ = new byte[0];
        try {
            // start capturing
            AudioInputStream aisFile_ = new AudioInputStream(currentLine);
            // start recording
            File out_ = DefaultFile.newFile(_file);
            DefaultFile def_ = new DefaultFile(_file);
            int write_ = AudioSystem.write(aisFile_, AudioFileFormat.Type.WAVE, out_);
            aisFile_.close();
            w_ = new byte[write_];
            FileInputStream fi_ = file(out_);
            read(w_, fi_);
            StreamCoreUtil.close(fi_);
            def_.delete();
            return w_;
        } catch (Exception e) {
            return w_;
        }
    }

    private int read(byte[] _w, FileInputStream _fi) {
        try {
            return _fi.read(_w);
        } catch (Exception e) {
            return 0;
        }
    }

    private static FileInputStream file(File _out) {
        try {
            return new FileInputStream(_out);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void stop() {
        currentLine.stop();
        currentLine.close();
        currentLine = null;
    }
}
