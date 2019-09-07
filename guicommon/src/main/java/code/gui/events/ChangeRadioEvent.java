package code.gui.events;

import code.gui.CustButtonGroup;
import code.gui.RadioButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ChangeRadioEvent implements ActionListener {
    private CustButtonGroup group;
    private RadioButton radio;

    public ChangeRadioEvent(CustButtonGroup _group, RadioButton _radio) {
        group = _group;
        radio = _radio;
    }

    @Override
    public void actionPerformed(ActionEvent _arg) {
        for (RadioButton r: group.getGroup()) {
            r.setSelected(false);
        }
        radio.setSelected(true);
    }
}
