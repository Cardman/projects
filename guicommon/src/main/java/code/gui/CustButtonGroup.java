package code.gui;

import code.gui.events.ChangeRadioEvent;
import code.util.CustList;

public final class CustButtonGroup {

    private CustList<RadioButton> group = new CustList<RadioButton>();

    private RadioButton selected;

    public void add(RadioButton b) {
        if (b.getButtonGroup() != null) {
            return;
        }
        b.setButtonGroup(this);
        if (b.isSelected()) {
            for (RadioButton c: group) {
                c.setSelected(false);
            }
            selected = b;
        }
        group.add(b);
        b.addActionListener(new ChangeRadioEvent(this,b));
    }

    public CustList<RadioButton> getGroup() {
        return group;
    }

    public RadioButton getSelected() {
        return selected;
    }

    public void clearSelection() {
        for (RadioButton c: group) {
            c.setSelected(false);
        }
    }

    public int getButtonCount() {
        return group.size();
    }
}
