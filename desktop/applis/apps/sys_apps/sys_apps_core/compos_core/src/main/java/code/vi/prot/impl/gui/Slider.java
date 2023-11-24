package code.vi.prot.impl.gui;

import code.gui.AbsSlider;
import code.gui.GuiBaseUtil;
import code.gui.events.AbsChangeListener;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.NumberUtil;
import code.vi.prot.impl.gui.events.WrChangeListener;

import javax.swing.*;

public final class Slider extends CustComponent implements AbsSlider {
    private final JSlider sl;
    private final IdMap<AbsChangeListener,WrChangeListener> changes = new IdMap<AbsChangeListener, WrChangeListener>();

    public Slider() {
        sl = new JSlider();
    }
    public Slider(int _o) {
        sl = new JSlider(SwingConstants.HORIZONTAL);
        setOrientation(_o);
    }
    public Slider(int _min,int _max) {
        sl = new JSlider(NumberUtil.min(_min, _max),NumberUtil.max(_min, _max));
    }
    public Slider(int _min,int _max,int _v) {
        sl = new JSlider();
        sl.getModel().setRangeProperties(_v,0,_min,_max,false);
    }
    public Slider(int _o,int _min,int _max,int _v) {
        sl = new JSlider(SwingConstants.HORIZONTAL);
        sl.getModel().setRangeProperties(_v,0,_min,_max,false);
        setOrientation(_o);
    }

    public void addChangeListener(AbsChangeListener _l) {
        WrChangeListener wr_ = new WrChangeListener(_l);
        sl.addChangeListener(wr_);
        changes.addEntry(_l,wr_);
    }

    @Override
    public void addChangeListenerMap(AbsChangeListener _l) {
        WrChangeListener wr_ = new WrChangeListener(_l);
        changes.addEntry(_l,wr_);
    }

    @Override
    public void removeChangeListener(AbsChangeListener _l) {
        WrChangeListener wr_ = changes.getVal(_l);
        sl.removeChangeListener(wr_);
        changes.removeKey(_l);
    }

    @Override
    public void removeChangeListenerMap(AbsChangeListener _l) {
        changes.removeKey(_l);
    }

    @Override
    public CustList<AbsChangeListener> getChangeListeners() {
        return changes.getKeys();
    }

    public int getValue() {
        return sl.getValue();
    }

    public void setValue(int _n) {
        sl.setValue(_n);
    }

    public int getMinimum() {
        return sl.getMinimum();
    }

    public void setMinimum(int _minimum) {
        sl.setMinimum(_minimum);
    }

    public int getMaximum() {
        return sl.getMaximum();
    }

    public void setMaximum(int _maximum) {
        sl.setMaximum(_maximum);
    }

    public int getOrientation() {
        return sl.getOrientation();
    }

    public void setOrientation(int _orientation) {
        GuiBaseUtil.setOrient(this,_orientation);
    }

    @Override
    public void setVertical() {
        sl.setOrientation(SwingConstants.VERTICAL);
    }

    @Override
    public void setHorizontal() {
        sl.setOrientation(SwingConstants.HORIZONTAL);
    }

    @Override
    public JComponent getNatComponent() {
        return sl;
    }
}
