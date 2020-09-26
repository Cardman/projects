package code.expressionlanguage.fwd.blocks;

public final class ExecClassContent {

    private final boolean finalType;
    private final boolean abstractType;
    private final boolean staticType;

    public ExecClassContent(AnaClassContent _cont) {
        this.finalType = _cont.isFinalType();
        this.abstractType = _cont.isAbstractType();
        this.staticType = _cont.isStaticType();
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
