package code.serialize.classes;
import code.util.ObjectMap;
import code.util.annot.RwXml;

@RwXml
public class ContainerObjectMapRef {

    private ObjectMap<CompositeTwo,CompositeTwo> object;

    public ObjectMap<CompositeTwo,CompositeTwo> getObject() {
        return object;
    }

    public void setObject(ObjectMap<CompositeTwo,CompositeTwo> _object) {
        object = _object;
    }
}
