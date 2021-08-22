package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsChangeListener;
import code.gui.events.WrActionListener;
import code.gui.events.WrChangeListener;
import code.util.IdMap;

import javax.swing.*;

public final class RadioButton extends CustComponent {
    private JRadioButton radioButton;
    private final IdMap<AbsActionListener, WrActionListener> mapAction = new IdMap<AbsActionListener, WrActionListener>();
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

    public void addChangeListener(AbsChangeListener _l) {
        radioButton.addChangeListener(new WrChangeListener(_l));
    }

    @Override
    protected JComponent getComponent() {
        return radioButton;
    }

    public void addActionListener(AbsActionListener _list) {
        WrActionListener wr_ = new WrActionListener(_list);
        radioButton.addActionListener(wr_);
        mapAction.addEntry(_list,wr_);
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
