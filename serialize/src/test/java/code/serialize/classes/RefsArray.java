package code.serialize.classes;
import code.util.annot.RwXml;

@RwXml
public class RefsArray {

    private Object[] array;

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] _array) {
        array = _array;
    }
}
