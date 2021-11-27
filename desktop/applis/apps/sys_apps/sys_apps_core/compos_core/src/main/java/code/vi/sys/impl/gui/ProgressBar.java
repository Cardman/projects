package code.vi.sys.impl.gui;

import code.gui.AbsProgressBar;
import code.gui.GuiConstants;

import javax.swing.*;

public final class ProgressBar extends CustComponent implements AbsProgressBar {
    private boolean horizontal = true;
    private final JProgressBar prog = new JProgressBar();

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getValue() {
        return prog.getValue();
    }

    public int getMinimum() {
        return prog.getMinimum();
    }

    public int getMaximum() {
        return prog.getMaximum();
    }

    public void setHorizontal(boolean _bool) {
        horizontal = _bool;
        GuiConstants.setHorizProg(this,_bool);
    }

    @Override
    public void setHorizontal() {
        prog.setOrientation(SwingConstants.HORIZONTAL);
    }

    @Override
    public void setVertical() {
        prog.setOrientation(SwingConstants.VERTICAL);
    }

    public void setValue(int _n) {
        prog.setValue(_n);
    }

    public void setMinimum(int _n) {
        prog.setMinimum(_n);
    }

    public void setMaximum(int _n) {
        prog.setMaximum(_n);
    }

    @Override
    public JComponent getNatComponent() {
        return prog;
    }
}
