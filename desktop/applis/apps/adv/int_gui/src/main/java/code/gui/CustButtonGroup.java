package code.gui;

import code.gui.events.ChangeRadioEvent;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class CustButtonGroup implements AbsCustButtonGroup {

    private final CustList<AbsRadioButton> group = new CustList<AbsRadioButton>();
    private final StringList values = new StringList();

    private AbsRadioButton selected;

    public void add(AbsRadioButton _b) {
        if (_b.getButtonGroup() != null) {
            return;
        }
        _b.setButtonGroup(this);
        if (_b.isSelected()) {
            for (AbsRadioButton c: group) {
                c.setSelected(false);
            }
            selected = _b;
        }
        group.add(_b);
        _b.addActionListener(new ChangeRadioEvent(this,_b));
    }

    public void add(AbsRadioButton _b, String _value) {
        if (_b.getButtonGroup() != null) {
            return;
        }
        _b.setButtonGroup(this);
        if (_b.isSelected()) {
            for (AbsRadioButton c: group) {
                c.setSelected(false);
            }
            int len_ = group.size();
            for (int i = 0; i < len_; i++) {
                AbsRadioButton r_ = group.get(i);
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
    public CustList<AbsRadioButton> getGroup() {
        return group;
    }

    public StringList getValues() {
        return values;
    }

    public AbsRadioButton getSelected() {
        return selected;
    }

    public void clearSelection() {
        for (AbsRadioButton c: group) {
            c.setSelected(false);
        }
    }

    public int getButtonCount() {
        return group.size();
    }
}
