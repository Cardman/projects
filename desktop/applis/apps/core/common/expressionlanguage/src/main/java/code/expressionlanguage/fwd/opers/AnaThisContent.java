package code.expressionlanguage.fwd.opers;

public final class AnaThisContent {

    private boolean intermediate;
    private int nbAncestors;
    private final int off;
    public AnaThisContent(int _off) {
        off = _off;
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public void setIntermediate(boolean _intermediate) {
        this.intermediate = _intermediate;
    }

    public int getNbAncestors() {
        return nbAncestors;
    }

    public void setNbAncestors(int _nbAncestors) {
        this.nbAncestors = _nbAncestors;
    }

    public int getOff() {
        return off;
    }
}
