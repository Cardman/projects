package code.gui.events;

import code.gui.AbsRadioButton;
import code.gui.CustButtonGroup;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ChangeRadioEvent implements AbsActionListener {
    private CustButtonGroup group;
    private AbsRadioButton radio;
    private boolean hasValue;
    private String value = "";

    public ChangeRadioEvent(CustButtonGroup _group, AbsRadioButton _radio) {
        group = _group;
        radio = _radio;
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
        for (AbsRadioButton r: g_) {
            r.setSelected(false);
        }
        if (hasValue) {
            int len_ = g_.size();
            for (int i = 0; i < len_; i++) {
                AbsRadioButton r_ = g_.get(i);
                String v_ = group.getValues().get(i);
                if (StringUtil.quickEq(v_, value)) {
                    r_.setSelected(true);
                }
            }
        }
        radio.setSelected(true);
    }
}
