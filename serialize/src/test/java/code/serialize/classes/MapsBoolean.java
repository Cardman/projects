package code.serialize.classes;
import code.util.BooleanMap;
import code.util.annot.RwXml;

@RwXml
public class MapsBoolean {

    private BooleanMap<String> map;

    public void setMap(BooleanMap<String> _map) {
        map = _map;
    }

    public BooleanMap<String> getMap() {
        return map;
    }
}
