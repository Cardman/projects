package code.expressionlanguage.exec.dbg;

public final class BreakPoint {
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }
}