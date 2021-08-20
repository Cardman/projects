package aiki.gui.components.checks;
import code.gui.events.AbsActionListener;

public final class CheckEvent implements AbsActionListener {

    private CheckBox checkBox;

    public CheckEvent(CheckBox _checkBox) {
        checkBox = _checkBox;
    }

    @Override
    public void action() {
        checkBox.processKey(checkBox.getKey());
    }

}
