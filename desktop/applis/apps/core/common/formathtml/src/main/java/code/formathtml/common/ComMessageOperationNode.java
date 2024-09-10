package code.formathtml.common;

public abstract class ComMessageOperationNode {
    private final boolean quotted;
    private final boolean escaped;
    private final String arg;

    protected ComMessageOperationNode(boolean _q, boolean _e, String _a) {
        this.quotted = _q;
        this.escaped = _e;
        this.arg = _a;
    }

    public boolean isQuotted() {
        return quotted;
    }

    public boolean isEscaped() {
        return escaped;
    }

    public String getArg() {
        return arg;
    }
}
