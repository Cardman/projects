package code.gui;

import code.gui.events.ChangeRadioEvent;
import code.util.CustList;
import code.util.StringList;

public final class CustButtonGroup {

    private CustList<RadioButton> group = new CustList<RadioButton>();
    private StringList values = new StringList();

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

    public void add(RadioButton b, String _value) {
        if (b.getButtonGroup() != null) {
            return;
        }
        b.setButtonGroup(this);
        if (b.isSelected()) {
            for (RadioButton c: group) {
                c.setSelected(false);
            }
            int len_ = group.size();
            for (int i = 0; i < len_; i++) {
                RadioButton r_ = group.get(i);
                String v_ = values.get(i);
                if (StringList.quickEq(v_, _value)) {
                    r_.setSelected(true);
                }
            }
            selected = b;
        }
        group.add(b);
        values.add(_value);
        b.addActionListener(new ChangeRadioEvent(this,b,_value));
    }
    public CustList<RadioButton> getGroup() {
        return group;
    }

    public StringList getValues() {
        return values;
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
