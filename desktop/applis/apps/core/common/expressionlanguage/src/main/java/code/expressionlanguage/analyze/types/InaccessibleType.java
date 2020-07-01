package code.expressionlanguage.analyze.types;

public final class InaccessibleType {
    private final int index;
    private final String type;

    public InaccessibleType(int _index, String _type) {
        index = _index;
        type = _type;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }
}
