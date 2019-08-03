package code.gui;

import javax.swing.*;
import javax.swing.event.ChangeListener;
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

    public Icon getIcon() {
        return checkBox.getIcon();
    }

    public void setIcon(Icon defaultIcon) {
        checkBox.setIcon(defaultIcon);
    }

    public Icon getPressedIcon() {
        return checkBox.getPressedIcon();
    }

    public void setPressedIcon(Icon pressedIcon) {
        checkBox.setPressedIcon(pressedIcon);
    }

    public Icon getSelectedIcon() {
        return checkBox.getSelectedIcon();
    }

    public void setSelectedIcon(Icon selectedIcon) {
        checkBox.setSelectedIcon(selectedIcon);
    }

    public Icon getRolloverIcon() {
        return checkBox.getRolloverIcon();
    }

    public void setRolloverIcon(Icon rolloverIcon) {
        checkBox.setRolloverIcon(rolloverIcon);
    }

    public Icon getRolloverSelectedIcon() {
        return checkBox.getRolloverSelectedIcon();
    }

    public void setRolloverSelectedIcon(Icon rolloverSelectedIcon) {
        checkBox.setRolloverSelectedIcon(rolloverSelectedIcon);
    }

    public void setDisabledIcon(Icon disabledIcon) {
        checkBox.setDisabledIcon(disabledIcon);
    }

    public Icon getDisabledSelectedIcon() {
        return checkBox.getDisabledSelectedIcon();
    }

    public void setDisabledSelectedIcon(Icon disabledSelectedIcon) {
        checkBox.setDisabledSelectedIcon(disabledSelectedIcon);
    }

    public int getVerticalAlignment() {
        return checkBox.getVerticalAlignment();
    }

    public void setVerticalAlignment(int alignment) {
        checkBox.setVerticalAlignment(alignment);
    }

    public int getHorizontalAlignment() {
        return checkBox.getHorizontalAlignment();
    }

    public void setHorizontalAlignment(int alignment) {
        checkBox.setHorizontalAlignment(alignment);
    }

    public int getVerticalTextPosition() {
        return checkBox.getVerticalTextPosition();
    }

    public void setVerticalTextPosition(int textPosition) {
        checkBox.setVerticalTextPosition(textPosition);
    }

    public int getHorizontalTextPosition() {
        return checkBox.getHorizontalTextPosition();
    }

    public void setHorizontalTextPosition(int textPosition) {
        checkBox.setHorizontalTextPosition(textPosition);
    }

    public int getIconTextGap() {
        return checkBox.getIconTextGap();
    }

    public void setIconTextGap(int iconTextGap) {
        checkBox.setIconTextGap(iconTextGap);
    }

    public void setActionCommand(String actionCommand) {
        checkBox.setActionCommand(actionCommand);
    }

    public String getActionCommand() {
        return checkBox.getActionCommand();
    }

    public boolean isBorderPainted() {
        return checkBox.isBorderPainted();
    }

    public void setBorderPainted(boolean b) {
        checkBox.setBorderPainted(b);
    }

    public boolean isFocusPainted() {
        return checkBox.isFocusPainted();
    }

    public void setFocusPainted(boolean b) {
        checkBox.setFocusPainted(b);
    }

    public boolean isContentAreaFilled() {
        return checkBox.isContentAreaFilled();
    }

    public void setContentAreaFilled(boolean b) {
        checkBox.setContentAreaFilled(b);
    }

    public boolean isRolloverEnabled() {
        return checkBox.isRolloverEnabled();
    }

    public void setRolloverEnabled(boolean b) {
        checkBox.setRolloverEnabled(b);
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

    public ButtonModel getModel() {
        return checkBox.getModel();
    }

    public void setModel(ButtonModel newModel) {
        checkBox.setModel(newModel);
    }

    public void addChangeListener(ChangeListener l) {
        checkBox.addChangeListener(l);
    }

    public void removeChangeListener(ChangeListener l) {
        checkBox.removeChangeListener(l);
    }

    public ChangeListener[] getChangeListeners() {
        return checkBox.getChangeListeners();
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

    public void setEnabled(boolean b) {
        checkBox.setEnabled(b);
    }

    @Override
    public JComponent getComponent() {
        return checkBox;
    }
}
