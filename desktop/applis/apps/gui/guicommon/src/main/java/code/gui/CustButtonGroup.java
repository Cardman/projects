package code.gui;

import code.gui.events.ChangeRadioEvent;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class CustButtonGroup {

    private CustList<RadioButton> group = new CustList<RadioButton>();
    private StringList values = new StringList();

    private RadioButton selected;

    public void add(RadioButton _b) {
        if (_b.getButtonGroup() != null) {
            return;
        }
        _b.setButtonGroup(this);
        if (_b.isSelected()) {
            for (RadioButton c: group) {
                c.setSelected(false);
            }
            selected = _b;
        }
        group.add(_b);
        _b.addActionListener(new ChangeRadioEvent(this,_b));
    }

    public void add(RadioButton _b, String _value) {
        if (_b.getButtonGroup() != null) {
            return;
        }
        _b.setButtonGroup(this);
        if (_b.isSelected()) {
            for (RadioButton c: group) {
                c.setSelected(false);
            }
            int len_ = group.size();
            for (int i = 0; i < len_; i++) {
                RadioButton r_ = group.get(i);
                String v_ = values.get(i);
                if (StringUtil.quickEq(v_, _value)) {
                    r_.setSelected(true);
                }
            }
            selected = _b;
        }
        group.add(_b);
        values.add(_value);
        _b.addActionListener(new ChangeRadioEvent(this,_b,_value));
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
