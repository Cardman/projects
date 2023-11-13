package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsChangeListener;

public interface AbsRadioButton extends AbsCustComponent {
    void setSelected(boolean _value);

    String getText();

    void setText(String _value);

    boolean isSelected();

    void addChangeListener(AbsChangeListener _list);

    void addActionListener(AbsActionListener _list);

    void setButtonGroup(AbsCustButtonGroup _custButtonGroup);

    AbsCustButtonGroup getButtonGroup();
}
