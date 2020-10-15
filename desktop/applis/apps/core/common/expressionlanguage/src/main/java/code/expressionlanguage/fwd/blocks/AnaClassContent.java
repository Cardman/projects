package code.expressionlanguage.fwd.blocks;

public final class AnaClassContent {

    private final boolean finalType;
    private final boolean abstractType;
    private final boolean staticType;

    public AnaClassContent(boolean _finalType, boolean _abstractType, boolean _staticType) {
        this.finalType = _finalType;
        this.abstractType = _abstractType;
        this.staticType = _staticType;
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
