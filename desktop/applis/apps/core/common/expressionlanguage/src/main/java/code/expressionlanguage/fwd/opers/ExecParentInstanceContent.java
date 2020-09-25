package code.expressionlanguage.fwd.opers;

public final class ExecParentInstanceContent {
    private final boolean intermediate;
    private final int off;

    public ExecParentInstanceContent(AnaParentInstanceContent _cont) {
        off = _cont.getOff();
        intermediate = _cont.isIntermediate();
    }

    public int getOff() {
        return off;
    }

    public boolean isIntermediate() {
        return intermediate;
    }
}
