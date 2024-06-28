package code.vi.sys.impl.deps;

import code.stream.LineShortListenable;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public final class SpeakingMp3Event extends PlaybackListener {
    private static final String START = "Start";
    private static final String STOP = "Stop";
    private final LineShortListenable window;

    public SpeakingMp3Event(LineShortListenable _window) {
        window = _window;
    }

    @Override
    public void playbackStarted(PlaybackEvent _event) {
        window.updateMp3(START, _event.getFrame());
    }

    @Override
    public void playbackFinished(PlaybackEvent _event) {
        window.updateMp3(STOP, _event.getFrame());
    }


}
