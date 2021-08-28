package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsChangeListener;
import code.gui.events.ChangeRadioEvent;

public interface AbsRadioButton extends AbsCustComponent {
    void setSelected(boolean _value);

    boolean isEnabled();

    String getText();

    void setText(String _value);

    boolean isSelected();

    void setEnabled(boolean _value);

    void addChangeListener(AbsChangeListener _list);

    void addActionListener(AbsActionListener _list);

    void setButtonGroup(CustButtonGroup _custButtonGroup);

    CustButtonGroup getButtonGroup();
}
