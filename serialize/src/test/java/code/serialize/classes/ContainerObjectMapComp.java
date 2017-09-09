package code.serialize.classes;
import code.util.ObjectMap;
import code.util.annot.RwXml;

@RwXml
public class ContainerObjectMapComp {

    private ObjectMap<MapComponent,String> object;

    public ObjectMap<MapComponent,String> getObject() {
        return object;
    }

    public void setObject(ObjectMap<MapComponent,String> _object) {
        object = _object;
    }
}
