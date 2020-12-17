package code.expressionlanguage.common;

import code.util.Ints;
import code.util.StringList;

public final class StackDelimiters {
    private Ints callings = new Ints();
    private Ints indexesNew = new Ints();
    private StringList stringsNew = new StringList();
    private Ints indexesNewEnd = new Ints();
    private StringList stringsNewEnd = new StringList();

    public Ints getCallings() {
        return callings;
    }

    public Ints getIndexesNew() {
        return indexesNew;
    }

    public StringList getStringsNew() {
        return stringsNew;
    }

    public Ints getIndexesNewEnd() {
        return indexesNewEnd;
    }

    public StringList getStringsNewEnd() {
        return stringsNewEnd;
    }
}
