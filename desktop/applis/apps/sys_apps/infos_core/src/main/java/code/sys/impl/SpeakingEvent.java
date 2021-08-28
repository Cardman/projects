package code.sys.impl;

import code.stream.LineShortListenable;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public final class SpeakingEvent implements LineListener {

    private final LineShortListenable window;

    public SpeakingEvent(LineShortListenable _window) {
        window = _window;
    }

    @Override
    public void update(LineEvent _event) {
        window.update(_event.getType().toString(),_event.getFramePosition());
    }

}
