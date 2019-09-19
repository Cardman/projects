package code.gui;

import javax.swing.*;
import java.awt.*;
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
    public void setBackground(Color _c) {
        checkBox.setBackground(_c);
    }
    public void setBorderPaintedFlat(boolean b) {
        checkBox.setBorderPaintedFlat(b);
    }

    public boolean isBorderPaintedFlat() {
        return checkBox.isBorderPaintedFlat();
    }

    public void setHideActionText(boolean hideActionText) {
        checkBox.setHideActionText(hideActionText);
    }

    public boolean getHideActionText() {
        return checkBox.getHideActionText();
    }

    public String getText() {
        return checkBox.getText();
    }

    public void setText(String text) {
        checkBox.setText(text);
    }

    public boolean isSelected() {
        return checkBox.isSelected();
    }

    public void setSelected(boolean b) {
        checkBox.setSelected(b);
    }

    public void doClick() {
        checkBox.doClick();
    }

    public void doClick(int pressTime) {
        checkBox.doClick(pressTime);
    }

    public int getMnemonic() {
        return checkBox.getMnemonic();
    }

    public void setMnemonic(int mnemonic) {
        checkBox.setMnemonic(mnemonic);
    }

    public void setMnemonic(char mnemonic) {
        checkBox.setMnemonic(mnemonic);
    }

    public int getDisplayedMnemonicIndex() {
        return checkBox.getDisplayedMnemonicIndex();
    }

    public void setMultiClickThreshhold(long threshhold) {
        checkBox.setMultiClickThreshhold(threshhold);
    }

    public long getMultiClickThreshhold() {
        return checkBox.getMultiClickThreshhold();
    }

    public void addActionListener(ActionListener l) {
        checkBox.addActionListener(l);
    }

    public void removeActionListener(ActionListener l) {
        checkBox.removeActionListener(l);
    }

    public ActionListener[] getActionListeners() {
        return checkBox.getActionListeners();
    }

    public boolean isEnabled() {
        return checkBox.isEnabled();
    }
    public void setEnabled(boolean b) {
        checkBox.setEnabled(b);
    }

    @Override
    protected JComponent getComponent() {
        return checkBox;
    }
}
