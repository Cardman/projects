package code.gui;

import code.gui.events.ChangeRadioEvent;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class CustButtonGroup implements AbsCustButtonGroup {

    private final IdList<AbsRadioButton> group = new IdList<AbsRadioButton>();
    private final IdList<ChangeRadioEvent> events = new IdList<ChangeRadioEvent>();
    private final StringList values = new StringList();

    private AbsRadioButton selected;

    public void add(AbsRadioButton _b) {
        if (_b.getButtonGroup() != null) {
            return;
        }
        _b.setButtonGroup(this);
        if (_b.isSelected()) {
            if (selected == null) {
                selected = _b;
            } else {
                _b.setSelected(false);
            }
        }
        group.add(_b);
        ChangeRadioEvent ev_ = new ChangeRadioEvent(this, _b);
        events.add(ev_);
        _b.addActionListener(ev_);
    }

    @Override
    public void remove(AbsRadioButton _b) {
        int ind_ = group.indexOfObj(_b);
        if (ind_ > -1) {
            if (selected == _b) {
                selected = null;
            }
            _b.setButtonGroup(null);
            _b.removeActionListener(events.get(ind_));
            events.remove(ind_);
            group.remove(ind_);
        }
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
        ChangeRadioEvent ev_ = new ChangeRadioEvent(this, _b, _value);
        events.add(ev_);
        _b.addActionListener(ev_);
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

    @Override
    public void setSelected(AbsRadioButton _r) {
        this.selected = _r;
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
