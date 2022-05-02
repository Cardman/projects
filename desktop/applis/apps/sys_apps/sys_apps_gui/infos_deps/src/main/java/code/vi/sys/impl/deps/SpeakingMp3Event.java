package code.vi.sys.impl.deps;

import code.stream.LineShortListenable;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public final class SpeakingMp3Event extends PlaybackListener {
    private static final String START_MP_3 = "start_mp3";
    private static final String STOP_MP_3 = "stop_mp3";
    private final LineShortListenable window;

    public SpeakingMp3Event(LineShortListenable _window) {
        window = _window;
    }

    @Override
    public void playbackStarted(PlaybackEvent _event) {
        window.updateMp3(START_MP_3, _event.getFrame());
    }

    @Override
    public void playbackFinished(PlaybackEvent _event) {
        window.updateMp3(STOP_MP_3, _event.getFrame());
    }


}
