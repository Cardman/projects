package code.gui;

import javax.swing.*;

public final class ProgressBar extends CustComponent implements AbsProgressBar {
    private JProgressBar progressBar = new JProgressBar();

    public boolean isHorizontal() {
        int orientation_ = progressBar.getOrientation();
        return GuiConstants.isHorizProgress(orientation_);
    }

    public int getValue() {
        return progressBar.getValue();
    }

    public int getMinimum() {
        return progressBar.getMinimum();
    }

    public int getMaximum() {
        return progressBar.getMaximum();
    }

    public void setHorizontal(boolean _bool) {
        int value_ = GuiConstants.getHoriz(_bool);
        progressBar.setOrientation(value_);
    }

    public void setValue(int _n) {
        progressBar.setValue(_n);
    }

    public void setMinimum(int _n) {
        progressBar.setMinimum(_n);
    }

    public void setMaximum(int _n) {
        progressBar.setMaximum(_n);
    }

    @Override
    protected JComponent getNatComponent() {
        return progressBar;
    }
}
