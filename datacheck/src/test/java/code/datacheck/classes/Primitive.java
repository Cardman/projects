package code.datacheck.classes;
import code.sml.FromAndToString;
import code.util.StringList;

public class Primitive {

    private static final String SEP = ";";

    private String first;

    private String second;

    public Primitive() {
    }

    public Primitive(String _string) {
        StringList list_ = StringList.splitStrings(_string, SEP);
        first = list_.first();
        second = list_.last();
    }

    @FromAndToString
    public static Primitive newCheckedPrimitive(String _string) {
        return new Primitive(_string);
    }

    @FromAndToString
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
