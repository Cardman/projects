package code.expressionlanguage.fwd.opers;

public final class ExecThisContent {

    private final boolean intermediate;
    private final int nbAncestors;
    private final int off;
    public ExecThisContent(AnaThisContent _cont) {
        off = _cont.getOff();
        nbAncestors = _cont.getNbAncestors();
        intermediate = _cont.isIntermediate();
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public int getNbAncestors() {
        return nbAncestors;
    }

    public int getOff() {
        return off;
    }
}
