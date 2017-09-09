package code.serialize.classes;
import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public class MapsDouble {

    private NumberMap<Double,String> map;

    public void setMap(NumberMap<Double, String> _map) {
        map = _map;
    }

    public NumberMap<Double, String> getMap() {
        return map;
    }
}
