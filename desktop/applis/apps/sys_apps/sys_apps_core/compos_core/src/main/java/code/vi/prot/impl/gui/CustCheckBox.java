package code.vi.prot.impl.gui;

import code.gui.AbsCustCheckBox;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.vi.prot.impl.gui.events.WrActionListener;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.gui.events.WrAdvActionListener;

import javax.swing.*;

public final class CustCheckBox extends CustComponent implements AbsCustCheckBox {

    private final IdMap<AbsActionListener,WrActionListener> mapAction = new IdMap<AbsActionListener, WrActionListener>();
    private final IdMap<AbsAdvActionListener,WrAdvActionListener> mapAdvAction = new IdMap<AbsAdvActionListener, WrAdvActionListener>();
    private final JCheckBox checkBox;
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

    @Override
    public void addActionListener(AbsAdvActionListener _l) {
        WrAdvActionListener wr_ = new WrAdvActionListener(_l);
        checkBox.addActionListener(wr_);
        mapAdvAction.addEntry(_l,wr_);
    }

    @Override
    public void addActionListenerMap(AbsAdvActionListener _l) {
        WrAdvActionListener wr_ = new WrAdvActionListener(_l);
        mapAdvAction.addEntry(_l,wr_);
    }

    public void addActionListener(AbsActionListener _l) {
        WrActionListener wr_ = new WrActionListener(_l);
        checkBox.addActionListener(wr_);
        mapAction.addEntry(_l,wr_);
    }

    @Override
    public void removeActionListener(AbsAdvActionListener _l) {
        WrAdvActionListener wr_ = mapAdvAction.getVal(_l);
        checkBox.removeActionListener(wr_);
        mapAdvAction.removeKey(_l);
    }

    @Override
    public void removeActionListenerMap(AbsAdvActionListener _l) {
        mapAdvAction.removeKey(_l);
    }

    public void removeActionListener(AbsActionListener _l) {
        WrActionListener wr_ = mapAction.getVal(_l);
        checkBox.removeActionListener(wr_);
        mapAction.removeKey(_l);
    }

    public CustList<AbsActionListener> getActionListeners() {
        return mapAction.getKeys();
    }

    @Override
    public JComponent getNatComponent() {
        return checkBox;
    }
}
