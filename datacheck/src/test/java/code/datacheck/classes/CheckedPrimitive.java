package code.datacheck.classes;
import code.datacheck.CheckedData;
import code.util.StringList;
import code.xml.FromAndToString;

@CheckedData
public class CheckedPrimitive {

    private static final String SEP = ";";

    private transient String pi;

    private String first;

    private String second;

    public CheckedPrimitive() {
    }

    public CheckedPrimitive(String _string) {
        StringList list_ = StringList.splitStrings(_string, SEP);
        first = list_.first();
        second = list_.last();
    }

    @FromAndToString
    public static CheckedPrimitive newCheckedPrimitive(String _string) {
        return new CheckedPrimitive(_string);
    }

    @FromAndToString
    @Override
    public String toString() {
        return first+SEP+second;
    }

    public String getPi() {
        return pi;
    }

    public void setPi(String _pi) {
        pi = _pi;
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
