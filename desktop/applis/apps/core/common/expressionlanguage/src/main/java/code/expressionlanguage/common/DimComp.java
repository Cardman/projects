package code.expressionlanguage.common;

public final class DimComp {

    private final int dim;

    private final String component;

    public DimComp(int _dim, String _component) {
        dim = _dim;
        component = _component;
    }

    public String getComponent() {
        return component;
    }

    public int getDim() {
        return dim;
    }
}
