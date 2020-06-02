package code.gui;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

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

    public void setText(String text) {
        radioButton.setText(text);
    }

    public boolean isSelected() {
        return radioButton.isSelected();
    }

    public void setSelected(boolean b) {
        radioButton.setSelected(b);
    }

    public void addChangeListener(ChangeListener l) {
        radioButton.addChangeListener(l);
    }

    @Override
    protected JComponent getComponent() {
        return radioButton;
    }

    public void addActionListener(ActionListener _list) {
        radioButton.addActionListener(_list);
    }

    public void setEnabled(boolean b) {
        radioButton.setEnabled(b);
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
