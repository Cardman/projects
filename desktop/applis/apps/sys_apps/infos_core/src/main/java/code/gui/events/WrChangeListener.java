package code.gui.events;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public final class WrChangeListener implements ChangeListener {
    private final AbsChangeListener changeListener;

    public WrChangeListener(AbsChangeListener _changeListener) {
        this.changeListener = _changeListener;
    }

    @Override
    public void stateChanged(ChangeEvent _e) {
        changeListener.stateChanged();
    }
}
