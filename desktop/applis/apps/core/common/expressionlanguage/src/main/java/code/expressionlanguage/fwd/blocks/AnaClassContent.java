package code.expressionlanguage.fwd.blocks;

public final class AnaClassContent {

    private final boolean finalType;
    private final boolean abstractType;
    private final boolean staticType;

    public AnaClassContent(boolean finalType, boolean abstractType, boolean staticType) {
        this.finalType = finalType;
        this.abstractType = abstractType;
        this.staticType = staticType;
    }

    public boolean isAbstractType() {
        return abstractType;
    }

    public boolean isFinalType() {
        return finalType;
    }

    public boolean isStaticType() {
        return staticType;
    }
}
