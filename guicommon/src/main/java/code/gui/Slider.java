package code.gui;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public final class Slider extends CustComponent {
    private JSlider slider;

    public Slider() {
        slider = new JSlider();
    }
    public Slider(int _o) {
        slider = new JSlider(_o);
    }
    public Slider(int _min,int _max) {
        slider = new JSlider(_min,_max);
    }
    public Slider(int _min,int _max,int _v) {
        slider = new JSlider(_min,_max,_v);
    }
    public Slider(int _o,int _min,int _max,int _v) {
        slider = new JSlider(_o,_min,_max,_v);
    }

    public void addChangeListener(ChangeListener l) {
        slider.addChangeListener(l);
    }

    public void removeChangeListener(ChangeListener l) {
        slider.removeChangeListener(l);
    }

    public BoundedRangeModel getModel() {
        return slider.getModel();
    }

    public void setModel(BoundedRangeModel newModel) {
        slider.setModel(newModel);
    }

    public int getValue() {
        return slider.getValue();
    }

    public void setValue(int n) {
        slider.setValue(n);
    }

    public int getMinimum() {
        return slider.getMinimum();
    }

    public void setMinimum(int minimum) {
        slider.setMinimum(minimum);
    }

    public int getMaximum() {
        return slider.getMaximum();
    }

    public void setMaximum(int maximum) {
        slider.setMaximum(maximum);
    }

    public int getOrientation() {
        return slider.getOrientation();
    }

    public void setOrientation(int orientation) {
        slider.setOrientation(orientation);
    }
    @Override
    public JComponent getComponent() {
        return slider;
    }
}
