package code.expressionlanguage.fwd.opers;

public final class AnaLambdaFieldContent {
    private boolean staticField;
    private boolean finalField;
    private boolean affField;

    public boolean isStaticField() {
        return staticField;
    }

    public void setStaticField(boolean staticField) {
        this.staticField = staticField;
    }

    public boolean isFinalField() {
        return finalField;
    }

    public void setFinalField(boolean finalField) {
        this.finalField = finalField;
    }

    public boolean isAffField() {
        return affField;
    }

    public void setAffField(boolean affField) {
        this.affField = affField;
    }
}
