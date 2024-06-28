package code.mock;

import code.maths.montecarlo.AbstractGenerator;
import code.stream.AbsClipStream;
import code.stream.LineShortListenable;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class MockClipStream implements AbsClipStream {

    private static final String START = "Start";
    private static final String STOP = "Stop";
//    private static final String CLOSE = "Close";
    private boolean running;
    private final long microsecondLength;
    private final boolean wave;
    private final CustList<LineShortListenable> listeners = new CustList<LineShortListenable>();
    private long position;
    private final MockAbsRand mockRand;
    public MockClipStream(AbstractGenerator _gen, long _length, boolean _wav) {
        this(new MockRand(_gen),_length,_wav);
    }
    public MockClipStream(MockAbsRand _gen, long _length, boolean _wav) {
        mockRand = _gen;
        microsecondLength = _length;
        wave = _wav;
    }
    @Override
    public long getMicrosecondLength() {
        return microsecondLength;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void addLineListener(LineShortListenable _lineShortListenable) {
        listeners.add(_lineShortListenable);
        resume();
    }

    @Override
    public void resume() {
        running = true;
        for (LineShortListenable l: listeners) {
            if (isWave()) {
                l.update(START,position);
            } else {
                l.updateMp3(START,position);
            }
        }
    }

    @Override
    public void stop(long _l) {
        position = _l;
        running = false;
        closeClipStream();
//        for (LineShortListenable l: listeners) {
//            if (isWave()) {
//                l.update(STOP,position);
//            } else {
//                l.updateMp3(STOP,position);
//            }
//        }
    }

    @Override
    public boolean closeClipStream() {
        for (LineShortListenable l: listeners) {
            if (isWave()) {
                l.update(STOP,position);
            } else {
                l.updateMp3(STOP,position);
            }
        }
        return mockRand.edit();
    }

    public boolean isWave() {
        return wave;
    }

    public void setPosition(long _pos) {
        this.position = NumberUtil.max(_pos,0);
        if (position >= getMicrosecondLength()) {
            stop(position);
        }
    }
}
