package code.player.gui;

import code.gui.events.AbsChangeListener;

public final class StatusChangeSlider extends AbsStatusChange implements AbsChangeListener {
    public StatusChangeSlider(WindowRecorder _window) {
        super(_window);
    }

    @Override
    public void stateChanged() {
        setState();
    }
}
