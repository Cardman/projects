package code.serialize.classes;
import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public class MapsShort {

    private NumberMap<Short,String> map;

    public void setMap(NumberMap<Short, String> _map) {
        map = _map;
    }

    public NumberMap<Short, String> getMap() {
        return map;
    }
}
