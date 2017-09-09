package code.serialize.classes;
import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public class MapsLong {

    private NumberMap<Long,String> map;

    public void setMap(NumberMap<Long, String> _map) {
        map = _map;
    }

    public NumberMap<Long, String> getMap() {
        return map;
    }
}
