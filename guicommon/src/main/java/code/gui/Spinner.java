package code.gui;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public final class Spinner extends CustComponent {

    private JSpinner spinner;

    public Spinner() {
        spinner = new JSpinner();
    }

    public Spinner(SpinnerModel _model) {
        spinner = new JSpinner(_model);
    }

    public Object getValue() {
        return spinner.getValue();
    }

    public void setValue(Object value) {
        spinner.setValue(value);
    }

    public void addChangeListener(ChangeListener listener) {
        spinner.addChangeListener(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        spinner.removeChangeListener(listener);
    }

    public ChangeListener[] getChangeListeners() {
        return spinner.getChangeListeners();
    }

    public void setVisible(boolean aFlag) {
        spinner.setVisible(aFlag);
    }

    public void setEnabled(boolean enabled) {
        spinner.setEnabled(enabled);
    }

    public void setForeground(Color fg) {
        spinner.setForeground(fg);
    }

    public void setBackground(Color bg) {
        spinner.setBackground(bg);
    }

    @Override
    public JComponent getComponent() {
        return spinner;
    }

    public void setModel(SpinnerModel _spin) {
        spinner.setModel(_spin);
    }

    public SpinnerModel getModel() {
        return spinner.getModel();
    }
}
