package code.vi.prot.impl.gui;

import code.gui.AbsCustButtonGroup;
import code.gui.AbsRadioButton;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsChangeListener;
import code.vi.prot.impl.gui.events.WrActionListener;
import code.vi.prot.impl.gui.events.WrChangeListener;
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

    public void addChangeListener(AbsChangeListener _l) {
        radio.addChangeListener(new WrChangeListener(_l));
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

    public void setEnabled(boolean _b) {
        radio.setEnabled(_b);
    }

    public AbsCustButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public void setButtonGroup(AbsCustButtonGroup _buttonGroup) {
        buttonGroup = _buttonGroup;
    }

    public boolean isEnabled() {
        return radio.isEnabled();
    }
}
