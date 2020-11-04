package code.expressionlanguage.common;

public final class IndexStrPart {
    private final int index;
    private final String part;

    public IndexStrPart(int _index, String _part) {
        index = _index;
        part = _part;
    }

    public int getIndex() {
        return index;
    }

    public String getPart() {
        return part;
    }
}
