package code.vi.prot.impl.gui;

import code.gui.AbsCustButtonGroup;
import code.gui.AbsRadioButton;
import code.gui.events.AbsActionListener;
import code.vi.prot.impl.gui.events.WrActionListener;
import code.util.IdMap;

import javax.swing.*;

public final class RadioButton extends CustComponent implements AbsRadioButton {
    private final JRadioButton radio;
    private final IdMap<AbsActionListener, WrActionListener> mapAction = new IdMap<AbsActionListener, WrActionListener>();
    private AbsCustButtonGroup buttonGroup;
    public RadioButton() {
        radio = new JRadioButton();
    }
    public RadioButton(String _text) {
        radio = new JRadioButton(_text);
    }
    public RadioButton(String _text,boolean _s) {
        radio = new JRadioButton(_text,_s);
    }

    public String getText() {
        return radio.getText();
    }

    public void setText(String _text) {
        radio.setText(_text);
    }

    public boolean isSelected() {
        return radio.isSelected();
    }

    public void setSelected(boolean _b) {
        radio.setSelected(_b);
    }

    @Override
    public JComponent getNatComponent() {
        return radio;
    }

    public void addActionListener(AbsActionListener _list) {
        WrActionListener wr_ = new WrActionListener(_list);
        radio.addActionListener(wr_);
        mapAction.addEntry(_list,wr_);
    }

    @Override
    public void removeActionListener(AbsActionListener _list) {
        WrActionListener wr_ =mapAction.getVal(_list);
        radio.removeActionListener(wr_);
        mapAction.removeKey(_list);
    }

    public AbsCustButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public void setButtonGroup(AbsCustButtonGroup _buttonGroup) {
        buttonGroup = _buttonGroup;
    }

}
