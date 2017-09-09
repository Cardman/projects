package code.serialize.classes;
import code.util.IdMap;
import code.util.annot.RwXml;

@RwXml
public class ContainerIdMapRef {

    private IdMap<CompositeTwo,CompositeTwo> object;

    public IdMap<CompositeTwo,CompositeTwo> getObject() {
        return object;
    }

    public void setObject(IdMap<CompositeTwo,CompositeTwo> _object) {
        object = _object;
    }
}
