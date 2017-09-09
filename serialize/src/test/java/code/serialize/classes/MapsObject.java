package code.serialize.classes;
import code.util.IdMap;
import code.util.annot.RwXml;

@RwXml
public class MapsObject {

    private IdMap<Object,String> map;

    public void setMap(IdMap<Object, String> _map) {
        map = _map;
    }

    public IdMap<Object, String> getMap() {
        return map;
    }
}
