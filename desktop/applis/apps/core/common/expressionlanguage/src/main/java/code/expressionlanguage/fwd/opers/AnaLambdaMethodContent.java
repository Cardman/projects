package code.expressionlanguage.fwd.opers;

public final class AnaLambdaMethodContent {
    private boolean polymorph;
    private boolean abstractMethod;
    private boolean directCast;
    private boolean expCast;

    public boolean isPolymorph() {
        return polymorph;
    }

    public void setPolymorph(boolean _polymorph) {
        this.polymorph = _polymorph;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public void setAbstractMethod(boolean _abstractMethod) {
        this.abstractMethod = _abstractMethod;
    }

    public boolean isDirectCast() {
        return directCast;
    }

    public void setDirectCast(boolean _directCast) {
        this.directCast = _directCast;
    }

    public boolean isExpCast() {
        return expCast;
    }

    public void setExpCast(boolean _expCast) {
        this.expCast = _expCast;
    }
}
