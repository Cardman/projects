package code.vi.prot.impl.gui;

import code.gui.AbsSlider;
import code.gui.GuiConstants;
import code.gui.events.AbsChangeListener;
import code.vi.prot.impl.gui.events.WrChangeListener;

import javax.swing.*;

public final class Slider extends CustComponent implements AbsSlider {
    private final JSlider sl;

    public Slider() {
        sl = new JSlider();
    }
    public Slider(int _o) {
        sl = new JSlider(SwingConstants.HORIZONTAL);
        setOrientation(_o);
    }
    public Slider(int _min,int _max) {
        sl = new JSlider(_min,_max);
    }
    public Slider(int _min,int _max,int _v) {
        sl = new JSlider(_min,_max,_v);
    }
    public Slider(int _o,int _min,int _max,int _v) {
        sl = new JSlider(SwingConstants.HORIZONTAL,_min,_max,_v);
        setOrientation(_o);
    }

    public void addChangeListener(AbsChangeListener _l) {
        sl.addChangeListener(new WrChangeListener(_l));
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
        GuiConstants.setOrient(this,_orientation);
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

    public boolean isEnabled() {
        return sl.isEnabled();
    }

    public void setEnabled(boolean _b) {
        sl.setEnabled(_b);
    }
}
