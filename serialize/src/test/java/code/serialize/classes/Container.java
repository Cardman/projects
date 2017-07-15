package code.serialize.classes;
import code.util.annot.RwXml;

@RwXml
public class Container {

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object _object) {
        object = _object;
    }
}
