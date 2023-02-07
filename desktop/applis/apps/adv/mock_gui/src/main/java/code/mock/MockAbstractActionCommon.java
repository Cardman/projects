package code.mock;

import code.gui.events.AbsEnabledAction;

public abstract class MockAbstractActionCommon implements AbsEnabledAction {

    private boolean enabled = true;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean _b) {
        enabled = _b;
    }

}
