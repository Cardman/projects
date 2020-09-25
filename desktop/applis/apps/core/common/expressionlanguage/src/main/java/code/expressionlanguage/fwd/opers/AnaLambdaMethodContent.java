package code.expressionlanguage.fwd.opers;

public final class AnaLambdaMethodContent {
    private boolean polymorph;
    private boolean abstractMethod;
    private boolean directCast;
    private boolean expCast;

    public boolean isPolymorph() {
        return polymorph;
    }

    public void setPolymorph(boolean polymorph) {
        this.polymorph = polymorph;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public void setAbstractMethod(boolean abstractMethod) {
        this.abstractMethod = abstractMethod;
    }

    public boolean isDirectCast() {
        return directCast;
    }

    public void setDirectCast(boolean directCast) {
        this.directCast = directCast;
    }

    public boolean isExpCast() {
        return expCast;
    }

    public void setExpCast(boolean expCast) {
        this.expCast = expCast;
    }
}
