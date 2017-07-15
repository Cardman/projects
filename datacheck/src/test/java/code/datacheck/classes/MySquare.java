package code.datacheck.classes;
import code.util.annot.RwXml;

@RwXml
public class MySquare<T> implements Carre<T> {

    private T aField;

    public T getaField() {
        return aField;
    }

    public void setaField(T _aField) {
        aField = _aField;
    }
}
