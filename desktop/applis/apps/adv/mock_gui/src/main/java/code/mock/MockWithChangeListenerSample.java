package code.mock;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;

public final class MockWithChangeListenerSample extends MockAbsCommonFrame implements MockWithChangeListener,MockWithAction {

    private final AbsSpinner spinner;
    private final AbsSlider slider;
    private final AbsTextField textField;
    private final AbsRadioButton radioButton;
    private final AbsSplitPane horizontalSplitPane;
    private final AbsSplitPane verticalSplitPane;
    private final AbsProgressBar progressBar;

    public MockWithChangeListenerSample(AbstractProgramInfos _f, String _lgKey) {
        super(_f, _lgKey);
        spinner = _f.getCompoFactory().newSpinner(5, 3, 7, 1);
        spinner.addChangeListener(new MockChangeListener(this,0));
        getContentPane().add(spinner);
        slider = _f.getCompoFactory().newAbsSlider(3,7,5);
        slider.addChangeListener(new MockChangeListener(this,1));
        getContentPane().add(slider);
        radioButton = _f.getCompoFactory().newRadioButton();
        radioButton.addChangeListener(new MockChangeListener(this,2));
        radioButton.addActionListener(new MockAction(3,this));
        progressBar = _f.getCompoFactory().newAbsProgressBar();
        horizontalSplitPane = _f.getCompoFactory().newHorizontalSplitPane(radioButton, progressBar);
        getContentPane().add(horizontalSplitPane);
        verticalSplitPane = _f.getCompoFactory().newVerticalSplitPane(_f.getCompoFactory().newPlainLabel(""), new MockPlainLabel(""));
        getContentPane().add(verticalSplitPane);
        textField = _f.getCompoFactory().newTextField();
        getContentPane().add(textField);
        pack();
    }

    @Override
    public void action(int _nb) {
        if (_nb == 0) {
            textField.setText(Long.toString(spinner.getValue()));
        } else if (_nb == 1){
            textField.setText(Long.toString(slider.getValue()));
        } else if (radioButton.isSelected()){
            textField.setText("->1");
        } else {
            textField.setText("->0");
        }
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }

    public AbsSpinner getSpinner() {
        return spinner;
    }

    public AbsSlider getSlider() {
        return slider;
    }

    public AbsRadioButton getRadioButton() {
        return radioButton;
    }

    public AbsSplitPane getHorizontalSplitPane() {
        return horizontalSplitPane;
    }

    public AbsSplitPane getVerticalSplitPane() {
        return verticalSplitPane;
    }

    public AbsProgressBar getProgressBar() {
        return progressBar;
    }

    public String getText() {
        return textField.getText();
    }
}
