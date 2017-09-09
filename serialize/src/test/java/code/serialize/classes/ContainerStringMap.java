package code.serialize.classes;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public class ContainerStringMap {

    private StringMap<CompositeTwo> object;

    public StringMap<CompositeTwo> getObject() {
        return object;
    }

    public void setObject(StringMap<CompositeTwo> _object) {
        object = _object;
    }
}
