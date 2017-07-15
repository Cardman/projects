package code.datacheck.classes;
import code.util.NumberMap;

public class TemplateString<U> extends Template<U, String> {

    private NumberMap<Integer,U> values;

    public NumberMap<Integer,U> getValues() {
        return values;
    }

    public void setValues(NumberMap<Integer,U> _values) {
        values = _values;
    }
}
