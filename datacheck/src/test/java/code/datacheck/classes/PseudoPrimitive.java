package code.datacheck.classes;
import code.sml.FromAndToString;
import code.util.StringList;

public final class PseudoPrimitive {

    private static final String SEP = ";";

    private String first;

    private String second;

    private PseudoPrimitive() {
    }

    @FromAndToString
    public static PseudoPrimitive newPseudoPrimitive(String _string) {
        PseudoPrimitive p_ = new PseudoPrimitive();
        StringList list_ = StringList.splitStrings(_string, SEP);
        p_.first = list_.first();
        p_.second = list_.last();
        return p_;
    }

    @Override
    public String toString() {
        return first+SEP+second;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String _first) {
        first = _first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String _second) {
        second = _second;
    }
}
