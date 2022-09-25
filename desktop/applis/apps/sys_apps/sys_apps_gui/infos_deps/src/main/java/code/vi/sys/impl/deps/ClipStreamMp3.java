package code.vi.sys.impl.deps;

import code.stream.AbsClipStream;
import code.stream.LineShortListenable;
import code.threads.AbstractThread;
import code.threads.AbstractThreadFactory;
import code.util.core.NumberUtil;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.ByteArrayInputStream;

public final class ClipStreamMp3 implements AbsClipStream {
    private final byte[] bytes;
    private AdvancedPlayer pl;
    private final AbstractThreadFactory abstractThreadFactory;
    private AbstractThread abstractThread;
    private long frame;
    private final long rat;
    private final long microsecondLength;
    private SpeakingMp3Event speakingMp3Event;

    public ClipStreamMp3(byte[] _bs, AbstractThreadFactory _threadFactory, long _micros, long _ratio) {
        bytes = _bs;
        abstractThreadFactory = _threadFactory;
        rat = _ratio;
        microsecondLength = _micros;
    }

    static AdvancedPlayer player(byte[] _file) {
        try {
            return new AdvancedPlayer(new ByteArrayInputStream(_file));
        } catch (Exception e) {
            return null;
        }
    }

    public long getFramePosition() {
        return frame;
    }

    @Override
    public long getMicrosecondLength() {
        return microsecondLength;
    }

    @Override
    public boolean isRunning() {
        try {
            return abstractThread.isAlive();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void addLineListener(LineShortListenable _line) {
        speakingMp3Event = new SpeakingMp3Event(_line);
        startMp3(0);
    }

    @Override
    public void resume() {
        startMp3((int) getFramePosition());
    }

    public void startMp3(int _framePosition) {
        pl = player(bytes);
        loop(_framePosition);
    }

    private void loop(int _framePosition) {
        pl.setPlayBackListener(speakingMp3Event);
        abstractThread = abstractThreadFactory.newThread(new RunnableMp3(pl, _framePosition));
        abstractThread.start();
    }

    @Override
    public void stop(long _lastPosition) {
        frame = (_lastPosition / 1000) / NumberUtil.max(rat,1);
        pl.stop();
    }

    @Override
    public boolean closeClipStream() {
        try {
            pl.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static boolean play(AdvancedPlayer _player, int _frames) {
        try {
            return _player.play(_frames, Integer.MAX_VALUE);
        } catch (Exception e) {
            return false;
        }
    }
}
