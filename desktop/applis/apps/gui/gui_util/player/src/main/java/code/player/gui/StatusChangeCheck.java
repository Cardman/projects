package code.player.gui;

import code.gui.events.AbsActionListener;

public final class StatusChangeCheck extends AbsStatusChange implements AbsActionListener {
    public StatusChangeCheck(WindowRecorder _window) {
        super(_window);
    }

    @Override
    public void action() {
        setState();
    }
}
