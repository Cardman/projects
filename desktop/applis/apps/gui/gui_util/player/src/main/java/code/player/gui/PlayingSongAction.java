package code.player.gui;

import code.gui.events.AbsActionListener;

public final class PlayingSongAction implements AbsActionListener {
    private final WindowRecorder windowRecorder;

    public PlayingSongAction(WindowRecorder _window) {
        this.windowRecorder = _window;
    }
    @Override
    public void action() {
        windowRecorder.play();
    }
}
