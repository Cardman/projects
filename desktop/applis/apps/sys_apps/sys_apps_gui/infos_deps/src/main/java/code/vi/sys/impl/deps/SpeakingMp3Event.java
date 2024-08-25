package code.vi.sys.impl.deps;

import code.player.gui.MessagesSongs;
import code.stream.LineShortListenable;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public final class SpeakingMp3Event extends PlaybackListener {
    private final LineShortListenable window;

    public SpeakingMp3Event(LineShortListenable _window) {
        window = _window;
    }

    @Override
    public void playbackStarted(PlaybackEvent _event) {
        window.updateMp3(MessagesSongs.START, _event.getFrame());
    }

    @Override
    public void playbackFinished(PlaybackEvent _event) {
        window.updateMp3(MessagesSongs.STOP, _event.getFrame());
    }


}
