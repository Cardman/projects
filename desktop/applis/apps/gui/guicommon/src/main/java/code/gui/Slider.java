package code.gui;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public final class Slider extends CustComponent {
    private JSlider slider;

    public Slider() {
        slider = new JSlider();
    }
    public Slider(int _o) {
        slider = new JSlider(getOrient(_o));
    }
    public Slider(int _min,int _max) {
        slider = new JSlider(_min,_max);
    }
    public Slider(int _min,int _max,int _v) {
        slider = new JSlider(_min,_max,_v);
    }
    public Slider(int _o,int _min,int _max,int _v) {
        slider = new JSlider(getOrient(_o),_min,_max,_v);
    }

    private static int getOrient(int _o) {
        if (_o == SwingConstants.VERTICAL) {
            return SwingConstants.VERTICAL;
        }
        return SwingConstants.HORIZONTAL;
    }
    public void addChangeListener(ChangeListener _l) {
        slider.addChangeListener(_l);
    }

    public void removeChangeListener(ChangeListener _l) {
        slider.removeChangeListener(_l);
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
        if (getOrient(_orientation) == SwingConstants.VERTICAL) {
            slider.setOrientation(SwingConstants.VERTICAL);
        } else {
            slider.setOrientation(SwingConstants.HORIZONTAL);
        }
    }
    @Override
    protected JComponent getComponent() {
        return slider;
    }

    public boolean isEnabled() {
        return slider.isEnabled();
    }

    public void setEnabled(boolean _b) {
        slider.setEnabled(_b);
    }
}
