package code.expressionlanguage.exec.dbg;

public final class WatchPoint {
    private boolean enabled;
    private boolean read;
    private boolean write;
    private boolean compoundRead;
    private boolean compoundWrite;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean _e) {
        this.read = _e;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean _e) {
        this.write = _e;
    }

    public boolean isCompoundRead() {
        return compoundRead;
    }

    public void setCompoundRead(boolean _e) {
        this.compoundRead = _e;
    }

    public boolean isCompoundWrite() {
        return compoundWrite;
    }

    public void setCompoundWrite(boolean _e) {
        this.compoundWrite = _e;
    }
}
