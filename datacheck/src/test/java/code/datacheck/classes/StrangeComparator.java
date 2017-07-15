package code.datacheck.classes;
import java.util.Comparator;

import code.util.annot.RwXml;

@RwXml
public class StrangeComparator<K extends Comparable<K>> implements Comparator<K> {

    private K field;

    public StrangeComparator() {
    }

    public StrangeComparator(K _field) {
        field = _field;
    }

    public K getField() {
        return field;
    }

    public void setField(K _field) {
        field = _field;
    }

    @Override
    public int compare(K _o1, K _o2) {
        return _o1.compareTo(_o2);
    }

}
