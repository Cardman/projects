package code.gui;

import code.gui.events.WrActionListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public final class CustCheckBox extends CustComponent {

    private JCheckBox checkBox;
    public CustCheckBox() {
        checkBox = new JCheckBox();
    }
    public CustCheckBox(String _t) {
        checkBox = new JCheckBox(_t);
    }
    public CustCheckBox(String _t,boolean _s) {
        checkBox = new JCheckBox(_t,_s);
    }

    public void setBorderPaintedFlat(boolean _b) {
        checkBox.setBorderPaintedFlat(_b);
    }

    public boolean isBorderPaintedFlat() {
        return checkBox.isBorderPaintedFlat();
    }

    public void setHideActionText(boolean _hideActionText) {
        checkBox.setHideActionText(_hideActionText);
    }

    public boolean getHideActionText() {
        return checkBox.getHideActionText();
    }

    public String getText() {
        return checkBox.getText();
    }

    public void setText(String _text) {
        checkBox.setText(_text);
    }

    public boolean isSelected() {
        return checkBox.isSelected();
    }

    public void setSelected(boolean _b) {
        checkBox.setSelected(_b);
    }

    public void doClick() {
        checkBox.doClick();
    }

    public void doClick(int _pressTime) {
        checkBox.doClick(_pressTime);
    }

    public int getMnemonic() {
        return checkBox.getMnemonic();
    }

    public void setMnemonic(int _mnemonic) {
        checkBox.setMnemonic(_mnemonic);
    }

    public void setMnemonic(char _mnemonic) {
        checkBox.setMnemonic(_mnemonic);
    }

    public int getDisplayedMnemonicIndex() {
        return checkBox.getDisplayedMnemonicIndex();
    }

    public void setMultiClickThreshhold(long _threshhold) {
        checkBox.setMultiClickThreshhold(_threshhold);
    }

    public long getMultiClickThreshhold() {
        return checkBox.getMultiClickThreshhold();
    }

    public void addActionListener(ActionListener _l) {
        checkBox.addActionListener(_l);
    }

    public void addActionListener(AbsActionListener _l) {
        checkBox.addActionListener(new WrActionListener(_l));
    }

    public void removeActionListener(ActionListener _l) {
        checkBox.removeActionListener(_l);
    }

    public ActionListener[] getActionListeners() {
        return checkBox.getActionListeners();
    }

    public boolean isEnabled() {
        return checkBox.isEnabled();
    }
    public void setEnabled(boolean _b) {
        checkBox.setEnabled(_b);
    }

    @Override
    protected JComponent getComponent() {
        return checkBox;
    }
}
