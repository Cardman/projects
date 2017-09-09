package code.serialize.classes;
import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public class MapsInteger {

    private NumberMap<Integer,String> map;

    public void setMap(NumberMap<Integer, String> _map) {
        map = _map;
    }

    public NumberMap<Integer, String> getMap() {
        return map;
    }
}
