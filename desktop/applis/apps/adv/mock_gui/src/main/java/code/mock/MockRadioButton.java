package code.mock;

import code.gui.AbsCustButtonGroup;
import code.gui.AbsRadioButton;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsChangeListener;
import code.util.CustList;
import code.util.IdList;

public final class MockRadioButton extends MockInput implements AbsRadioButton {
    private final IdList<AbsActionListener> actionListeners = new IdList<AbsActionListener>();
    private final IdList<AbsChangeListener> changeListeners = new IdList<AbsChangeListener>();
    private boolean selected;
    private String text;
    private AbsCustButtonGroup buttonGroup;
    public MockRadioButton() {
        this("");
    }
    public MockRadioButton(String _s) {
        this(_s,false);
    }
    public MockRadioButton(String _s, boolean _b) {
        text=_s;
        selected = _b;
    }
    @Override
    public void addActionListener(AbsActionListener _l) {
        actionListeners.add(_l);
    }

    @Override
    public void addChangeListener(AbsChangeListener _l) {
        changeListeners.add(_l);
    }

    public IdList<AbsChangeListener> getChangeListeners() {
        return changeListeners;
    }

    public CustList<AbsActionListener> getActionListeners() {
        return actionListeners;
    }

    @Override
    public void setSelected(boolean _b) {
        selected = _b;
        for (AbsChangeListener a: getChangeListeners()) {
            a.stateChanged();
        }
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setText(String _s) {
        text = _s;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public AbsCustButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    @Override
    public void setButtonGroup(AbsCustButtonGroup _b) {
        this.buttonGroup = _b;
    }
}
