package code.vi.sys.impl.gui;

import code.gui.AbsProgressBar;
import code.gui.GuiConstants;

import javax.swing.*;

public final class ProgressBar extends CustComponent implements AbsProgressBar {
    private boolean horizontal = true;
    private final JProgressBar progressBar = new JProgressBar();

    public boolean isHorizontal() {
        return horizontal;
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
        horizontal = _bool;
        GuiConstants.setHorizProg(this,_bool);
    }

    @Override
    public void setHorizontal() {
        progressBar.setOrientation(SwingConstants.HORIZONTAL);
    }

    @Override
    public void setVertical() {
        progressBar.setOrientation(SwingConstants.VERTICAL);
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
    public JComponent getNatComponent() {
        return progressBar;
    }
}
