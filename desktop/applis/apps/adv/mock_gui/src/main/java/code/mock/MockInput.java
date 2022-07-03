package code.mock;

public abstract class MockInput extends MockCustComponent{
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _en) {
        this.enabled = _en;
    }

    @Override
    public boolean isAccessible() {
        if (!enabled) {
            return false;
        }
        return super.isAccessible();
    }
}
