package code.player.gui;

import code.gui.events.AbsActionListener;

public final class RecordingSongAction implements AbsActionListener {
    private final WindowRecorder windowRecorder;

    public RecordingSongAction(WindowRecorder _window) {
        this.windowRecorder = _window;
    }
    @Override
    public void action() {
        windowRecorder.eventRecord();
    }
}
