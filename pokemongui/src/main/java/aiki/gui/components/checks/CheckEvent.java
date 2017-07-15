package aiki.gui.components.checks;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class CheckEvent implements ActionListener {

    private CheckBox checkBox;

    public CheckEvent(CheckBox _checkBox) {
        checkBox = _checkBox;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        checkBox.processKey(checkBox.getKey());
    }

}
