package code.gui;

import code.gui.events.AbsActionListener;

public interface AbsRadioButton extends AbsCustComponent,TranslatableComponent {
    void setSelected(boolean _value);

    String getText();

    boolean isSelected();

    void addActionListener(AbsActionListener _list);
    void removeActionListener(AbsActionListener _list);

    void setButtonGroup(AbsCustButtonGroup _custButtonGroup);

    AbsCustButtonGroup getButtonGroup();
}
