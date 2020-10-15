package code.gui;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public final class RadioButton extends CustComponent {
    private JRadioButton radioButton;
    private CustButtonGroup buttonGroup;
    public RadioButton() {
        radioButton = new JRadioButton();
    }
    public RadioButton(String _text) {
        radioButton = new JRadioButton(_text);
    }
    public RadioButton(String _text,boolean _s) {
        radioButton = new JRadioButton(_text,_s);
    }

    public String getText() {
        return radioButton.getText();
    }

    public void setText(String _text) {
        radioButton.setText(_text);
    }

    public boolean isSelected() {
        return radioButton.isSelected();
    }

    public void setSelected(boolean _b) {
        radioButton.setSelected(_b);
    }

    public void addChangeListener(ChangeListener _l) {
        radioButton.addChangeListener(_l);
    }

    @Override
    protected JComponent getComponent() {
        return radioButton;
    }

    public void addActionListener(ActionListener _list) {
        radioButton.addActionListener(_list);
    }

    public void setEnabled(boolean _b) {
        radioButton.setEnabled(_b);
    }

    public CustButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public void setButtonGroup(CustButtonGroup _buttonGroup) {
        buttonGroup = _buttonGroup;
    }

    public boolean isEnabled() {
        return radioButton.isEnabled();
    }
}
