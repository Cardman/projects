package code.serialize.classes;
import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public class MapsFloat {

    private NumberMap<Float,String> map;

    public void setMap(NumberMap<Float, String> _map) {
        map = _map;
    }

    public NumberMap<Float, String> getMap() {
        return map;
    }
}
