package code.serialize.classes;
import java.util.Comparator;

import code.util.annot.RwXml;

@RwXml
public class MyStringComparator implements Comparator<String> {

    private int mult = 1;

    public MyStringComparator() {
    }

    public MyStringComparator(int _mult) {
        mult = _mult;
    }

    @Override
    public int compare(String _one, String _two) {
        return mult * _one.compareTo(_two);
    }

    public int getMult() {
        return mult;
    }

    public void setMult(int _mult) {
        mult = _mult;
    }
}
