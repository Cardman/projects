package code.gui.events;

import code.gui.CustButtonGroup;
import code.gui.RadioButton;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ChangeRadioEvent implements AbsActionListener {
    private CustButtonGroup group;
    private RadioButton radio;
    private boolean hasValue;
    private String value = "";

    public ChangeRadioEvent(CustButtonGroup _group, RadioButton _radio) {
        group = _group;
        radio = _radio;
    }

    public ChangeRadioEvent(CustButtonGroup _group, RadioButton _radio, String _value) {
        group = _group;
        radio = _radio;
        hasValue = true;
        value = _value;
    }
    @Override
    public void action() {
        CustList<RadioButton> g_ = group.getGroup();
        for (RadioButton r: g_) {
            r.setSelected(false);
        }
        if (hasValue) {
            int len_ = g_.size();
            for (int i = 0; i < len_; i++) {
                RadioButton r_ = g_.get(i);
                String v_ = group.getValues().get(i);
                if (StringUtil.quickEq(v_, value)) {
                    r_.setSelected(true);
                }
            }
        }
        radio.setSelected(true);
    }
}
