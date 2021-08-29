package code.sys.impl.gui;

import code.gui.AbsSlider;
import code.gui.GuiConstants;
import code.gui.events.AbsChangeListener;
import code.sys.impl.gui.events.WrChangeListener;

import javax.swing.*;

public final class Slider extends CustComponent implements AbsSlider {
    private final JSlider slider;

    public Slider() {
        slider = new JSlider();
    }
    public Slider(int _o) {
        slider = new JSlider(GuiConstants.getOrient(_o));
    }
    public Slider(int _min,int _max) {
        slider = new JSlider(_min,_max);
    }
    public Slider(int _min,int _max,int _v) {
        slider = new JSlider(_min,_max,_v);
    }
    public Slider(int _o,int _min,int _max,int _v) {
        slider = new JSlider(GuiConstants.getOrient(_o),_min,_max,_v);
    }

    public void addChangeListener(AbsChangeListener _l) {
        slider.addChangeListener(new WrChangeListener(_l));
    }

    public int getValue() {
        return slider.getValue();
    }

    public void setValue(int _n) {
        slider.setValue(_n);
    }

    public int getMinimum() {
        return slider.getMinimum();
    }

    public void setMinimum(int _minimum) {
        slider.setMinimum(_minimum);
    }

    public int getMaximum() {
        return slider.getMaximum();
    }

    public void setMaximum(int _maximum) {
        slider.setMaximum(_maximum);
    }

    public int getOrientation() {
        return slider.getOrientation();
    }

    public void setOrientation(int _orientation) {
        slider.setOrientation(GuiConstants.getOrient(_orientation));
    }
    @Override
    public JComponent getNatComponent() {
        return slider;
    }

    public boolean isEnabled() {
        return slider.isEnabled();
    }

    public void setEnabled(boolean _b) {
        slider.setEnabled(_b);
    }
}
