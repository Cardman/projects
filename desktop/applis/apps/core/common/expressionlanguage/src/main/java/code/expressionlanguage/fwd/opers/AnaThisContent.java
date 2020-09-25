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

    public void setIntermediate(boolean intermediate) {
        this.intermediate = intermediate;
    }

    public int getNbAncestors() {
        return nbAncestors;
    }

    public void setNbAncestors(int nbAncestors) {
        this.nbAncestors = nbAncestors;
    }

    public int getOff() {
        return off;
    }
}
