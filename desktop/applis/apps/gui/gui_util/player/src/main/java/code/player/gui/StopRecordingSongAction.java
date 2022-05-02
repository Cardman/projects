package code.player.gui;

import code.gui.events.AbsActionListener;

public final class StopRecordingSongAction implements AbsActionListener {
    private final WindowRecorder windowRecorder;

    public StopRecordingSongAction(WindowRecorder _window) {
        this.windowRecorder = _window;
    }
    @Override
    public void action() {
        windowRecorder.stop();
    }
}
