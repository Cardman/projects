package code.gui;

import javax.swing.*;

public final class ProgressBar extends CustComponent {
    private JProgressBar progressBar = new JProgressBar();

    public int getValue() {
        return progressBar.getValue();
    }

    public int getMinimum() {
        return progressBar.getMinimum();
    }

    public int getMaximum() {
        return progressBar.getMaximum();
    }

    public void setValue(int n) {
        progressBar.setValue(n);
    }

    public void setMinimum(int n) {
        progressBar.setMinimum(n);
    }

    public void setMaximum(int n) {
        progressBar.setMaximum(n);
    }

    @Override
    protected JComponent getComponent() {
        return progressBar;
    }
}
