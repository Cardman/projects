package code.serialize.classes;
import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public class MapsByte {

    private NumberMap<Byte,String> map;

    public void setMap(NumberMap<Byte, String> _map) {
        map = _map;
    }

    public NumberMap<Byte, String> getMap() {
        return map;
    }
}
