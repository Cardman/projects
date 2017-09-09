package code.serialize.classes;
import code.util.ObjectMap;
import code.util.annot.RwXml;

@RwXml
public class ContainerObjectMapClassic {

    private ObjectMap<CompositeTwo,String> object;

    public ObjectMap<CompositeTwo,String> getObject() {
        return object;
    }

    public void setObject(ObjectMap<CompositeTwo,String> _object) {
        object = _object;
    }
}
