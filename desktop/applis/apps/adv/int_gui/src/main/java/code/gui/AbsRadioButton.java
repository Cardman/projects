package code.gui;

import code.gui.events.AbsActionListener;

public interface AbsRadioButton extends AbsCustComponent {
    void setSelected(boolean _value);

    String getText();

    void setText(String _value);

    boolean isSelected();

    void addActionListener(AbsActionListener _list);
    void removeActionListener(AbsActionListener _list);

    void setButtonGroup(AbsCustButtonGroup _custButtonGroup);

    AbsCustButtonGroup getButtonGroup();
}
