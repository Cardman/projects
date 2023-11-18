package code.gui.events;

import code.gui.AbsRadioButton;
import code.gui.CustButtonGroup;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ChangeRadioEvent implements AbsActionListener {
    private final CustButtonGroup group;
    private final AbsRadioButton radio;
    private final boolean hasValue;
    private final String value;

    public ChangeRadioEvent(CustButtonGroup _group, AbsRadioButton _radio) {
        group = _group;
        radio = _radio;
        hasValue = false;
        value = "";
    }

    public ChangeRadioEvent(CustButtonGroup _group, AbsRadioButton _radio, String _value) {
        group = _group;
        radio = _radio;
        hasValue = true;
        value = _value;
    }
    @Override
    public void action() {
        CustList<AbsRadioButton> g_ = group.getGroup();
        if (hasValue) {
            boolean chg_ = radio.isSelected();
            for (AbsRadioButton r: g_) {
                r.setSelected(false);
            }
            int len_ = g_.size();
            for (int i = 0; i < len_; i++) {
                AbsRadioButton r_ = g_.get(i);
                String v_ = group.getValues().get(i);
                if (StringUtil.quickEq(v_, value)) {
                    r_.setSelected(chg_);
                }
            }
            changeSelect();
        } else {
            AbsRadioButton previousSel_ = group.getSelected();
            if (previousSel_ != null) {
                previousSel_.setSelected(false);
            }
            changeSelect();
        }
    }

    private void changeSelect() {
        if (radio.isSelected()) {
            group.setSelected(radio);
        } else {
            group.setSelected(null);
        }
    }
}
